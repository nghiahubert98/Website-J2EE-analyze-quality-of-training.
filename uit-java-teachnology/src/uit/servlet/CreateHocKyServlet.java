package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.HocKyBUS;
import uit.dao.HocKyDAO;
import uit.model.HocKy;
import uit.utils.MessageSessionUtil;

@WebServlet("/hocky-create")
public class CreateHocKyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(DashboardServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/hocky/create.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get hoc ky", ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Application Context path.
        String ctxPath = request.getContextPath();

        // Initialize a new instance of Hoc Ky.
        String loai = request.getParameter("loai");
        int namBatDau = Integer.parseInt(request.getParameter("namBatDau"));       
        int namKetThuc = Integer.parseInt(request.getParameter("namKetThuc"));
        HocKy HocKy = new HocKy(loai, namBatDau, namKetThuc);
        
        try {

            /* In case input data valid (dataError == null) */
            if (HocKy.validate(request)) {
                // Save new "Hoc Ky" to database,
                HocKyDAO.getInstance().create(HocKy);

                MessageSessionUtil.createSuccessMsg(request, "Bạn đã tạo thành công một học kỳ mới");
                // Redirect to last page.
                response.sendRedirect(ctxPath + "/hocky?page=" + HocKyBUS.getInstance().getTotalPage());

            } else {
                 response.sendRedirect(ctxPath + "/hocky-create");
            }

        } catch (Exception ex) {
            LOG.error("Could not create Hoc Ky", ex);

            String mess = "Xảy ra lỗi không thể tạo học kỳ được, Xin hãy kiểm tra lại dữ liệu đầu vào.";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, HocKy);

            // redirect to /hocky-create.
            response.sendRedirect(ctxPath + "/hocky-create");
        }
    }
}
