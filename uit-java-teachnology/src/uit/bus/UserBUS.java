package uit.bus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uit.dao.UserDAO;
import uit.model.User;
import uit.modelview.FilterUserViewModel;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.PasswordUtils;

public class UserBUS {
    private UserBUS() {}
    private static UserBUS instance;
    public static UserBUS getInstance() {
        if (instance == null) {
            instance = new UserBUS();
        }
        return instance;
    }
    
    public User findByCode(String code) throws ClassNotFoundException, SQLException, IOException {
        User user = UserDAO.getInstance().findByCode(code);
        if (user != null) {
            user.setSalt(null);
            user.setPassword(null);
        }
        return user;
    }
    
    public User findByEmail(String email) throws ClassNotFoundException, SQLException, IOException {
        User user = UserDAO.getInstance().findByEmail(email);
        if (user != null) {
            user.setSalt(null);
            user.setPassword(null);
        }
        return user;
    }
    
    public User findByCodeAndPassword(String code, String pwd) throws ClassNotFoundException, SQLException, IOException {
        User user = null;
        user = UserDAO.getInstance().findByCode(code);

        if (user != null) {
            Boolean isMatch = PasswordUtils.verifyUserPassword(pwd, user.getPassword(), user.getSalt());
            if (isMatch) {
                user.setPassword(null);
                user.setSalt(null);
            } else {
                user = null;
            }
        }
        return user;
    }
    
    public IndexModelView<User> getIndexViewModel(int page, final FilterUserViewModel filter) throws ClassNotFoundException, SQLException, IOException {
        if (page < Constants.DEFAULT_PAGE_NUMBER) {
            page = Constants.DEFAULT_PAGE_NUMBER;
        }
        
        // Calculate skip and limit.
        int skip = (page - Constants.DEFAULT_PAGE_NUMBER) * Constants.NUMBER_ITEMS_PER_PAGE;
        int limit = Constants.NUMBER_ITEMS_PER_PAGE;    
        
        List<User> userList = null;
        int totalItems = 0;
        if (filter.hasFilter()) {
            userList = UserDAO.getInstance().search(skip, limit, filter);
            totalItems = UserDAO.getInstance().countWithFilter(filter);
        } else {
            userList = UserDAO.getInstance().getList(skip, limit);
            totalItems = UserDAO.getInstance().count();
        }
        
        IndexModelView<User> result = new IndexModelView<>(page, totalItems, limit, userList);
        
        return result;
    }
    
    public User findById(final int id) throws ClassNotFoundException, SQLException, IOException {
        return UserDAO.getInstance().findById(id);
    }
    
    public int updateStatusAndRole(final int id, final String[] roles, final Boolean status) throws Exception {
        for (String role : roles) {
            if (!role.equals(Constants.ADMIN_ROLE) 
             && !role.equals(Constants.LECTURER_ROLE)
             && !role.equals(Constants.STUDENT_ROLE)
             && !role.equals(Constants.HEAD_FACULY_ROLE)) {
                throw new Exception("Roles invalid: " + role);
            }
        }
        String updateRoles = String.join(Constants.ROLE_JOIN_DELIMITER, roles);
        return UserDAO.getInstance().updateSatusAndRoles(id, status, updateRoles);
    }
    
    public int create(User user) throws ClassNotFoundException, SQLException, IOException {
        user.setSalt(PasswordUtils.getSalt(Constants.DEF_SALT_LENGTH));
        user.setPassword(PasswordUtils.generateSecurePassword(user.getPassword(), user.getSalt()));

        return UserDAO.getInstance().create(user);
    }

    public int edit(String code, String pwd) throws ClassNotFoundException, SQLException, IOException {
    	User user = null;
        user = UserDAO.getInstance().findByCode(code);

		if (user != null) {
			
			user.setPassword(pwd);
			user.setSalt(PasswordUtils.getSalt(Constants.DEF_SALT_LENGTH));
	        user.setPassword(PasswordUtils.generateSecurePassword(user.getPassword(), user.getSalt()));
	        
		} else {
			user = null;
		}
		
        return UserDAO.getInstance().edit(user);
    }
}
