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

import uit.bus.GiangVienBUS;
import uit.bus.HocKyBUS;
import uit.bus.LopBUS;
import uit.bus.MonHocBUS;
import uit.model.GiangVien;
import uit.model.HocKy;
import uit.model.Lop;
import uit.model.MonHoc;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

@WebServlet("/lop-edit")
public class EditLop extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(EditLop.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            IndexModelView<HocKy> listHK = HocKyBUS.getInstance().findForDropDown(Constants.DEFAULT_PAGE_NUMBER);
            req.setAttribute("listHK", listHK);
            IndexModelView<MonHoc> listMH = MonHocBUS.getInstance().findForDropDown(Constants.DEFAULT_PAGE_NUMBER);
            req.setAttribute("listMH", listMH);
            IndexModelView<GiangVien> listGV = GiangVienBUS.getInstance()
                    .findForDropDown(Constants.DEFAULT_PAGE_NUMBER);
            req.setAttribute("listGV", listGV);
            // Page of list lop
            // using redirect after edit success.
            int backPage = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(req.getParameter("backPage"))) {
                backPage = Integer.parseInt(req.getParameter("backPage"));
            }

            if (ValidateUtils.canConvertToInt(req.getParameter("id"))) {
                int id = Integer.parseInt(req.getParameter("id"));
                Lop Lop = LopBUS.getInstance().findById(id);

                if (Lop != null) {
                    req.setAttribute("data", Lop);
                    req.setAttribute("backPage", backPage);

                    // Get edit.jsp dispatcher and forward.
                    RequestDispatcher dispatcher = req.getRequestDispatcher("templates/Lop/edit.jsp");
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

        String maLop = request.getParameter("maLop");
        int hocKyID = Integer.parseInt(request.getParameter("hocky"));
        int monHocID = Integer.parseInt(request.getParameter("monhoc"));
        int gvID = Integer.parseInt(request.getParameter("giangvien"));
        Lop Lop = new Lop(maLop, hocKyID, monHocID, gvID);

        if (ValidateUtils.canConvertToInt(request.getParameter("id"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            Lop.setId(id);
        }

        int page = Constants.DEFAULT_PAGE_NUMBER;
        if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
            if (Lop.validate(request)) {

                LopBUS.getInstance().update(Lop);

                // Set success message to session
                HttpSession session = request.getSession(true);
                session.setAttribute("success", "Bạn đã cập nhật thông tin thành công");

                // Redirect to last page.
                response.sendRedirect(ctxPath + "/lop?page=" + page);
            } else {

                response.sendRedirect(ctxPath + "/lop-edit?id=" + Lop.getId() + "&backPage=" + page);
            }

        } catch (Exception ex) {
            LOG.error("Could not create lop", ex);

            String mess = "Xảy ra lỗi trong quá trình cập nhật dữ liệu, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng mã lớp không trùng với bất kỳ mã lớp nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, Lop);

            // redirect to /Lop-create.
            response.sendRedirect(ctxPath + "/lop-edit?id=" + Lop.getId() + "&backPage=" + page);
        }
    }

}
