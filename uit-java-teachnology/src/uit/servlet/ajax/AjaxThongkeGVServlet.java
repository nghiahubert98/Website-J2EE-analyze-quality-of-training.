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

import uit.bus.StatisticsBUS;
import uit.model.TiLeG;
import uit.utils.ValidateUtils;

/**
 * Servlet implementation class AjaxThongkeGVServlet
 */
@WebServlet("/ajax-gv-thongke")
public class AjaxThongkeGVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(AjaxThongkeGVServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int lid = 0;
	    if (ValidateUtils.canConvertToInt(request.getParameter("lid"))) {
            lid = Integer.parseInt(request.getParameter("lid"));
        }
	    
	    try {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("utf-8");
            List<TiLeG> listTileG = StatisticsBUS.getInstance().getGLopAllGVByID(lid);
            String data = new Gson().toJson(listTileG);
            response.getWriter().write(data);
        } catch (Exception ex) {
            LOG.error("Error at AjaxThongkeGVServlet@doGet", ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
