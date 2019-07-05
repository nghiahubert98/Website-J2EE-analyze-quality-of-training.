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
import uit.modelview.FilterUserViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.ValidateUtils;

@WebServlet("/tai-khoan")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(UserListServlet.class);   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        // Handle search params
	        FilterUserViewModel filter = new FilterUserViewModel();
	        filter.setCode(request.getParameter("code"));
	        filter.setName(request.getParameter("name"));
	        filter.setRole(request.getParameter("role"));
	        
	        if (ValidateUtils.canConvertToInt(request.getParameter("type"))) {
	            filter.setUserType(Integer.parseInt(request.getParameter("type")));
	        }
	        if (ValidateUtils.canConvertToBoolean(request.getParameter("status"))) {
	            filter.setStatus(Boolean.parseBoolean(request.getParameter("status")));
	        }	        
	        
	        int page = Constants.DEFAULT_PAGE_NUMBER;
	        if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
	            page = Integer.parseInt(request.getParameter("page")) > Constants.DEFAULT_PAGE_NUMBER 
	                   ? Integer.parseInt(request.getParameter("page"))
	                   : Constants.DEFAULT_PAGE_NUMBER;
	        }
	        
            IndexModelView<User> data = UserBUS.getInstance().getIndexViewModel(page, filter);
            request.setAttribute("data", data);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/user/index.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            LOG.error("Could not get user.index page", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
