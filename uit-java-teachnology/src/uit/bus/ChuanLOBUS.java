package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.ChuanLODAO;
import uit.model.ChuanLO;
import uit.modelview.IndexModelView;
import uit.utils.Constants;

public class ChuanLOBUS {
    private ChuanLOBUS() {};
    private static ChuanLOBUS instance;
    
    // This method get an instance of ChuanLOBUS
    public static ChuanLOBUS getInstance() {
        if(instance == null) {
            instance = new ChuanLOBUS();
        }
        return instance;
    }
    
    public int getTotalPage(int id) throws ClassNotFoundException, SQLException, IOException {
        int count = ChuanLODAO.getInstance().count(id);
        return (int) Math.ceil((double)count / Constants.NUMBER_ITEMS_PER_PAGE);
    }
    
    public int getTotalPage() throws ClassNotFoundException, SQLException, IOException {
        int count = ChuanLODAO.getInstance().count();
        return (int) Math.ceil((double)count / Constants.NUMBER_ITEMS_PER_PAGE);
    }
    
    public IndexModelView<ChuanLO> getIndexViewModel(int nganhid, int page) throws ClassNotFoundException, SQLException, IOException {
        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;
        
        // Get data from database.
        List<ChuanLO> listLO = ChuanLODAO.getInstance().getListChuanLOByNganh(nganhid, skip, limit);
        int totalItems = ChuanLODAO.getInstance().count(nganhid);
               
        // Initialize a instance of IndexModelView.
        IndexModelView<ChuanLO> result = new IndexModelView<ChuanLO>(nganhid, page, totalItems, limit, listLO);
          
        return result;
    }
    
    public IndexModelView<ChuanLO> getIndexViewAllModel(int page) throws ClassNotFoundException, SQLException, IOException {
        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;
        
        // Get data from database.
        List<ChuanLO> listLO = ChuanLODAO.getInstance().getListChuanLO(skip, limit);
        int totalItems = ChuanLODAO.getInstance().count();
               
        // Initialize a instance of IndexModelView.
        IndexModelView<ChuanLO> result = new IndexModelView<ChuanLO>(page, totalItems, limit, listLO);
          
        return result;
    }
    
    public IndexModelView<ChuanLO> getLONotInNganhModel(int nganhid) throws ClassNotFoundException, SQLException, IOException {
        // Get data from database.
        List<ChuanLO> listLO = ChuanLODAO.getInstance().getLONotInNganh(nganhid);
               
        // Initialize a instance of IndexModelView.
        IndexModelView<ChuanLO> result = new IndexModelView<ChuanLO>(listLO);
          
        return result;
    }
    
    public List<ChuanLO> getLONotInNganh(int nganhid) throws ClassNotFoundException, SQLException, IOException {
        return ChuanLODAO.getInstance().getLONotInNganh(nganhid);
    }
    
    public int create(final ChuanLO chuanlo) throws ClassNotFoundException, SQLException, IOException {
        return ChuanLODAO.getInstance().create(chuanlo);
    }
    
    public int createLOOfNganh(final int nganhid, final int loid, final String nienkhoa) throws ClassNotFoundException, SQLException, IOException {
        return ChuanLODAO.getInstance().createLOOfNganh(nganhid, loid, nienkhoa);
    }
    
    public int update(final ChuanLO chuanlo) throws ClassNotFoundException, SQLException, IOException {
        return ChuanLODAO.getInstance().edit(chuanlo);
    }
    
    //Delete LO
    public int delete(final int id) throws ClassNotFoundException, SQLException, IOException {
        return ChuanLODAO.getInstance().delete(id);
    }
    //Delete LO of Nganh
    public int deleteLOOfNganh(int id_nganh, int id_lo) throws ClassNotFoundException, SQLException, IOException {
        return ChuanLODAO.getInstance().deleteLOOfNganh(id_nganh, id_lo);
    }
    
    public ChuanLO findById(final int id) throws ClassNotFoundException, SQLException, IOException {
        return ChuanLODAO.getInstance().findById(id);
    }
    
    public int insertMany(final List<ChuanLO> insertList) throws ClassNotFoundException, SQLException, IOException {
        return ChuanLODAO.getInstance().insertMany(insertList);
    }
}
