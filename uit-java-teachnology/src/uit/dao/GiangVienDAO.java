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
import uit.model.GiangVien;
import uit.modelview.FilterGiangVienViewModel;

public class GiangVienDAO
        implements CreateFrame<GiangVien>, EditFrame<GiangVien>, DeleteFrame<GiangVien>, FindByIdFrame<GiangVien>,
        FindByCodeFrame<GiangVien>, InsertManyFrame<GiangVien>, CountFrame, GetListFrame<GiangVien> {

    private static GiangVienDAO instance;

    private GiangVienDAO() {
    }

    public static GiangVienDAO getInstance() {
        if (instance == null) {
            instance = new GiangVienDAO();
        }
        return instance;
    }

    @Override
    public List<GiangVien> transferResultSetToList(ResultSet rs) throws SQLException {
        List<GiangVien> resultList = new ArrayList<>();

        // Convert rs to list.
        while (rs.next()) {
            GiangVien gv = new GiangVien(rs.getInt("id"), rs.getString("ma_gv"), rs.getString("ten"),
                    rs.getString("email"));
            resultList.add(gv);
        }

        return resultList;
    }

    @Override
    public List<GiangVien> getList(int skip, int limit) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM GIANG_VIEN LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);

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

    public List<GiangVien> search(int skip, int limit, FilterGiangVienViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT giang_vien.id, giang_vien.ma_gv, giang_vien.ten, giang_vien.email FROM giang_vien";
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

    public int countWithFilter(FilterGiangVienViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS 'count' FROM giang_vien";
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

    public int create(final GiangVien giangvien) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO giang_vien(`ma_gv`, `ten`, `email`) VALUES (?, ?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, giangvien.getCode());
            stmt.setString(2, giangvien.getName());
            stmt.setString(3, giangvien.getEmail());
            return stmt.executeUpdate();

        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public int edit(final GiangVien giangvien) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE java_uit.giang_vien SET ma_gv = ?, ten = ?, email = ? WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, giangvien.getCode());
            stmt.setString(2, giangvien.getName());
            stmt.setString(3, giangvien.getEmail());
            stmt.setInt(4, giangvien.getId());

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
    public int delete(int id) throws ClassNotFoundException, SQLException, IOException {
        String sql = "DELETE FROM GIANG_VIEN WHERE id = ?";
        String sqlDeleteLop = "DELETE FROM LOP WHERE gv_id = ?";
        String sqlDeleteLopSH = "DELETE FROM LOP_SINH_HOAT WHERE co_van = ?";
        String sqlDeleteUser = "DELETE FROM USERS WHERE gv_id = ?";

        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {

            // Delete data refer to sinhvien from LOP.
            stmt = conn.prepareStatement(sqlDeleteLop);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // Delete data refer to sinhvien from LOP_SINH_HOAT.
            stmt = conn.prepareStatement(sqlDeleteLopSH);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // Delete data refer to sinhvien from USERS.
            stmt = conn.prepareStatement(sqlDeleteUser);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // PrepareStatement delete giang vien.
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
    public int count() throws ClassNotFoundException, SQLException, IOException {
        String countSQL = "SELECT COUNT(*) AS count FROM GIANG_VIEN";
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
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public GiangVien findById(final int id) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM giang_vien WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                GiangVien giangvien = new GiangVien();
                giangvien.setId(rs.getInt("id"));
                giangvien.setCode(rs.getString("ma_gv"));
                giangvien.setName(rs.getString("ten"));
                giangvien.setEmail(rs.getString("email"));

                return giangvien;
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

    @Override
    public GiangVien findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM GIANG_VIEN WHERE ma_gv = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        GiangVien gv = null;

        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                String maGV = rs.getString("ma_gv");
                String email = rs.getString("email");

                gv = new GiangVien(id, maGV, ten, email);
            }

            return gv;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public int insertMany(final List<GiangVien> insertList) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO giang_vien(`ma_gv`, `ten`,`email`) VALUES (?, ?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        int batchSize = 100;

        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            int count = 0;
            for (GiangVien giangvien : insertList) {
                stmt.setString(1, giangvien.getCode());
                stmt.setString(2, giangvien.getName());
                stmt.setString(3, giangvien.getEmail());
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

   public List<GiangVien> findForDropDown(int limit, int skip) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT id, CONCAT(ma_gv, ' - ', ten) AS `name` FROM giang_vien ORDER BY ma_gv ASC LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);

            ResultSet rs = stmt.executeQuery();
            List<GiangVien> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new GiangVien(rs.getInt("id"), rs.getString("name")));
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
  
    public GiangVien findByEmail(final String email) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM java_uit.giang_vien WHERE email = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            GiangVien gv = null;
            if (rs.next()) {
                gv = new GiangVien(
                    rs.getInt("id"), 
                    rs.getString("ma_gv"),  
                    rs.getString("ten"), 
                    rs.getString("email")
                );
            }

            return gv;
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
