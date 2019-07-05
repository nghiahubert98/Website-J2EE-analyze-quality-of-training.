package uit.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import uit.bus.CauHoiBUS;
import uit.bus.DeThiBUS;
import uit.bus.DiemBUS;
import uit.bus.LopBUS;
import uit.dao.ChuanGDAO;
import uit.model.CauHoi;
import uit.model.ChuanG;
import uit.model.DeThi;
import uit.model.Diem;
import uit.model.Lop;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

@WebServlet("/lop-detail")
public class DetailLopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(DetailLopServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        if (!ValidateUtils.canConvertToInt(request.getParameter("lid"))) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        int lopId = Integer.parseInt(request.getParameter("lid"));
        try {
            List<DeThi> listDethi = DeThiBUS.geInstance().findByLopId(lopId);
            // Not exist dethi => create new 2 dethi.
            if (listDethi.size() == 0) {
                Lop lop = LopBUS.getInstance().findById(lopId);
                if (lop == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                
                // LopBUS.geInstance().finById(lopId);
                DeThi dtGK = new DeThi( Constants.DE_GK, lopId);
                DeThi dtCK = new DeThi( Constants.DE_CK, lopId);
                DeThiBUS.geInstance().insertMany(Arrays.asList(dtGK, dtCK));
                listDethi = DeThiBUS.geInstance().findByLopId(lopId);
                
            } else if ( listDethi.size() != 2) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            List<Diem> diemDeCK = null;
            List<Diem> diemDeGK = null;
            List<CauHoi> cauHoiCK = null;
            List<CauHoi> cauHoiGK = null;
            for (DeThi deThi : listDethi) {
                if (deThi.getType() == Constants.DE_CK) {
                    diemDeCK = DiemBUS.getInstance().findTablePointByDethiId(deThi.getId());
                    cauHoiCK = CauHoiBUS.getInstance().findByDethiId(deThi.getId());
                }
                if (deThi.getType() == Constants.DE_GK) {
                    diemDeGK = DiemBUS.getInstance().findTablePointByDethiId(deThi.getId());
                    cauHoiGK = CauHoiBUS.getInstance().findByDethiId(deThi.getId());
                }
            }
            
            List<CauHoi> listCauHoi = CauHoiBUS.getInstance().findByLopId(lopId);
            List<ChuanG> listChuanG = ChuanGDAO.getInstance().findByLopId(lopId);
            request.setAttribute("data", listCauHoi);
            request.setAttribute("listChuanG", listChuanG);
            request.setAttribute("diemDeCK", diemDeCK);
            request.setAttribute("diemDeGK", diemDeGK);
            request.setAttribute("cauHoiCK", cauHoiCK);
            request.setAttribute("cauHoiGK", cauHoiGK);
            
            // Find cauhoi by userCode.
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/cauhoi/index.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            LOG.error("Error at HttpServlet@doGet", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ctx = request.getContextPath();
        if (request.getParameterMap().containsKey("editQ")) {
            if (!ValidateUtils.canConvertToInt(request.getParameter("num"))
                    || !ValidateUtils.canConvertToInt(request.getParameter("point"))
                    || !ValidateUtils.canConvertToInt(request.getParameter("num"))
                    || !ValidateUtils.canConvertToInt(request.getParameter("editQ"))
                    || !ValidateUtils.canConvertToInt(request.getParameter("lid"))){
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        return;
            }
            
            int lid = Integer.parseInt(request.getParameter("lid"));
            int qId = Integer.parseInt(request.getParameter("editQ"));
            int order = Integer.parseInt(request.getParameter("num"));
            float maxPoint = Float.parseFloat(request.getParameter("point"));
            CauHoi q = new CauHoi(qId, order, maxPoint);
            
            try {
                CauHoiBUS.getInstance().update(q);
                if (request.getParameterValues("gID") != null ) {
                    String[] gIDs = request.getParameterValues("gID");
                    CauHoiBUS.getInstance().updateG(q, gIDs);
                }
                MessageSessionUtil.createSuccessMsg(request, "Cập nhật thành công!");
                response.sendRedirect(ctx + "/lop-detail?lid="+lid);
            } catch (MySQLIntegrityConstraintViolationException ex1) {
                LOG.error("Xảy ra lỗi, không thể cập nhật dữ liệu câu hỏi này", ex1);
                MessageSessionUtil.createServerErrorMsg(request, "Không thể tạo được câu hỏi, Có thể số thứ tự của câu hỏi này đã tồn tại", null);
                
                response.sendRedirect(ctx + "/lop-detail?lid=" + lid);
            } catch (Exception ex2) {
                LOG.error("Xảy ra lỗi, không thể cập nhật dữ liệu", ex2);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }else {
            try {            
                if (!ValidateUtils.canConvertToInt(request.getParameter("lid"))
                || !ValidateUtils.canConvertToInt(request.getParameter("deId"))
                || !ValidateUtils.canConvertToInt(request.getParameter("num"))
                || !ValidateUtils.canConvertToFloat(request.getParameter("point"))) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                
                int lid = Integer.parseInt(request.getParameter("lid"));
                int deId = Integer.parseInt(request.getParameter("deId"));
                int stt = Integer.parseInt(request.getParameter("num"));
                float point = Float.parseFloat(request.getParameter("point"));
                String[] listG = request.getParameterValues("gID");
                
                CauHoi cauHoi = new CauHoi();
                cauHoi.setDethiId(deId);
                cauHoi.setMaxPoint(point); 
                cauHoi.setOrder(stt);
                CauHoiBUS.getInstance().create(cauHoi, listG);
                
                MessageSessionUtil.createSuccessMsg(request, "Tạo câu hỏi thành công!");
                response.sendRedirect(ctx + "/lop-detail?lid=" + lid);
            } catch (MySQLIntegrityConstraintViolationException ex1) {
                LOG.error("Xảy ra lỗi, không thể tạo câu hỏi cho đề này", ex1);
                MessageSessionUtil.createServerErrorMsg(request, "Không thể tạo được câu hỏi, Có thể số thứ tự của câu hỏi này đã tồn tại", null);
                
                response.sendRedirect(ctx + "/lop-detail?lid=" + request.getParameter("lid"));
            } catch (Exception ex2) {
                LOG.error("Xảy ra lỗi, không thể câu hỏi cho đề này", ex2);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
    
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ValidateUtils.canConvertToInt(request.getParameter("qid"))) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int qid = Integer.parseInt(request.getParameter("qid"));
        
        try {
            boolean has = DiemBUS.getInstance().checkRefCauhoi(qid);
            if (!has) {
                try {
                    CauHoiBUS.getInstance().delete(qid);
                    response.getWriter().println("Delete success!");
                } catch (Exception ex1) {
                    LOG.error("Không thể xóa câu hỏi"+ ex1);
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }else {
                LOG.error("Không thể xóa câu hỏi");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (Exception ex) {
            LOG.error("Không thể xóa câu hỏi vì xung đột dữ liệu"+ex);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }
}
