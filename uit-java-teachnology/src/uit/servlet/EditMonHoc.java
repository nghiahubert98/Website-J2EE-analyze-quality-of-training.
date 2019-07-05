package uit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import uit.bus.MonHocBUS;
import uit.model.MonHoc;
import uit.utils.Constants;
import uit.utils.MessageSessionUtil;
import uit.utils.ValidateUtils;

@WebServlet("/monhoc-edit")
public class EditMonHoc extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(EditMonHoc.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // Page of list MonHocs
            // using redirect after edit success.
            int backPage = Constants.DEFAULT_PAGE_NUMBER;
            if (ValidateUtils.canConvertToInt(req.getParameter("backPage"))) {
                backPage = Integer.parseInt(req.getParameter("backPage"));
            }

            if (ValidateUtils.canConvertToInt(req.getParameter("id"))) {
                int id = Integer.parseInt(req.getParameter("id"));
                MonHoc MonHoc = MonHocBUS.getInstance().findById(id);

                if (MonHoc != null) {
                    req.setAttribute("data", MonHoc);
                    req.setAttribute("backPage", backPage);

                    // Get edit.jsp dispatcher and forward.
                    RequestDispatcher dispatcher = req.getRequestDispatcher("templates/MonHoc/edit.jsp");
                    dispatcher.forward(req, resp);

                    return;
                }
            }

            // Invalid queryString => set status code 404 and return 404 page.

            LOG.info("Id = " + req.getParameter("id") + "invalid");

            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);

            RequestDispatcher dispatcher = req.getRequestDispatcher("templates/404.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception ex) {
            // Serve error set status code is 500 and return 500 page.

            LOG.error("Could not load edit page.", ex);

            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);

            RequestDispatcher dispatcher = req.getRequestDispatcher("templates/500.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ctxPath = request.getContextPath();
        String tenMonHoc = request.getParameter("name");
        String maMonHoc = request.getParameter("maMonHoc");

        MonHoc MonHoc = new MonHoc(tenMonHoc, maMonHoc);

        if (ValidateUtils.canConvertToInt(request.getParameter("id"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            MonHoc.setId(id);
            MonHoc.setName(tenMonHoc);
            MonHoc.setMaMonHoc(maMonHoc);
        }

        int page = Constants.DEFAULT_PAGE_NUMBER;
        if (ValidateUtils.canConvertToInt(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
            if (MonHoc.validate(request)) {

                MonHocBUS.getInstance().update(MonHoc);

                // Set success message to session
                HttpSession session = request.getSession(true);
                session.setAttribute("success", "Bạn đã cập nhật thông tin thành công");

                // Redirect to last page.
                response.sendRedirect(ctxPath + "/monhoc?page=" + page);
            } else {

                response.sendRedirect(ctxPath + "/monhoc-edit?id=" + MonHoc.getId() + "&backPage=" + page);
            }

        } catch (Exception ex) {
            LOG.error("Could not create Mon Hoc", ex);

            String mess = "Xảy ra lỗi trong quá trình cập nhật dữ liệu, Xin hãy kiểm tra lại dữ liệu đầu vào "
                    + "và chắc chắn rằng mã môn học không trùng với bất kỳ mã môn học nào trong hệ thống hiện tại";

            // Set error into session. Prepare to display in redirect page.
            MessageSessionUtil.createServerErrorMsg(request, mess, MonHoc);

            // redirect to /MonHoc-create.
            response.sendRedirect(ctxPath + "/monhoc-edit?id=" + MonHoc.getId() + "&backPage=" + page);
        }
    }

}
