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
import uit.model.Lop;
import uit.modelview.FilterLopViewModel;

public class LopDAO implements FindByCodeFrame<Lop>, FindByIdFrame<Lop>, CreateFrame<Lop>, EditFrame<Lop>,
        DeleteFrame<Lop>, InsertManyFrame<Lop>, CountFrame, GetListFrame<Lop> {

    private LopDAO() {
    }

    private static LopDAO instance;

    public static LopDAO getInstance() {
        if (instance == null) {
            instance = new LopDAO();
        }
        return instance;
    }

    public List<Lop> transferResultSetToList(final ResultSet rs) throws SQLException {
        List<Lop> result = new ArrayList<Lop>();

        // Convert result set to list of Lop.
        while (rs.next()) {
            Lop lop = new Lop();
            lop.setId(rs.getInt("id"));
            lop.setMaLop(rs.getString("malop"));
            lop.setHocKy(rs.getString("hocky"));
            lop.setMonHoc(rs.getString("ten_monhoc"));
            lop.setGiangVien(rs.getString("ten_giangvien"));
            lop.setHocKyID(rs.getInt("mahocky"));
            lop.setMonHocID(rs.getInt("mamon"));
            lop.setGvID(rs.getInt("id_gv"));
            lop.setMavGV("ma_gv");

            result.add(lop);
        }

        return result;
    }

    public int count() throws ClassNotFoundException, SQLException, IOException {
        String countSQL = "SELECT COUNT(*) AS count FROM LOP";
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

    public List<Lop> search(int skip, int limit, FilterLopViewModel filter)
            throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM(" + 
        		"SELECT DISTINCT LOP.id as id, LOP.ma_lop as malop, LOP.gv_id, hoc_ky.loai as hocky, " + 
        		"MON_HOC.ten as ten_monhoc, GIANG_VIEN.ten as ten_giangvien, LOP.hocky_id as mahocky, " + 
        		"MON_HOC.id as mamon, giang_vien.id AS id_gv, giang_vien.ma_gv, sinh_vien.mssv as mssv, " + 
        		"concat(HOC_KY.loai, \" (\" ,HOC_KY.nam_bat_dau, \"-\", HOC_KY.nam_ket_thuc, \")\") AS hk " + 
        		"FROM lop LEFT JOIN giang_vien ON lop.gv_id = giang_vien.id " + 
        		"LEFT JOIN hoc_ky ON lop.hocky_id = hoc_ky.id LEFT JOIN mon_hoc ON lop.id_mon = mon_hoc.id " + 
        		"LEFT JOIN sinhvien_lop ON lop.id = sinhvien_lop.lop_id " + 
        		"LEFT JOIN sinh_vien ON sinh_vien.id = sinhvien_lop.sv_id) AS SEARCH_LOP ";
        
        try {
            if (filter.hasFilter()) {
                sql += "WHERE " + filter.getQuery();
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

    public int countWithFilter(FilterLopViewModel filter) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS count FROM ( " + 
        		"SELECT DISTINCT * FROM( " + 
        		"SELECT LOP.id, LOP.ma_lop as malop,  LOP.gv_id, hoc_ky.loai as hocky, " + 
        		"MON_HOC.ten as ten_monhoc, GIANG_VIEN.ten as ten_giangvien, LOP.hocky_id as mahocky, " + 
        		"MON_HOC.id as mamon, giang_vien.id as id_gv, giang_vien.ma_gv, sinh_vien.mssv as mssv," + 
        		"concat(HOC_KY.loai, \" (\" ,HOC_KY.nam_bat_dau, \"-\", HOC_KY.nam_ket_thuc, \")\") AS hk " + 
        		"FROM lop LEFT JOIN giang_vien ON lop.gv_id = giang_vien.id " + 
        		"LEFT JOIN hoc_ky ON lop.hocky_id = hoc_ky.id LEFT JOIN mon_hoc ON lop.id_mon = mon_hoc.id " + 
        		"LEFT JOIN sinhvien_lop ON lop.id = sinhvien_lop.lop_id " + 
        		"LEFT JOIN sinh_vien ON sinh_vien.id = sinhvien_lop.sv_id ) AS SEARCH_LOP ";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            if (filter.hasFilter()) {
                sql += " WHERE " + filter.getQuery();
            }
            sql += ") AS LIST";
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

    public List<Lop> getList(int skip, int limit) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT LOP.id, LOP.ma_lop as malop, LOP.hocky_id as mahocky, LOP.id_mon as mamon, LOP.gv_id as id_gv, giang_vien.ma_gv AS ma_gv, GIANG_VIEN.ten as ten_giangvien, MON_HOC.ten as ten_monhoc, "
                + "concat(HOC_KY.loai,\"(\", HOC_KY.nam_bat_dau,\"-\",HOC_KY.nam_ket_thuc, \")\") AS `hocky` "
                + "FROM lop LEFT JOIN giang_vien ON lop.gv_id = giang_vien.id "
                + "LEFT JOIN hoc_ky ON lop.hocky_id = hoc_ky.id LEFT JOIN mon_hoc ON lop.id_mon = mon_hoc.id "
                + "LIMIT ? OFFSET ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, skip);
            ResultSet rsList = stmt.executeQuery();
            List<Lop> result = transferResultSetToList(rsList);

            return result;
        } finally { // Close all connect.
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public int create(final Lop Lop) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO LOP(`ma_lop`,`hocky_id` , `id_mon` , `gv_id`) VALUES (?, ?, ?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Lop.getMaLop());
            stmt.setInt(2, Lop.getHocKyID());
            stmt.setInt(3, Lop.getMonHocID());
            stmt.setInt(4, Lop.getGvID());

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

    public int getIdMon(int id) throws ClassNotFoundException, SQLException, IOException {
    	
    	String sql = "SELECT id_mon from lop where id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
        	stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
           int kq = 0;
           ResultSet rs = stmt.executeQuery();
           
           if (rs.next()) {
        	   kq = rs.getInt("id_mon");
           }
           return kq;
            
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
        String deleteDethi = "DELETE FROM dethi WHERE id_lop = ?";
        String deleteSinhVien_Lop = "DELETE FROM SinhVien_Lop WHERE lop_id = ?";
        String sql = "DELETE FROM LOP WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {

            // Delete data refer to Lop from dethi.
            stmt = conn.prepareStatement(deleteDethi);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // Delete data refer to Lop from sinhvien_lop.
            stmt = conn.prepareStatement(deleteSinhVien_Lop);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            // PrepareStatement delete Lop.
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

    public int edit(final Lop Lop) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE LOP SET ma_lop = ? , hocky_id = ?, id_mon = ? , gv_id = ? WHERE id = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Lop.getMaLop());
            stmt.setInt(2, Lop.getHocKyID());
            stmt.setInt(3, Lop.getMonHocID());
            stmt.setInt(4, Lop.getGvID());
            stmt.setInt(5, Lop.getId());

            // Return number of record updated
            return stmt.executeUpdate();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            conn.close();
        }
    }

    public Lop findById(final int id) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM(SELECT LOP.id as id_lop, LOP.ma_lop as malop, LOP.hocky_id as mahocky, LOP.id_mon as mamonhoc, "
                + "LOP.gv_id as magiangvien, GIANG_VIEN.ma_gv as maGV, GIANG_VIEN.ten as ten_giangvien, MON_HOC.ten as ten_monhoc ,"
                + "concat(HOC_KY.loai,\"(\", HOC_KY.nam_bat_dau,\"-\",HOC_KY.nam_ket_thuc, \")\") AS hocky "
                + "FROM lop LEFT JOIN giang_vien ON lop.gv_id = giang_vien.id "
                + "LEFT JOIN hoc_ky ON lop.hocky_id = hoc_ky.id LEFT JOIN mon_hoc ON lop.id_mon = mon_hoc.id) AS SEARCH_LOP "
                + "WHERE SEARCH_LOP.id_lop = ?";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // Prepare statement and get result data.
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Lop lop = new Lop();
                lop.setId(rs.getInt("id_lop"));
                lop.setMaLop(rs.getString("malop"));
                lop.setHocKy(rs.getString("hocky"));
                lop.setMonHoc(rs.getString("ten_monhoc"));
                lop.setGiangVien(rs.getString("ten_giangvien"));
                lop.setHocKyID(rs.getInt("mahocky"));
                lop.setMonHocID(rs.getInt("mamonhoc"));
                lop.setGvID(rs.getInt("magiangvien"));
                lop.setMavGV(rs.getString("maGV"));
              
                return lop;
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

    public Lop findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT * FROM LOP WHERE ma_lop = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        Lop lop = new Lop();
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                lop.setId(rs.getInt("id"));
                lop.setMaLop(rs.getString("ma_lop"));
                lop.setHocKyID(rs.getInt("hocky_id"));
                lop.setMonHocID(rs.getInt("id_mon"));
                lop.setGvID(rs.getInt("gv_id"));
            }
            return lop;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public int insertMany(final List<Lop> insertList) throws ClassNotFoundException, SQLException, IOException {
        String sql = "INSERT INTO LOP(`ma_lop`, `hocky_id` , `id_mon` , `gv_id`) VALUES (?, ?, ?, ?)";
        Connection conn = DBConnectionProvider.getInstance().getConnection();
        PreparedStatement stmt = null;
        int batchSize = 100;

        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            int count = 0;
            for (Lop lop : insertList) {
                stmt.setString(1, lop.getMaLop());
                stmt.setInt(2, lop.getHocKyID());
                stmt.setInt(3, lop.getMonHocID());
                stmt.setInt(4, lop.getGvID());
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