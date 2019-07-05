package uit.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import uit.dao.frame.CountFrame;
import uit.dao.frame.CreateFrame;
import uit.dao.frame.DeleteFrame;
import uit.dao.frame.EditFrame;
import uit.dao.frame.FindByCodeFrame;
import uit.dao.frame.FindByIdFrame;
import uit.dao.frame.GetListFrame;
import uit.dao.frame.InsertManyFrame;
import uit.model.LopSinhHoat;
import uit.modelview.FilterLopSinhHoatViewModel;

public class LopSinhHoatDAO implements FindByCodeFrame<LopSinhHoat>, FindByIdFrame<LopSinhHoat>,
        CreateFrame<LopSinhHoat>, EditFrame<LopSinhHoat>, DeleteFrame<LopSinhHoat>, InsertManyFrame<LopSinhHoat>,
        CountFrame, GetListFrame<LopSinhHoat> {

    private LopSinhHoatDAO() {
    }

    private static LopSinhHoatDAO instance;

    public static LopSinhHoatDAO getInstance() {
        if (instance == null) {
            instance = new LopSinhHoatDAO();
        }
        return instance;
    }

    public List<LopSinhHoat> transferResultSetToList(final ResultSet rs) throws SQLException {
        List<LopSinhHoat> result = new ArrayList<LopSinhHoat>();

// Convert result set to list of LopSinhHoat.
        while (rs.next()) {
            LopSinhHoat LopSinhHoat = new LopSinhHoat();
            LopSinhHoat.setId(rs.getInt("id"));
            LopSinhHoat.setMaLop(rs.getString("ma_lop"));
            LopSinhHoat.setBatDau(rs.getString("batdau"));
            LopSinhHoat.setTotNghiep(rs.getString("totnghiep"));
            LopSinhHoat.setTen(rs.getString("ten_lop"));
            LopSinhHoat.setCoVan(rs.getString("tencovan"));
            LopSinhHoat.setCoVanID(rs.getInt("co_Van"));
            result.add(LopSinhHoat);
        }

        return result;
    }

    public int count() throws ClassNotFoundException, SQLException, IOException {
        String countSQL = "SELECT COUNT(*) AS count FROM Lop_Sinh_Hoat";
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

    public List<LopSinhHoat> search(int skip, int limit, FilterLopSinhHoatViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM(SELECT Lop_Sinh_Hoat.id as id, Lop_Sinh_Hoat.ma_lop as ma_Lop, Lop_Sinh_Hoat.batdau as batdau, Lop_Sinh_Hoat.totnghiep as totnghiep, Lop_Sinh_Hoat.ten_lop as ten_lop,LOP_Sinh_Hoat.co_van as co_van, Giang_Vien.ten as tencovan "
                + "FROM Lop_Sinh_Hoat LEFT JOIN giang_vien ON Lop_Sinh_Hoat.co_van = giang_vien.id) AS LopSinhHoat";
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

    public int countWithFilter(FilterLopSinhHoatViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS 'count' FROM(SELECT Lop_Sinh_Hoat.id as id, Lop_Sinh_Hoat.ma_lop as ma_Lop, Lop_Sinh_Hoat.batdau as batdau, Lop_Sinh_Hoat.totnghiep as totnghiep, Lop_Sinh_Hoat.ten_lop as ten_lop, LOP_Sinh_Hoat.co_van as co_van, Giang_Vien.ten as tencovan "
                + "FROM Lop_Sinh_Hoat LEFT JOIN giang_vien ON Lop_Sinh_Hoat.co_van = giang_vien.id) AS LopSinhHoat";
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

    public List<LopSinhHoat> getList(int skip, int limit) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM (SELECT Lop_Sinh_Hoat.id as id, Lop_Sinh_Hoat.ma_lop as ma_Lop, Lop_Sinh_Hoat.batdau as batdau, Lop_Sinh_Hoat.totnghiep as totnghiep, Lop_Sinh_Hoat.ten_lop as ten_lop, LOP_Sinh_Hoat.co_van as co_van, Giang_Vien.ten as tencovan "
                + "FROM Lop_Sinh_Hoat LEFT JOIN giang_vien ON Lop_Sinh_Hoat.co_van = giang_vien.id) AS LopSinhHoat "
                + "LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);
            ResultSet rsList = stmt.executeQuery();
            List<LopSinhHoat> result = transferResultSetToList(rsList);

            return result;
        } finally { // Close all connect.
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public int create(final LopSinhHoat lopSinhHoat) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO Lop_Sinh_Hoat(`ma_lop`,`batdau` , `totnghiep` , `ten_lop`,  `co_van`, id_nganh) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            try {
                stmt = conn.prepareStatement(sql);
                Date batdau = df.parse(lopSinhHoat.getBatDau());
                Date totnghiep = df.parse(lopSinhHoat.getTotNghiep());
                stmt.setString(1, lopSinhHoat.getMaLop());
                stmt.setDate(2, new java.sql.Date(batdau.getTime()));
                stmt.setDate(3, new java.sql.Date(totnghiep.getTime()));
                stmt.setString(4, lopSinhHoat.getTen());
                stmt.setInt(5, lopSinhHoat.getCoVanID());
                stmt.setInt(6, lopSinhHoat.getIdNganh());
                
            } catch (java.text.ParseException e) {
                e.printStackTrace();
                System.out.print("you get the ParseException");
            }

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
        String deleteSinhVien = "DELETE FROM Sinh_Vien WHERE Lopsh_id = ?";
        String sql = "DELETE FROM Lop_Sinh_Hoat WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {

            // Delete data refer to LopSinhHoat from sinhvien.
            stmt = conn.prepareStatement(deleteSinhVien);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // PrepareStatement delete LopSinhHoat.
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

    public int edit(final LopSinhHoat lopSinhHoat) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE Lop_Sinh_Hoat SET ma_lop= ?, batdau= ? , totnghiep = ? , ten_lop = ?,  co_van = ?, `id_nganh`=? WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            try {
                stmt = conn.prepareStatement(sql);
                Date batdau = df.parse(lopSinhHoat.getBatDau());
                Date totnghiep = df.parse(lopSinhHoat.getTotNghiep());
                stmt.setString(1, lopSinhHoat.getMaLop());
                stmt.setDate(2, new java.sql.Date(batdau.getTime()));
                stmt.setDate(3, new java.sql.Date(totnghiep.getTime()));
                stmt.setString(4, lopSinhHoat.getTen());
                stmt.setInt(5, lopSinhHoat.getCoVanID());
                stmt.setInt(6, lopSinhHoat.getIdNganh());
                stmt.setInt(7, lopSinhHoat.getId());
            } catch (java.text.ParseException e) {
                e.printStackTrace();
                System.out.print("you get the ParseException");
            }

            // Return number of record updated
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public LopSinhHoat findById(final int id) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM (SELECT Lop_Sinh_Hoat.id as id, Lop_Sinh_Hoat.ma_lop as ma_Lop, Lop_Sinh_Hoat.batdau as batdau, Lop_Sinh_Hoat.id_nganh, "
                + "Lop_Sinh_Hoat.totnghiep as totnghiep, Lop_Sinh_Hoat.ten_lop as ten_lop, LOP_Sinh_Hoat.co_van as co_van, Giang_Vien.ten as tencovan "
                + "FROM Lop_Sinh_Hoat LEFT JOIN giang_vien ON Lop_Sinh_Hoat.co_van = giang_vien.id) AS LopSinhHoat "
                + "WHERE LopSinhHoat.id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String batdau = df.format(rs.getDate("batdau"));
                String totnghiep = df.format(rs.getDate("totnghiep"));
                LopSinhHoat lopSinhHoat = new LopSinhHoat();
                lopSinhHoat.setId(rs.getInt("id"));
                lopSinhHoat.setMaLop(rs.getString("ma_Lop"));
                lopSinhHoat.setBatDau(batdau);
                lopSinhHoat.setTotNghiep(totnghiep);
                lopSinhHoat.setTen(rs.getString("ten_lop"));
                lopSinhHoat.setCoVan(rs.getString("LopSinhHoat.tencovan"));
                lopSinhHoat.setCoVanID(rs.getInt("co_van"));
                lopSinhHoat.setIdNganh(rs.getInt("id_nganh"));
                
                return lopSinhHoat;
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

    public LopSinhHoat findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM (SELECT Lop_Sinh_Hoat.id as id, Lop_Sinh_Hoat.ma_lop as "
                + "ma_Lop, Lop_Sinh_Hoat.batdau as batdau, Lop_Sinh_Hoat.totnghiep as totnghiep, "
                + "Lop_Sinh_Hoat.ten_lop as ten_lop, LOP_Sinh_Hoat.co_van as co_van, Giang_Vien.ten as tencovan "
                + "FROM Lop_Sinh_Hoat LEFT JOIN giang_vien ON Lop_Sinh_Hoat.co_van = giang_vien.id) AS LopSinhHoat "
                + "WHERE LopSinhHoat.ma_Lop = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        LopSinhHoat LopSinhHoat = new LopSinhHoat();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                LopSinhHoat.setId(rs.getInt("LopSinhHoat.id"));
                LopSinhHoat.setMaLop(rs.getString("LopSinhHoat.ma_Lop"));
                String batdau = df.format(rs.getDate("LopSinhHoat.batdau"));
                String totnghiep = df.format(rs.getDate("LopSinhHoat.totnghiep"));
                LopSinhHoat.setBatDau(batdau);
                LopSinhHoat.setTotNghiep(totnghiep);
                LopSinhHoat.setTen(rs.getString("LopSinhHoat.ten_lop"));
                LopSinhHoat.setCoVan(rs.getString("LopSinhHoat.tencovan"));
                LopSinhHoat.setCoVanID(rs.getInt("LopSinhHoat.co_van"));

            }
            return LopSinhHoat;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public int insertMany(final List<LopSinhHoat> insertList) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO Lop_Sinh_Hoat(`ma_lop`,`batdau` , `totnghiep` , `ten_lop`,  `co_van`, `id_nganh`) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        int batchSize = 100;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            try {
                int count = 0;
                for (LopSinhHoat lopSinhHoat : insertList) {
                    Date batdau = df.parse(lopSinhHoat.getBatDau());
                    Date totnghiep = df.parse(lopSinhHoat.getTotNghiep());
                    stmt.setString(1, lopSinhHoat.getMaLop());
                    stmt.setDate(2, new java.sql.Date(batdau.getTime()));
                    stmt.setDate(3, new java.sql.Date(totnghiep.getTime()));
                    stmt.setString(4, lopSinhHoat.getTen());
                    stmt.setInt(5, lopSinhHoat.getCoVanID());
                    stmt.setInt(6, lopSinhHoat.getIdNganh());
                    stmt.addBatch();

                    if (++count % batchSize == 0) {
                        stmt.executeBatch();
                    }
                }
            } catch (java.text.ParseException e) {
                e.printStackTrace();
                System.out.print("you get the ParseException");
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

    public List<LopSinhHoat> findForDropDown(int limit, int skip)
            throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT id, ma_lop FROM lop_sinh_hoat ORDER BY ma_lop ASC LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);

            ResultSet rs = stmt.executeQuery();
            List<LopSinhHoat> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new LopSinhHoat(rs.getInt("id"), rs.getString("ma_lop")));
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