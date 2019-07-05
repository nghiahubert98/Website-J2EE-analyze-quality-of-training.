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
import uit.model.Lop;
import uit.model.User;
import uit.modelview.FilterLopViewModel;
import uit.modelview.IndexModelView;
import uit.utils.AuthUtil;
import uit.utils.Constants;
import uit.utils.ValidateUtils;

@WebServlet("/lop")
public class LopListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(LopListServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FilterLopViewModel filter = new FilterLopViewModel();
            filter.setMaLop(request.getParameter("maLop"));
            filter.setHocKy(request.getParameter("hocKy"));
            filter.setMonHoc(request.getParameter("monHoc"));
            filter.setGiangVien(request.getParameter("giangVien"));
            int page = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
                page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER
                        ? Integer.parseInt(request.getParameter("page"))
                        : Constants.DEFAULT_PAGE_NUMBER;
            }
            
            User user = AuthUtil.getLoginedUser(request);
            if (user.getRoles().contains(Constants.ADMIN_ROLE)
            || user.getRoles().contains(Constants.HEAD_FACULY_ROLE)) {
                // Do nothing
            } else if (user.getRoles().contains(Constants.STUDENT_ROLE)) {
                filter.setMssv(user.getCode());
            } else if (user.getRoles().contains(Constants.LECTURER_ROLE)) {
                filter.setMa_gv(user.getCode());
            }
                        
            IndexModelView<Lop> data = LopBUS.getInstance().getIndexViewModel(page, filter);
            request.setAttribute("data", data);
            
            // Get index.jsp dispatcher and forward.
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/Lop/index.jsp");
            dispatcher.forward(request, response);

        } catch (

        Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get lop", ex);
        }
    }

}
