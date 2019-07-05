package uit.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Common utility to use POI.
 * @author PhongNguyen
 */
public class PoiUtils {
    /** For logging. */
    private final static Logger LOG = Logger.getLogger(PoiUtils.class);
    
    /**
     * Load excel workbook form InputStream.
     * @param is A {@link InputStream}
     * @return A instance of {@link Workbook} or null if could not load WorkBook
     */
    public static Workbook loadWorkbook(final InputStream is) {
        Workbook workbook = null;
        if (is == null) {
            return null;
        }

        // Try to load with Excel 2007 or later
        try {
            workbook = new XSSFWorkbook(is);
        } catch (Throwable ex) {
            LOG.warn("Could not load the Workbook with Excel 2007 or later");
            // Try to load with Excel 2003
            try {
                workbook = new HSSFWorkbook(is);
            } catch (IOException ex1) {
                LOG.error("Could not try to load the Workbook with Excel 2003", ex1);
            }
        }

        return workbook;
    }
    
    /**
     * Check struture of file excel.
     * @param workbook A instance of workbook need check structure.
     * @param struct A String array which contains headers of Excel file.
     * @return 
     * <ul>
     *   <li>If valid structure return true.<li/>
     *   <li>If invalid structure return false.<li/>
     * </ul>
     */
    public static Boolean checkExcelStructure(final Workbook workbook, String...struct) {
        try {
            if (workbook != null) {
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator(); 

                if (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();

                    int cellIndex = 0;
                    Cell cell = null;
                    String cellText;
                    while (cellIterator.hasNext()) {                        
                        if (cellIndex == struct.length) {
                            break;
                        }
                        
                        // Check value off cell and title.
                        cell = cellIterator.next();
                        cellText = cell.getStringCellValue().trim();
                        if (!struct[cellIndex].trim().equalsIgnoreCase(cellText)) {
                            break;
                        }

                        cellIndex++;
                    }

                    if (cellIndex == struct.length) {
                        return true;
                    }
                }
            }            
            
        } catch (Exception ex) {
           LOG.warn("Could not check exel structure", ex);
        }

        return false;
    }
}
