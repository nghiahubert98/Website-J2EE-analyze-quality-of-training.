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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(DashboardServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("templates/login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ctx = request.getContextPath();
        try {
            String loginCode = request.getParameter("code");
            String pwd = request.getParameter("pwd");
            if (loginCode != null && pwd != null && !loginCode.isEmpty() && !pwd.isEmpty()) {
                User loginUser = UserBUS.getInstance().findByCodeAndPassword(loginCode, pwd);
                if (loginUser != null) {
                    //  Login success, store user info to session and redirect to dashboard.
                    AuthUtil.storeLoginedUser(request.getSession(), loginUser);
                    response.sendRedirect(ctx + "/");
                } else {
                    Map<String, String> error = new HashMap<>();
                    error.put("code", "Mật khẩu hoặc tên định danh không đúng");
                    
                    // Set error into session, redirect to login page and display error.
                    MessageSessionUtil.createErrorMsg(request, error, loginCode);
                    response.sendRedirect(ctx + "/login");
                }
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("pwd", "Mật khẩu không được trống");
                error.put("code", "Tên định danh không được để trống");
                
                // Set error into session, redirect to login page and display error.
                MessageSessionUtil.createErrorMsg(request, error, loginCode);
                response.sendRedirect(ctx + "/login");
            }
        } catch (Exception ex) {
            LOG.error("Could not login", ex);
            String mess = "Hệ thống xảy ra lỗi, xin hãy quay lại sau ít phút!";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, null);
            response.sendRedirect(ctx + "/login");
        }
    }

}
