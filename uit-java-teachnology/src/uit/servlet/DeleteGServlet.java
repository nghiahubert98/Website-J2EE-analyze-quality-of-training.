package uit.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.ChuanGBUS;
import uit.utils.ValidateUtils;

/**
 * Servlet implementation class DeleteGServlet
 */
@WebServlet("/g-delete")
public class DeleteGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(DeleteGServlet.class); 
    
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /**
         * Check Request Param
         * "lo-id": if exist -> delete G of LO
         *           : if not exist -> delete G
         */
        if (request.getParameterMap().containsKey("lo-id")) {
            //Delete G of LO 
            if (ValidateUtils.canConvertToInt(request.getParameter("lo-id")) && 
                    ValidateUtils.canConvertToInt(request.getParameter("g-id"))) {
                int lo_id = Integer.parseInt(request.getParameter("lo-id"));
                int g_id = Integer.parseInt(request.getParameter("g-id"));
                try {
                    ChuanGBUS.getInstance().deleteGOfLO(g_id, lo_id);
                    response.getWriter().println("Delete success!");
                } catch (Exception ex) {
                    LOG.error("This G cannot be deleted!!!", ex);
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().println("Server err");
                }
                
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Id invalid!");
            }
        }else {
          //Delete LO
            if (ValidateUtils.canConvertToInt(request.getParameter("g-id")) ) {
                int g_id = Integer.parseInt(request.getParameter("g-id"));
                try {
                    ChuanGBUS.getInstance().delete(g_id);

                    response.getWriter().println("Delete success!");
                } catch (Exception ex) {
                    LOG.error("This G cannot be deleted!!!", ex);
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().println("Server err");
                }
                
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Id invalid!");
            }
        }
	}

}
