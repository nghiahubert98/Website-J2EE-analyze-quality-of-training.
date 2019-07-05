package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.MonHocBUS;
import uit.dao.MonHocDAO;
import uit.model.MonHoc;
import uit.utils.MessageSessionUtil;

@WebServlet("/monhoc-create")
public class CreateMonHocServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(DashboardServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/MonHoc/create.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get Mon Hoc", ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Application Context path.
        String ctxPath = request.getContextPath();

        // Initialize a new instance of MonHoc.
        String tenMonHoc = request.getParameter("name");
        String maMonHoc = request.getParameter("maMonHoc");
        MonHoc MonHoc = new MonHoc(tenMonHoc, maMonHoc);

        try {

            /* In case input data valid (dataError == null) */
            if (MonHoc.validate(request)) {
                // Save new "MonHoc" to database,
                MonHocDAO.getInstance().create(MonHoc);

                MessageSessionUtil.createSuccessMsg(request, "Bạn đã tạo thành công một môn học mới");
                // Redirect to last page.
                response.sendRedirect(ctxPath + "/monhoc?page=" + MonHocBUS.getInstance().getTotalPage());

            } else {
                response.sendRedirect(ctxPath + "/monhoc-create");
            }

        } catch (Exception ex) {
            LOG.error("Could not create Mon Hoc", ex);

            String mess = "Xảy ra lỗi không thể tạo môn học được, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng mã môn học không trùng với bất kỳ mã môn học nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, MonHoc);

            // redirect to /MonHoc-create.
            response.sendRedirect(ctxPath + "/monhoc-create");
        }
    }

}
