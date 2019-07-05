package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.StatisticsDAO;
import uit.model.ListLO;
import uit.model.TiLeG;

public class StatisticsBUS {

	private StatisticsBUS() {
    }

    private static StatisticsBUS instance;

    public static StatisticsBUS getInstance() {
        if (instance == null) {
            instance = new StatisticsBUS();
        }
        return instance;
    }
    
    public List<TiLeG> getGMonSv (int idSv, int idMon) throws ClassNotFoundException, SQLException, IOException {
    	return StatisticsDAO.getInstance().getGMonSv(idSv, idMon);
    }
    
    public List<TiLeG> getGSv (int idSv) throws ClassNotFoundException, SQLException, IOException {
    	return StatisticsDAO.getInstance().getGSv(idSv);
    }
    
    public List<TiLeG> getGLoaiSvGk (int idSv, int idMon) throws ClassNotFoundException, SQLException, IOException {
    	return StatisticsDAO.getInstance().getGLoaiSvGk(idSv, idMon);
    }
    
    public List<TiLeG> getGLoaiSvCk (int idSv, int idMon) throws ClassNotFoundException, SQLException, IOException {
    	return StatisticsDAO.getInstance().getGLoaiSvGk(idSv, idMon);
    }
    
    public List<ListLO> getLoMonSv (int idSv, int idMon) 
    		throws ClassNotFoundException, SQLException, IOException {
    	return StatisticsDAO.getInstance().getLoMonSv(idSv, idMon);
    }
    
    public List<ListLO> getLoSv (int idSv) 
    		throws ClassNotFoundException, SQLException, IOException {
    	return StatisticsDAO.getInstance().getLoSv(idSv);
    }
    
    public Float tiLeLoMonSv (int idSv, int idMon) 
    		throws ClassNotFoundException, SQLException, IOException {
    	return StatisticsDAO.getInstance().tiLeLoMonSv(idSv, idMon);
    }
    
    public Float tiLeLoAllSv (int idSv) 
    		throws ClassNotFoundException, SQLException, IOException {
    	return StatisticsDAO.getInstance().tiLeLoAllSv(idSv);
    }
    
    public List<TiLeG> getGLopGV (int idGv, int idLop, int idSv) 
    		throws ClassNotFoundException, SQLException, IOException {
    	return StatisticsDAO.getInstance().getGLopGV(idGv, idLop, idSv);
    }
    
    public List<TiLeG> getGLopAllGV (int idGv, int idLop) 
    		throws ClassNotFoundException, SQLException, IOException {
    	return StatisticsDAO.getInstance().getGLopAllGV(idGv, idLop);
    }
    
    public List<TiLeG> getGLopAllGVByID (int idLop) throws ClassNotFoundException, SQLException, IOException{
        return StatisticsDAO.getInstance().getGLopAllGVByID(idLop);
    }
}
