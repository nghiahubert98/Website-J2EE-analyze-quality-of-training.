package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.LopSinhHoatDAO;
import uit.model.LopSinhHoat;
import uit.modelview.FilterLopSinhHoatViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;

public class LopSinhHoatBUS {
    private LopSinhHoatBUS() {
    }

    private static LopSinhHoatBUS instance;

    public static LopSinhHoatBUS getInstance() {
        if (instance == null) {
            instance = new LopSinhHoatBUS();
        }
        return instance;
    }

    public IndexModelView<LopSinhHoat> getIndexViewModel(int page, final FilterLopSinhHoatViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;

        List<LopSinhHoat> LopSinhHoatList = null;
        int totalItems = 0;
        if (filter.hasFilter()) {
            LopSinhHoatList = LopSinhHoatDAO.getInstance().search(skip, limit, filter);
            totalItems = LopSinhHoatDAO.getInstance().countWithFilter(filter);
        } else {
            LopSinhHoatList = LopSinhHoatDAO.getInstance().getList(skip, limit);
            totalItems = LopSinhHoatDAO.getInstance().count();
        }

        // Initialize a instance of IndexModelView.
        IndexModelView<LopSinhHoat> result = new IndexModelView<LopSinhHoat>(page, totalItems, limit, LopSinhHoatList);

        return result;
    }

    public IndexModelView<LopSinhHoat> findForDropDown(int page)
            throws SQLException, ClassNotFoundException, IOException {
        if (page < Constants.DEFAULT_PAGE_NUMBER) {
            page = Constants.DEFAULT_PAGE_NUMBER;
        }

        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;

        // Get data from database.
        List<LopSinhHoat> lshList = LopSinhHoatDAO.getInstance().findForDropDown(limit, skip);
        int totalItems = LopSinhHoatDAO.getInstance().count();

        IndexModelView<LopSinhHoat> result = new IndexModelView<>(page, totalItems, limit, lshList);
        return result;
    }

    public int create(final LopSinhHoat lopSinhHoat) throws ClassNotFoundException, SQLException, IOException {
        return LopSinhHoatDAO.getInstance().create(lopSinhHoat);
    }

    public int delete(final int id) throws ClassNotFoundException, SQLException, IOException {
        return LopSinhHoatDAO.getInstance().delete(id);
    }

    public int update(final LopSinhHoat lopSinhHoat) throws ClassNotFoundException, SQLException, IOException {
        return LopSinhHoatDAO.getInstance().edit(lopSinhHoat);
    }

    public int getTotalPage() throws ClassNotFoundException, SQLException, IOException {
        int count = LopSinhHoatDAO.getInstance().count();
        return (int) Math.ceil((double) count / Constants.NUMBER_ITEMS_PER_PAGE);
    }

    public LopSinhHoat findById(final int id) throws ClassNotFoundException, SQLException, IOException {
        return LopSinhHoatDAO.getInstance().findById(id);
    }

    public LopSinhHoat findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        return LopSinhHoatDAO.getInstance().findByCode(code);
    }

    public int insertMany(final List<LopSinhHoat> insertMany) throws ClassNotFoundException, SQLException, IOException {
        return LopSinhHoatDAO.getInstance().insertMany(insertMany);
    }
}
