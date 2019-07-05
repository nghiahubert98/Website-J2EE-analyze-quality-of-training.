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
import uit.model.MonHoc;
import uit.modelview.FilterMonHocViewModel;

public class MonHocDAO implements CreateFrame<MonHoc>, EditFrame<MonHoc>, DeleteFrame<MonHoc>, FindByIdFrame<MonHoc>,
        InsertManyFrame<MonHoc>, CountFrame, GetListFrame<MonHoc>, FindByCodeFrame<MonHoc> {

    private static MonHocDAO instance;

    public static MonHocDAO getInstance() {
        if (instance == null) {
            instance = new MonHocDAO();
        }

        return instance;
    }

    public List<MonHoc> transferResultSetToList(final ResultSet rs) throws SQLException {
        List<MonHoc> result = new ArrayList<MonHoc>();

        // Convert result set to list of MonHoc.
        while (rs.next()) {
            MonHoc MonHoc = new MonHoc();
            MonHoc.setId(rs.getInt("id"));
            MonHoc.setMaMonHoc(rs.getString("ma_mon"));
            MonHoc.setName(rs.getString("ten"));

            result.add(MonHoc);
        }

        return result;
    }

    public int count() throws ClassNotFoundException, SQLException, IOException {
        String countSQL = "SELECT COUNT(*) AS count FROM Mon_Hoc";
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
    
    public int countMonHocSv(int idSv) throws ClassNotFoundException, SQLException, IOException {
        String countSQL = "SELECT COUNT(*) AS count FROM ( " + 
        		"SELECT DISTINCT mon_hoc.id " + 
        		"FROM mon_hoc INNER JOIN lop ON mon_hoc.id = lop.id_mon " + 
        		"INNER JOIN sinhvien_lop ON lop.id = sinhvien_lop.lop_id " + 
        		"INNER JOIN sinh_vien ON sinh_vien.id = sinhvien_lop.sv_id " + 
        		"WHERE sinh_vien.id = ? ) AS list_mon";
        
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmtCount = null;

        try {
            stmtCount = conn.prepareStatement(countSQL);
            stmtCount.setInt(1, idSv);
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
    
    public int countMonHocSvWithFilter(int idSv, FilterMonHocViewModel filter) throws ClassNotFoundException, SQLException, IOException {
        String countSQL = "SELECT COUNT(*) AS count FROM ( " + 
        		"SELECT DISTINCT mon_hoc.id " + 
        		"FROM mon_hoc INNER JOIN lop ON mon_hoc.id = lop.id_mon " + 
        		"INNER JOIN sinhvien_lop ON lop.id = sinhvien_lop.lop_id " + 
        		"INNER JOIN sinh_vien ON sinh_vien.id = sinhvien_lop.sv_id " + 
        		"WHERE sinh_vien.id = ? ";
        
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmtCount = null;

        try {
        	
        	if (filter.hasFilter()) {
                countSQL += "AND " + filter.getQuery();
            }
        	countSQL += " ) AS list_mon";
            stmtCount = conn.prepareStatement(countSQL);
            stmtCount.setInt(1, idSv);
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
    
    public List<MonHoc> getList(int skip, int limit) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM Mon_Hoc  LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);
            ResultSet rsList = stmt.executeQuery();
            List<MonHoc> result = transferResultSetToList(rsList);

            return result;
        } finally { // Close all connect.
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public List<MonHoc> search(int skip, int limit, FilterMonHocViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT Mon_Hoc.id, Mon_Hoc.ma_Mon, Mon_Hoc.ten FROM Mon_Hoc";
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
    
    public List<MonHoc> getMonHocSvWithFilter(int idSv, int skip, int limit, FilterMonHocViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
    	
        Connection conn = null;
        PreparedStatement stmt = null;
        
        String sql = "SELECT DISTINCT mon_hoc.* " + 
        		"FROM mon_hoc INNER JOIN lop ON mon_hoc.id = lop.id_mon " + 
        		"INNER JOIN sinhvien_lop ON lop.id = sinhvien_lop.lop_id " + 
        		"INNER JOIN sinh_vien ON sinh_vien.id = sinhvien_lop.sv_id " + 
        		"WHERE sinh_vien.id = ? ";		
        try {
        	
            if (filter.hasFilter()) {
                sql += "AND " + filter.getQuery();
            }
            
            sql += " LIMIT ? OFFSET ?";

            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, idSv);
            stmt.setInt(2, limit);
            stmt.setInt(3, skip);
            
            ResultSet rsList = stmt.executeQuery();
            return transferResultSetToList(rsList);
            
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    public List<MonHoc> getMonHocSv(int idSv, int skip, int limit)
            throws ClassNotFoundException, SQLException, IOException {
    	
        Connection conn = null;
        PreparedStatement stmt = null;
        
        String sql = "SELECT DISTINCT mon_hoc.* " + 
        		"FROM mon_hoc INNER JOIN lop ON mon_hoc.id = lop.id_mon " + 
        		"INNER JOIN sinhvien_lop ON lop.id = sinhvien_lop.lop_id " + 
        		"INNER JOIN sinh_vien ON sinh_vien.id = sinhvien_lop.sv_id " + 
        		"WHERE sinh_vien.id = ? ";	
        
        try {
        	
            sql += " LIMIT ? OFFSET ?";

            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, idSv);
            stmt.setInt(2, limit);
            stmt.setInt(3, skip);

            ResultSet rsList = stmt.executeQuery();
            return transferResultSetToList(rsList);
            
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    public int countWithFilter(FilterMonHocViewModel filter) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS 'count' FROM Mon_Hoc";
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

    public int create(final MonHoc MonHoc) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO Mon_Hoc(`ma_Mon`, `ten`) VALUES (?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, MonHoc.getMaMonHoc());
            stmt.setString(2, MonHoc.getName());

            return stmt.executeUpdate();

        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public int delete(final int id) throws SQLException, ClassNotFoundException, IOException {
        String deleteMon_G = "DELETE FROM MON_G WHERE id_monhoc = ?";
        String deleteLop = "DELETE FROM LOP WHERE id_mon = ?";
        String sql = "DELETE FROM Mon_Hoc WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {

            // Delete all data refer to MonHoc from MON_G.
            stmt = conn.prepareStatement(deleteMon_G);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // Delete all data refer to MonHoc from LOP.
            stmt = conn.prepareStatement(deleteLop);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // PrepareStatement delete MonHoc.
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

    public MonHoc findById(final int id) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM Mon_Hoc WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                MonHoc MonHoc = new MonHoc();
                MonHoc.setId(rs.getInt("id"));
                MonHoc.setMaMonHoc(rs.getString("ma_mon"));
                MonHoc.setName(rs.getString("ten"));

                return MonHoc;
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

    public int edit(final MonHoc MonHoc) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE java_uit.Mon_Hoc SET ma_Mon = ?, ten = ? WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, MonHoc.getMaMonHoc());
            stmt.setString(2, MonHoc.getName());
            stmt.setInt(3, MonHoc.getId());

            // Return number of record updated
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public int insertMany(final List<MonHoc> insertList) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO Mon_Hoc(`ma_mon`, `ten`) VALUES (?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        int batchSize = 100;

        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            int count = 0;
            for (MonHoc MonHoc : insertList) {
                stmt.setString(1, MonHoc.getMaMonHoc());
                stmt.setString(2, MonHoc.getName());
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

    @Override
    public MonHoc findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM mon_hoc WHERE ma_mon = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                MonHoc monHoc = new MonHoc();
                monHoc.setId(rs.getInt("id"));
                monHoc.setMaMonHoc(rs.getString("ma_mon"));
                monHoc.setName(rs.getString("ten"));

                return monHoc;
            }

            return null;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public List<MonHoc> findForDropDown(int limit, int skip) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT id, CONCAT(ma_mon, ' - ', ten)"
                + " AS `name` FROM mon_hoc ORDER BY ma_mon ASC LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);

            ResultSet rs = stmt.executeQuery();
            List<MonHoc> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new MonHoc(rs.getInt("id"), rs.getString("name")));
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
}