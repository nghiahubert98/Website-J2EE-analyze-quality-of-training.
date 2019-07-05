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
import uit.dao.frame.InsertManyFrame;
import uit.model.DeThi;

public class DeThiDAO implements CreateFrame<DeThi>, InsertManyFrame<DeThi>{
    private DeThiDAO() {
    }
    private static DeThiDAO instance;
    public static DeThiDAO getInstance() {
        if (instance == null)
            instance = new DeThiDAO();
        return instance;
    }
    
    public List<DeThi> findByLopId(int lopId) throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM dethi where id_lop = ?";

        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, lopId);
            
            ResultSet rs = stmt.executeQuery();            
            List<DeThi> result = new ArrayList<>();
            while (rs.next()) {
                DeThi dt = new DeThi(
                    rs.getInt("id"),
                    rs.getBoolean("loai_de"),
                    rs.getInt("id_lop")
                );
                result.add(dt);
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
    
    public DeThi findOneByTypeAndlopId(boolean type, int lopId) throws SQLException, ClassNotFoundException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM dethi where loai_de = ? and id_lop = ?";

        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, type);
            stmt.setInt(2, lopId);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                DeThi dt = new DeThi(
                    rs.getInt("id"),
                    rs.getBoolean("loai_de"),
                    rs.getInt("id_lop")
                );
                return dt;
            }
            
            return null;            
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
    public int create(DeThi dt) throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO `dethi` (`loai_de`, `id_lop`) VALUES (? , ?)";

        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, dt.getType());
            stmt.setInt(2, dt.getLopId());
            
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

    @Override
    public int insertMany(List<DeThi> insertList) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO `dethi` (`loai_de`, `id_lop`) VALUES (? , ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        int batchSize = 100;

        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            int count = 0;
            for (DeThi dt : insertList) {
                stmt.setBoolean(1, dt.getType());
                stmt.setInt(2, dt.getLopId());
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
