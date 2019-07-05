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
import uit.dao.frame.FindByIdFrame;
import uit.dao.frame.GetListFrame;
import uit.dao.frame.InsertManyFrame;
import uit.model.HocKy;
import uit.modelview.FilterHocKyViewModel;

public class HocKyDAO implements CreateFrame<HocKy>, EditFrame<HocKy>, DeleteFrame<HocKy>, FindByIdFrame<HocKy>,
        InsertManyFrame<HocKy>, CountFrame, GetListFrame<HocKy> {

    private static HocKyDAO instance;

    public static HocKyDAO getInstance() {
        if (instance == null) {
            instance = new HocKyDAO();
        }

        return instance;
    }

    public List<HocKy> transferResultSetToList(final ResultSet rs) throws SQLException {
    	
        List<HocKy> result = new ArrayList<HocKy>();

        // Convert result set to list of HocKy.
        while (rs.next()) {
        	
            HocKy HocKy = new HocKy();
            HocKy.setId(rs.getInt("id"));
            HocKy.setLoai(rs.getString("loai"));
            HocKy.setNamBatDau(rs.getInt("nam_bat_dau"));
            HocKy.setNamKetThuc(rs.getInt("nam_ket_thuc"));
            
            result.add(HocKy);
        }

        return result;
    }

    public int count() throws ClassNotFoundException, SQLException, IOException {
        String countSQL = "SELECT COUNT(*) AS 'count' FROM Hoc_Ky";
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

    public List<HocKy> getList(int skip, int limit) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM Hoc_Ky LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);
            ResultSet rsList = stmt.executeQuery();
            List<HocKy> result = transferResultSetToList(rsList);

            return result;
        } finally { // Close all connect.
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public List<HocKy> search(int skip, int limit, FilterHocKyViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM Hoc_Ky";
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

    public int countWithFilter(FilterHocKyViewModel filter) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS 'count' FROM Hoc_Ky";
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

    public int create(final HocKy HocKy) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO Hoc_Ky(`loai`, `nam_bat_dau`, `nam_ket_thuc`) VALUES (?, ?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, HocKy.getLoai());
            stmt.setInt(2, HocKy.getNamBatDau());
            stmt.setInt(3,  HocKy.getNamKetThuc());

            return stmt.executeUpdate();

        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public int delete(final int id) throws SQLException, ClassNotFoundException, IOException {
    	String checkOff = "SET FOREIGN_KEY_CHECKS=0;";
        String deleteLop = "delete from lop where hocky_id in (select id from hoc_ky where id = ?)";
        String sql = "DELETE FROM hoc_ky WHERE id = ?";
        String checkOn = "SET FOREIGN_KEY_CHECKS=1";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
        	
        	//Set check constraint off
        	stmt = conn.prepareStatement(checkOff);
            stmt.executeUpdate();
            stmt.close();
            
         // Delete all data refer to HocKy from Lop.
            stmt = conn.prepareStatement(deleteLop);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // PrepareStatement delete HocKy.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            
         // PrepareStatement delete HocKy.
            stmt = conn.prepareStatement(checkOn);
            
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }

            conn.close();
        }
    }

    public int edit(final HocKy HocKy) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE java_uit.hoc_ky SET loai = ?, nam_bat_dau = ?, nam_ket_thuc = ? WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, HocKy.getLoai());
            stmt.setInt(2, HocKy.getNamBatDau());
            stmt.setInt(3, HocKy.getNamKetThuc());
            stmt.setInt(4, HocKy.getId());

            // Return number of record updated
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }
    
    public HocKy findById(final int id) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM Hoc_Ky WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HocKy HocKy = new HocKy();
                HocKy.setId(rs.getInt("id"));
                HocKy.setLoai(rs.getString("loai"));
                HocKy.setNamBatDau(rs.getInt("nam_bat_dau"));
                HocKy.setNamKetThuc(rs.getInt("nam_ket_thuc"));

                return HocKy;
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

    public HocKy findByType(String type) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM Hoc_Ky WHERE loai = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        HocKy hocky = null;
        
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String loai = rs.getString("loai");
                Integer namBatDau = rs.getInt("nam_bat_dau");
                Integer namKetThuc = rs.getInt("nam_ket_thuc");
                
                hocky = new HocKy(loai, namBatDau, namKetThuc);
                hocky.setId(rs.getInt("id"));
            }

            return hocky;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    public int insertMany(final List<HocKy> insertList) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO hoc_ky(`loai`, `nam_bat_dau`, `nam_ket_thuc`) VALUES (?, ?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        int batchSize = 100;

        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            int count = 0;
            for (HocKy HocKy : insertList) {
            	stmt.setString(1, HocKy.getLoai());
                stmt.setInt(2, HocKy.getNamBatDau());
                stmt.setInt(3,  HocKy.getNamKetThuc());
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
    
    public List<HocKy> findForDropDown(int limit, int skip) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT id, CONCAT(loai, ' (', nam_bat_dau, '-', nam_ket_thuc, ')')"
                + " AS `name` FROM hoc_ky ORDER BY nam_bat_dau DESC LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);

            ResultSet rs = stmt.executeQuery();
            List<HocKy> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new HocKy(rs.getInt("id"), rs.getString("name")));
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