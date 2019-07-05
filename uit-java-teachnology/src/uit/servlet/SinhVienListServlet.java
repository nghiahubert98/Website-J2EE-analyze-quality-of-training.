package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.LopBUS;
import uit.bus.SinhVienBUS;
import uit.model.SinhVien;
import uit.model.User;
import uit.modelview.FilterSinhVienViewModel;
import uit.modelview.IndexModelView;
import uit.utils.AuthUtil;
import uit.utils.Constants;
import uit.utils.ValidateUtils;

@WebServlet("/sinhvien")
public class SinhVienListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(SinhVienListServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String servletPath = request.getServletPath();
    	
        try {
            FilterSinhVienViewModel filter = new FilterSinhVienViewModel();
            filter.setMssv(request.getParameter("mssv"));
            filter.setTen(request.getParameter("ten"));
            filter.setTenLSH(request.getParameter("tenLSH"));

            int page = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
                page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER
                        ? Integer.parseInt(request.getParameter("page"))
                        : Constants.DEFAULT_PAGE_NUMBER;
            }
            
            IndexModelView<SinhVien> data = null;
            User user = AuthUtil.getLoginedUser(request);
            int id = 0;
            		
            if (ValidateUtils.canConvertToInt(request.getParameter("id"))) {
            	
            	id = Integer.parseInt(request.getParameter("id"));
               	data = SinhVienBUS.getInstance().getListSvLop(id, page, filter);
               	int idMon = LopBUS.getInstance().getIdMon(id);
               	request.setAttribute("data", data);
               	request.setAttribute("idMon", idMon);
       			RequestDispatcher dispatcher = request.getRequestDispatcher("templates/SinhVien/index.jsp");
       			dispatcher.forward(request, response);
       			
            } else {
            	
            	if(AuthUtil.adminRole(servletPath, user)) {
            		data = SinhVienBUS.getInstance().getIndexViewModel(page, filter);
            		request.setAttribute("data", data);
            		RequestDispatcher dispatcher = request.getRequestDispatcher("templates/SinhVien/index.jsp");
                    dispatcher.forward(request, response);
            	} else {
            		RequestDispatcher dispatcher = request.getRequestDispatcher("templates/403.jsp");
            		dispatcher.forward(request, response);
            	}				
            }
            
        } catch (

        Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get sinh vien", ex);
        }
    }

}
