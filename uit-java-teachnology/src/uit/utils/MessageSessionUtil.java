package uit.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MessageSessionUtil {    
   
    public static void createServerErrorMsg(final HttpServletRequest req, final String mess, final Object postData) {
        Map<String, String> error = new HashMap<String, String>();
        error.put("svError", mess);

        createErrorMsg(req, error, postData);
    }

    /**
     * Add error to session when post request data invalid.
     * This message will be obtained from {@link #processErrorMsg(HttpServletRequest)}
     * @param dataError A map contains all error.
     * @param postData The data was posted.
     * @param request {@link HttpServletRequest}
     */
    public static void createErrorMsg(final HttpServletRequest request, final Map<String, String> dataError, final Object postData) {
        HttpSession session = request.getSession(true);
        session.setAttribute("error", dataError);
        if (postData != null) {
            session.setAttribute("oldData", postData);
        }
    }
    
    /**
     * Get error form session and set to request attribute.
     * All error will was get from session attribute called error
     * and set to request attribute named error,
     * The old data (Data form last port request) will set to request attribute oldData.
     * @param req
     */
    public static void processErrorMsg(final HttpServletRequest req) {
        HttpSession session = req.getSession(false);
      
        if (session != null && session.getAttribute("error") != null) {
            /* In case: request session has error key.
             * => This is request which was redirected form doPost. */
            
            // Receive error data and old input form session.
            Object error = session.getAttribute("error");
            Object oldData = session.getAttribute("oldData");

            // Remove session error.
            session.removeAttribute("oldData");
            session.removeAttribute("error");

            // Set error attribute to send to JSP.
            req.setAttribute("oldData", oldData);
            req.setAttribute("error", error);
        }         
    }
    
    /**
     * Set success message before redirect page.
     * This method usually is used to add a message after perform success a action 
     * such as "create", "edit", "delete".... A session attribute called "success" will
     * be used to add message before redirect to another page. This message will be proccessed
     * by method {@link #processErrorMsg(HttpServletRequest)}
     * @param request {@link HttpServletRequest}
     * @param message
     * @see {@link #processSuccessMsg(HttpServletRequest)}
     */
    public static void createSuccessMsg(final HttpServletRequest request, final String message) {
        HttpSession session = request.getSession(true);
        session.setAttribute("success", message);
    }
    
    /**
     * This method will get success message form a session attribute called "success".
     * This session usually is used to add a message after perform success a action 
     * such as "create", "edit", "delete"....
     * After call this method, if has success message, this method will set a request attribute
     * called "success" and we call access with syntax ${success} in jsp file. 
     * @see {@link #createSuccessMsg(HttpServletRequest, String)}
     * @param request {@link HttpServletRequest}
     */
    public static void processSuccessMsg(final HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        if (session != null && session.getAttribute("success") != null) {
            String success = (String) session.getAttribute("success");                
            
            // Remove session error.
            session.removeAttribute("success");
            request.setAttribute("success", success);
        }
    }
}
