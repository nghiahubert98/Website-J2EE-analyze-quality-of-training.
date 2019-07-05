package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uit.dao.CauHoiDAO;
import uit.model.CauHoi;
import uit.model.SinhVien;

public class CauHoiBUS {
    private static CauHoiBUS instance;
    public static CauHoiBUS getInstance() {
        if (instance == null) {
            instance = new CauHoiBUS();
        }
        return instance;
    }
    private CauHoiBUS() {}
    
    public List<CauHoi> findByDethiId(int dethiId) throws SQLException, ClassNotFoundException, IOException {
        return CauHoiDAO.getInstance().findByDethiId(dethiId);
    }
    
    public int countByDethiId(int dethiId) throws ClassNotFoundException, SQLException, IOException {
        return CauHoiDAO.getInstance().countByDethiId(dethiId);
    }
    
    public int create(CauHoi cauhoi) throws ClassNotFoundException, SQLException, IOException {
        return CauHoiDAO.getInstance().create(cauhoi);
    }
    
    public void create(CauHoi cauhoi, String... gIds) throws ClassNotFoundException, SQLException, IOException {
        CauHoiDAO.getInstance().create(cauhoi);    
        CauHoiDAO.getInstance().setGForCauHoi(cauhoi.getId(), gIds);
    }
    
    public void updateG(CauHoi cauhoi, String... gIds) throws ClassNotFoundException, SQLException, IOException {
        CauHoiDAO.getInstance().setGForCauHoi(cauhoi.getId(), gIds);
    }
    
    public int update(CauHoi q) throws ClassNotFoundException, SQLException, IOException {
        return CauHoiDAO.getInstance().edit(q);
    }
    
    public int delete(int id) throws ClassNotFoundException, SQLException, IOException {
        return CauHoiDAO.getInstance().delete(id);
    }
    public List<CauHoi> findByLopId(int lopId) throws ClassNotFoundException, SQLException, IOException {
        return CauHoiDAO.getInstance().findByLopId(lopId);
    }
    
    public XSSFWorkbook getImportDiemWorkbook(final int dethiId, final int lopId) throws ClassNotFoundException, SQLException, IOException {
        List<CauHoi> cauHoi = CauHoiBUS.getInstance().findByDethiId(dethiId);
        List<SinhVien> svList = SinhVienBUS.getInstance().findByLopId(lopId);
        int svListSize = svList.size();
        int cauHoiListSize = cauHoi.size();
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("ImportExcel");
        Row row = sheet.createRow(0);
        Cell cell = null;
        cell = row.createCell(0);
        cell.setCellValue("MSSV");
        
        for (int j = 1; j <= cauHoiListSize; j++) {
            cell = row.createCell(j);
            cell.setCellValue("Câu hỏi " + cauHoi.get(j -1).getOrder());
        }
        
        for (int i = 0; i< svListSize; i++) {
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(svList.get(i).getMssv());
        }
              
        return workbook;
    }
}
