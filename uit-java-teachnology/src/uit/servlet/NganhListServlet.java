package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.NganhBUS;
import uit.model.Nganh;
import uit.modelview.FilterNganhViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.ValidateUtils;


@WebServlet("/nganh")
public class NganhListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(NganhListServlet.class);   
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FilterNganhViewModel filter = new FilterNganhViewModel();
            filter.setName(request.getParameter("name"));
            filter.setManganh(request.getParameter("manganh"));
            
            int page = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
                page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER 
                       ? Integer.parseInt(request.getParameter("page")) 
                       : Constants.DEFAULT_PAGE_NUMBER;                
            } 
            
            IndexModelView<Nganh> data = NganhBUS.getInstance().getIndexViewModel(page, filter);
            request.setAttribute("data", data);
            
            // Get index.jsp dispatcher and forward.
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/nganh/index.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get Nganh", ex);
        }
    }
    
}
