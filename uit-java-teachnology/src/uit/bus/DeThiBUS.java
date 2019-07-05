package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.DeThiDAO;
import uit.model.DeThi;

public class DeThiBUS {
    private DeThiBUS() {}
    private static DeThiBUS instance;
    public static DeThiBUS geInstance() {
        if (instance == null) {
            instance = new DeThiBUS();
        }
        return instance;
    }
    
    public List<DeThi> findByLopId(int lopId) throws ClassNotFoundException, SQLException, IOException {
        return DeThiDAO.getInstance().findByLopId(lopId);
    }
    
    public DeThi findOneByTypeAndlopId(boolean type, int lopId) throws ClassNotFoundException, SQLException, IOException {
        return DeThiDAO.getInstance().findOneByTypeAndlopId(type, lopId);
    }
    
    public int create(DeThi dt) throws ClassNotFoundException, SQLException, IOException {
        return DeThiDAO.getInstance().create(dt);
    }
    
    public int insertMany(List<DeThi> listDethi) throws ClassNotFoundException, SQLException, IOException {
        return DeThiDAO.getInstance().insertMany(listDethi);
    }
    
}
