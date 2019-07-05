package uit.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import uit.bus.NganhBUS;
import uit.utils.ValidateUtils;

@WebServlet("/nganh-delete")
public class DeleteNganhServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(DeleteNganhServlet.class);

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/txt");
        try {
            if (ValidateUtils.canConvertToInt(req.getParameter("id"))) {
                // In case id valid get id
                // form request and perform delete Nganh from database.
                int id = Integer.parseInt(req.getParameter("id"));
                NganhBUS.getInstance().delete(id);

                resp.getWriter().println("Delete success!");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Id invalid!");
            }
        } catch (Exception ex) {
            LOG.error("Could not delete Nganh which id is " + req.getParameter("id"), ex);
            
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Server err");
        }

    }
}
