package uit.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uit.model.User;
import uit.utils.AuthUtil;

@WebFilter(urlPatterns = {"/sinhvien", "/hocky", "/chuan-g"})

public class LecturerFilter implements Filter {

    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String servletPath = request.getServletPath();
        if (!AuthUtil.isLoggined(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        } 
        User loggedUser = AuthUtil.getLoginedUser(request);
        if (AuthUtil.lecturerRole(servletPath, loggedUser) 
        || AuthUtil.headerFacultyRole(servletPath, loggedUser) 
        || AuthUtil.adminRole(servletPath, loggedUser)) {
            chain.doFilter(request, response);
            return;
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {}

}
