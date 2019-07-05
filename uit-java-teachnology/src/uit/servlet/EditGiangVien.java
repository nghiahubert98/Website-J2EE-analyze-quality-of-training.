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
import uit.model.GiangVien;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

@WebServlet("/giangvien-edit")
public class EditGiangVien extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(EditGiangVien.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // Page of list giangviens
            // using redirect after edit success.
            int backPage = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(req.getParameter("backPage"))) {
                backPage = Integer.parseInt(req.getParameter("backPage"));
            }

            if (ValidateUtils.canConvertToInt(req.getParameter("id"))) {
                int id = Integer.parseInt(req.getParameter("id"));
                GiangVien giangvien = GiangVienBUS.getInstance().findById(id);

                if (giangvien != null) {
                    req.setAttribute("data", giangvien);
                    req.setAttribute("backPage", backPage);

                    // Get edit.jsp dispatcher and forward.
                    RequestDispatcher dispatcher = req.getRequestDispatcher("templates/giangvien/edit.jsp");
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
        String ctxPath = request.getContextPath();
        String tenNganh = request.getParameter("name");
        String maNganh = request.getParameter("code");
        String email = request.getParameter("email");

        GiangVien giangvien = new GiangVien();

        if (ValidateUtils.canConvertToInt(request.getParameter("id"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            giangvien.setId(id);
            giangvien.setName(tenNganh);
            giangvien.setCode(maNganh);
            giangvien.setEmail(email);
        }

        int page = Constants.DEFAULT_PAGE_NUMBER;
        if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
            if (giangvien.validate(request)) {

                GiangVienBUS.getInstance().update(giangvien);

                // Set success message to session
                HttpSession session = request.getSession(true);
                session.setAttribute("success", "Bạn đã cập nhật thông tin thành công");

                // Redirect to last page.
                response.sendRedirect(ctxPath + "/giang-vien?page=" + page);
            } else {

                response.sendRedirect(ctxPath + "/giangvien-edit?id=" + giangvien.getId() + "&backPage=" + page);
            }

        } catch (Exception ex) {
            LOG.error("Could not create giang vien", ex);

            String mess = "Xảy ra lỗi trong quá trình cập nhật dữ liệu, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng mã giảng viên không trùng với bất kỳ mã giảng viên nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, giangvien);

            // redirect to / giangvien-create.
            response.sendRedirect(ctxPath + "/ giangvien-edit?id=" + giangvien.getId() + "&backPage=" + page);
        }
    }

}
