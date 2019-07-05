package uit.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import uit.dao.frame.CreateFrame;
import uit.dao.frame.DeleteFrame;
import uit.dao.frame.EditFrame;
import uit.model.ChuanLO;

public class ChuanLODAO implements CreateFrame<ChuanLO>, EditFrame<ChuanLO>, DeleteFrame<ChuanLO> {

    private static ChuanLODAO instance;
    private ChuanLODAO() {};
    
    public static ChuanLODAO getInstance() {
        if (instance == null) {
            instance = new ChuanLODAO();
        }
        return instance;
    }
    
    public int deleteLOOfNganh(int id_nganh, int id_lo) throws ClassNotFoundException, SQLException, IOException {
        String sql = "DELETE FROM LO_NGANH WHERE lo_id = ? AND nganh_id = ?";
        
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_lo);
            stmt.setInt(2, id_nganh);
            
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }

            conn.close();
        }
    }
    
    @Override
    public int delete(final int id) throws ClassNotFoundException, SQLException, IOException {
        String sql = "DELETE FROM CHUAN_LO WHERE id = ?";
        String deleteGLO = "DELETE FROM G_LO WHERE id_lo = ?";
        String deleteLONganh = "DELETE FROM LO_NGANH WHERE lo_id = ?";
        
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            // Delete data refer to Chuan_LO from G_LO
            stmt = conn.prepareStatement(deleteGLO);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // Delete data refer to Chuan_LO from LO_Nganh
            stmt = conn.prepareStatement(deleteLONganh);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            
            // PrepareStatement delete Chuan_LO.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }

            conn.close();
        }
    }

    @Override
    public int edit(final ChuanLO chuanlo) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE CHUAN_LO SET ma_lo = ?, mota = ? WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, chuanlo.getMaLO());
            stmt.setString(2, chuanlo.getMota());
            stmt.setInt(3, chuanlo.getId());
            // Return number of record updated
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    @Override
    public int create(ChuanLO chuanlo) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO CHUAN_LO(`ma_lo`, `mota`) VALUES (?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, chuanlo.getMaLO());
            stmt.setString(2, chuanlo.getMota());
            // Return number of record updated
            return stmt.executeUpdate();

        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    public int createLOOfNganh(int idnganh, int idlo, String nienkhoa) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO LO_NGANH(`lo_id`, `nganh_id`, `nien_khoa`) VALUES (?, ?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idlo);
            stmt.setInt(2, idnganh);
            stmt.setString(3, nienkhoa);
            // Return number of record updated
            return stmt.executeUpdate();

        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
  //Transfer ResultSet to List
    public List<ChuanLO> transferResultSetToList(final ResultSet rs) throws SQLException {
        List<ChuanLO> result = new ArrayList<ChuanLO>();

        // Convert result set to list of ChuanLO.
        while (rs.next()) {
            ChuanLO chuanLO = new ChuanLO(
                    rs.getInt("id"), 
                    rs.getString("ma_lo"), 
                    rs.getString("mota"));
            //Add item to List<ChuanLO>
            result.add(chuanLO);
        }

        return result;
    }
    
    //Get List LO By Nganh
    public List<ChuanLO> getListChuanLOByNganh(int nganhid, int skip, int limit) throws ClassNotFoundException, SQLException, IOException{
        List<ChuanLO> result = new ArrayList<ChuanLO>();
        String sql = "SELECT * FROM CHUAN_LO AS CLO, LO_NGANH AS LON "
                + "WHERE LON.lo_id = CLO.id "
                + "AND LON.nganh_id = ? "
                + "LIMIT ? "
                + "OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nganhid);
            stmt.setInt(2, limit);
            stmt.setInt(3, skip);
            ResultSet rs = stmt.executeQuery();
            
            //Transfer ResultSet to List
            result = transferResultSetToList(rs);
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return result;
    }
    
    //Get List LO
    public List<ChuanLO> getListChuanLO(int skip, int limit) throws ClassNotFoundException, SQLException, IOException{
        List<ChuanLO> result = new ArrayList<ChuanLO>();
        String sql = "SELECT * FROM CHUAN_LO "
                + "LIMIT ? "
                + "OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);
            ResultSet rs = stmt.executeQuery();
            
            //Transfer ResultSet to List
            result = transferResultSetToList(rs);
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return result;
    }
    
    //Count LO by Nganh
    public int count(int id) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS count FROM CHUAN_LO AS CLO, LO_NGANH AS LON "
                + "WHERE LON.lo_id = CLO.id "
                + "AND LON.nganh_id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
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
            conn.close();
        }
    }
    
    //Count all LO
    public int count() throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS count FROM CHUAN_LO";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
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
            conn.close();
        }
    }
    
    //Get List LO not in list LO of Nganh to add new LO
    public List<ChuanLO> getLONotInNganh(int nganhid) throws ClassNotFoundException, SQLException, IOException{
        List<ChuanLO> result = new ArrayList<ChuanLO>();
        String sql = "SELECT * FROM CHUAN_LO "
                + "WHERE id NOT IN ( "
                + "SELECT lo_id "
                + "FROM CHUAN_LO AS CLO, LO_NGANH AS LON "
                + "WHERE LON.lo_id = CLO.id AND LON.nganh_id = ? )";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nganhid);
            ResultSet rs = stmt.executeQuery();
            
            //Transfer ResultSet to List
            result = transferResultSetToList(rs);
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return result;
    }
    
    public ChuanLO findById(final int id) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM  CHUAN_LO WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ChuanLO lo = new ChuanLO();
                lo.setId(rs.getInt("id"));
                lo.setMaLO(rs.getString("ma_lo"));
                lo.setMota(rs.getString("mota"));

                return lo;
            } else {
                return null;
            }
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }
    
    public int insertMany(final List<ChuanLO> insertList) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO CHUAN_LO(`ma_lo`, `mota`) VALUES (?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        int batchSize = 100;

        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            int count = 0;
            for (ChuanLO lo : insertList) {
                stmt.setString(1, lo.getMaLO());
                stmt.setString(2, lo.getMota());
                stmt.addBatch();

                if (++count % batchSize == 0) {
                    stmt.executeBatch();
                }
            }

            int[] result = stmt.executeBatch();

            conn.commit();

            return Arrays.stream(result).sum();
        } catch (Throwable ex) {
            conn.rollback();
            throw ex;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }
}

