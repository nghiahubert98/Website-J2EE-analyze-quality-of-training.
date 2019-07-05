package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import uit.bus.LopSinhHoatBUS;
import uit.bus.SinhVienBUS;
import uit.model.LopSinhHoat;
import uit.model.SinhVien;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

@WebServlet("/sinhvien-edit")
public class EditSinhVien extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(EditSinhVien.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            IndexModelView<LopSinhHoat> listLSH = LopSinhHoatBUS.getInstance()
                    .findForDropDown(Constants.DEFAULT_PAGE_NUMBER);
            req.setAttribute("listLSH", listLSH);
            // Page of list sinh vien
            // using redirect after edit success.
            int backPage = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(req.getParameter("backPage"))) {
                backPage = Integer.parseInt(req.getParameter("backPage"));
            }

            if (ValidateUtils.canConvertToInt(req.getParameter("id"))) {
                int id = Integer.parseInt(req.getParameter("id"));
                SinhVien sinhvien = SinhVienBUS.getInstance().findById(id);

                if (sinhvien != null) {
                    req.setAttribute("data", sinhvien);
                    req.setAttribute("backPage", backPage);

                    // Get edit.jsp dispatcher and forward.
                    RequestDispatcher dispatcher = req.getRequestDispatcher("templates/SinhVien/edit.jsp");
                    dispatcher.forward(req, resp);

                    return;
                }
            }

            // Invalid queryString => set status code 404 and return 404 page.

            LOG.info("Id = " + req.getParameter("id") + "invalid");

            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);

            RequestDispatcher dispatcher = req.getRequestDispatcher("templates/404.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception ex) {
            // Serve error set status code is 500 and return 500 page.

            LOG.error("Could not load edit page.", ex);

            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);

            RequestDispatcher dispatcher = req.getRequestDispatcher("templates/500.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Application Context path.
        String ctxPath = request.getContextPath();

        String tensinhvien = request.getParameter("ten_sinhvien");
        String mssv = request.getParameter("mssv");
        int maLopSH = Integer.parseInt(request.getParameter("lopsinhhoat"));
        SinhVien sinhvien = new SinhVien();

        if (ValidateUtils.canConvertToInt(request.getParameter("id"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            sinhvien.setId(id);
            sinhvien.setTen(tensinhvien);
            sinhvien.setMssv(mssv);
            sinhvien.setMaLopSH(maLopSH);
        }

        int page = Constants.DEFAULT_PAGE_NUMBER;
        if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
            if (sinhvien.validate(request)) {

                SinhVienBUS.getInstance().update(sinhvien);

                // Set success message to session
                HttpSession session = request.getSession(true);
                session.setAttribute("success", "Bạn đã cập nhật thông tin thành công");

                // Redirect to last page.
                response.sendRedirect(ctxPath + "/sinhvien?page=" + page);
            } else {

                response.sendRedirect(ctxPath + "/sinhvien-edit?id=" + sinhvien.getId() + "&backPage=" + page);
            }

        } catch (Exception ex) {
            LOG.error("Could not create Nganh", ex);

            String mess = "Xảy ra lỗi trong quá trình cập nhật dữ liệu, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng mã ngành không trùng với bất kỳ mã ngành nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, sinhvien);

            // redirect to /sinhvien-create.
            response.sendRedirect(ctxPath + "/sinhvien-edit?id=" + sinhvien.getId() + "&backPage=" + page);
        }
    }

}
