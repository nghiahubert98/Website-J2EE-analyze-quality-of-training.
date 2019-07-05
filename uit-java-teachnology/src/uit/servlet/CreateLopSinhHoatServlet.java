package uit.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.GiangVienBUS;
import uit.bus.LopSinhHoatBUS;
import uit.bus.NganhBUS;
import uit.model.GiangVien;
import uit.model.LopSinhHoat;
import uit.model.Nganh;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

@WebServlet("/lopsinhhoat-create")
public class CreateLopSinhHoatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(CreateLopSinhHoatServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            IndexModelView<GiangVien> listGV = GiangVienBUS.getInstance().findForDropDown(Constants.DEFAULT_PAGE_NUMBER);
            List<Nganh> listNganh = NganhBUS.getInstance().findAll();
            request.setAttribute("listGV", listGV);
            request.setAttribute("listNganh", listNganh);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/LopSinhHoat/create.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get Lop Sinh Hoat", ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Application Context path.
        String ctxPath = request.getContextPath();

        // Initialize a new instance of LopSinhHoat.
        String maLop = request.getParameter("maLop");
        String batDau = request.getParameter("batDau");
        String totNghiep = request.getParameter("totNghiep");
        String ten = request.getParameter("ten");
        if (!ValidateUtils.canConvertToInt(request.getParameter("covan"))) {
            MessageSessionUtil.createServerErrorMsg(request, "Xin hãy kiểm tra lại mã cố vấn của bạn", null);
            response.sendRedirect(ctxPath + "/lopsinhhoat-create");
            return;
        }
        if (!ValidateUtils.canConvertToInt(request.getParameter("nganh"))) {
            MessageSessionUtil.createServerErrorMsg(request, "Xin hãy kiểm tra lại mã ngành của bạn", null);
            response.sendRedirect(ctxPath + "/lopsinhhoat-create");
            return;
        }
        int coVanID = Integer.parseInt(request.getParameter("covan"));
        int  nganhID = Integer.parseInt(request.getParameter("nganh"));
        LopSinhHoat lopSinhHoat = new LopSinhHoat(maLop, batDau, totNghiep, ten, coVanID);
        lopSinhHoat.setIdNganh(nganhID);

        try {

            /* In case input data valid (dataError == null) */
            if (lopSinhHoat.validate(request)) {
                // Save new "LopSinhHoat" to database,
                LopSinhHoatBUS.getInstance().create(lopSinhHoat);

                MessageSessionUtil.createSuccessMsg(request, "Bạn đã tạo thành công một lớp sinh hoạt mới");
                // Redirect to last page.
                response.sendRedirect(ctxPath + "/lopsinhhoat?page=" + LopSinhHoatBUS.getInstance().getTotalPage());

            } else {
                response.sendRedirect(ctxPath + "/lopsinhhoat-create");
            }

        } catch (Exception ex) {
            LOG.error("Could not create Lop Sinh Hoat", ex);

            String mess = "Xảy ra lỗi không thể tạo lớp sinh hoạt được, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng mã lớp sinh hoạt không trùng với bất kỳ mã lớp nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, lopSinhHoat);

            // redirect to /lop sinh hoat-create.
            response.sendRedirect(ctxPath + "/lopsinhhoat-create");
        }
    }

}
