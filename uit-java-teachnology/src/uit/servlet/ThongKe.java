package uit.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.StatisticsBUS;
import uit.model.User;
import uit.model.ListLO;
import uit.model.TiLeG;

import uit.utils.ValidateUtils;
import uit.utils.AuthUtil;

@WebServlet("/thong-ke")
public class ThongKe extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(ThongKe.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
	    	String servletPath = request.getServletPath();
	    	int id_mon = 0;
	    	User user = AuthUtil.getLoginedUser(request);	        
	    	List<TiLeG> data_g = null;
	    	List<ListLO> data_lo = null;
	    	
	    	if(AuthUtil.studentRole(servletPath, user) && !AuthUtil.lecturerRole(servletPath, user) &&
	    			!AuthUtil.headerFacultyRole(servletPath, user) && !AuthUtil.adminRole(servletPath, user)) {
	    		
	    		if (ValidateUtils.canConvertToInt(request.getParameter("idMon"))) {

	    			id_mon = Integer.parseInt(request.getParameter("idMon"));
					data_g = StatisticsBUS.getInstance().getGMonSv(user.getId(), id_mon);
					data_lo = StatisticsBUS.getInstance().getLoMonSv(user.getId(), id_mon);
	            	
	    		} else {
	    			data_g = StatisticsBUS.getInstance().getGSv(user.getId());
	    			data_lo = StatisticsBUS.getInstance().getLoSv(user.getId());
	    		}
	    		
	    		request.setAttribute("data_g", data_g);
            	request.setAttribute("data_lo", data_lo);
            	
		        // Get index.jsp dispatcher and forward.
		        RequestDispatcher dispatcher = request.getRequestDispatcher("templates/thongke/index.jsp");
		        dispatcher.forward(request, response);
		        
	    	} else {
	    		int idSv = 0;
	    		
	    		if (ValidateUtils.canConvertToInt(request.getParameter("idSv"))) {
	    			idSv = Integer.parseInt(request.getParameter("idSv"));
			    	if (ValidateUtils.canConvertToInt(request.getParameter("idMon"))) {
			    		id_mon = Integer.parseInt(request.getParameter("idMon"));
						data_g = StatisticsBUS.getInstance().getGMonSv(idSv, id_mon);
						data_lo = StatisticsBUS.getInstance().getLoMonSv(idSv, id_mon);
			            	
			    	} else {
			    		data_g = StatisticsBUS.getInstance().getGSv(idSv);
			    		data_lo = StatisticsBUS.getInstance().getLoSv(idSv);
			    	}
	    		}
	    		
	    		request.setAttribute("data_g", data_g);
            	request.setAttribute("data_lo", data_lo);
            	
		        // Get index.jsp dispatcher and forward.
		        RequestDispatcher dispatcher = request.getRequestDispatcher("templates/thongke/index.jsp");
		        dispatcher.forward(request, response);
	    	}
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get Mon Hoc", ex);
        }
    }

}
