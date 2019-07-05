package uit.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.ChuanLOBUS;
import uit.utils.ValidateUtils;

/**
 * Servlet implementation class DeleteLOServlet
 */
@WebServlet("/lo-delete")
public class DeleteLOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(DeleteLOServlet.class);
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Check Request Param
         * "nganh-id": if exist -> delete LO of Nganh
         *           : if not exist -> delete LO
         */
        if (request.getParameterMap().containsKey("nganh-id")) {
            //Delete LO in Nganh 
            if (ValidateUtils.canConvertToInt(request.getParameter("nganh-id")) && 
                    ValidateUtils.canConvertToInt(request.getParameter("lo-id"))) {
                int lo_id = Integer.parseInt(request.getParameter("lo-id"));
                int nganh_id = Integer.parseInt(request.getParameter("nganh-id"));
                try {
                    ChuanLOBUS.getInstance().deleteLOOfNganh(nganh_id, lo_id);
                    response.getWriter().println("Delete success!");
                } catch (Exception ex) {
                    LOG.error("This LO cannot be deleted!!!", ex);
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().println("Server err");
                }
                
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Id invalid!");
            }
        }else {
          //Delete LO
            if (ValidateUtils.canConvertToInt(request.getParameter("lo-id")) ) {
                int lo_id = Integer.parseInt(request.getParameter("lo-id"));
                try {
                    ChuanLOBUS.getInstance().delete(lo_id);

                    response.getWriter().println("Delete success!");
                } catch (Exception ex) {
                    LOG.error("This LO cannot be deleted!!!", ex);
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
