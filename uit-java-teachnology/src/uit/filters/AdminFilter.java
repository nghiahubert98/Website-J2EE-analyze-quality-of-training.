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

@WebFilter(urlPatterns = {"/giangvien-create", "/g-create", "/hocky-create", "/lop-create", "/lopsinhhoat-create", 
		"/lo-create", "/monhoc-create", "/nganh-create", "/sinhvien-create", "/giangvien-delete", "/g-delete",
		"/hocky-delete", "/lop-delete", "/lopsinhhoat-delete", "/lo-delete", "/monhoc-delete", "/nganh-delete",
		"/sinhvien-delete", "/giangvien-edit", "/g-edit", "/hocky-edit", "/lop-edit", "/lopsinhhoat-edit",
		"/lo-edit", "/monhoc-edit", "/nganh-edit", "/sinhvien-edit", "/g-excel", "/giangvien-excel", "/hocky-excel",
		"/lo-excel", "/lop-excel", "/lopsinhhoat-excel", "/monhoc-excel", "/nganh-excel", "/sinhvien-excel",
		"/giang-vien", "/tai-khoan", "/user-detail", "/giang-vien"})

public class AdminFilter implements Filter {
    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
       
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String servletPath = request.getServletPath();

        User loggedUser = AuthUtil.getLoginedUser(request);
        if (!AuthUtil.isLoggined(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        } 
        if (AuthUtil.adminRole(servletPath, loggedUser)) {
            chain.doFilter(request, response);
            return;
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
