package uit.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uit.model.TiLeG;
import uit.model.ListLO;

public class StatisticsDAO  {
	
	private StatisticsDAO() {
    }

    private static StatisticsDAO instance;

    public static StatisticsDAO getInstance() {
        if (instance == null) {
            instance = new StatisticsDAO();
        }
        return instance;
    }
    
    public List<TiLeG> transferResultSetToListG(final ResultSet rs) throws SQLException {
    	
    	List<TiLeG> result = new ArrayList<TiLeG>();

        // Convert result set to list of TiLeG
        while (rs.next()) {
            TiLeG tiLe = new TiLeG();
            tiLe.setMaG(rs.getString("ma_G"));
            tiLe.setMoTa(rs.getString("mo_ta"));
            tiLe.setTiLe(rs.getFloat("Percent"));
            
            result.add(tiLe);
        }

        return result;
    }
    
    public List<ListLO> transferResultSetToListLO(final ResultSet rs) throws SQLException {
    	
    	List<ListLO> result = new ArrayList<ListLO>();

        // Convert result set to list of ListLO
        while (rs.next()) {
            ListLO listLo = new ListLO();
            listLo.setMaLO(rs.getString("ma_LO"));
            listLo.setMoTa(rs.getString("mo_ta"));
            
            result.add(listLo);
        }

        return result;
    }
    
    public Float transferResultSetToFloat(final ResultSet rs) throws SQLException {
    	
    	Float result = null;

        // Convert result set to float
        while (rs.next()) {
            result = rs.getFloat("tiLe_Lo");
        }

        return result;
    }
    
    public int count(List<TiLeG> list) {
    	return list.size();
    }
    
    // Dùng cho sinh viên
     
    // Lấy danh sách phần trăm các chuẩn G đã đạt trên 1 môn học của sinh viên
    public List<TiLeG> getGMonSv (int idSv, int idMon) 
    		throws ClassNotFoundException, SQLException, IOException {
    	
    	 Connection conn = null;
         PreparedStatement stmt = null;
         
         String sql = "SELECT ma_G, mo_ta, ROUND(avg(Tile_G), 2) AS Percent " +
         		"FROM ( " +
         		"SELECT ma_G, mo_ta, loaide, (SUM(tilech)/COUNT(ma_G)) AS Tile_G " + 
         		"from( " + 
         		"Select loaide, ma_G, mo_ta, TiLe_CH as tilech " + 
         		"from (SELECT mon_hoc.id as monhoc, chuan_g.ma_g as ma_G, chuan_g.mota as mo_ta, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, " + 
         		"dethi.loai_de as loaide, (diem.diem * 100)/ cau_hoi.diemtoida as TiLe_CH " + 
         		"FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id " + 
         		"left join dethi on cau_hoi.dethi_id = dethi.id " + 
         		"left join cauhoi_g on cau_hoi.id = cauhoi_g.cauhoi_id " + 
         		"left join chuan_g on cauhoi_g.g_id = chuan_g.id " + 
         		"left join sinh_vien on diem.id_sv = sinh_vien.id " + 
         		"left join lop on dethi.id_lop = lop.id " + 
         		"left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id " + 
         		"inner join mon_hoc on lop.id_mon = mon_hoc.id " + 
         		") as TI_LE_CH " + 
         		"where sinhvien = ? and monhoc = ? ) as TI_LE_LoaiDe " + 
         		"group by ma_G, mo_ta, loaide) as TI_LE_Tong " + 
         		"group by ma_G, mo_ta " + 
         		"having Percent > 50";
         
         try {
        	 
             conn = DBConnectionProvider.getInstance().getConnection();
             stmt = conn.prepareStatement(sql);

             stmt.setInt(1, idSv);
             stmt.setInt(2, idMon);
             ResultSet result = stmt.executeQuery();
             return transferResultSetToListG(result);
             
         } finally {
             if (stmt != null && !stmt.isClosed()) {
                 stmt.close();
             }
             if (conn != null && !conn.isClosed()) {
                 conn.close();
             }
         }
    }
    
 // Lấy danh sách phần trăm các chuẩn G đã đạt trên tất cả môn của sinh viên
    public List<TiLeG> getGSv (int idSv) 
    		throws ClassNotFoundException, SQLException, IOException {
    	
    	 Connection conn = null;
         PreparedStatement stmt = null;
         
         String sql = "SELECT ma_G, mo_ta, ROUND(avg(Tile_G), 2) AS Percent " +
         		"FROM ( " +
         		"SELECT ma_G, mo_ta, loaide, (SUM(tilech)/COUNT(ma_G)) AS Tile_G " + 
         		"from( " + 
         		"Select loaide, ma_G, mo_ta, TiLe_CH as tilech " + 
         		"from (SELECT mon_hoc.id as monhoc, chuan_g.ma_g as ma_G, chuan_g.mota as mo_ta, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, " + 
         		"dethi.loai_de as loaide, (diem.diem * 100)/ cau_hoi.diemtoida as TiLe_CH " + 
         		"FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id " + 
         		"left join dethi on cau_hoi.dethi_id = dethi.id " + 
         		"left join cauhoi_g on cau_hoi.id = cauhoi_g.cauhoi_id " + 
         		"left join chuan_g on cauhoi_g.g_id = chuan_g.id " + 
         		"left join sinh_vien on diem.id_sv = sinh_vien.id " + 
         		"left join lop on dethi.id_lop = lop.id " + 
         		"left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id " + 
         		"inner join mon_hoc on lop.id_mon = mon_hoc.id " + 
         		") as TI_LE_CH " + 
         		"where sinhvien = ?) as TI_LE_LoaiDe " + 
         		"group by ma_G, mo_ta, loaide) as TI_LE_Tong " + 
         		"group by ma_G, mo_ta " + 
         		"having Percent > 50";
         
         try {
        	 
             conn = DBConnectionProvider.getInstance().getConnection();
             stmt = conn.prepareStatement(sql);

             stmt.setInt(1, idSv);
             ResultSet result = stmt.executeQuery();
             return transferResultSetToListG(result);
             
         } finally {
             if (stmt != null && !stmt.isClosed()) {
                 stmt.close();
             }
             if (conn != null && !conn.isClosed()) {
                 conn.close();
             }
         }
    }
    
    //Lấy danh sách phần trăm các chuẩn G trên 1 môn học vào giữa kỳ của sinh viên
    public List<TiLeG> getGLoaiSvGk (int idSv, int idMon) 
    		throws ClassNotFoundException, SQLException, IOException {
    	
    	 Connection conn = null;
         PreparedStatement stmt = null;
         
         String sql = "Select ma_G, mo_ta, round(TiLe_CH, 2) as Percent " + 
         		"from (SELECT mon_hoc.id as monhoc, chuan_g.ma_g as ma_G, chuan_g.mota as mo_ta, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, " + 
         		"dethi.loai_de as loaide, (diem.diem * 100)/ cau_hoi.diemtoida as TiLe_CH " + 
         		"FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id " + 
         		"left join dethi on cau_hoi.dethi_id = dethi.id " + 
         		"left join cauhoi_g on cau_hoi.id = cauhoi_g.cauhoi_id " + 
         		"left join chuan_g on cauhoi_g.g_id = chuan_g.id " + 
         		"left join sinh_vien on diem.id_sv = sinh_vien.id " + 
         		"left join lop on dethi.id_lop = lop.id " + 
         		"left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id " + 
         		"inner join mon_hoc on lop.id_mon = mon_hoc.id " + 
         		") as TI_LE_CH " + 
         		"where sinhvien = ? and monhoc = ? and loaide = 0 " + 
         		"having Percent > 50 ";
         
         try {
        	 
             conn = DBConnectionProvider.getInstance().getConnection();
             stmt = conn.prepareStatement(sql);

             stmt.setInt(1, idSv);
             stmt.setInt(2, idMon);
             
             return transferResultSetToListG(stmt.executeQuery());
             
         } finally {
             if (stmt != null && !stmt.isClosed()) {
                 stmt.close();
             }
             if (conn != null && !conn.isClosed()) {
                 conn.close();
             }
         }
    }
    
    //Lấy danh sách phần trăm các chuẩn G trên 1 môn học vào cuối kỳ của sinh viên
    public List<TiLeG> getGLoaiSvCk (int idSv, int idMon) 
    		throws ClassNotFoundException, SQLException, IOException {
    	
    	 Connection conn = null;
         PreparedStatement stmt = null;
         
         String sql = "Select ma_G, mo_ta, round(TiLe_CH, 2) as Percent " + 
         		"from (SELECT mon_hoc.id as monhoc, chuan_g.ma_g as ma_G, chuan_g.mota as mo_ta, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, " + 
         		"dethi.loai_de as loaide, (diem.diem * 100)/ cau_hoi.diemtoida as TiLe_CH " + 
         		"FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id " + 
         		"left join dethi on cau_hoi.dethi_id = dethi.id " + 
         		"left join cauhoi_g on cau_hoi.id = cauhoi_g.cauhoi_id " + 
         		"left join chuan_g on cauhoi_g.g_id = chuan_g.id " + 
         		"left join sinh_vien on diem.id_sv = sinh_vien.id " + 
         		"left join lop on dethi.id_lop = lop.id " + 
         		"left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id " + 
         		"inner join mon_hoc on lop.id_mon = mon_hoc.id " + 
         		") as TI_LE_CH " + 
         		"where sinhvien = ? and monhoc = ? and loaide = 1 " + 
         		"having Percent > 50 ";
         
         try {
        	 
             conn = DBConnectionProvider.getInstance().getConnection();
             stmt = conn.prepareStatement(sql);

             stmt.setInt(1, idSv);
             stmt.setInt(2, idMon);
             
             return transferResultSetToListG(stmt.executeQuery());
             
         } finally {
             if (stmt != null && !stmt.isClosed()) {
                 stmt.close();
             }
             if (conn != null && !conn.isClosed()) {
                 conn.close();
             }
         }
    }
    
    //Lấy các LO đã đạt được sau khi học xong 1 môn của sinh viên
    public List<ListLO> getLoMonSv (int idSv, int idMon) 
    		throws ClassNotFoundException, SQLException, IOException {
    	
    	 Connection conn = null;
         PreparedStatement stmt = null;
         
         String sql = "select distinct chuan_lo.ma_lo as ma_LO, chuan_lo.mota as mo_ta from ( " + 
         		"Select chuan_g, ma_G, avg(Tile_G) as Tile_Tong " + 
         		"from( " + 
         		"Select chuan_g, ma_G, loaide, (sum(tilech)/count(ma_G)) as Tile_G " + 
         		"from( " + 
         		"Select sothutu, loaide as loaide, chuan_g, maG as ma_G, TiLe_CH as tilech " + 
         		"from (SELECT mon_hoc.id as monhoc, chuan_g.id as chuan_g,chuan_g.ma_g as maG, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, " + 
         		"dethi.loai_de as loaide, (diem.diem * 100)/ cau_hoi.diemtoida as TiLe_CH " + 
         		"FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id " + 
         		"left join dethi on cau_hoi.dethi_id = dethi.id " + 
         		"left join cauhoi_g on cau_hoi.id = cauhoi_g.cauhoi_id " + 
         		"left join chuan_g on cauhoi_g.g_id = chuan_g.id " + 
         		"left join sinh_vien on diem.id_sv = sinh_vien.id " + 
         		"left join lop on dethi.id_lop = lop.id " + 
         		"left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id " + 
         		"left join mon_hoc on lop.id_mon = mon_hoc.id " + 
         		") as TI_LE_CH " + 
         		"where sinhvien = ? and monhoc = ?) as TI_LE_LoaiDe " + 
         		"group by ma_G, loaide ) as TI_LE_Tong " + 
         		"group by ma_G " + 
         		"having Tile_Tong > 50) as G_datduoc " + 
         		"left join g_lo on chuan_g = g_lo.id_g " + 
         		"left join chuan_lo on chuan_lo.id = g_lo.id_lo ";
         
         try {
        	 
             conn = DBConnectionProvider.getInstance().getConnection();
             stmt = conn.prepareStatement(sql);

             stmt.setInt(1, idSv);
             stmt.setInt(2, idMon);
             
             return transferResultSetToListLO(stmt.executeQuery());
             
         } finally {
             if (stmt != null && !stmt.isClosed()) {
                 stmt.close();
             }
             if (conn != null && !conn.isClosed()) {
                 conn.close();
             }
         }
    }
    
  //Lấy các LO đã đạt được sau khi học xong 1 môn của sinh viên
    public List<ListLO> getLoSv (int idSv) 
    		throws ClassNotFoundException, SQLException, IOException {
    	
    	 Connection conn = null;
         PreparedStatement stmt = null;
         
         String sql = "select distinct chuan_lo.ma_lo as ma_LO, chuan_lo.mota as mo_ta from ( " + 
         		"Select chuan_g, ma_G, avg(Tile_G) as Tile_Tong " + 
         		"from( " + 
         		"Select chuan_g, ma_G, loaide, (sum(tilech)/count(ma_G)) as Tile_G " + 
         		"from( " + 
         		"Select sothutu, loaide as loaide, chuan_g, maG as ma_G, TiLe_CH as tilech " + 
         		"from (SELECT mon_hoc.id as monhoc, chuan_g.id as chuan_g,chuan_g.ma_g as maG, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, " + 
         		"dethi.loai_de as loaide, (diem.diem * 100)/ cau_hoi.diemtoida as TiLe_CH " + 
         		"FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id " + 
         		"left join dethi on cau_hoi.dethi_id = dethi.id " + 
         		"left join cauhoi_g on cau_hoi.id = cauhoi_g.cauhoi_id " + 
         		"left join chuan_g on cauhoi_g.g_id = chuan_g.id " + 
         		"left join sinh_vien on diem.id_sv = sinh_vien.id " + 
         		"left join lop on dethi.id_lop = lop.id " + 
         		"left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id " + 
         		"left join mon_hoc on lop.id_mon = mon_hoc.id " + 
         		") as TI_LE_CH " + 
         		"where sinhvien = ?) as TI_LE_LoaiDe " + 
         		"group by ma_G, loaide ) as TI_LE_Tong " + 
         		"group by ma_G " + 
         		"having Tile_Tong > 50) as G_datduoc " + 
         		"left join g_lo on chuan_g = g_lo.id_g " + 
         		"left join chuan_lo on chuan_lo.id = g_lo.id_lo ";
         
         try {
        	 
             conn = DBConnectionProvider.getInstance().getConnection();
             stmt = conn.prepareStatement(sql);

             stmt.setInt(1, idSv);
             
             return transferResultSetToListLO(stmt.executeQuery());
             
         } finally {
             if (stmt != null && !stmt.isClosed()) {
                 stmt.close();
             }
             if (conn != null && !conn.isClosed()) {
                 conn.close();
             }
         }
    }
    
    //Phần trăm đạt được các LO đã đạt được đến sau khi xong 1 môn (tính cho 1 môn) của sinh viên
    public Float tiLeLoMonSv (int idSv, int idMon) 
    		throws ClassNotFoundException, SQLException, IOException {
    	
    	 Connection conn = null;
         PreparedStatement stmt = null;
         
         String sql = "select round((lo_dat_duoc*100/tong_LO_MH), 2) as tiLe_Lo " +
         "from( select count( distinct chuan_lo.id) as lo_dat_duoc, (select count( distinct chuan_lo.id) " +
         "from mon_hoc " +
         "left join mon_g on mon_hoc.id = mon_g.id_monhoc "+
         "left join chuan_g on chuan_g.id = mon_g.id_g "+
         "left join g_lo on chuan_g.id = g_lo.id_g "+
         "left join chuan_lo on chuan_lo.id = g_lo.id_lo "+
         "where mon_hoc.id = ?) as tong_LO_MH "+
         "from ( "+
         "Select chuan_g, ma_G, avg(Tile_G) as Tile_Tong " +
         "from( " +
         "Select chuan_g, ma_G, loaide, (sum(tilech)/count(ma_G)) as Tile_G " +
         "from( " +
         "Select sothutu, loaide as loaide, chuan_g, maG as ma_G, TiLe_CH as tilech " +
         "from (SELECT mon_hoc.id as monhoc, chuan_g.id as chuan_g,chuan_g.ma_g as maG, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, "+ 
         "dethi.loai_de as loaide, (diem.diem * 100)/ cau_hoi.diemtoida as TiLe_CH "+
         "FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id "+
         "left join dethi on cau_hoi.dethi_id = dethi.id "+
         "left join cauhoi_g on cau_hoi.id = cauhoi_g.cauhoi_id "+
         "left join chuan_g on cauhoi_g.g_id = chuan_g.id "+
         "left join sinh_vien on diem.id_sv = sinh_vien.id "+
         "left join lop on dethi.id_lop = lop.id "+
         "left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id "+
         "left join mon_hoc on lop.id_mon = mon_hoc.id "+
         ") as TI_LE_CH "+
         "where sinhvien = ? and monhoc = ?) as TI_LE_LoaiDe "+
         "group by ma_G, loaide ) as TI_LE_Tong "+
         "group by ma_G "+
         "having Tile_Tong > 50) as G_datduoc "+ 
         "left join g_lo on chuan_g = g_lo.id_g "+
         "left join chuan_lo on chuan_lo.id = g_lo.id_lo "+
         "left join mon_hoc on chuan_lo.id = g_lo.id_lo) as lo_DatDuoc_MH ";
         
         try {
        	 
             conn = DBConnectionProvider.getInstance().getConnection();
             stmt = conn.prepareStatement(sql);
             
             stmt.setInt(1, idMon);
             stmt.setInt(2, idSv);
             stmt.setInt(3, idMon);
             
             return transferResultSetToFloat(stmt.executeQuery());
             
         } finally {
             if (stmt != null && !stmt.isClosed()) {
                 stmt.close();
             }
             if (conn != null && !conn.isClosed()) {
                 conn.close();
             }
         }
    }
    
    //Phần trăm đạt được các LO đã đạt được đến hiện tại (tính cho tất cả môn) của sinh viên
    public Float tiLeLoAllSv (int idSv) 
    		throws ClassNotFoundException, SQLException, IOException {
    	
    	 Connection conn = null;
         PreparedStatement stmt = null;
         
         String sql = "select round(avg(lo_dat_duoc*100/tong_LO_Nganh), 2) as tiLe_Lo "+
         "from( select count( distinct chuan_lo.id) as lo_dat_duoc, (Select count(distinct lo_id) "+
         "from (SELECT sinh_vien.id as sinhvien, lop_sinh_hoat.ten_lop as tenlsh, "+
         "nganh.ten as tennganh,  chuan_lo.id as lo_id "+
         "FROM sinh_vien "+
         "left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id "+
         "left join nganh on lop_sinh_hoat.nganh_id = nganh.id "+
         "left join lo_nganh on lo_nganh.nganh_id = nganh.id "+
         "left join chuan_lo on lo_nganh.lo_id = chuan_lo.id ) as LO "+
         "where sinhvien = ?) as tong_LO_Nganh "+
         "from ( "+
         "Select monhoc, chuan_g, ma_G, avg(Tile_G) as Tile_Tong "+
         "from( "+
         "Select monhoc, chuan_g, ma_G, loaide, (sum(tilech)/count(ma_G)) as Tile_G "+
         "from( "+
         "Select monhoc, sothutu, loaide as loaide, chuan_g, maG as ma_G, TiLe_CH as tilech "+
         "from (SELECT mon_hoc.id as monhoc, chuan_g.id as chuan_g,chuan_g.ma_g as maG, "+
         "diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, "+
         "dethi.loai_de as loaide, (diem.diem * 100)/ cau_hoi.diemtoida as TiLe_CH "+
         "FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id "+
         "left join dethi on cau_hoi.dethi_id = dethi.id "+
         "left join cauhoi_g on cau_hoi.id = cauhoi_g.cauhoi_id "+
         "left join chuan_g on cauhoi_g.g_id = chuan_g.id "+
         "left join sinh_vien on diem.id_sv = sinh_vien.id "+
         "left join lop on dethi.id_lop = lop.id "+
         "left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id "+
         "left join mon_hoc on lop.id_mon = mon_hoc.id "+
         ") as TI_LE_CH "+
         "where sinhvien = ?) as TI_LE_LoaiDe "+
         "group by monhoc, ma_G, loaide ) as TI_LE_Tong "+
         "group by monhoc, ma_G "+
         "having Tile_Tong > 50) as G_datduoc "+
         "left join g_lo on chuan_g = g_lo.id_g "+
         "left join chuan_lo on chuan_lo.id = g_lo.id_lo "+
         "left join mon_hoc on chuan_lo.id = g_lo.id_lo "+
         "group by monhoc) as lo_DatDuoc_MH ";

         
         try {
        	 
             conn = DBConnectionProvider.getInstance().getConnection();
             stmt = conn.prepareStatement(sql);

             stmt.setInt(1, idSv);
             stmt.setInt(2, idSv);
             
             return transferResultSetToFloat(stmt.executeQuery());
             
         } finally {
             if (stmt != null && !stmt.isClosed()) {
                 stmt.close();
             }
             if (conn != null && !conn.isClosed()) {
                 conn.close();
             }
         }
    }
    
    // Dùng cho giảng viên
    
    // Lấy danh sách phần trăm các chuẩn G đã đạt của 1 sv trong 1 lớp (góc nhìn của gv)
    public List<TiLeG> getGLopGV (int idGv, int idLop, int idSv) 
    		throws ClassNotFoundException, SQLException, IOException {
    	
    	Connection conn = null;
        PreparedStatement stmt = null;
        
        String sql = "Select ma_G, mo_ta, round(avg(Tile_G), 2) as Percent " + 
        		"from " + 
        		"Select sinhvien, ma_G, mo_ta, loaide, (sum(tilech)/count(ma_G)) as Tile_G " + 
        		"from( " + 
        		"Select sinhvien, sothutu, loaide as loaide, ma_G, mo_ta, TiLe_CH as tilech " + 
        		"from (SELECT giang_vien.id as giangvien, lop.id as lop, chuan_g.ma_g as ma_G, chuan_g.mota as mo_ta, sinh_vien.id as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, " + 
        		"dethi.loai_de as loaide, (diem.diem * 100)/ cau_hoi.diemtoida as TiLe_CH " + 
        		"FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id " + 
        		"left join dethi on cau_hoi.dethi_id = dethi.id " + 
        		"left join cauhoi_g on cau_hoi.id = cauhoi_g.cauhoi_id " + 
        		"left join chuan_g on cauhoi_g.g_id = chuan_g.id " + 
        		"left join sinh_vien on diem.id_sv = sinh_vien.id " + 
        		"left join lop on dethi.id_lop = lop.id " + 
        		"left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id " + 
        		"left join giang_vien on giang_vien.id = lop.gv_id " + 
        		"left join mon_hoc on lop.id_mon = mon_hoc.id " + 
        		") as TI_LE_CH " + 
        		"where giangvien = ? and lop = ? and sinhvien = ? " + 
        		") as TI_LE_LoaiDe " + 
        		"group by sinhvien, loaide, ma_G, mo_ta) as TI_LE_Tong " + 
        		"group by ma_G, mo_ta " + 
        		"having Percent > 50 ";
        
        try {
       	 
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idGv);
            stmt.setInt(2, idLop);
            stmt.setInt(3, idSv);
            
            return transferResultSetToListG(stmt.executeQuery());
            
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    // Lấy danh sách phần trăm các chuẩn G đã đạt trong 1 lớp (góc nhìn của gv)
    public List<TiLeG> getGLopAllGV (int idGv, int idLop) 
    		throws ClassNotFoundException, SQLException, IOException {
    	
    	Connection conn = null;
        PreparedStatement stmt = null;
        
        String sql = "Select ma_G, mo_ta, round(avg(Tile_Tong), 2) as Percent " + 
        		"from( " + 
        		"Select sinhvien, ma_G, mo_ta, avg(Tile_G) as Tile_Tong " + 
        		"from( " + 
        		"Select sinhvien, ma_G, mo_ta, loaide, (sum(tilech)/count(ma_G)) as Tile_G " + 
        		"from( " + 
        		"Select sinhvien, sothutu, loaide as loaide, ma_G, mo_ta, TiLe_CH as tilech " + 
        		"from (SELECT giang_vien.id as giangvien, lop.id as lop, chuan_g.ma_g as ma_G, chuan_g.mota as mo_ta, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, " + 
        		"dethi.loai_de as loaide, (diem.diem * 100)/ cau_hoi.diemtoida as TiLe_CH " + 
        		"FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id " + 
        		"left join dethi on cau_hoi.dethi_id = dethi.id " + 
        		"left join cauhoi_g on cau_hoi.id = cauhoi_g.cauhoi_id " + 
        		"left join chuan_g on cauhoi_g.g_id = chuan_g.id " + 
        		"left join sinh_vien on diem.id_sv = sinh_vien.id " + 
        		"left join lop on dethi.id_lop = lop.id " + 
        		"left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id " + 
        		"left join giang_vien on giang_vien.id = lop.gv_id " + 
        		"left join mon_hoc on lop.id_mon = mon_hoc.id " + 
        		") as TI_LE_CH " + 
        		"where giangvien = ? and lop = ?" + 
        		") as TI_LE_LoaiDe " + 
        		"group by sinhvien, loaide, ma_G, mo_ta) as TI_LE_Tong " + 
        		"group by sinhvien, ma_G, mo_ta) as TI_LE_Tong_SV " + 
        		"group by ma_G, mo_ta " + 
        		"having Percent >= 50 ";
        
        try {
       	 
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idGv);
            stmt.setInt(2, idLop);
            
            return transferResultSetToListG(stmt.executeQuery());
            
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
 // Lấy danh sách phần trăm các chuẩn G đã đạt trong 1 lớp dựa vào ID lớp (góc nhìn của gv)
    public List<TiLeG> getGLopAllGVByID (int idLop) 
            throws ClassNotFoundException, SQLException, IOException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        String sql = "Select ma_G, mo_ta, round(avg(Tile_Tong), 2) as Percent " + 
                "from( " + 
                "   Select sinhvien, ma_G, mo_ta, avg(Tile_G) as Tile_Tong " + 
                "   from( " + 
                "       Select sinhvien, ma_G, mo_ta, loaide, (sum(tilech)/count(ma_G)) as Tile_G " + 
                "       from( " + 
                "       Select sinhvien, sothutu, loaide as loaide, ma_G, mo_ta, TiLe_CH as tilech " + 
                "           from (SELECT giang_vien.id as giangvien, lop.id as lop, chuan_g.ma_g as ma_G, chuan_g.mota as mo_ta, diem.id_sv as sinhvien, dethi.id as dethiID, cau_hoi.sothutu as sothutu, " + 
                "               dethi.loai_de as loaide, (diem.diem * 100)/ cau_hoi.diemtoida as TiLe_CH " + 
                "               FROM diem left join cau_hoi on diem.cauhoi_id = cau_hoi.id " + 
                "                           left join dethi on cau_hoi.dethi_id = dethi.id " + 
                "                           left join cauhoi_g on cau_hoi.id = cauhoi_g.cauhoi_id " + 
                "                           left join chuan_g on cauhoi_g.g_id = chuan_g.id " + 
                "                           left join sinh_vien on diem.id_sv = sinh_vien.id " + 
                "                           left join lop on dethi.id_lop = lop.id " + 
                "                           left join lop_sinh_hoat on sinh_vien.lopsh_id = lop_sinh_hoat.id " + 
                "                            left join giang_vien on giang_vien.id = lop.gv_id " + 
                "                            left join mon_hoc on lop.id_mon = mon_hoc.id " + 
                "           ) as TI_LE_CH " + 
                "            where lop = ?" + 
                "            ) as TI_LE_LoaiDe " + 
                "       group by sinhvien, loaide, ma_G, mo_ta) as TI_LE_Tong " + 
                "   group by sinhvien, ma_G, mo_ta) as TI_LE_Tong_SV " + 
                "group by ma_G, mo_ta " + 
                "having Percent >= 50 ";
        
        try {
         
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idLop);
            
            return transferResultSetToListG(stmt.executeQuery());
            
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
