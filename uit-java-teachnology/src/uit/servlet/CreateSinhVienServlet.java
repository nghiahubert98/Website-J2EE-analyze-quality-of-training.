package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.LopSinhHoatBUS;
import uit.bus.SinhVienBUS;
import uit.model.LopSinhHoat;
import uit.model.SinhVien;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;

@WebServlet("/sinhvien-create")
public class CreateSinhVienServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(DashboardServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            IndexModelView<LopSinhHoat> listLSH = LopSinhHoatBUS.getInstance()
                    .findForDropDown(Constants.DEFAULT_PAGE_NUMBER);
            request.setAttribute("listLSH", listLSH);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/SinhVien/create.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get sinh vien", ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Application Context path.
        String ctxPath = request.getContextPath();

        // Initialize a new instance of sinh vien.
        String tensinhvien = request.getParameter("ten_sinhvien");
        String mssv = request.getParameter("mssv");
        int maLopSH = Integer.parseInt(request.getParameter("lopsinhhoat"));
        SinhVien sinhvien = new SinhVien(mssv, tensinhvien, maLopSH);

        try {

            /* In case input data valid (dataError == null) */
            if (sinhvien.validate(request)) {
                // Save new "sinh vien" to database,
                SinhVienBUS.getInstance().create(sinhvien);

                MessageSessionUtil.createSuccessMsg(request, "Bạn đã tạo thành công một sinh viên mới");
                // Redirect to last page.
                response.sendRedirect(ctxPath + "/sinhvien?page=" + SinhVienBUS.getInstance().getTotalPage());

            } else {
                response.sendRedirect(ctxPath + "/sinhvien-create");
            }

        } catch (Exception ex) {
            LOG.error("Could not create sinhvien", ex);

            String mess = "Xảy ra lỗi không thể tạo sinh viên được, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng mã số sinhv viên không trùng với bất kỳ mã số sinh viên nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, sinhvien);

            // redirect to /sinh vien-create.
            response.sendRedirect(ctxPath + "/sinhvien-create");
        }
    }

}
