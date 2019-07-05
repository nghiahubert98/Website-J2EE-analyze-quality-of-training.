package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.ChuanGBUS;
import uit.model.ChuanG;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

/**
 * Servlet implementation class CreateGServlet
 */
@WebServlet("/g-create")
public class CreateGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(CreateGServlet.class);
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/chuang/create.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            LOG.error("Could not get create-g page", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String ctxPath = request.getContextPath();
        
        if (request.getParameterMap().containsKey("lo-id")) {
            
            //Get params from request
            String gid = request.getParameter("g-id");
            String loid = request.getParameter("lo-id");
            if (ValidateUtils.canConvertToInt(loid) && ValidateUtils.canConvertToInt(gid)) {
                int newloid = Integer.parseInt(loid);
                int newgid = Integer.parseInt(gid);
                
                try {
                    ChuanGBUS.getInstance().createGOfLO(newloid, newgid);
                    
                    //response for client success msg
                    String successMsg = "Thêm G cho LO thành công!";
                    response.setContentType("text/txt");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().println(successMsg);
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    String errMsg = "Không thể thêm G cho LO!";
                    response.setContentType("text/txt");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().println(errMsg);
                }
            }
        }else {
            String code = request.getParameter("code");
            String description = request.getParameter("description");
            ChuanG newg = new ChuanG(code, description);
            
            try {
                if (newg.validate(request)) {
                    ChuanGBUS.getInstance().create(newg);
                    MessageSessionUtil.createSuccessMsg(request, "Tạo mới G thành công!");
                    response.sendRedirect(ctxPath + "/chuan-g?page=" + ChuanGBUS.getInstance().getTotalPage());
                } else {
                    response.sendRedirect(ctxPath + "/g-create");
                }   
            } catch (Exception ex) {
                LOG.error("Could not create G", ex);

                String mess = "Xảy ra lỗi không thể tạo G được, Xin hãy kiểm tra lại dữ liệu đầu vào "
                        + "và chắc chắn rằng mã LO không trùng với bất kỳ mã G nào trong hệ thống hiện tại";

                // Set error into session. Prepare to display in redirect page.
                MessageSessionUtil.createServerErrorMsg(request, mess, newg);

                // redirect to /lo-create.
                response.sendRedirect(ctxPath + "/g-create");
            }
        }
	}

}
