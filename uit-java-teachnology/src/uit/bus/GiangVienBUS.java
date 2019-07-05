package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.GiangVienDAO;
import uit.model.GiangVien;
import uit.modelview.FilterGiangVienViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;

public class GiangVienBUS {
    private static GiangVienBUS instance;
    private GiangVienBUS() {
    }
    public static GiangVienBUS getInstance() {
        if (instance == null)
            instance = new GiangVienBUS();
        return instance;
    }

    public IndexModelView<GiangVien> getIndexViewModel(int page, final FilterGiangVienViewModel filter) throws ClassNotFoundException, SQLException, IOException {
        if (page < Constants.DEFAULT_PAGE_NUMBER) {
            page = Constants.DEFAULT_PAGE_NUMBER;
        }        
        
     // Calculate skip and limit.
        int skip = (page -Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;
        
     // Get data from database.
        List<GiangVien> GVList = null;
        int totalItems = 0;
        if (filter.hasFilter()) {
            GVList = GiangVienDAO.getInstance().search(skip, limit, filter);
            totalItems = GiangVienDAO.getInstance().countWithFilter(filter);
        } else {
            GVList = GiangVienDAO.getInstance().getList(skip, limit);
            totalItems = GiangVienDAO.getInstance().count();
        }
        
        IndexModelView<GiangVien> result = new IndexModelView<>(page, totalItems, limit, GVList);
        return result;
    }
    
    public IndexModelView<GiangVien> findForDropDown(int page)
            throws SQLException, ClassNotFoundException, IOException {
        if (page < Constants.DEFAULT_PAGE_NUMBER) {
            page = Constants.DEFAULT_PAGE_NUMBER;
        }

        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;

        // Get data from database.
        List<GiangVien> gvList = GiangVienDAO.getInstance().findForDropDown(limit, skip);
        int totalItems = GiangVienDAO.getInstance().count();

        IndexModelView<GiangVien> result = new IndexModelView<>(page, totalItems, limit, gvList);
        return result;
    }

    public int create(final GiangVien giangvien) throws ClassNotFoundException, SQLException, IOException {
        return GiangVienDAO.getInstance().create(giangvien);
    }
    
    public int delete(final int id) throws ClassNotFoundException, SQLException, IOException {
        return GiangVienDAO.getInstance().delete(id);
    }
    
    public int update(final GiangVien giangvien) throws ClassNotFoundException, SQLException, IOException {
        return GiangVienDAO.getInstance().edit( giangvien);
    }
    
    public int getTotalPage() throws ClassNotFoundException, SQLException, IOException {
        int count = GiangVienDAO.getInstance().count();
        return (int) Math.ceil((double)count / Constants.NUMBER_ITEMS_PER_PAGE);
    }
    
    public GiangVien findById(final int id) throws ClassNotFoundException, SQLException, IOException {
        return GiangVienDAO.getInstance().findById(id);
    }
    
    public int insertMany(final List<GiangVien> insertMany) throws ClassNotFoundException, SQLException, IOException {
        return GiangVienDAO.getInstance().insertMany(insertMany);
    }
    
    public GiangVien findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        return GiangVienDAO.getInstance().findByCode(code);
    }
    
    public GiangVien findByEmail(final String email) throws SQLException, ClassNotFoundException, IOException {
        return GiangVienDAO.getInstance().findByEmail(email);
    } 
}
