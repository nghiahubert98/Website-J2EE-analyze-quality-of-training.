package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.ChuanLOBUS;
import uit.model.ChuanLO;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

/**
 * Servlet implementation class CreateLOServlet
 */
@WebServlet("/lo-create")
public class CreateLOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(CreateLOServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/chuanlo/create.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            LOG.error("Could not get add-lo page", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String ctxPath = request.getContextPath();
	    
        if (request.getParameterMap().containsKey("nganh-id")) {
            
            //Get params from request
            String nienkhoa = request.getParameter("nk");
            String nganhid = request.getParameter("nganh-id");
            String loid = request.getParameter("lo-id");
            
            if (ValidateUtils.canConvertToInt(nganhid) && ValidateUtils.canConvertToInt(loid)) {
                int ngid = Integer.parseInt(nganhid);
                int lid = Integer.parseInt(loid);
                
                try {
                    ChuanLOBUS.getInstance().createLOOfNganh(ngid, lid, nienkhoa);
                    
                    //response for client success msg
                    String successMsg = "Thêm LO cho Ngành thành công!";
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().println(successMsg);
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    String errMsg = "Không thể thêm LO cho ngành!";
                    response.setContentType("text/txt");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().println(errMsg);
                }
            }
        }else {
            String code = request.getParameter("code");
            String description = request.getParameter("description");
            ChuanLO newlo = new ChuanLO(code, description);
            
            try {
                if (newlo.validate(request)) {
                    ChuanLOBUS.getInstance().create(newlo);
                    MessageSessionUtil.createSuccessMsg(request, "Tạo mới LO thành công!");
                    response.sendRedirect(ctxPath + "/chuan-lo?page=" + ChuanLOBUS.getInstance().getTotalPage());
                } else {
                    response.sendRedirect(ctxPath + "/lo-create");
                }   
            } catch (Exception ex) {
                LOG.error("Could not create LO", ex);

                String mess = "Xảy ra lỗi không thể tạo LO được, Xin hãy kiểm tra lại dữ liệu đầu vào "
                        + "và chắc chắn rằng mã LO không trùng với bất kỳ mã LO nào trong hệ thống hiện tại";

                // Set error into session. Prepare to display in redirect page.
                MessageSessionUtil.createServerErrorMsg(request, mess, newlo);

                // redirect to /lo-create.
                response.sendRedirect(ctxPath + "/lo-create");
            }
        }
	}
}
