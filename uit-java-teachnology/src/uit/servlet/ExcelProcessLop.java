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

import uit.bus.GiangVienBUS;
import uit.bus.HocKyBUS;
import uit.bus.LopBUS;
import uit.bus.MonHocBUS;
import uit.model.GiangVien;
import uit.model.HocKy;
import uit.model.Lop;
import uit.model.MonHoc;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.PoiUtils;
import uit.utils.ValidateUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet("/lop-excel")
public class ExcelProcessLop extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String[] excelStruct = { "Mã lớp", "Mã môn", "Mã giảng viên" };
    private final static Logger LOG = Logger.getLogger(PoiUtils.class);

    private Lop convertExcelRowToLop(final Row row, int hocKyId) throws Exception {
        Iterator<Cell> cellIterator = null;
        Cell cell = null;
        Lop lop = null;

        cellIterator = row.cellIterator();
        MonHoc moHoc = null;
        while (cellIterator.hasNext()) {
            lop = new Lop();
            lop.setHocKyID(hocKyId);
            
            cell = cellIterator.next();
            lop.setMaLop(cell.getStringCellValue());            
            cell = cellIterator.next();
            String maMH = cell.getStringCellValue();
            moHoc = MonHocBUS.getInstance().findByCode(maMH);
            if (moHoc == null) {
                throw new Exception("File invalid");
            }
            lop.setMonHocID(moHoc.getId());
            cell = cellIterator.next();
            GiangVien gv = GiangVienBUS.getInstance().findByCode(cell.getStringCellValue());
            if (gv == null) {
                throw new Exception("File invalid");
            }
            lop.setGvID(gv.getId());
        }

        return lop;
    }

    private List<Lop> convertWorkBookToLopList(final Workbook workbook, int hocKyId) throws Exception {
        if (workbook.getNumberOfSheets() > 0) {
            List<Lop> result = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            Row row = null;
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                if (row.getRowNum() == 0) { // Ignore header cell;
                    continue;
                }

                Lop Lop = convertExcelRowToLop(row, hocKyId);
                result.add(Lop);
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
            IndexModelView<HocKy> listHK = HocKyBUS.getInstance().findForDropDown(Constants.DEFAULT_PAGE_NUMBER);
            request.setAttribute("listHK", listHK);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("templates/Lop/excel.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            LOG.error("Could not get Import lop", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ctxPath = req.getContextPath();
        String errorMessage = "";

        try {
            if (!ValidateUtils.canConvertToInt(req.getParameter("hocky"))) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            int hocKyId = Integer.parseInt(req.getParameter("hocky"));
            Part excel = req.getPart("excel");

            if (excel != null && excel.getSize() > 0) {
                String submitFileName = excel.getSubmittedFileName();

                // Check file extendsions.
                if (submitFileName.contains(Constants.DOT_XLS) || submitFileName.contains(Constants.DOT_XLSX)) {
                    Workbook workbook = PoiUtils.loadWorkbook(excel.getInputStream());

                    if (PoiUtils.checkExcelStructure(workbook, excelStruct)) {
                        // Get list Lop form excel and insert to database.
                        List<Lop> insertList = convertWorkBookToLopList(workbook, hocKyId);
                        LopBUS.getInstance().insertMany(insertList);

                        // Add success message, Prepare redirect to index page.
                        String message = "Đã tiến hành import bằng excel thành công!";
                        MessageSessionUtil.createSuccessMsg(req, message);

                        resp.sendRedirect(ctxPath + "/lop");

                        return;
                    }
                    // Check excel structure invalid.
                    errorMessage = "File excel không đúng cấu trúc, Bạn có thể xem cấu trúc file trong file mẫu!";
                } else {
                    // File extendsions not *.xls & *.xlsx.
                    errorMessage = "File sai định dạng! File chỉ có thể là *.xls hoặc *.xlsx.";
                }
            } else {
                // File upload empty.
                errorMessage = "Không được để file trống!";
            }

            excelProcessError(req, errorMessage, false);
        } catch (Exception ex) {
            LOG.error("Could not insert Lop by Excel", ex);

            errorMessage = "<div>Xảy ra lỗi khi tiến hành import dữ liệu, Lỗi này xảy ra khi: </div>"
                    + "<ul class='m-0'> " + "<li>Trong file Excel của bạn có dữ liệu không phù hợp.</li>"
                    + "<li>File sai định dạng được yêu cầu.</li>" + "</ul><div>"
                    + "Hãy chắc rằng file Excel của bạn không có dữ liệu nào trùng và không có dữ liệu nào sai định dạng</div>";

            excelProcessError(req, errorMessage, true);
        }

        resp.sendRedirect(ctxPath + "/lop-excel");
    }

}
