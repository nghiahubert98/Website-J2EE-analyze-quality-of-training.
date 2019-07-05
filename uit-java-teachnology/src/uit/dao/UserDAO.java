package uit.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
import uit.model.GiangVien;
import uit.model.SinhVien;
import uit.model.User;
import uit.modelview.FilterUserViewModel;
import uit.utils.Constants;
import uit.utils.PropertiesUtil;

public class UserDAO  implements CreateFrame<User>, EditFrame<User>, CountFrame,
    DeleteFrame<User>, GetListFrame<User>, FindByCodeFrame<User> , FindByIdFrame<User>{
    
    private static UserDAO instance;
    private UserDAO() {}
    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public int create(User user) throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO USERS(`pwd`, `salt`, `user_type`, `gv_id`, `sv_id`, `roles`, `status`) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getSalt());
            stmt.setInt(3, user.getUserType());
            
            if (user.getUserType() == Constants.SV_USER) {
                SinhVien sv = SinhVienDAO.getInstance().findByCode(user.getCode());
                stmt.setNull(4, Types.INTEGER);
                stmt.setInt(5, sv.getId());
            } 
            if (user.getUserType() == Constants.GV_USER) {
                GiangVien gv = GiangVienDAO.getInstance().findByCode(user.getCode());
                stmt.setInt(4, gv.getId());
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.setString(6, String.join(Constants.ROLE_JOIN_DELIMITER, user.getRoles()));
            stmt.setBoolean(7, true);
            
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

    @SuppressWarnings("resource")
	@Override
    public int edit(User user) throws ClassNotFoundException, SQLException, IOException {
    	Connection conn = null;
        PreparedStatement stmt = null;
        String sql;
        try {
        	conn = DBConnectionProvider.getInstance().getConnection();
        	
        	if (user.getUserType() == Constants.SV_USER) {
        		
        		sql = "UPDATE java_uit.users SET pwd = ?, salt = ? where sv_id = ?";
        		stmt = conn.prepareStatement(sql);
        		SinhVien sv = SinhVienDAO.getInstance().findByCode(user.getCode());
        		stmt.setString(1, user.getPassword());
        		stmt.setString(2, user.getSalt());
        		stmt.setInt(3, sv.getId());
        	}
        	
        	if (user.getUserType() == Constants.GV_USER)  {
        		
        		sql = "UPDATE java_uit.users SET pwd = ?, salt = ? where gv_id = ?";
        		stmt = conn.prepareStatement(sql);
        		GiangVien gv = GiangVienDAO.getInstance().findByCode(user.getCode());
        		stmt.setString(1, user.getPassword());
        		stmt.setString(2, user.getSalt());
        		stmt.setInt(3, gv.getId());
        
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

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException, IOException {
        return 0;
    }

    @Override
    public List<User> transferResultSetToList(ResultSet rs) throws SQLException {
        List<User> result = new ArrayList<>();
        
        while (rs.next()) {
            String[] rolseArray = rs.getString("roles").split(Constants.ROLE_JOIN_DELIMITER);
            
            User user = new User(
               rs.getInt("id"), 
               rs.getString("name"), 
               rs.getString("code"), 
               rs.getInt("user_type"), 
               Arrays.asList(rolseArray), 
               rs.getBoolean("status")
            );
            
            result.add(user);
        }
        
        return result;
    }

    @Override
    public List<User> getList(int skip, int limit) throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT users.id, users.user_type, users.roles, `users`.`status`, "
                + "CASE (user_type) WHEN ? THEN giang_vien.ten WHEN ? THEN sinh_vien.ten END AS 'name', "
                + "CASE (user_type) WHEN ? THEN giang_vien.ma_gv WHEN ? THEN sinh_vien.mssv END AS 'code' "
                + "FROM users LEFT JOIN giang_vien ON users.gv_id = giang_vien.id "
                + "LEFT JOIN sinh_vien ON users.sv_id = sinh_vien.id " + "LIMIT ? OFFSET ?";
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, Constants.GV_USER);
            stmt.setInt(2, Constants.SV_USER);
            stmt.setInt(3, Constants.GV_USER);
            stmt.setInt(4, Constants.SV_USER);
            stmt.setInt(5, limit);
            stmt.setInt(6, skip);

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

    public List<User> search(int skip, int limit, FilterUserViewModel filter)  throws ClassNotFoundException, SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT users.id, users.user_type, users.roles, `users`.`status`, "
                + "CASE (user_type) WHEN ? THEN giang_vien.ten WHEN ? THEN sinh_vien.ten END AS 'name', "
                + "CASE (user_type) WHEN ? THEN giang_vien.ma_gv WHEN ? THEN sinh_vien.mssv END AS 'code' "
                + "FROM users LEFT JOIN giang_vien ON users.gv_id = giang_vien.id "
                + "LEFT JOIN sinh_vien ON users.sv_id = sinh_vien.id";
        
        try {
            if (filter.hasFilter()) {
                sql += " WHERE " + filter.getQuery();
            }
            
            sql += " LIMIT ? OFFSET ?";
            
            conn = DBConnectionProvider.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, Constants.GV_USER);
            stmt.setInt(2, Constants.SV_USER);
            stmt.setInt(3, Constants.GV_USER);
            stmt.setInt(4, Constants.SV_USER);
            stmt.setInt(5, limit);
            stmt.setInt(6, skip);

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
    
    @Override
    public User findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "SELECT USERS.*, CASE (user_type) WHEN ? THEN giang_vien.ma_gv WHEN ? THEN sinh_vien.mssv END AS 'code',"
                    + "CASE (user_type) WHEN ? THEN giang_vien.ten WHEN ? THEN sinh_vien.ten END AS 'name'" 
                    + " FROM users LEFT JOIN SINH_VIEN ON USERS.sv_id = SINH_VIEN.id LEFT JOIN GIANG_VIEN ON USERS.gv_id = GIANG_VIEN.id"
                    + " WHERE status = 1 AND (mssv = ? OR  ma_gv = ?)";
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Constants.GV_USER);
            stmt.setInt(2, Constants.SV_USER);
            stmt.setInt(3, Constants.GV_USER);
            stmt.setInt(4, Constants.SV_USER);
            stmt.setString(5, code);
            stmt.setString(6, code);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String[] rolseArray = rs.getString("roles").split(Constants.ROLE_JOIN_DELIMITER);
                user = new User(
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getString("pwd"),
                    rs.getInt("user_type"),  
                    rs.getString("salt"), 
                    rs.getString("code"), 
                    Arrays.asList(rolseArray)
                );
            }

            return user;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
 
    public User findByEmail(String email) throws ClassNotFoundException, SQLException, IOException {
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        String hostDomain = PropertiesUtil.getProperties().getProperty("GOOGLE_HD");
        String userCode = email.replace("@" + hostDomain, "");
        
        String sql = "SELECT USERS.*, CASE (user_type) WHEN ? THEN giang_vien.ma_gv WHEN ? THEN sinh_vien.mssv END AS 'code',"
                    + "CASE (user_type) WHEN ? THEN giang_vien.ten WHEN ? THEN sinh_vien.ten END AS 'name'" 
                    + " FROM users LEFT JOIN SINH_VIEN ON USERS.sv_id = SINH_VIEN.id LEFT JOIN GIANG_VIEN ON USERS.gv_id = GIANG_VIEN.id"
                    + " WHERE status = 1 AND (mssv = ? OR  email = ?)";
        
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Constants.GV_USER);
            stmt.setInt(2, Constants.SV_USER);
            stmt.setInt(3, Constants.GV_USER);
            stmt.setInt(4, Constants.SV_USER);
            stmt.setString(5, userCode);
            stmt.setString(6, email);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String[] rolseArray = rs.getString("roles").split(Constants.ROLE_JOIN_DELIMITER);
                user = new User(
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getString("pwd"),
                    rs.getInt("user_type"),  
                    rs.getString("salt"), 
                    rs.getString("code"), 
                    Arrays.asList(rolseArray)
                );
            }

            return user;
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
    public int count() throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS 'count' FROM USERS";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
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
    
    public int countWithFilter(FilterUserViewModel filter) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT COUNT(*) AS 'count' FROM users LEFT JOIN giang_vien ON users.gv_id = giang_vien.id "
                   + "LEFT JOIN sinh_vien ON users.sv_id = sinh_vien.id";
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
    
    @Override
    public User findById(int id) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT USERS.*, CASE (user_type) WHEN ? THEN giang_vien.ma_gv WHEN ? THEN sinh_vien.mssv END AS 'code',"
                + "CASE (user_type) WHEN ? THEN giang_vien.ten WHEN ? THEN sinh_vien.ten END AS 'name'" 
                + " FROM users LEFT JOIN SINH_VIEN ON USERS.sv_id = SINH_VIEN.id LEFT JOIN GIANG_VIEN ON USERS.gv_id = GIANG_VIEN.id"
                + " WHERE USERS.id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        User user = null;
        
        try {
            conn = DBConnectionProvider.getInstance().getConnection();
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Constants.GV_USER);
            stmt.setInt(2, Constants.SV_USER);
            stmt.setInt(3, Constants.GV_USER);
            stmt.setInt(4, Constants.SV_USER);
            stmt.setInt(5, id);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String[] rolseArray = rs.getString("roles").split(Constants.ROLE_JOIN_DELIMITER);
                user = new User(
                   rs.getInt("id"), 
                   rs.getString("name"), 
                   rs.getString("code"), 
                   rs.getInt("user_type"), 
                   Arrays.asList(rolseArray), 
                   rs.getBoolean("status")
              );
            }

            return user;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
    
    public int updateSatusAndRoles(int id, Boolean status, String roles) throws SQLException, ClassNotFoundException, IOException {
        String sql = "UPDATE users SET status = ?, roles = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnectionProvider.getInstance().getConnection();

            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, status);
            stmt.setString(2, roles);
            stmt.setInt(3, id);
            
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

}
