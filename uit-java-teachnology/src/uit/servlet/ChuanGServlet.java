package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.ChuanGBUS;
import uit.model.ChuanG;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.ValidateUtils;

/**
 * Servlet implementation class ChuanGServlet
 */
@WebServlet("/chuan-g")
public class ChuanGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(ChuanGServlet.class); 
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int page = Constants.DEFAULT_PAGE_NUMBER;
	    if (request.getParameterMap().containsKey("lo-id")) {
            int loid = Integer.parseInt(request.getParameter("lo-id"));;
            if (ValidateUtils.canConvertToInt(request.getParameter("page")) 
                    && ValidateUtils.canConvertToInt(request.getParameter("lo-id"))) {
                page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER 
                       ? Integer.parseInt(request.getParameter("page")) 
                       : Constants.DEFAULT_PAGE_NUMBER; }      
                
            try {
                IndexModelView<ChuanG> listG = ChuanGBUS.getInstance().getGOfLOModel(loid, page);
                IndexModelView<ChuanG> newG = ChuanGBUS.getInstance().getGNotInLOModel(loid);
                request.setAttribute("data", listG);
                request.setAttribute("newdata", newG);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("templates/chuang/g-lo.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {
                LOG.error("Could not get G_LO page", ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            
          }else { 
              if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
                  page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER 
                         ? Integer.parseInt(request.getParameter("page")) 
                         : Constants.DEFAULT_PAGE_NUMBER; }
              try {
                  IndexModelView<ChuanG> listG = ChuanGBUS.getInstance().getGModel(page);
                  request.setAttribute("data", listG);
                  
                  RequestDispatcher dispatcher = request.getRequestDispatcher("templates/chuang/index.jsp");
                  dispatcher.forward(request, response);
              } catch (Exception ex) {
                  LOG.error("Could not get G page", ex);
                  response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
              }
          }
         
	}

}
