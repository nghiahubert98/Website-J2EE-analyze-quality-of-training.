package uit.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import uit.bus.HocKyBUS;
import uit.model.HocKy;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.PoiUtils;

/**
 * Servlet implementation class ExcelProcessHocKy
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB

@WebServlet("/hocky-excel")
public class ExcelProcessHocKy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(PoiUtils.class);
	private static final String[] excelStruct = {"Loại", "Năm bắt đầu", "Năm kết thúc" };
	
	private HocKy convertExcelRowToHocKy(final Row row) {
        Iterator<Cell> cellIterator = null;
        Cell cell = null;
        HocKy hocky = null;

        cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            hocky = new HocKy();
            cell = cellIterator.next();
            hocky.setLoai(cell.getStringCellValue());
            
            cell = cellIterator.next();
            int startYear = Integer.parseInt(Double.toString(cell.getNumericCellValue()).split("\\.")[0]);
            hocky.setNamBatDau(startYear);
            
            cell = cellIterator.next();
            int endYear = Integer.parseInt(Double.toString(cell.getNumericCellValue()).split("\\.")[0]);
            hocky.setNamKetThuc(endYear);
        }

        return hocky;
    }
    
    private List<HocKy> convertWorkBookToHocKyList(final Workbook workbook) {
        if (workbook.getNumberOfSheets() > 0) {
            List<HocKy> result = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            Row row = null;
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                if (row.getRowNum() == 0) { // Ignore header cell;
                    continue;
                }

                HocKy hocky = convertExcelRowToHocKy(row);
                result.add(hocky);
            }
            return result;
        }
        return null;
    }

    private void excelProcessError(final HttpServletRequest req, final String message, Boolean serverError) {
        Map<String, String> dataError = new HashMap<>();
        if (serverError)
            dataError.put("svError", message);
        else
            dataError.put("excelFile", message);
        
        MessageSessionUtil.createErrorMsg(req, dataError, null);
    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("templates/hocky/excel.jsp");
	        dispatcher.forward(request, response);
        } catch (Exception ex) {
            LOG.error("Could not get Import HocKy", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String ctxPath = request.getContextPath();
        String errorMessage = "";

        try {
            Part excel = request.getPart("excel");

            if (excel != null && excel.getSize() > 0) {
                String submitFileName = excel.getSubmittedFileName();

                // Check file extensions.
                if (submitFileName.contains(Constants.DOT_XLS) 
                || submitFileName.contains(Constants.DOT_XLSX)) {
                    Workbook workbook = PoiUtils.loadWorkbook(excel.getInputStream());
                    
                    if (PoiUtils.checkExcelStructure(workbook, excelStruct)) {
                        // Get list HocKy form excel and insert to database.
                        List<HocKy> insertList = convertWorkBookToHocKyList(workbook);
                        HocKyBUS.getInstance().insertMany(insertList);

                        // Add success message, Prepare redirect to index page.
                        String message = "Đã tiến hành import bằng excel thành công!";
                        MessageSessionUtil.createSuccessMsg(request, message);

                        response.sendRedirect(ctxPath + "/hocky");

                        return;
                    }
                    // Check excel structure invalid.
                    errorMessage = "File excel không đúng cấu trúc, Bạn có thể xem cấu trúc file trong file mẫu!";
                } else {
                    // File extensions not *.xls & *.xlsx.
                    errorMessage = "File sai định dạng! File chỉ có thể là *.xls hoặc *.xlsx.";
                }
            } else {
                // File upload empty.
                errorMessage = "Không được để file trống!";
            }

            excelProcessError(request, errorMessage, false);
        } catch (Exception ex) {
            LOG.error("Could not insert HocKy by Excel", ex);

            errorMessage = "<div>Xảy ra lỗi khi tiến hành import dữ liệu, Lỗi này xảy ra khi: </div>"
                    + "<ul class='m-0'> " + "<li>Trong file Excel của bạn có dữ liệu không phù hợp.</li>"
                    + "<li>File sai định dạng được yêu cầu.</li>" + "</ul><div>"
                    + "Hãy chắc rằng file Excel của bạn không có dữ liệu nào trùng và không có dữ liệu nào sai định dạng</div>";

            excelProcessError(request, errorMessage, true);
        }

        response.sendRedirect(ctxPath + "/hocky");
    }
}
