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
import uit.model.SinhVien;
import uit.modelview.FilterSinhVienViewModel;

public class SinhVienDAO implements FindByCodeFrame<SinhVien>, FindByIdFrame<SinhVien>, CreateFrame<SinhVien>,
        EditFrame<SinhVien>, DeleteFrame<SinhVien>, InsertManyFrame<SinhVien>, CountFrame, GetListFrame<SinhVien> {

    private SinhVienDAO() {
    }

    private static SinhVienDAO instance;

    public static SinhVienDAO getInstance() {
        if (instance == null) {
            instance = new SinhVienDAO();
        }
        return instance;
    }

    
    public List<SinhVien> transferResultSetToList(final ResultSet rs) throws SQLException {
        List<SinhVien> result = new ArrayList<SinhVien>();

        // Convert result set to list of SinhVien.
        while (rs.next()) {
            SinhVien sinhvien = new SinhVien();
            sinhvien.setId(rs.getInt("id"));
            sinhvien.setMssv(rs.getString("mssv"));
            sinhvien.setTen(rs.getString("ten"));
            sinhvien.setMaLopSH(rs.getInt("lopsh_id"));
            sinhvien.setCodeLSH(rs.getString("codelsh"));
            sinhvien.setTenLSH(rs.getString("tenLSH"));
            result.add(sinhvien);
        }

        return result;
             
    }

    public int count() throws ClassNotFoundException, SQLException, IOException {
        String countSQL = "SELECT COUNT(*) AS count FROM SINH_VIEN";
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

    public List<SinhVien> search(int skip, int limit, FilterSinhVienViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT *FROM (SELECT sinh_vien.id as id, sinh_vien.mssv as mssv, sinh_vien.ten as ten, sinh_vien.lopsh_id as lopsh_id, lop_sinh_hoat.ma_lop as codelsh, lop_sinh_hoat.ten_lop as tenLSH FROM sinh_vien LEFT JOIN lop_sinh_hoat ON sinh_vien.lopsh_id = lop_sinh_hoat.id ) AS SinhVien_LSH";
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

    public int countWithFilter(FilterSinhVienViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS 'count' FROM (SELECT sinh_vien.id as id, sinh_vien.mssv as mssv, sinh_vien.ten as ten, sinh_vien.lopsh_id as lopsh_id, lop_sinh_hoat.ma_lop as codelsh,, lop_sinh_hoat.ten_lop as tenLSH FROM sinh_vien LEFT JOIN lop_sinh_hoat ON sinh_vien.lopsh_id = lop_sinh_hoat.id ) AS SinhVien_LSH";
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
    
    public int countSvLop(int idLop)
            throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(id) AS count FROM( "+
        		"SELECT DISTINCT sinh_vien.*, lop_sinh_hoat.ma_lop AS codelsh, lop_sinh_hoat.ten_lop AS tenLSH "+
        		"FROM sinh_vien INNER JOIN sinhvien_lop ON sinh_vien.id = sinhvien_lop.sv_id "+
        		"INNER JOIN lop ON sinhvien_lop.lop_id = lop.id "+
        		"INNER JOIN lop_sinh_hoat ON lop_sinh_hoat.id = sinh_vien.lopsh_id "+
        		"WHERE lop.id = ?) AS LIST_SV";

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idLop);
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
    
    public int countSvLopFilter(int idLop, FilterSinhVienViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(id) AS count FROM( "+
        		"SELECT DISTINCT sinh_vien.*, lop_sinh_hoat.ma_lop AS codelsh, lop_sinh_hoat.ten_lop AS tenLSH "+
        		"FROM sinh_vien INNER JOIN sinhvien_lop ON sinh_vien.id = sinhvien_lop.sv_id "+
        		"INNER JOIN lop ON sinhvien_lop.lop_id = lop.id "+
        		"INNER JOIN lop_sinh_hoat ON lop_sinh_hoat.id = sinh_vien.lopsh_id "+
        		"WHERE lop.id = ? ";

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            if (filter.hasFilter()) {
                sql += "AND " + filter.getQuery();
            }
            sql += ") AS LIST_SV";
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idLop);
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
    
    public List<SinhVien> getList(int skip, int limit) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT sinh_vien.id as id, sinh_vien.mssv as mssv, sinh_vien.ten as ten, sinh_vien.lopsh_id as lopsh_id, lop_sinh_hoat.ma_lop as codelsh, lop_sinh_hoat.ten_lop as tenLSH FROM sinh_vien LEFT JOIN lop_sinh_hoat ON sinh_vien.lopsh_id = lop_sinh_hoat.id LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);
            ResultSet rsList = stmt.executeQuery();
            List<SinhVien> result = transferResultSetToList(rsList);

            return result;
        } finally { // Close all connect.
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }
    
    public List<SinhVien> getListSvLopFilter(int idLop, int skip, int limit, FilterSinhVienViewModel filter) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT DISTINCT sinh_vien.*, lop_sinh_hoat.ma_lop AS codelsh, lop_sinh_hoat.ten_lop AS tenLSH " + 
        		"FROM sinh_vien INNER JOIN sinhvien_lop ON sinh_vien.id = sinhvien_lop.sv_id " + 
        		"INNER JOIN lop ON sinhvien_lop.lop_id = lop.id " + 
        		"INNER JOIN lop_sinh_hoat ON lop_sinh_hoat.id = sinh_vien.lopsh_id " + 
        		"WHERE lop.id = ? ";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
        	
        	if (filter.hasFilter()) {
                sql += "AND " + filter.getQuery();
            }
        	
        	sql += "LIMIT ? OFFSET ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idLop);
            stmt.setInt(2, limit);
            stmt.setInt(3, skip);
            ResultSet rsList = stmt.executeQuery();
            List<SinhVien> result = transferResultSetToList(rsList);

            return result;
        } finally { // Close all connect.
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }
    
    public List<SinhVien> getListSvLop(int idLop, int skip, int limit) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT DISTINCT sinh_vien.*, lop_sinh_hoat.ma_lop AS codelsh, lop_sinh_hoat.ten_lop AS tenLSH " + 
        		"FROM sinh_vien INNER JOIN sinhvien_lop ON sinh_vien.id = sinhvien_lop.sv_id " + 
        		"INNER JOIN lop ON sinhvien_lop.lop_id = lop.id " + 
        		"INNER JOIN lop_sinh_hoat ON lop_sinh_hoat.id = sinh_vien.lopsh_id " + 
        		"WHERE lop.id = ? LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idLop);
            stmt.setInt(2, limit);
            stmt.setInt(3, skip);
            ResultSet rsList = stmt.executeQuery();
            List<SinhVien> result = transferResultSetToList(rsList);

            return result;
        } finally { // Close all connect.
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }
    
    public int create(final SinhVien sinhvien) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO SINH_VIEN(`mssv`, `ten`, `lopsh_id`) VALUES (?, ?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sinhvien.getMssv());
            stmt.setString(2, sinhvien.getTen());
            stmt.setInt(3, sinhvien.getMaLopSH());

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

    public int delete(final int id) throws SQLException, ClassNotFoundException, IOException {
        String deleteSinhVienLop = "DELETE FROM SINHVIEN_LOP WHERE sv_id = ?";
        String deleteDiem = "DELETE FROM DIEM WHERE id_sv = ?";
        String deleteUsers = "DELETE FROM USERS WHERE sv_id = ?";
        String sql = "DELETE FROM SINH_VIEN WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {

            // Delete data refer to sinhvien from sinhvien_lop.
            stmt = conn.prepareStatement(deleteSinhVienLop);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // Delete data refer to sinhvien from diem.
            stmt = conn.prepareStatement(deleteDiem);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // Delete data refer to sinhvien from users.
            stmt = conn.prepareStatement(deleteUsers);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // PrepareStatement delete sinhvien.
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

    public int edit(final SinhVien sinhvien) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE SINH_VIEN SET mssv = ?, ten = ?, lopsh_id = ? WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sinhvien.getMssv());
            stmt.setString(2, sinhvien.getTen());
            stmt.setInt(3, sinhvien.getMaLopSH());
            stmt.setInt(4, sinhvien.getId());

            // Return number of record updated
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public SinhVien findById(final int id) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT sinh_vien.id as id, sinh_vien.mssv as mssv, sinh_vien.ten as ten, sinh_vien.lopsh_id as lopsh_id, lop_sinh_hoat.ma_lop as codelsh, lop_sinh_hoat.ten_lop as tenLSH FROM sinh_vien LEFT JOIN lop_sinh_hoat ON sinh_vien.lopsh_id = lop_sinh_hoat.id WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                SinhVien sinhvien = new SinhVien();
                sinhvien.setId(rs.getInt("id"));
                sinhvien.setMssv(rs.getString("mssv"));
                sinhvien.setTen(rs.getString("ten"));
                sinhvien.setMaLopSH(rs.getInt("lopsh_id"));
                sinhvien.setCodeLSH(rs.getString("codelsh"));

                return sinhvien;
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

    public SinhVien findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM SINH_VIEN WHERE mssv = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        SinhVien sv = null;
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String ten = rs.getString("ten");
                String mssv = rs.getString("mssv");
                int maLopSH = rs.getInt("lopsh_id");

                sv = new SinhVien(mssv, ten, maLopSH);
                sv.setId(rs.getInt("id"));
            }

            return sv;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public int insertMany(final List<SinhVien> insertList) throws ClassNotFoundException, SQLException, IOException {
        String sql_codeLSH = "SELECT DISTINCT lopsh_id FROM (SELECT sinh_vien.lopsh_id as lopsh_id, lop_sinh_hoat.ma_lop as ma_lop "
                + "FROM sinh_vien LEFT JOIN lop_sinh_hoat ON sinh_vien.lopsh_id = lop_sinh_hoat.id ) AS SinhVien_LSH WHERE ma_lop = ?";
        String sql = "INSERT INTO SINH_VIEN(`mssv`, `ten`, `lopsh_id`) VALUES (?, ?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        int batchSize = 100;

        try {
            conn.setAutoCommit(false);
            int count = 0;
            for (SinhVien sinhvien : insertList) {
                int maLopSH = 0;
                stmt = conn.prepareStatement(sql_codeLSH);
                stmt.setString(1, sinhvien.getCodeLSH());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    maLopSH = rs.getInt("lopsh_id");
                }
                stmt.close();

                stmt = conn.prepareStatement(sql);
                stmt.setString(1, sinhvien.getMssv());
                stmt.setString(2, sinhvien.getTen());
                stmt.setInt(3, maLopSH);
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
    
    public List<SinhVien> findByLopId(int lopId) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT sinh_vien.* FROM sinh_vien INNER JOIN sinhvien_lop ON sinh_vien.id = sinhvien_lop.id "
                    + "INNER JOIN lop ON lop.id = sinhvien_lop.lop_id WHERE lop.id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, lopId);
            ResultSet rs = stmt.executeQuery();
            List<SinhVien> result = new ArrayList<>();
            while (rs.next()) {
                SinhVien sinhvien = new SinhVien();
                sinhvien.setId(rs.getInt("id"));
                sinhvien.setMssv(rs.getString("mssv"));
                sinhvien.setTen(rs.getString("ten"));

                result.add(sinhvien);
            }

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
}
