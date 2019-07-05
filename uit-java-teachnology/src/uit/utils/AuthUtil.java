package uit.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import uit.model.User;

public class AuthUtil {

    public static Boolean isLoggined(HttpServletRequest req) {
        User loginedUser = AuthUtil.getLoginedUser(req);
        if (loginedUser != null) {
            return true;
        } else {
            return false;
        }
    }

    public static User getLoginedUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }

    public static void storeLoginedUser(final HttpSession session, final User loginedUser) {
        // Get loginedUser in jsp file with ${loginedUser}.
        session.setAttribute("loginedUser", loginedUser);
    }

    /**
     * Clear all session attribute and logout system.
     * 
     * @param session {@link HttpSession}
     */
    public static void logoutSystem(final HttpSession session) {
        session.invalidate();
    }

    /**
     * Check permission of user.
     * 
     * @param path
     * @param loggedUser
     * @return
     */
    public static Boolean adminRole(final String path, final User loggedUser) {
        if (loggedUser.getRoles().contains(Constants.ADMIN_ROLE)) {
            return true;
        }
        return false;
    }
    
    public static Boolean headerFacultyRole(final String path, final User loggedUser) {
        if (loggedUser.getRoles().contains(Constants.HEAD_FACULY_ROLE)) {
            return true;
        }
        return false;
    }
    
    public static Boolean lecturerRole(final String path, final User loggedUser) {
        if (loggedUser.getRoles().contains(Constants.LECTURER_ROLE)) {
            return true;
        }
        return false;
    }
    
    public static Boolean studentRole(final String path, final User loggedUser) {
        if (loggedUser.getRoles().contains(Constants.STUDENT_ROLE)){
            return true;
        }
        return false;
    }

    public static void storeUserInfoForFirstLogin(final HttpServletRequest req, final User user) {
        HttpSession session = req.getSession();
        session.setAttribute("firtLoginInfo", user);
    }

    public static void clearUserFirstLoginInfo(final HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.removeAttribute("firtLoginInfo");
    }

    public static User getUserFirtLoginInfo(final HttpServletRequest req) {
        HttpSession session = req.getSession();
        User result = (User) session.getAttribute("firtLoginInfo");

        return result;
    }
}
