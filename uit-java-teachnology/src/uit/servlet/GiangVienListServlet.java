package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.GiangVienBUS;
import uit.model.GiangVien;
import uit.modelview.FilterGiangVienViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.ValidateUtils;

@WebServlet("/giang-vien")
public class GiangVienListServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(GiangVienListServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {    
            
            FilterGiangVienViewModel filter = new FilterGiangVienViewModel();
            filter.setName(request.getParameter("name"));
            filter.setCode(request.getParameter("code"));
            
            // Database pagination.
            int page = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
                page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER 
                       ? Integer.parseInt(request.getParameter("page")) 
                       : Constants.DEFAULT_PAGE_NUMBER;                
            }
            
            // Get data from database and set request attribute.
            IndexModelView<GiangVien> data = GiangVienBUS.getInstance().getIndexViewModel(page, filter);            
            request.setAttribute("data", data);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/giangvien/index.jsp");          
            dispatcher.forward(request, response);              
        } catch (Exception ex) {
            LOG.error("Could not giang vien index page.", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/txt");
        try {
            if (ValidateUtils.canConvertToInt(request.getParameter("id"))) {
                // In case id valid get id
                // form request and perform delete giang vien from database.
                int id = Integer.parseInt(request.getParameter("id"));
                GiangVienBUS.getInstance().delete(id);

                response.getWriter().println("Delete success!");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Id invalid!");
            }
        } catch (Exception ex) {
            LOG.error("Could not delete giang vien which id is " + request.getParameter("id"), ex);
            
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Server err");
        }
    }
    
}
