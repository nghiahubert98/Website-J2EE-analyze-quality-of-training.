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

import org.apache.log4j.Logger;

import uit.bus.UserBUS;
import uit.model.User;
import uit.utils.AuthUtil;
import uit.utils.MessageSessionUtil;

@WebServlet("/first-login")
public class FirstLoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(FirstLoginServlet.class);   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/first_login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ctx = request.getContextPath();
        String pwd = request.getParameter("pwd");
        String pwdConfirm = request.getParameter("pwd_confirm");
        try {
            
            if (pwd == null || pwdConfirm == null || pwd.isEmpty() || pwd.isEmpty()) {
                Map<String, String> dataError = new HashMap<>();
                dataError.put("pwd", "Không được để trống mục mật khẩu và nhập lại mật khẩu");
                MessageSessionUtil.createErrorMsg(request, dataError, null);
                response.sendRedirect(ctx + "/first-login");
                return;
            }
            
            if (!pwd.equals(pwdConfirm)) {
                Map<String, String> dataError = new HashMap<>();
                dataError.put("pwd", "Mục nhập lại mật khẩu và mục mật khẩu không trùng nhau");
                MessageSessionUtil.createErrorMsg(request, dataError, null);
                response.sendRedirect(ctx + "/first-login");
                return;
            }
            
            User user = AuthUtil.getUserFirtLoginInfo(request);
            user.setPassword(pwd);
            UserBUS.getInstance().create(user);
            
            // Save login to session
            user.setPassword(null);
            AuthUtil.storeLoginedUser(request.getSession(), user);
            
            AuthUtil.clearUserFirstLoginInfo(request);
            response.sendRedirect(ctx);
        } catch (Exception ex) {
            LOG.error("Could not update user info", ex);
            String mess = "Xảy ra lỗi, không thể cập nhật tài khoản của bạn!";
            
            MessageSessionUtil.createServerErrorMsg(request, mess, null);
            response.sendRedirect(ctx + "/first-login");
        }
    }
}
