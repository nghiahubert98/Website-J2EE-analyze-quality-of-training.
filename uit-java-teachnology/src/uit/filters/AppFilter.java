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

import uit.utils.AuthUtil;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;

/**
 * Servlet Filter implementation class AppFilter
 */
@WebFilter("*")
public class AppFilter implements Filter {

    private void setCharacterEncoding(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Set encode is UTF-8
        request.setCharacterEncoding(Constants.DEF_ENCODE);
        response.setCharacterEncoding(Constants.DEF_ENCODE);
    }

    private void processSessionMessage(HttpServletRequest request, HttpServletResponse response) {
        // Process message within session.
        MessageSessionUtil.processErrorMsg(request);
        MessageSessionUtil.processSuccessMsg(request);
    }
  
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Set request and response encoding
        setCharacterEncoding(req, res);

        // Handle message in request session.
        processSessionMessage(req, res);
        
        filterRequest(req, res, chain);
	}
	
	private void filterRequest(HttpServletRequest request, HttpServletResponse response, final FilterChain chain) throws IOException, ServletException {
        String servletPath = request.getServletPath();
        
        // Not allow access jsp file.
        if (servletPath.trim().matches("^.*?\\.jsp")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } 
        
        // Allow all request access static resources.
        if (servletPath.matches("^/resources/.*?$")) {
            chain.doFilter(request, response);
            return;
        }
        
        // User logged in.
        if (AuthUtil.isLoggined(request)) {
            // Not allow access login page if logged in.
            if (servletPath.equalsIgnoreCase("/login")) {
                response.sendRedirect(request.getContextPath());
                return;
            }
            chain.doFilter(request, response);
        } else {
            // The First Logged in.
            if (AuthUtil.getUserFirtLoginInfo(request) != null) {
                if (servletPath.equalsIgnoreCase("/first-login")) {
                    chain.doFilter(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/first-login");
                }
                
                return;
            }
            
            // Not logged in. Only allow access login page
            // and /login-google.
            if (!servletPath.equalsIgnoreCase("/login") 
            && !servletPath.equalsIgnoreCase("/login-google")) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            } else {
                chain.doFilter(request, response);
                return;
            }
        }
    }

	public void init(FilterConfig fConfig) throws ServletException {}

    @Override
    public void destroy() { }

}
