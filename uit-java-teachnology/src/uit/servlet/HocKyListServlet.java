package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.HocKyBUS;
import uit.model.HocKy;
import uit.modelview.FilterHocKyViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.ValidateUtils;

@WebServlet("/hocky")
public class HocKyListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(HocKyListServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FilterHocKyViewModel filter = new FilterHocKyViewModel();
            filter.setLoai(request.getParameter("loai"));
            if (ValidateUtils.canConvertToInt(request.getParameter("namBatDau"))) {
                filter.setNamBatDau(Integer.parseInt(request.getParameter("namBatDau")));
            }
            if (ValidateUtils.canConvertToInt(request.getParameter("namKetThuc"))) {
                filter.setNamKetThuc(Integer.parseInt(request.getParameter("namKetThuc")));
            }
           
            int page = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
                page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER
                        ? Integer.parseInt(request.getParameter("page"))
                        : Constants.DEFAULT_PAGE_NUMBER;
            }

            IndexModelView<HocKy> data = HocKyBUS.getInstance().getIndexViewModel(page, filter);
            request.setAttribute("data", data);

            // Get index.jsp dispatcher and forward.
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/hocky/index.jsp");
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get Hoc Ky", ex);
        }
    }

}