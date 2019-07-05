package uit.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import uit.bus.HocKyBUS;
import uit.model.HocKy;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.ValidateUtils;

@WebServlet("/ajax-get-hk")
public class AjaxHocKyListServelt extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(AjaxHocKyListServelt.class);
    private Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Constants.DEFAULT_PAGE_NUMBER;
        if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page")) >= Constants.DEFAULT_PAGE_NUMBER
                    ? Integer.parseInt(request.getParameter("page"))
                    : Constants.DEFAULT_PAGE_NUMBER;
        }        
        try {
            response.setContentType("application/json");
            IndexModelView<HocKy> listHK = HocKyBUS.getInstance().findForDropDown(page);
            String json = this.gson.toJson(listHK);
            response.getWriter().println(json);
        } catch (Exception ex) {
            LOG.error("Error at GetHocKyListServelt@doGet", ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
