package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.UserBUS;
import uit.model.User;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

/**
 * Servlet implementation class DetailUserServlet
 */
@WebServlet("/user-detail")
public class DetailUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(UserListServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {            
            if (ValidateUtils.canConvertToInt(request.getParameter("id"))) {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = UserBUS.getInstance().findById(id);

                if (user != null) {
                    request.setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/user/detail.jsp");
                    dispatcher.forward(request, response);

                    return;
                }
            }
            
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception ex) {
            LOG.error("Could not get user detail page");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ctxPath = request.getContextPath();
        String[] roles = request.getParameterValues("roles");
        String statusStr = request.getParameter("status");
        String idStr = request.getParameter("id");        
        
        try {
            if (ValidateUtils.canConvertToBoolean(statusStr) 
            && ValidateUtils.canConvertToBoolean(idStr)) {
                Boolean status = Boolean.parseBoolean(statusStr);
                Integer id = Integer.parseInt(idStr);
                UserBUS.getInstance().updateStatusAndRole(id, roles, status);
                MessageSessionUtil.createSuccessMsg(request, "Lưu thông tin người dùng thành công!");
            } else {
                MessageSessionUtil.createServerErrorMsg(request, "Thông tin cập nhật không đúng, xin hãy kiểm tra lại", null);
            }
        } catch (Exception ex) {
            LOG.error("Could not update user info", ex);
            MessageSessionUtil.createServerErrorMsg(request, "Xảy ra lỗi, không thể lưu thông tin user", null);
        }
        
        response.sendRedirect(ctxPath + "/user-detail?id="+ idStr);
    }

}
