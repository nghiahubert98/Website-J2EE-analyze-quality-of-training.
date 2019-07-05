package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.ChuanGDAO;
import uit.model.ChuanG;
import uit.modelview.IndexModelView;
import uit.utils.Constants;

public class ChuanGBUS {
    private ChuanGBUS() {};
    private static ChuanGBUS instance;
    public static ChuanGBUS getInstance() {
        if (instance == null) {
            instance = new ChuanGBUS();
        }
        return instance;
    }
    
    public int create(ChuanG g) throws ClassNotFoundException, SQLException, IOException {
        return ChuanGDAO.getInstance().create(g);
    }
    
    public int delete(int id) throws ClassNotFoundException, SQLException, IOException {
        return ChuanGDAO.getInstance().delete(id);
    }
    
    public int deleteGOfLO(int gid, int loid) throws ClassNotFoundException, SQLException, IOException {
        return ChuanGDAO.getInstance().deleteGOfLO(gid, loid);
    }
    
    public int update(ChuanG g) throws ClassNotFoundException, SQLException, IOException {
        return ChuanGDAO.getInstance().edit(g);
    }
    
    public int createGOfLO(int loid, int gid) throws ClassNotFoundException, SQLException, IOException {
        return ChuanGDAO.getInstance().createGOfLO(loid, gid);
    }
    
    public ChuanG findById(int id) throws ClassNotFoundException, SQLException, IOException {
        return ChuanGDAO.getInstance().findById(id);
    }
    
    public int getTotalPage() throws ClassNotFoundException, SQLException, IOException {
        int count = ChuanGDAO.getInstance().count();
        return (int) Math.ceil((double)count / Constants.NUMBER_ITEMS_PER_PAGE);
    }
    
    public int insertMany(List<ChuanG> insertList) throws ClassNotFoundException, SQLException, IOException {
        return ChuanGDAO.getInstance().insertMany(insertList);
    }
    //Get G of LO
    public IndexModelView<ChuanG> getGOfLOModel(int loid, int page) throws ClassNotFoundException, SQLException, IOException{
     // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;
        
        List<ChuanG> listG = ChuanGDAO.getInstance().getGOfLO(loid, skip, limit);
        int totalItems = ChuanGDAO.getInstance().count(loid);
        
        IndexModelView<ChuanG> result = new IndexModelView<ChuanG>(loid, page, totalItems, limit, listG);
        return result;
    }
    
    //Get list G By Cau hoi ID
    public IndexModelView<ChuanG> findByCauhoiId(int qid) throws ClassNotFoundException, SQLException, IOException{
        List<ChuanG> listG = ChuanGDAO.getInstance().findByCauhoiId(qid);
        
        IndexModelView<ChuanG> result = new IndexModelView<ChuanG>(listG);
        return result;
    }
    
    //Get list G
    public IndexModelView<ChuanG> getGModel(int page) throws ClassNotFoundException, SQLException, IOException{
        // Calculate skip and limit.
           int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
           int limit = Constants.NUMBER_ITEMS_PER_PAGE;
           
           List<ChuanG> listG = ChuanGDAO.getInstance().getListChuanG(skip, limit);
           int totalItems = ChuanGDAO.getInstance().count();
           
           IndexModelView<ChuanG> result = new IndexModelView<ChuanG>(page, totalItems, limit, listG);
           return result;
       }
    
    //Get G not in LO
    public IndexModelView<ChuanG> getGNotInLOModel(int loid) throws ClassNotFoundException, SQLException, IOException {
        // Get data from database.
        List<ChuanG> listG = ChuanGDAO.getInstance().getGNotInLO(loid);
               
        // Initialize a instance of IndexModelView.
        IndexModelView<ChuanG> result = new IndexModelView<ChuanG>(listG);
          
        return result;
    }
    
    public List<ChuanG> findByLopId(final int lopId) throws SQLException, ClassNotFoundException, IOException {
        return ChuanGDAO.getInstance().findByLopId(lopId);
    }
}
