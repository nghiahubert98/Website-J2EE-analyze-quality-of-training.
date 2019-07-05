package uit.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uit.bus.CauHoiBUS;
import uit.bus.DiemBUS;
import uit.bus.LopBUS;
import uit.model.Lop;
import uit.utils.AuthUtil;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.PoiUtils;
import uit.utils.ResponseUtils;
import uit.utils.ValidateUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet("/excel-diem")
public class ExcelProcessDiem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(ExcelProcessDiem.class);   
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        if(!ValidateUtils.canConvertToInt(request.getParameter("lid"))
	        || !ValidateUtils.canConvertToInt(request.getParameter("dethiId"))) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
	        }
	        int lopId = Integer.parseInt(request.getParameter("lid"));
	        int dethiId = Integer.parseInt(request.getParameter("dethiId"));
            Lop lop = LopBUS.getInstance().findById(lopId);
            if (!AuthUtil.getLoginedUser(request).getCode().equals(lop.getMavGV())) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            
            XSSFWorkbook importWorkbook = CauHoiBUS.getInstance().getImportDiemWorkbook(dethiId, lopId);
            ResponseUtils.writeResponseExcelXLSX(response, importWorkbook, "ImportDiemExcel_" + lop.getMaLop());
        } catch (ClassNotFoundException | SQLException ex) {
           LOG.error("Could not get file excel", ex);
           response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String ctxPath = request.getContextPath();
        String errorMessage = "";
        try {
            if (!ValidateUtils.canConvertToInt(request.getParameter("detId"))
            || !ValidateUtils.canConvertToInt(request.getParameter("lopId"))) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            int dethiId = Integer.parseInt(request.getParameter("detId"));
            int lopId = Integer.parseInt(request.getParameter("lopId"));
            Part excel = request.getPart("excel");

            if (excel != null && excel.getSize() > 0) {
                String submitFileName = excel.getSubmittedFileName();

                // Check file extensions.
                if (submitFileName.contains(Constants.DOT_XLS) 
                || submitFileName.contains(Constants.DOT_XLSX)) {
                    Workbook workbook = PoiUtils.loadWorkbook(excel.getInputStream());
                    DiemBUS.getInstance().importExcel(workbook, dethiId, lopId);
                    
                    MessageSessionUtil.createSuccessMsg(request, "Import bảng điểm thành công!");
                } else {
                    // File extensions not *.xls & *.xlsx.
                    errorMessage = "File sai định dạng! File chỉ có thể là *.xls hoặc *.xlsx.";
                    MessageSessionUtil.createServerErrorMsg(request, errorMessage, null);
                }
            } else {
                // File upload empty.
                errorMessage = "Không được để file trống!";
                MessageSessionUtil.createServerErrorMsg(request, errorMessage, null);
            }
            
            response.sendRedirect(ctxPath + "/lop-detail?lid=" + lopId);
        } catch (Exception ex) {
            LOG.error("Could not import excel", ex);
            errorMessage ="Xảy ra lỗi, không thể tiền hành import đữ liệu. Xin hãy kiểm tra lại file excel của bạn. ";
            MessageSessionUtil.createServerErrorMsg(request, errorMessage, null);
            
            response.sendRedirect(ctxPath + "/lop-detail?lid=" + request.getParameter("lopId"));
        }
	}

}
