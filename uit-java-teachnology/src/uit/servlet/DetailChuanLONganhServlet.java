package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.ChuanLOBUS;
import uit.model.ChuanLO;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.ValidateUtils;

/**
 * Servlet implementation class DetailChuanLONganh
 */
@WebServlet("/chuan-lo")
public class DetailChuanLONganhServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(DetailChuanLONganhServlet.class);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (request.getParameterMap().containsKey("nganh-id")) {
            /* *
             * Case Request was send from Nganh page to view LO of Nganh 
             * "nganh-id" param in request
             * Case "nganh-id" exists: get list LO of nganh
             * */
	        try {    
	            int page = Constants.DEFAULT_PAGE_NUMBER;
	            int nganhid = Integer.parseInt(request.getParameter("nganh-id"));
	            if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
	                page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER 
	                       ? Integer.parseInt(request.getParameter("page")) 
	                       : Constants.DEFAULT_PAGE_NUMBER;  
	                nganhid = Integer.parseInt(request.getParameter("nganh-id"));       
	            }
	            
	            //Proccessing Data
	            IndexModelView<ChuanLO> loNganh = ChuanLOBUS.getInstance().getIndexViewModel(nganhid, page); 
	            IndexModelView<ChuanLO> newLONganh = ChuanLOBUS.getInstance().getLONotInNganhModel(nganhid);
	            request.setAttribute("data", loNganh);
	            request.setAttribute("newlo", newLONganh);
	            
	            //Proccessing Request
	            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/chuanlo/lo-nganh.jsp");
	            dispatcher.forward(request, response);
	        }catch (Exception ex) {
	            LOG.error("Could not get LO-Nganh page", ex);
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
        } else {
            /* *
             * Case Request was send from Home page to view all list LO 
             * */
            try {    
                int page = Constants.DEFAULT_PAGE_NUMBER;
                if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
                    page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER 
                           ? Integer.parseInt(request.getParameter("page")) 
                           : Constants.DEFAULT_PAGE_NUMBER;
                }
                
                //Proccessing Data
                IndexModelView<ChuanLO> LO = ChuanLOBUS.getInstance().getIndexViewAllModel(page); 
                request.setAttribute("data", LO);
                
                //Proccessing Request
                RequestDispatcher dispatcher = request.getRequestDispatcher("templates/chuanlo/index.jsp");
                dispatcher.forward(request, response);
            }catch (Exception ex) {
                LOG.error("Could not get LO page", ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
