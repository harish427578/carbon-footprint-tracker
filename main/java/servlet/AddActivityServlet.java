package servlet;

import dao.ActivityDAO;
import model.Activity;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

@WebServlet("/addActivity")
public class AddActivityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            String type = req.getParameter("type");
            double qty = Double.parseDouble(req.getParameter("quantity"));

            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                out.println("<h3>Please login first!</h3>");
                out.println("<a href='login.html'>Go to Login</a>");
                return;
            }

            int userId = (Integer) session.getAttribute("userId");

            Activity a = new Activity();
            a.setUserId(userId);
            a.setType(type);
            a.setQuantity(qty);

            new ActivityDAO().addActivity(a);

            out.println("<h2>Activity Added Successfully!</h2>");
            out.println("<a href='addActivity.html'>Add More</a><br>");
            out.println("<a href='report'>View Report</a>");

        } catch (NumberFormatException e) {
            out.println("<h3>Invalid quantity format!</h3>");
            out.println("<a href='addActivity.html'>Try Again</a>");
        } catch (Exception e) {
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }
}
