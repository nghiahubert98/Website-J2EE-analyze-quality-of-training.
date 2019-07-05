package uit.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uit.model.Diem;

public class DiemDAO {
    private DiemDAO() {}
    private static DiemDAO instance;
    public static DiemDAO getInstance() {
        if (instance == null) {
            instance = new DiemDAO();
        }
        return instance;
    }
    
    private void closeConnect(final Connection conn, final PreparedStatement stmt) throws SQLException {
        if (stmt != null && !stmt.isClosed()) {
            stmt.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
    
    public List<Diem> findTablePointByDethiId(int dethiId) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT diem.diem, cau_hoi.sothutu, sinh_vien.mssv, sinh_vien.ten FROM diem "
                        + "INNER JOIN cau_hoi ON cau_hoi.id = diem.cauhoi_id INNER JOIN sinh_vien ON sinh_vien.id = diem.id_sv "
                        + "INNER JOIN dethi ON dethi.id = cau_hoi.dethi_id WHERE dethi.id = ? ORDER BY mssv asc, sothutu asc";
        Connection conn = null;
        PreparedStatement stmt = null;        
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dethiId);            
            
            ResultSet rs = stmt.executeQuery();
            
            List<Diem> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Diem(
                    rs.getFloat("diem"),
                    rs.getInt("sothutu"),
                    rs.getString("mssv"),
                    rs.getString("ten")
                ));
            }            
            return result;
        } finally {
            closeConnect(conn, stmt);
        }
    }
    
    public void insertMany(int dethiId, List<Diem> insertList) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO `java_uit`.`diem`(`cauhoi_id`, `id_sv`, `diem`) VALUES(?, ?, ?)";
        String dropSQL = "DELETE diem FROM diem INNER JOIN cau_hoi ON diem.cauhoi_id = cau_hoi.id WHERE cau_hoi.dethi_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;       
        int batchSize = 100;
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            conn.setAutoCommit(false);
           
            // Drop old point.
            stmt = conn.prepareStatement(dropSQL);
            stmt.setInt(1, dethiId);
            stmt.executeUpdate();
            stmt.close();
            
            int count = 0;
            stmt = conn.prepareStatement(sql);
            for (Diem diem : insertList) {
                stmt.setInt(1, diem.getCauHoiId());
                stmt.setInt(2, diem.getSvId());
                stmt.setFloat(3, diem.getDiem());
                stmt.addBatch();

                if (++count % batchSize == 0) {
                    stmt.executeBatch();
                }
            }
            stmt.executeBatch();
            
            conn.commit();
        } catch (Throwable ex) {
            conn.rollback();
            throw ex;
            
        } finally {
            closeConnect(conn, stmt);
        }
    }
    
    public boolean checkRefCauhoi(int qid) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM DIEM WHERE cauhoi_id = ?";
        
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, qid);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            return false;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }
    
    public Float getDiemGk(int idSv, int idMon) throws ClassNotFoundException, SQLException, IOException {
        String sql = "use java_uit; "+
        		"Select sum(DiemGK) as DiemGK "+
        		"from ( SELECT mon_hoc.id as monhoc, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, "+
        			"dethi.loai_de as loaide, diem.diem as DiemGK "+
        			"FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id "+ 
        						"left join dethi on cau_hoi.dethi_id = dethi.id "+
        						"left join sinh_vien on diem.id_sv = sinh_vien.id "+
        						"left join lop on dethi.id_lop = lop.id "+
        						"left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id "+
        	                    "inner join mon_hoc on lop.id_mon = mon_hoc.id "+
        			 "group by sinhvien, loaide, sothutu "+
        		") as TI_LE_CH "+
        	"where sinhvien = ? and loaide = 0 and monhoc = ? "+
        	"group by sinhvien, loaide ";
        
        Connection conn = null;
        PreparedStatement stmt = null;        
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSv);
            stmt.setInt(2, idMon);
            
            ResultSet rs = stmt.executeQuery();
            
            Float result = null;
            while (rs.next()) {
                result = rs.getFloat("DiemGK");
            }
            
            return result;
            
        } finally {
            closeConnect(conn, stmt);
        }
    }
    
    public Float getDiemCk (int idSv, int idMon) throws ClassNotFoundException, SQLException, IOException {
        String sql = "use java_uit; "+
        "Select sum(DiemCK) as DiemCK "+
    	"from (SELECT  mon_hoc.id as monhoc, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, "+
    		"dethi.loai_de as loaide, diem.diem as DiemCK "+
    		"FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id "+ 
    					"left join dethi on cau_hoi.dethi_id = dethi.id "+
    					"left join sinh_vien on diem.id_sv = sinh_vien.id "+
    					"left join lop on dethi.id_lop = lop.id "+
    					"left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id "+
                        "inner join mon_hoc on lop.id_mon = mon_hoc.id "+
    		 "group by sinhvien, loaide, sothutu "+
    	") as TI_LE_CH "+
    "where sinhvien = ? and loaide = 1 and monhoc = ? "+
    "group by sinhvien, loaide ";
        
        Connection conn = null;
        PreparedStatement stmt = null;        
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSv);
            stmt.setInt(2, idMon);
            
            ResultSet rs = stmt.executeQuery();
            
            Float result = null;
            while (rs.next()) {
                result = rs.getFloat("DiemCK");
            }
            
            return result;
            
        } finally {
            closeConnect(conn, stmt);
        }
    }
    
    public Map<Integer, Float> getDiemTb(int idMon) throws ClassNotFoundException, SQLException, IOException {
        String sql = "use java_uit; "+
        		"Select sinhvien, round(((sum(DiemGK_Va_CK) + diemqt)/3), 2) as DiemTB " + 
        		"		from (SELECT mon_hoc.id as monhoc, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, " + 
        		"			dethi.loai_de as loaide, diem.diem as DiemGK_Va_CK, sinhvien_Lop.diem_qt as diemqt " + 
        		"			FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id " + 
        		"						left join dethi on cau_hoi.dethi_id = dethi.id " + 
        		"						left join sinh_vien on diem.id_sv = sinh_vien.id " + 
        		"						left join sinhvien_Lop on sinh_vien.id = sinhvien_Lop.lop_id " + 
        		"                        left join lop on dethi.id_lop = lop.id " + 
        		"						left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id " + 
        		"                        inner join mon_hoc on lop.id_mon = mon_hoc.id " + 
        		"             group by sinhvien, loaide, sothutu " + 
        		"		) as TI_LE_CH " + 
        		"        where monhoc = ? " + 
        		" group by sinhvien ";
        
        Connection conn = null;
        PreparedStatement stmt = null;        
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            
            
            stmt.setInt(1, idMon);
            
            ResultSet rs = stmt.executeQuery();
            
            Map<Integer, Float> result = new HashMap<>();
            while (rs.next()) {
                result.put(rs.getInt("sinhvien"), rs.getFloat("DiemTB"));
            }
            
            return result;
            
        } finally {
            closeConnect(conn, stmt);
        }
    }
}
