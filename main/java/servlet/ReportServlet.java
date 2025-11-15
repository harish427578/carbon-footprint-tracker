package servlet;

import dao.ActivityDAO;
import model.Activity;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.List;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                out.println("<h3>Please login first!</h3>");
                out.println("<a href='login.html'>Go to Login</a>");
                return;
            }

            int userId = (Integer) session.getAttribute("userId");
            List<Activity> activities = new ActivityDAO().getActivitiesByUser(userId);

            double totalCO2 = 0;
            out.println("<h2>Monthly Carbon Footprint Report</h2>");
            out.println("<table border='1'><tr><th>Type</th><th>Quantity</th><th>CO₂ (kg)</th></tr>");

            for (Activity a : activities) {
                double co2 = 0;
                switch (a.getType()) {
                    case "Car": co2 = a.getQuantity() * 0.12; break;
                    case "Bus": co2 = a.getQuantity() * 0.05; break;
                    case "Flight": co2 = a.getQuantity() * 0.25; break;
                    case "Electricity": co2 = a.getQuantity() * 0.85; break;
                }
                totalCO2 += co2;
                out.println("<tr><td>" + a.getType() + "</td><td>" + a.getQuantity() + "</td><td>" + co2 + "</td></tr>");
            }

            out.println("</table>");
            out.println("<h3>Total CO₂ Emission: " + totalCO2 + " kg</h3>");
            out.println("<a href='addActivity.html'>Add More Activities</a>");

        } catch (Exception e) {
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }
}
