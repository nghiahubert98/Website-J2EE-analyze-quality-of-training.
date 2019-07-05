package uit.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uit.utils.AuthUtil;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        // Clear login session.
        AuthUtil.logoutSystem(request.getSession());
        
        // Redirect to login page.
        String ctxPath = request.getContextPath();        
        response.sendRedirect(ctxPath + "/login");
    }

}
