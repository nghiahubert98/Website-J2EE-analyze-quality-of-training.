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

import uit.bus.HocKyBUS;
import uit.model.HocKy;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

@WebServlet("/hocky-edit")
public class EditHocKy extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(EditHocKy.class);
  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {       
        
        try {
            // Page of list hoc ky
            // using redirect after edit success.
            int backPage = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(req.getParameter("backPage"))) {
                backPage = Integer.parseInt(req.getParameter("backPage"));                
            }
            
            if (ValidateUtils.canConvertToInt(req.getParameter("id"))) {                
                Integer id = Integer.parseInt(req.getParameter("id"));
                HocKy HocKy = HocKyBUS.getInstance().findById(id);
                
                if (HocKy != null) {
                    req.setAttribute("data", HocKy);
                    req.setAttribute("backPage", backPage);
                    
                    // Get edit.jsp dispatcher and forward.
                    RequestDispatcher dispatcher = req.getRequestDispatcher("templates/hocky/edit.jsp");
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
        String loai = request.getParameter("loai");
        Integer namBatDau = Integer.parseInt(request.getParameter("namBatDau"));
        Integer namKetThuc = Integer.parseInt(request.getParameter("namKetThuc"));
       
        HocKy HocKy = new HocKy(loai, namBatDau, namKetThuc);
        
        if (ValidateUtils.canConvertToInt(request.getParameter("id"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            HocKy.setId(id);
            HocKy.setLoai(loai);
            HocKy.setNamBatDau(namBatDau);
            HocKy.setNamKetThuc(namKetThuc);
        } 
        
        int page = Constants.DEFAULT_PAGE_NUMBER;
        if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        try {
            if (HocKy.validate(request)) {

                HocKyBUS.getInstance().update(HocKy);

                // Set success message to session
                HttpSession session = request.getSession(true);
                session.setAttribute("success", "Bạn đã cập nhật thông tin thành công");
                
                // Redirect to last page.
                response.sendRedirect(ctxPath + "/hocky?page=" + page);
            } else {

                response.sendRedirect(ctxPath + "/hocky-edit?id="+ HocKy.getId() +"&backPage=" + page);
            }
            
        } catch (Exception ex) {
            LOG.error("Could not create Hoc Ky", ex);
            
            String mess = "Xảy ra lỗi trong quá trình cập nhật dữ liệu, Xin hãy kiểm tra lại dữ liệu đầu vào ";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, HocKy);

            // redirect to /hocky-create.
            response.sendRedirect(ctxPath + "/hocky-edit?id="+ HocKy.getId() +"&backPage=" + page);
        }
    }
}
