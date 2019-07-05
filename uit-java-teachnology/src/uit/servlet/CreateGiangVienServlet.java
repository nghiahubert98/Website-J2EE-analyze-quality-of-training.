package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.GiangVienBUS;
import uit.dao.GiangVienDAO;
import uit.model.GiangVien;
import uit.utils.MessageSessionUtil;

@WebServlet("/giangvien-create")
public class CreateGiangVienServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(DashboardServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/giangvien/create.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get giang vien", ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Application Context path.
        String ctxPath = request.getContextPath();

        // Initialize a new instance of Giang Vien.
        String tenGiangVien = request.getParameter("name");
        String maGiangVien = request.getParameter("code");
        String email = request.getParameter("email");
        GiangVien giangvien = new GiangVien(maGiangVien, tenGiangVien, email);

        try {

            /* In case input data valid (dataError == null) */
            if (giangvien.validate(request)) {
                // Save new "Giang vien" to database,
                GiangVienDAO.getInstance().create(giangvien);

                MessageSessionUtil.createSuccessMsg(request, "Bạn đã tạo thành công một giảng viên mới");
                // Redirect to last page.
                response.sendRedirect(ctxPath + "/giang-vien?page=" + GiangVienBUS.getInstance().getTotalPage());

            } else {
                response.sendRedirect(ctxPath + "/giangvien-create");
            }

        } catch (Exception ex) {
            LOG.error("Could not create Giang Vien", ex);

            String mess = "Xảy ra lỗi không thể tạo giảng viên được, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng mã giảng viên không trùng với bất kỳ mã giảng viên nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, giangvien);

            // redirect to /giangvien-create.
            response.sendRedirect(ctxPath + "/giangvien-create");
        }
    }

}
