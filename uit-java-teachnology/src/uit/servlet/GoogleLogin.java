package uit.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.GiangVienBUS;
import uit.bus.SinhVienBUS;
import uit.bus.UserBUS;
import uit.model.GiangVien;
import uit.model.SinhVien;
import uit.model.User;
import uit.modelview.GooglePojo;
import uit.utils.AuthUtil;
import uit.utils.Constants;
import uit.utils.GoogleUtil;
import uit.utils.MessageSessionUtil;
import uit.utils.PropertiesUtil;

@WebServlet("/login-google")
public class GoogleLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(GoogleLogin.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ctx = request.getContextPath();
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            response.sendRedirect(ctx + "/login");
        } else {
            try {
                String accessToken = GoogleUtil.getToken(code);
                GooglePojo googlePojo = GoogleUtil.getUserInfo(accessToken);
                
                String hostDomain = PropertiesUtil.getProperties().getProperty("GOOGLE_HD");
                // Check login email must match with .*?@uit.edu.vn
                String validateEmailPattern = "^.*?@" + hostDomain + "$";
                if (!googlePojo.getEmail().matches(validateEmailPattern)) {
                    MessageSessionUtil.createServerErrorMsg(request,
                            "Email bạn dụng để đăng nhập không đúng, Hãy xử dụng Email được cấp", null);
                    response.sendRedirect(ctx + "/login");

                    return;
                }

                User user = UserBUS.getInstance().findByEmail( googlePojo.getEmail());
                if (user == null) {
                    String userCode = googlePojo.getEmail().replace("@" + hostDomain, "");                    
                    SinhVien sv = SinhVienBUS.getInstance().findByCode(userCode);
                    if (sv != null) { // user is SV
                        user = new User();
                        user.setCode(sv.getMssv());
                        user.setName(sv.getTen());
                        user.setUserType(Constants.SV_USER);
                        user.setRoles(Arrays.asList(Constants.STUDENT_ROLE));
                    } else {
                        GiangVien gv = GiangVienBUS.getInstance().findByEmail(googlePojo.getEmail());
                        if (gv != null) { // Set user info is GV
                            user = new User();
                            user.setCode(gv.getCode());
                            user.setName(gv.getName());
                            user.setUserType(Constants.GV_USER);
                            user.setRoles(Arrays.asList(Constants.LECTURER_ROLE));
                        } else {
                            MessageSessionUtil.createServerErrorMsg(request,
                                    "Xảy ra lỗi, không thể login với tài khoản Google của bạn", null);
                            response.sendRedirect(ctx + "/login");

                            return;
                        }
                    }

                    // The first login: Not exits account in user table.
                    AuthUtil.storeUserInfoForFirstLogin(request, user);
                    response.sendRedirect(ctx + "/first-login");
                } else {
                    AuthUtil.storeLoginedUser(request.getSession(), user);
                    response.sendRedirect(ctx);
                }
            } catch (Exception ex) {
                LOG.error("Could not login with google", ex);
                MessageSessionUtil.createServerErrorMsg(request,
                        "Xảy ra lỗi, không thể login với tài khoản Google của bạn", null);
                response.sendRedirect(ctx + "/login");
            }
        }
    }
}
