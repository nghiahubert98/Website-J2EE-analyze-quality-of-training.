package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.MonHocDAO;
import uit.model.MonHoc;
import uit.modelview.FilterMonHocViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;

public class MonHocBUS {
    private MonHocBUS() {
    }

    private static MonHocBUS instance;

    public static MonHocBUS getInstance() {
        if (instance == null) {
            instance = new MonHocBUS();
        }

        return instance;
    }

    public IndexModelView<MonHoc> getIndexViewModel(int page, final FilterMonHocViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        if (page < Constants.DEFAULT_PAGE_NUMBER) {
            page = Constants.DEFAULT_PAGE_NUMBER;
        }

        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;

        // Get data from database.
        List<MonHoc> MonHocList = null;
        int totalItems = 0;
        if (filter.hasFilter()) {
            MonHocList = MonHocDAO.getInstance().search(skip, limit, filter);
            totalItems = MonHocDAO.getInstance().countWithFilter(filter);
        } else {
            MonHocList = MonHocDAO.getInstance().getList(skip, limit);
            totalItems = MonHocDAO.getInstance().count();
        }

        // Initialize a instance of IndexModelView.
        IndexModelView<MonHoc> result = new IndexModelView<MonHoc>(page, totalItems, limit, MonHocList);

        return result;
    }
    
    public IndexModelView<MonHoc> getMonHocSV(int idSv, int page, final FilterMonHocViewModel filter) 
    		throws ClassNotFoundException, SQLException, IOException {
    	if (page < Constants.DEFAULT_PAGE_NUMBER) {
            page = Constants.DEFAULT_PAGE_NUMBER;
        }

        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;
        
        // Get data from database.
        List<MonHoc> MonHocList = null;
        int totalItems = 0;
        
        if (filter.hasFilter()) {
        	
	        MonHocList = MonHocDAO.getInstance().getMonHocSvWithFilter(idSv, skip, limit, filter);
	        totalItems = MonHocDAO.getInstance().countMonHocSvWithFilter(idSv, filter);
	        
	    } else {
	    	
	        MonHocList = MonHocDAO.getInstance().getMonHocSv(idSv, skip, limit);
	        totalItems = MonHocDAO.getInstance().countMonHocSv(idSv);
	        
	    }
        
        // Initialize a instance of IndexModelView.
        IndexModelView<MonHoc> result = new IndexModelView<MonHoc>(page, totalItems, limit, MonHocList);

        return result;
        
    }
    
    public IndexModelView<MonHoc> findForDropDown(int page) throws SQLException, ClassNotFoundException, IOException {
        if (page < Constants.DEFAULT_PAGE_NUMBER) {
            page = Constants.DEFAULT_PAGE_NUMBER;
        }

        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;

        // Get data from database.
        List<MonHoc> mhList = MonHocDAO.getInstance().findForDropDown(limit, skip);
        int totalItems = MonHocDAO.getInstance().count();

        IndexModelView<MonHoc> result = new IndexModelView<>(page, totalItems, limit, mhList);
        return result;
    }

    public int create(final MonHoc MonHoc) throws ClassNotFoundException, SQLException, IOException {
        return MonHocDAO.getInstance().create(MonHoc);
    }

    public int delete(final int id) throws ClassNotFoundException, SQLException, IOException {
        return MonHocDAO.getInstance().delete(id);
    }

    public int update(final MonHoc MonHoc) throws ClassNotFoundException, SQLException, IOException {
        return MonHocDAO.getInstance().edit(MonHoc);
    }

    public int getTotalPage() throws ClassNotFoundException, SQLException, IOException {
        int count = MonHocDAO.getInstance().count();
        return (int) Math.ceil((double) count / Constants.NUMBER_ITEMS_PER_PAGE);
    }

    public MonHoc findById(final int id) throws ClassNotFoundException, SQLException, IOException {
        return MonHocDAO.getInstance().findById(id);
    }

    public int insertMany(final List<MonHoc> insertMany) throws ClassNotFoundException, SQLException, IOException {
        return MonHocDAO.getInstance().insertMany(insertMany);
    }

    public MonHoc findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        return MonHocDAO.getInstance().findByCode(code);
    }
}
