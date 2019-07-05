package uit.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uit.dao.frame.CreateFrame;
import uit.dao.frame.DeleteFrame;
import uit.dao.frame.EditFrame;
import uit.model.CauHoi;

public class CauHoiDAO implements CreateFrame<CauHoi>, DeleteFrame<CauHoi>, EditFrame<CauHoi> {
    private CauHoiDAO() {}
    private static CauHoiDAO instance;
    public static CauHoiDAO getInstance() {
        if (instance == null)
            instance = new CauHoiDAO();
        return instance;
    }
    
    @Override
    public int edit(CauHoi q) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE CAU_HOI SET sothutu = ?, diemtoida = ? WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, q.getOrder());
            stmt.setFloat(2, q.getMaxPoint());
            stmt.setInt(3, q.getId());
            
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }
    
    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException, IOException {
        String sql = "DELETE FROM CAU_HOI WHERE id = ?";
        String sqlRef1 = "DELETE FROM DIEM WHERE cauhoi_id = ?";
        String sqlRef2 = "DELETE FROM CAUHOI_G WHERE cauhoi_id = ?";
        
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sqlRef1);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            
            stmt = conn.prepareStatement(sqlRef2);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            return stmt.executeUpdate();
        } catch (SQLException se) {
            conn.rollback();
            return 0;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }
    
    public List<CauHoi> findByLopId(int lopId) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT lop.id AS 'lopId', giang_vien.ma_gv,CONCAT(hoc_ky.loai, '(', hoc_ky.nam_bat_dau, '-', hoc_ky.nam_ket_thuc, ')') AS 'hocky', "
                    + "dethi.loai_de, lop.ma_lop, cau_hoi.*, chuan_g.id as gId, chuan_g.ma_g as maG, chuan_g.mota as mota "
                    + "FROM lop INNER JOIN giang_vien ON giang_vien.id = lop.gv_id "
                    + "INNER JOIN hoc_ky ON hoc_ky.id = lop.hocky_id INNER JOIN dethi ON lop.id = dethi.id_lop "
                    + "LEFT JOIN cau_hoi ON cau_hoi.dethi_id = dethi.id LEFT JOIN cauhoi_g ON cau_hoi.id = cauhoi_g.cauhoi_id "
                    + "LEFT JOIN chuan_g ON chuan_g.id = cauhoi_g.g_id WHERE lop.id = ? ORDER BY cau_hoi.sothutu ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, lopId);
            
            ResultSet rs = stmt.executeQuery();
            List<CauHoi> result = new ArrayList<CauHoi>();
            while (rs.next()) {
               CauHoi ch = new CauHoi(
                   rs.getInt("id"),
                   rs.getInt("sothutu"), 
                   rs.getFloat("diemtoida"),
                   rs.getInt("dethi_id"),
                   rs.getString("ma_gv")
               );    
               ch.setLopId(rs.getInt("lopId"));
               ch.setgID(rs.getInt("gId"));
               ch.setDethiType(rs.getBoolean("loai_de"));
               ch.setMaG(rs.getString("maG"));
               ch.setMotaG(rs.getString("mota"));
               ch.setHocKyName(rs.getString("hocky"));
               ch.setMalop(rs.getString("ma_lop"));
               
               result.add(ch);
            }

            return result;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    public List<CauHoi> findByDethiId(int dethiId) throws SQLException, ClassNotFoundException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM cau_hoi WHERE cau_hoi.dethi_id = ? ORDER BY sothutu ASC";
        
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dethiId);
            
            ResultSet rs = stmt.executeQuery();
            List<CauHoi> result = new ArrayList<CauHoi>();
            while (rs.next()) {
               CauHoi ch = new CauHoi(
                   rs.getInt("id"),
                   rs.getInt("sothutu"), 
                   rs.getFloat("diemtoida"),
                   rs.getInt("dethi_id")
               );    
               result.add(ch);
            }

            return result;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    @Override
    public int create(CauHoi cauhoi) throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO `cau_hoi`(`sothutu`,`diemtoida`, `dethi_id`) VALUES (?, ?, ?)";

        try {
            String generatedColumns[] = { "id" };
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql, generatedColumns);
            stmt.setInt(1, cauhoi.getOrder());
            stmt.setFloat(2, cauhoi.getMaxPoint());
            stmt.setInt(3, cauhoi.getDethiId());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cauhoi.setId(rs.getInt(1));
                return 1;
            } else {
                return 0;
            }
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    public void setGForCauHoi(int cauhoiId, String... gIds) throws SQLException, ClassNotFoundException, IOException {
        String removeGSQL = "DELETE FROM cauhoi_g WHERE cauhoi_id = ?";
        String addGSQL = "INSERT INTO `cauhoi_g` ( `cauhoi_id`, `g_id`) VALUES (?, ?)";
        
        int batchSize = 100;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {            
            // Delete all cauhoi_g which has cauhoi_id = parameter cauhoiId.
            conn = DBConnectionProvider.getInstance().getConnection();
            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement(removeGSQL);
            stmt.setInt(1, cauhoiId);
            stmt.executeUpdate();
            stmt.close();
            
            // Insert into cauHoiG
            stmt = conn.prepareStatement(addGSQL);
            int count = 0;
            for (String gId : gIds) {
                stmt.setInt(1, cauhoiId);
                stmt.setInt(2, Integer.parseInt(gId));
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
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    public int countByDethiId(int dethiId) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS `count` FROM cau_hoi where dethi_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dethiId);
            
            ResultSet rs = stmt.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt("count");
            }
            
            return count;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
}