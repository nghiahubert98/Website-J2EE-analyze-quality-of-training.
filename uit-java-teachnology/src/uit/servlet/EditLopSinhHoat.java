package uit.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import uit.bus.GiangVienBUS;
import uit.bus.LopSinhHoatBUS;
import uit.bus.NganhBUS;
import uit.model.GiangVien;
import uit.model.LopSinhHoat;
import uit.model.Nganh;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

@WebServlet("/lopsinhhoat-edit")
public class EditLopSinhHoat extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(EditLopSinhHoat.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            IndexModelView<GiangVien> listGV = GiangVienBUS.getInstance().findForDropDown(Constants.DEFAULT_PAGE_NUMBER);
            List<Nganh> listNganh = NganhBUS.getInstance().findAll();
            req.setAttribute("listGV", listGV);
            req.setAttribute("listNganh", listNganh);
            // Page of list LopSinhHoat
            // using redirect after edit success.
            int backPage = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(req.getParameter("backPage"))) {
                backPage = Integer.parseInt(req.getParameter("backPage"));
            }

            if (ValidateUtils.canConvertToInt(req.getParameter("id"))) {
                int id = Integer.parseInt(req.getParameter("id"));
                LopSinhHoat lopSH = LopSinhHoatBUS.getInstance().findById(id);

                if (lopSH != null) {
                    req.setAttribute("data", lopSH);
                    req.setAttribute("backPage", backPage);

                    // Get edit.jsp dispatcher and forward.
                    RequestDispatcher dispatcher = req.getRequestDispatcher("templates/LopSinhHoat/edit.jsp");
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
        String batDau = request.getParameter("batDau");
        String totNghiep = request.getParameter("totNghiep");
        String ten = request.getParameter("ten");
        if (!ValidateUtils.canConvertToInt(request.getParameter("covan"))) {
            MessageSessionUtil.createServerErrorMsg(request, "Xin hãy kiểm tra lại mã cố vấn của bạn", null);
            response.sendRedirect(ctxPath + "/lopsinhhoat-edit?id=" + request.getParameter("id"));
            return;
        }
        if (!ValidateUtils.canConvertToInt(request.getParameter("nganh"))) {
            MessageSessionUtil.createServerErrorMsg(request, "Xin hãy kiểm tra lại mã ngành của bạn", null);
            response.sendRedirect(ctxPath + "/lopsinhhoat-edit?id=" + request.getParameter("id"));
            return;
        }
        int coVanID = Integer.parseInt(request.getParameter("covan"));
        int  nganhID = Integer.parseInt(request.getParameter("nganh"));
        LopSinhHoat lopSinhHoat = new LopSinhHoat(maLop, batDau, totNghiep, ten, coVanID);
        lopSinhHoat.setIdNganh(nganhID);
        
        if (ValidateUtils.canConvertToInt(request.getParameter("id"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            lopSinhHoat.setId(id);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int page = Constants.DEFAULT_PAGE_NUMBER;
        if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
            if (lopSinhHoat.validate(request)) {

                LopSinhHoatBUS.getInstance().update(lopSinhHoat);

                // Set success message to session
                HttpSession session = request.getSession(true);
                session.setAttribute("success", "Bạn đã cập nhật thông tin thành công");

                // Redirect to last page.
                response.sendRedirect(ctxPath + "/lopsinhhoat?page=" + page);
            } else {

                response.sendRedirect(ctxPath + "/lopsinhhoat-edit?id=" + lopSinhHoat.getId() + "&backPage=" + page);
            }

        } catch (Exception ex) {
            LOG.error("Could not create Lop Sinh Hoat", ex);

            String mess = "Xảy ra lỗi trong quá trình cập nhật dữ liệu, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng mã lớp sinh hoạt không trùng với bất kỳ mã lớp sinh hoạt nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, lopSinhHoat);

            // redirect to /lop sinh hoat-create.
            response.sendRedirect(ctxPath + "/lopsinhhoat-edit?id=" + lopSinhHoat.getId() + "&backPage=" + page);
        }
    }

}
