package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import uit.dao.DiemDAO;
import uit.model.CauHoi;
import uit.model.Diem;
import uit.model.SinhVien;

public class DiemBUS {
    private DiemBUS() {
    }
    private static DiemBUS instance;
    public static DiemBUS getInstance() {
        if (instance == null) {
            instance = new DiemBUS();
        }
        return instance;
    }

    public List<Diem> findTablePointByDethiId(int dethiId) throws ClassNotFoundException, SQLException, IOException {
        return DiemDAO.getInstance().findTablePointByDethiId(dethiId);
    }
    
    public boolean checkRefCauhoi(int qid) throws ClassNotFoundException, SQLException, IOException {
        return DiemDAO.getInstance().checkRefCauhoi(qid);
    }

    private List<Diem> convertExcelRowToListDiem(final Row row, final List<SinhVien> svList, final List<CauHoi> cauHoiList)
            throws Exception {
        Iterator<Cell> cellIterator = null;
        Cell cell = null;
        List<Diem> result = new ArrayList<>();

        cellIterator = row.cellIterator();
        Optional<SinhVien> sv = null;

        // Firt cell is MSSV
        cell = cellIterator.next();
        final String mssv = cell.getStringCellValue();
        sv = svList.stream().filter(x -> x.getMssv().equals(mssv)).findFirst();
        if (!sv.isPresent()) {
            throw new Exception("File invalid mssv " + mssv);
        }

        // cauHoiList.size() cell point.
        for (CauHoi cauHoi : cauHoiList) {
            cell = cellIterator.next();
            result.add(new Diem(
                Float.parseFloat(cell.getNumericCellValue() + ""), 
                sv.get().getId(), 
                cauHoi.getId())
            );
        }

        return result;
    }

    private List<Diem> converWorkbookToListDiem(final Workbook workbook, final List<SinhVien> svList,
            final List<CauHoi> cauHoiList) throws Exception {
        
        if (workbook.getNumberOfSheets() > 0) {
            List<Diem> result = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            Row row = null;
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    continue;
                }
                result.addAll(convertExcelRowToListDiem(row, svList, cauHoiList));
            }

            return result;
        }

        return null;
    }

    public void importExcel(Workbook workbook, int dethiId, int lopId) throws Exception {
        List<SinhVien> svList = SinhVienBUS.getInstance().findByLopId(lopId);
        List<CauHoi> cauHoiList = CauHoiBUS.getInstance().findByDethiId(dethiId);
        List<Diem> innsertList = converWorkbookToListDiem(workbook, svList, cauHoiList);
        
        DiemDAO.getInstance().insertMany(dethiId, innsertList);
    }
}
