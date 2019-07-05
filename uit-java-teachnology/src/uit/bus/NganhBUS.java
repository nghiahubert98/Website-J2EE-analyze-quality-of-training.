package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.NganhDAO;
import uit.model.Nganh;
import uit.modelview.FilterNganhViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;

public class NganhBUS {
    private static NganhBUS instance;
    private NganhBUS() {}
    public static NganhBUS getInstance() {
        if (instance == null) {
            instance = new NganhBUS();
        }
        
        return instance;
    }

    public IndexModelView<Nganh> getIndexViewModel(int page, final FilterNganhViewModel filter) throws ClassNotFoundException, SQLException, IOException {
        if (page < Constants.DEFAULT_PAGE_NUMBER) {
            page = Constants.DEFAULT_PAGE_NUMBER;
        }
        
        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;    
        
        // Get data from database.
        List<Nganh> nganhList = null;
        int totalItems = 0;
        if (filter.hasFilter()) {
            nganhList = NganhDAO.getInstance().search(skip, limit, filter);
            totalItems = NganhDAO.getInstance().countWithFilter(filter);
        } else {
            nganhList = NganhDAO.getInstance().getList(skip, limit);
            totalItems = NganhDAO.getInstance().count();
        }
               
        // Initialize a instance of IndexModelView.
        IndexModelView<Nganh> result = new IndexModelView<Nganh>(page, totalItems, limit, nganhList);
          
        return result;
    }

    public int create(final Nganh nganh) throws ClassNotFoundException, SQLException, IOException {
        return NganhDAO.getInstance().create(nganh);
    }
    
    public int delete(final int id) throws ClassNotFoundException, SQLException, IOException {
        return NganhDAO.getInstance().delete(id);
    }
    
    public int update(final Nganh nganh) throws ClassNotFoundException, SQLException, IOException {
        return NganhDAO.getInstance().edit(nganh);
    }
    
    public int getTotalPage() throws ClassNotFoundException, SQLException, IOException {
        int count = NganhDAO.getInstance().count();
        return (int) Math.ceil((double)count / Constants.NUMBER_ITEMS_PER_PAGE);
    }
    
    public Nganh findById(final int id) throws ClassNotFoundException, SQLException, IOException {
        return NganhDAO.getInstance().findById(id);
    }
    
    public int insertMany(final List<Nganh> insertMany) throws ClassNotFoundException, SQLException, IOException {
        return NganhDAO.getInstance().insertMany(insertMany);
    }
    
    public List<Nganh> findAll() throws ClassNotFoundException, SQLException, IOException {
        return NganhDAO.getInstance().findAll();
    }
    
    public Nganh findByCode(final String code) throws ClassNotFoundException, SQLException, IOException {
        return NganhDAO.getInstance().findByCode(code);
    }
}
