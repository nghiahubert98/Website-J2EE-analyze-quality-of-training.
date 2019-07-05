package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.NganhBUS;
import uit.dao.NganhDAO;
import uit.model.Nganh;
import uit.utils.MessageSessionUtil;

@WebServlet("/nganh-create")
public class CreateNganhServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(DashboardServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/nganh/create.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get Nganh", ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Application Context path.
        String ctxPath = request.getContextPath();

        // Initialize a new instance of Nganh.
        String tenNganh = request.getParameter("ten_nganh");
        String maNganh = request.getParameter("ma_nganh");
        Nganh nganh = new Nganh(tenNganh, maNganh);
        
        try {

            /* In case input data valid (dataError == null) */
            if (nganh.validate(request)) {
                // Save new "Nganh" to database,
                NganhDAO.getInstance().create(nganh);

                MessageSessionUtil.createSuccessMsg(request, "Bạn đã tạo thành công một ngành mới");
                // Redirect to last page.
                response.sendRedirect(ctxPath + "/nganh?page=" + NganhBUS.getInstance().getTotalPage());

            } else {
                 response.sendRedirect(ctxPath + "/nganh-create");
            }

        } catch (Exception ex) {
            LOG.error("Could not create Nganh", ex);

            String mess = "Xảy ra lỗi không thể tạo ngành được, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng mã ngành không trùng với bất kỳ mã ngành nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, nganh);

            // redirect to /nganh-create.
            response.sendRedirect(ctxPath + "/nganh-create");
        }
    }

}
