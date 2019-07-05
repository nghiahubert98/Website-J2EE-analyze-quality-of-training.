package uit.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import uit.bus.UserBUS;
import uit.utils.MessageSessionUtil;
import uit.utils.AuthUtil;

@WebServlet("/edit-pwd")
public class EditPasswordUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(EditPasswordUserServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("templates/user/editPwd.jsp");
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ctx = request.getContextPath();
		
        try {
        	
        	String loginCode = AuthUtil.getLoginedUser(request).getCode();
            String oldPwd = request.getParameter("oldPwd");
            String newPwd = request.getParameter("newPwd");
            String confirmPwd = request.getParameter("confirmPwd");
            
            // Check all fields are not empty.
            if (oldPwd == null || newPwd == null || confirmPwd == null || oldPwd.isEmpty() || newPwd.isEmpty() || confirmPwd.isEmpty()) {
            	
            	Map<String, String> error = new HashMap<>();
                error.put("oldPwd", "Trường mật khẩu cũ không được để trống");
                error.put("newPwd", "Trường mật khẩu mới không được để trống");
                error.put("confirmPwd", "Trường xác nhận mật khẩu không được để trống");
                
                // Set error into session, redirect to editPwd again
                MessageSessionUtil.createErrorMsg(request, error, loginCode);
                response.sendRedirect(ctx + "/edit-pwd");
            
            //Check old password is correct.
            } else if (UserBUS.getInstance().findByCodeAndPassword(loginCode, oldPwd) == null) {
            	
            	Map<String, String> error = new HashMap<>();
                error.put("oldPwd", "Mật khẩu hiện tại không đúng.");
                
                // Set error into session, redirect to editPwd again
                MessageSessionUtil.createErrorMsg(request, error, loginCode);
                response.sendRedirect(ctx + "/edit-pwd");
                
             // Check old password and new password are same.
            } else if (UserBUS.getInstance().findByCodeAndPassword(loginCode, newPwd) != null) {
            	Map<String, String> error = new HashMap<>();
            	error.put("oldPwd", "Mật khẩu mới không được giống mật khẩu hiện tại");
            	
            	 // Set error into session, redirect to editPwd again
            	MessageSessionUtil.createErrorMsg(request, error, loginCode);
            	response.sendRedirect(ctx + "/edit-pwd");
            	
            // Check confirm password is same with new password
            } else if (!newPwd.equals(confirmPwd)) {
            	
            	Map<String, String> error = new HashMap<>();
                error.put("confirmPwd", "Mật khẩu xác nhận không đúng.");
                    
                // Set error into session, redirect to editPwd again
                MessageSessionUtil.createErrorMsg(request, error, loginCode);
                response.sendRedirect(ctx + "/edit-pwd");
                
            // Change password success
            } else {
            	
            	UserBUS.getInstance().edit(loginCode, newPwd);
            	
            	HttpSession session = request.getSession(true);
            	session.setAttribute("success", "Đổi mật khẩu thành công");

            	 // Redirect to editPwd again
                 response.sendRedirect(ctx + "/edit-pwd");
            	
            }	
            
        } catch (Exception ex) {
            LOG.error("Could not change password", ex);
            String mess = "Hệ thống xảy ra lỗi, xin hãy quay lại sau ít phút!";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, null);
            response.sendRedirect(ctx + "/edit-pwd");
        }
    }
}
