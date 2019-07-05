package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.LopSinhHoatBUS;
import uit.model.LopSinhHoat;
import uit.modelview.FilterLopSinhHoatViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.ValidateUtils;

@WebServlet("/lopsinhhoat")
public class LopSinhHoatListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(LopSinhHoatListServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FilterLopSinhHoatViewModel filter = new FilterLopSinhHoatViewModel();
            filter.setMaLop(request.getParameter("maLop"));
            filter.setBatDau(request.getParameter("batDau"));
            filter.setTotNghiep(request.getParameter("totNghiep"));
            filter.setTen(request.getParameter("ten"));
            filter.setCoVan(request.getParameter("coVan"));
            int page = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
                page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER
                        ? Integer.parseInt(request.getParameter("page"))
                        : Constants.DEFAULT_PAGE_NUMBER;
            }

            IndexModelView<LopSinhHoat> data = LopSinhHoatBUS.getInstance().getIndexViewModel(page, filter);
            request.setAttribute("data", data);

            // Get index.jsp dispatcher and forward.
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/LopSinhHoat/index.jsp");
            dispatcher.forward(request, response);

        } catch (

        Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get Lop Sinh Hoat", ex);
        }
    }

}
