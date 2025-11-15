package servlet;

import dao.UserDAO;
import model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            User u = new UserDAO().validateUser(email, password);
            if (u != null) {
                HttpSession session = req.getSession();
                session.setAttribute("userId", u.getId());
                out.println("<h2>Login successful!</h2>");
                out.println("<a href='addActivity.html'>Go to Add Activity</a>");
            } else {
                out.println("<h2>Invalid login, try again</h2>");
                out.println("<a href='login.html'>Back to Login</a>");
            }
        } catch (Exception e) {
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }
}
