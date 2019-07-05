package uit.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uit.dao.frame.CountFrame;
import uit.dao.frame.CreateFrame;
import uit.dao.frame.DeleteFrame;
import uit.dao.frame.EditFrame;
import uit.dao.frame.FindByCodeFrame;
import uit.dao.frame.FindByIdFrame;
import uit.dao.frame.GetListFrame;
import uit.dao.frame.InsertManyFrame;
import uit.model.Nganh;
import uit.modelview.FilterNganhViewModel;

public class NganhDAO  implements CreateFrame<Nganh>, EditFrame<Nganh>, DeleteFrame<Nganh>, FindByIdFrame<Nganh>,
            InsertManyFrame<Nganh>, CountFrame, GetListFrame<Nganh>, FindByCodeFrame<Nganh> {

    private static NganhDAO instance;
    public static NganhDAO getInstance() {
        if (instance == null) {
            instance = new NganhDAO();
        }

        return instance;
    }

    public List<Nganh> transferResultSetToList(final ResultSet rs) throws SQLException {
        List<Nganh> result = new ArrayList<Nganh>();

        // Convert result set to list of Nganh.
        while (rs.next()) {
            Nganh nganh = new Nganh();
            nganh.setId(rs.getInt("id"));
            nganh.setManganh(rs.getString("ma_nganh"));
            nganh.setName(rs.getString("ten"));

            result.add(nganh);
        }

        return result;
    }

    public int count() throws ClassNotFoundException, SQLException, IOException {
        String countSQL = "SELECT COUNT(*) AS count FROM NGANH";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmtCount = null;

        try {
            stmtCount = conn.prepareStatement(countSQL);
            ResultSet rsCount = stmtCount.executeQuery();
            int count = 0;
            if (rsCount.next()) {
                count = rsCount.getInt("count");
            }

            return count;
        } finally {
            if (stmtCount != null && !stmtCount.isClosed()) {
                stmtCount.close();
            }
            conn.close();
        }
    }

    public List<Nganh> getList(int skip, int limit) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM NGANH  LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);
            ResultSet rsList = stmt.executeQuery();
            List<Nganh> result = transferResultSetToList(rsList);

            return result;
        } finally { // Close all connect.
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }
    public List<Nganh> search(int skip, int limit, FilterNganhViewModel filter)  throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT nganh.id, nganh.ma_nganh, nganh.ten FROM nganh";
        try {
            if (filter.hasFilter()) {
                sql += " WHERE " + filter.getQuery();
            }
            
            sql += " LIMIT ? OFFSET ?";
            
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);

            return transferResultSetToList(stmt.executeQuery());
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    public int countWithFilter(FilterNganhViewModel filter) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS 'count' FROM nganh";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            if (filter.hasFilter()) {
                sql += " WHERE " + filter.getQuery();
            }
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count");
            }
            
            return 0;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    public int create(final Nganh nganh) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO NGANH(`ma_nganh`, `ten`) VALUES (?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nganh.getManganh());
            stmt.setString(2, nganh.getName());

            return stmt.executeUpdate();

        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public int delete(final int id) throws SQLException, ClassNotFoundException, IOException {
        String deleteLoNganh = "DELETE FROM LO_NGANH WHERE nganh_id = ?";
        String sql = "DELETE FROM NGANH WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {

            // Delete all data refer to nganh.
            stmt = conn.prepareStatement(deleteLoNganh);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // PrepareStatement delete nganh.
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
 
    public Nganh findById(final int id) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM NGANH WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Nganh nganh = new Nganh();
                nganh.setId(rs.getInt("id"));
                nganh.setManganh(rs.getString("ma_nganh"));
                nganh.setName(rs.getString("ten"));

                return nganh;
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

    public int edit(final Nganh nganh) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE java_uit.nganh SET ma_nganh = ?, ten = ? WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nganh.getManganh());
            stmt.setString(2, nganh.getName());
            stmt.setInt(3, nganh.getId());

            // Return number of record updated
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public int insertMany(final List<Nganh> insertList) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO NGANH(`ma_nganh`, `ten`) VALUES (?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        int batchSize = 100;

        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            int count = 0;
            for (Nganh nganh : insertList) {
                stmt.setString(1, nganh.getManganh());
                stmt.setString(2, nganh.getName());
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
    
    public List<Nganh> findAll() throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM NGANH";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rsList = stmt.executeQuery();
            List<Nganh> result = transferResultSetToList(rsList);

            return result;
        } finally { // Close all connect.
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    @Override
    public Nganh findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM NGANH WHERE nganh.ma_nganh = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Nganh nganh = new Nganh();
                nganh.setId(rs.getInt("id"));
                nganh.setManganh(rs.getString("ma_nganh"));
                nganh.setName(rs.getString("ten"));

                return nganh;
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
}
