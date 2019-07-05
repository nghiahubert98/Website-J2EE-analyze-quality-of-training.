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
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

/**
 * Servlet implementation class EditLOServlet
 */
@WebServlet("/lo-edit")
public class EditLOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(EditLOServlet.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
            int backPage = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(request.getParameter("backPage"))) {
                backPage = Integer.parseInt(request.getParameter("backPage"));                
            }
            
            if (ValidateUtils.canConvertToInt(request.getParameter("lo-id"))) {                
                int id = Integer.parseInt(request.getParameter("lo-id"));
                ChuanLO lo = ChuanLOBUS.getInstance().findById(id);
                
                if (lo != null) {
                    request.setAttribute("data", lo);
                    request.setAttribute("backPage", backPage);
                    
                    // Get edit.jsp dispatcher and forward.
                    RequestDispatcher dispatcher = request.getRequestDispatcher("templates/chuanlo/edit.jsp");
                    dispatcher.forward(request, response);
                    
                    return;
                }
            }
            
            // Invalid queryString => set status code 404 and return 404 page.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/404.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            // Serve error set status code is 500 and return 500 page.
            
            LOG.error("Could not load edit page.", ex);
            
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/500.jsp");
            dispatcher.forward(request, response);
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String ctxPath = request.getContextPath();
	    String code = request.getParameter("code");
        String description = request.getParameter("description");
        int id = Integer.parseInt(request.getParameter("lo-id"));
        ChuanLO newlo = new ChuanLO(id ,code, description);
        
        int page = Constants.DEFAULT_PAGE_NUMBER;
        if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        try {
            if (newlo.validate(request)) {
                ChuanLOBUS.getInstance().update(newlo);
                MessageSessionUtil.createSuccessMsg(request, "Cập nhật thành công!");
                response.sendRedirect(ctxPath + "/chuan-lo?page=" + page);
            } else {
                response.sendRedirect(ctxPath + "/lo-edit?lo-id="+newlo.getId()+"&backPage="+page);
            }   
        } catch (Exception ex) {
            LOG.error("Could not edit LO", ex);

            String mess = "Xảy ra lỗi trong quá trình cập nhật dữ liệu, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng bạn đang cập nhật dữ liệu cho Lo đã tồn tại trong hệ thống";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, newlo);

            // redirect to /lo-edit.
            response.sendRedirect(ctxPath + "/lo-edit?lo-id="+id+"&backPage="+page);
        }
	}

}
