package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.HocKyDAO;
import uit.model.HocKy;
import uit.modelview.FilterHocKyViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;

public class HocKyBUS {
    private HocKyBUS() {
    }

    private static HocKyBUS instance;

    public static HocKyBUS getInstance() {
        if (instance == null) {
            instance = new HocKyBUS();
        }

        return instance;
    }

    public IndexModelView<HocKy> getIndexViewModel(int page, final FilterHocKyViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        if (page < Constants.DEFAULT_PAGE_NUMBER) {
            page = Constants.DEFAULT_PAGE_NUMBER;
        }

        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;

        // Get data from database.
        List<HocKy> HocKyList = null;
        int totalItems = 0;
        if (filter.hasFilter()) {
            HocKyList = HocKyDAO.getInstance().search(skip, limit, filter);
            totalItems = HocKyDAO.getInstance().countWithFilter(filter);
        } else {
            HocKyList = HocKyDAO.getInstance().getList(skip, limit);
            totalItems = HocKyDAO.getInstance().count();
        }

        // Initialize a instance of IndexModelView.
        IndexModelView<HocKy> result = new IndexModelView<HocKy>(page, totalItems, limit, HocKyList);

        return result;
    }
    
    public IndexModelView<HocKy> findForDropDown(int page) throws SQLException, ClassNotFoundException, IOException {
        if (page < Constants.DEFAULT_PAGE_NUMBER) {
            page = Constants.DEFAULT_PAGE_NUMBER;
        }

        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;

        // Get data from database.
        List<HocKy> hkList = HocKyDAO.getInstance().findForDropDown(limit, skip);
        int totalItems = HocKyDAO.getInstance().count();

        IndexModelView<HocKy> result = new IndexModelView<>(page, totalItems, limit, hkList);
        return result;
    }

    public int create(final HocKy HocKy) throws ClassNotFoundException, SQLException, IOException {
        return HocKyDAO.getInstance().create(HocKy);
    }

    public int delete(final int id) throws ClassNotFoundException, SQLException, IOException {
        return HocKyDAO.getInstance().delete(id);
    }

    public int update(final HocKy HocKy) throws ClassNotFoundException, SQLException, IOException {
        return HocKyDAO.getInstance().edit(HocKy);
    }

    public int getTotalPage() throws ClassNotFoundException, SQLException, IOException {
        int count = HocKyDAO.getInstance().count();
        return (int) Math.ceil((double) count / Constants.NUMBER_ITEMS_PER_PAGE);
    }

    public HocKy findById(final int id) throws ClassNotFoundException, SQLException, IOException {
        return HocKyDAO.getInstance().findById(id);
    }

    public int insertMany(final List<HocKy> insertMany) throws ClassNotFoundException, SQLException, IOException {
        return HocKyDAO.getInstance().insertMany(insertMany);
    }
}