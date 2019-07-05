package uit.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uit.dao.frame.CreateFrame;
import uit.dao.frame.DeleteFrame;
import uit.dao.frame.EditFrame;
import uit.model.ChuanG;

public class ChuanGDAO implements CreateFrame<ChuanG>, DeleteFrame<ChuanG>, EditFrame<ChuanG>{
    private static ChuanGDAO instance;
    private ChuanGDAO() {};
    public static ChuanGDAO getInstance() {
        if (instance == null) {
            instance = new ChuanGDAO();
        }
        return instance;
    }

    @Override
    public int edit(ChuanG g) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE CHUAN_G SET ma_g = ?, mota = ? WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, g.getMaG());
            stmt.setString(2, g.getMota());
            stmt.setInt(3, g.getId());
            
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
            String sql = "DELETE FROM CHUAN_G WHERE id = ?";
            String sqlref1 = "DELETE FROM CAUHOI_G WHERE g_id = ?";
            String sqlref2 = "DELETE FROM MON_G WHERE id_g = ?";
            String sqlref3 = "DELETE FROM G_LO WHERE id_g = ?";
            
            Connection conn = DBConnectionProvider.getInstance().getConnection();
            PreparedStatement stmt = null;
            
            try {
                //DELETE G REF FROM CAUHOI_G
                stmt = conn.prepareStatement(sqlref1);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                stmt.close();
                
                //DELETE G REF FROM MON_G
                stmt = conn.prepareStatement(sqlref2);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                stmt.close();
                
                //DELETE G REF FROM G_LO
                stmt = conn.prepareStatement(sqlref3);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                stmt.close();
                
                //DELETE G REF FROM G_LO
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
    public int create(ChuanG g) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO CHUAN_G(`ma_g`, `mota`) VALUES(?, ?)";
        
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, g.getMaG());
            stmt.setString(2, g.getMota());
            
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }
    
    //Delete G of LO
    public int deleteGOfLO(int id_g, int id_lo) throws ClassNotFoundException, SQLException, IOException {
        String sql = "DELETE FROM G_LO WHERE id_g = ? AND id_lo = ?";
        
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_g);
            stmt.setInt(2, id_lo);
            
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }

            conn.close();
        }
    }
    
    //Create G of LO
    public int createGOfLO(int idlo, int idg) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO G_LO(`id_g`, `id_lo`) VALUES (?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idg);
            stmt.setInt(2, idlo);
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
    
    //GET G OF LO
    public List<ChuanG> getGOfLO(int loid, int skip, int limit) throws ClassNotFoundException, SQLException, IOException{
        List<ChuanG> result = new ArrayList<ChuanG>();
        String sql = "SELECT * FROM CHUAN_G AS CG, G_LO AS GLO "
                + "WHERE GLO.id_g = CG.id "
                + "AND GLO.id_lo = ? "
                + "LIMIT ? "
                + "OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, loid);
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
    
  //Get List G
    public List<ChuanG> getListChuanG(int skip, int limit) throws ClassNotFoundException, SQLException, IOException{
        List<ChuanG> result = new ArrayList<ChuanG>();
        String sql = "SELECT * FROM CHUAN_G "
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
    
  //Get List G not in list G of LO to add new G
    public List<ChuanG> getGNotInLO(int loid) throws ClassNotFoundException, SQLException, IOException{
        List<ChuanG> result = new ArrayList<ChuanG>();
        String sql = "SELECT * FROM CHUAN_G "
                + "WHERE id NOT IN ( "
                + "SELECT id_g "
                + "FROM CHUAN_G AS CG, G_LO AS GLO "
                + "WHERE GLO.id_lo = CG.id AND GLO.id_lo = ? )";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, loid);
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
    
    public ChuanG findById(final int id) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM  CHUAN_G WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ChuanG g = new ChuanG();
                g.setId(rs.getInt("id"));
                g.setMaG(rs.getString("ma_g"));
                g.setMota(rs.getString("mota"));

                return g;
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
    
    public int insertMany(final List<ChuanG> insertList) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO CHUAN_G(`ma_g`, `mota`) VALUES (?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        int batchSize = 100;

        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            int count = 0;
            for (ChuanG g : insertList) {
                stmt.setString(1, g.getMaG());
                stmt.setString(2, g.getMota());
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
    
  //Count G by LO
    public int count(int id) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS count FROM CHUAN_G AS CG, G_LO AS GLO "
                + "WHERE GLO.id_lo = CG.id "
                + "AND GLO.id_lo = ?";
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
    
    //Count all G
    public int count() throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS count FROM CHUAN_G";
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
    
  //Transfer ResultSet to List
    public List<ChuanG> transferResultSetToList(final ResultSet rs) throws SQLException {
        List<ChuanG> result = new ArrayList<ChuanG>();

        // Convert result set to list of ChuanG.
        while (rs.next()) {
            ChuanG g = new ChuanG(
                    rs.getInt("id"), 
                    rs.getString("ma_g"), 
                    rs.getString("mota"));
            //Add item to List<ChuanG>
            result.add(g);
        }

        return result;
    }
    
    public List<ChuanG> findByLopId(int lopId) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT chuan_g.* FROM chuan_g INNER JOIN mon_g ON mon_g.id_g = chuan_g.id "
                    + "INNER JOIN mon_hoc ON mon_hoc.id = mon_g.id_monhoc "
                    + "INNER JOIN lop ON lop.id_mon = mon_hoc.id WHERE lop.id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, lopId);
            ResultSet rs = stmt.executeQuery();
            
            return transferResultSetToList(rs);
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    //Get list G by Cau hoi ID
    public List<ChuanG> findByCauhoiId(int id) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM CAUHOI_G AS CHG, CHUAN_G AS CG "
                + "WHERE CHG.g_id = CG.id AND CHG.cauhoi_id = ?";
        
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return transferResultSetToList(rs);
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
