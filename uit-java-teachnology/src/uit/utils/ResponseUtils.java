package uit.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ResponseUtils {
    public static void writeResponseExcelXLSX(final HttpServletResponse response, final XSSFWorkbook wb, final String fileName) throws IOException {
        response.setContentType("application/vnd.ms-excel"); 
        response.setHeader("content-disposition", String.format("attachment; filename=%s.xlsx", fileName));
        wb.write(response.getOutputStream()); 
    }
}
