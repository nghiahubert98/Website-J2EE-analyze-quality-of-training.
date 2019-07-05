package uit.servlet.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import uit.dao.ChuanGDAO;
import uit.model.ChuanG;
import uit.utils.ValidateUtils;

/**
 * Servlet implementation class AjaxGCauhoiServlet
 */
@WebServlet("/ajax-g-question")
public class AjaxGCauhoiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(AjaxGCauhoiServlet.class);
    private Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int qid = 0;
		int lid = 0;
	    if (ValidateUtils.canConvertToInt(request.getParameter("lid"))) {
//            qid = Integer.parseInt(request.getParameter("qid"));
            lid = Integer.parseInt(request.getParameter("lid"));
        }
	    
	    try {
            response.setContentType("application/json");
//            List<ChuanG> listGCauhoi = ChuanGDAO.getInstance().findByCauhoiId(qid);
            List<ChuanG> listGMon = ChuanGDAO.getInstance().findByLopId(lid);
//            String gQues = this.gson.toJson(listGCauhoi);
            String gMon = this.gson.toJson(listGMon);
//            response.getWriter().println(gQues);
            response.getWriter().println(gMon);
        } catch (Exception ex) {
            LOG.error("Error at GetHocKyListServelt@doGet", ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
	}

}
