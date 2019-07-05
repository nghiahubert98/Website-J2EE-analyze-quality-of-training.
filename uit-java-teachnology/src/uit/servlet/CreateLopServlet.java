package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.GiangVienBUS;
import uit.bus.HocKyBUS;
import uit.bus.LopBUS;
import uit.bus.MonHocBUS;
import uit.model.GiangVien;
import uit.model.HocKy;
import uit.model.Lop;
import uit.model.MonHoc;
import uit.modelview.IndexModelView;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;

@WebServlet("/lop-create")
public class CreateLopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(DashboardServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            IndexModelView<HocKy> listHK = HocKyBUS.getInstance().findForDropDown(Constants.DEFAULT_PAGE_NUMBER);
            request.setAttribute("listHK", listHK);
            IndexModelView<MonHoc> listMH = MonHocBUS.getInstance().findForDropDown(Constants.DEFAULT_PAGE_NUMBER);
            request.setAttribute("listMH", listMH);
            IndexModelView<GiangVien> listGV = GiangVienBUS.getInstance()
                    .findForDropDown(Constants.DEFAULT_PAGE_NUMBER);
            request.setAttribute("listGV", listGV);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/Lop/create.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.error("Could not get lop", ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Application Context path.
        String ctxPath = request.getContextPath();

        // Initialize a new instance of lop.
        String maLop = request.getParameter("maLop");
        int hocKyID = Integer.parseInt(request.getParameter("hocky"));
        int monHocID = Integer.parseInt(request.getParameter("monhoc"));
        int gvID = Integer.parseInt(request.getParameter("giangvien"));
        Lop Lop = new Lop(maLop, hocKyID, monHocID, gvID);

        try {

            /* In case input data valid (dataError == null) */
            if (Lop.validate(request)) {
                // Save new "lop" to database,
                LopBUS.getInstance().create(Lop);

                MessageSessionUtil.createSuccessMsg(request, "Bạn đã tạo thành công một lớp mới");
                // Redirect to last page.
                response.sendRedirect(ctxPath + "/lop?page=" + LopBUS.getInstance().getTotalPage());

            } else {
                response.sendRedirect(ctxPath + "/lop-create");
            }

        } catch (Exception ex) {
            LOG.error("Could not create Lop", ex);

            String mess = "Xảy ra lỗi không thể tạo lớp được, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng mã lớp không trùng với bất kỳ mã lớp nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, Lop);

            // redirect to /lop-create.
            response.sendRedirect(ctxPath + "/lop-create");
        }
    }

}
