package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.SinhVienDAO;
import uit.model.SinhVien;
import uit.modelview.FilterSinhVienViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;

public class SinhVienBUS {
    private SinhVienBUS() {
    }

    private static SinhVienBUS instance;

    public static SinhVienBUS getInstance() {
        if (instance == null) {
            instance = new SinhVienBUS();
        }
        return instance;
    }

    public IndexModelView<SinhVien> getIndexViewModel(int page, final FilterSinhVienViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;

        List<SinhVien> sinhvienList = null;
        int totalItems = 0;
        if (filter.hasFilter()) {
            sinhvienList = SinhVienDAO.getInstance().search(skip, limit, filter);
            totalItems = SinhVienDAO.getInstance().countWithFilter(filter);
        } else {
            sinhvienList = SinhVienDAO.getInstance().getList(skip, limit);
            totalItems = SinhVienDAO.getInstance().count();
        }

        // Initialize a instance of IndexModelView.
        IndexModelView<SinhVien> result = new IndexModelView<SinhVien>(page, totalItems, limit, sinhvienList);

        return result;
    }
    
    public IndexModelView<SinhVien> getListSvLop(int idLop, int page, final FilterSinhVienViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;

        List<SinhVien> sinhvienList = null;
        int totalItems = 0;
        if (filter.hasFilter()) {
            sinhvienList = SinhVienDAO.getInstance().getListSvLopFilter(idLop, skip, limit, filter);
            totalItems = SinhVienDAO.getInstance().countSvLopFilter(idLop, filter);
        } else {
            sinhvienList = SinhVienDAO.getInstance().getListSvLop(idLop, skip, limit);
            totalItems = SinhVienDAO.getInstance().countSvLop(idLop);
        }

        // Initialize a instance of IndexModelView.
        IndexModelView<SinhVien> result = new IndexModelView<SinhVien>(page, totalItems, limit, sinhvienList);

        return result;
    }

    public int create(final SinhVien sinhvien) throws ClassNotFoundException, SQLException, IOException {
        return SinhVienDAO.getInstance().create(sinhvien);
    }

    public int delete(final int id) throws ClassNotFoundException, SQLException, IOException {
        return SinhVienDAO.getInstance().delete(id);
    }

    public int update(final SinhVien sinhvien) throws ClassNotFoundException, SQLException, IOException {
        return SinhVienDAO.getInstance().edit(sinhvien);
    }

    public int getTotalPage() throws ClassNotFoundException, SQLException, IOException {
        int count = SinhVienDAO.getInstance().count();
        return (int) Math.ceil((double) count / Constants.NUMBER_ITEMS_PER_PAGE);
    }

    public SinhVien findById(final int id) throws ClassNotFoundException, SQLException, IOException {
        return SinhVienDAO.getInstance().findById(id);
    }

    public SinhVien findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        return SinhVienDAO.getInstance().findByCode(code);
    }

    public int insertMany(final List<SinhVien> insertMany) throws ClassNotFoundException, SQLException, IOException {
        return SinhVienDAO.getInstance().insertMany(insertMany);
    }
    
    public List<SinhVien> findByLopId(int lopId) throws ClassNotFoundException, SQLException, IOException {
        return SinhVienDAO.getInstance().findByLopId(lopId);
    }

}
