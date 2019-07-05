package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.MonHocBUS;
import uit.model.MonHoc;
import uit.model.User;
import uit.modelview.FilterMonHocViewModel;
import uit.modelview.IndexModelView;
import uit.utils.AuthUtil;
import uit.utils.Constants;
import uit.utils.ValidateUtils;

@WebServlet("/monhoc")
public class MonHocListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(MonHocListServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FilterMonHocViewModel filter = new FilterMonHocViewModel();
            filter.setName(request.getParameter("name"));
            filter.setMaMonHoc(request.getParameter("maMonHoc"));
            
            String servletPath = request.getServletPath();
            
            int page = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
                page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER
                        ? Integer.parseInt(request.getParameter("page"))
                        : Constants.DEFAULT_PAGE_NUMBER;
            }
            
            // Nếu check = 1 sẽ hiển thị danh sách các môn đã học của sinh viên
            User user = AuthUtil.getLoginedUser(request);
            if (AuthUtil.studentRole(servletPath, user) && !AuthUtil.lecturerRole(servletPath, user) &&
            		!AuthUtil.headerFacultyRole(servletPath, user) && !AuthUtil.adminRole(servletPath, user)
            		&& ValidateUtils.canConvertToInt(request.getParameter("check"))) {
            	
            	IndexModelView<MonHoc> data = MonHocBUS.getInstance().getMonHocSV(user.getId(), page, filter);
            	request.setAttribute("data", data);
            	
            } else {
            	IndexModelView<MonHoc> data = MonHocBUS.getInstance().getIndexViewModel(page, filter);
            	request.setAttribute("data", data);
            }
            // Get index.jsp dispatcher and forward.
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/MonHoc/index.jsp");
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get Mon Hoc", ex);
        }
    }

}