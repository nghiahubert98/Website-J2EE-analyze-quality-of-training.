package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import uit.bus.NganhBUS;
import uit.model.Nganh;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

@WebServlet("/nganh-edit")
public class EditNganh extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(EditNganh.class);
  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {       
        
        try {
            // Page of list nganhs
            // using redirect after edit success.
            int backPage = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(req.getParameter("backPage"))) {
                backPage = Integer.parseInt(req.getParameter("backPage"));                
            }
            
            if (ValidateUtils.canConvertToInt(req.getParameter("id"))) {                
                int id = Integer.parseInt(req.getParameter("id"));
                Nganh nganh = NganhBUS.getInstance().findById(id);
                
                if (nganh != null) {
                    req.setAttribute("data", nganh);
                    req.setAttribute("backPage", backPage);
                    
                    // Get edit.jsp dispatcher and forward.
                    RequestDispatcher dispatcher = req.getRequestDispatcher("templates/nganh/edit.jsp");
                    dispatcher.forward(req, resp);
                    
                    return;
                }
            }
            
            // Invalid queryString => set status code 404 and return 404 page.
           
            LOG.info("Id = " + req.getParameter("id") + "invalid");
            
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            
            RequestDispatcher dispatcher = req.getRequestDispatcher("templates/404.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception ex) {
            // Serve error set status code is 500 and return 500 page.
            
            LOG.error("Could not load edit page.", ex);
            
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            
            RequestDispatcher dispatcher = req.getRequestDispatcher("templates/500.jsp");
            dispatcher.forward(req, resp);
        }
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ctxPath = request.getContextPath();
        String tenNganh = request.getParameter("ten_nganh");
        String maNganh = request.getParameter("ma_nganh");
       
        Nganh nganh = new Nganh(tenNganh, maNganh);
        
        if (ValidateUtils.canConvertToInt(request.getParameter("id"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            nganh.setId(id);
            nganh.setName(tenNganh);
            nganh.setManganh(maNganh);
        } 
        
        int page = Constants.DEFAULT_PAGE_NUMBER;
        if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        try {
            if (nganh.validate(request)) {

                NganhBUS.getInstance().update(nganh);

                // Set success message to session
                HttpSession session = request.getSession(true);
                session.setAttribute("success", "Bạn đã cập nhật thông tin thành công");
                
                // Redirect to last page.
                response.sendRedirect(ctxPath + "/nganh?page=" + page);
            } else {

                response.sendRedirect(ctxPath + "/nganh-edit?id="+ nganh.getId() +"&backPage=" + page);
            }
            
        } catch (Exception ex) {
            LOG.error("Could not create Nganh", ex);
            
            String mess = "Xảy ra lỗi trong quá trình cập nhật dữ liệu, Xin hãy kiểm tra lại dữ liệu đầu vào "      
                    + "và chắc chắn rằng mã ngành không trùng với bất kỳ mã ngành nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, nganh);

            // redirect to /nganh-create.
            response.sendRedirect(ctxPath + "/nganh-edit?id="+ nganh.getId() +"&backPage=" + page);
        }
    }
    


}
