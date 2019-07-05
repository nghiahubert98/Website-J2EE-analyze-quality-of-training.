package uit.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet(name="home", urlPatterns = {"/home", ""})
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(DashboardServlet.class);
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {            
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/index.jsp");          
            dispatcher.forward(request, response);              
        } catch (Exception ex) {
            LOG.error("Could not get home page", ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}