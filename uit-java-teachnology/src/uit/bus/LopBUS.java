package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.LopDAO;
import uit.model.Lop;
import uit.modelview.FilterLopViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;

public class LopBUS {
    private LopBUS() {
    }

    private static LopBUS instance;

    public static LopBUS getInstance() {
        if (instance == null) {
            instance = new LopBUS();
        }
        return instance;
    }

    public IndexModelView<Lop> getIndexViewModel(int page, final FilterLopViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;

        List<Lop> LopList = null;
        int totalItems = 0;
        if (filter.hasFilter()) {
            LopList = LopDAO.getInstance().search(skip, limit, filter);
            totalItems = LopDAO.getInstance().countWithFilter(filter);
        } else {
            LopList = LopDAO.getInstance().getList(skip, limit);
            totalItems = LopDAO.getInstance().count();
        }

        // Initialize a instance of IndexModelView.
        IndexModelView<Lop> result = new IndexModelView<Lop>(page, totalItems, limit, LopList);

        return result;
    }

    public int create(final Lop Lop) throws ClassNotFoundException, SQLException, IOException {
        return LopDAO.getInstance().create(Lop);
    }

    public int delete(final int id) throws ClassNotFoundException, SQLException, IOException {
        return LopDAO.getInstance().delete(id);
    }

    public int update(final Lop Lop) throws ClassNotFoundException, SQLException, IOException {
        return LopDAO.getInstance().edit(Lop);
    }

    public int getTotalPage() throws ClassNotFoundException, SQLException, IOException {
        int count = LopDAO.getInstance().count();
        return (int) Math.ceil((double) count / Constants.NUMBER_ITEMS_PER_PAGE);
    }

    public Lop findById(final int id) throws ClassNotFoundException, SQLException, IOException {
        return LopDAO.getInstance().findById(id);
    }

    public Lop findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        return LopDAO.getInstance().findByCode(code);
    }

    public int insertMany(final List<Lop> insertMany) throws ClassNotFoundException, SQLException, IOException {
        return LopDAO.getInstance().insertMany(insertMany);
    }
    
    public int getIdMon(int id) throws ClassNotFoundException, SQLException, IOException {
    	return LopDAO.getInstance().getIdMon(id);
    }
}
