package servlet;

import dao.UserDAO;
import model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/register")  // 👈 this makes /register work
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            User u = new User();
            u.setName(name);
            u.setEmail(email);
            u.setPassword(password);

            new UserDAO().addUser(u);  // insert user into DB

            out.println("<h2>Registration Successful!</h2>");
            out.println("<a href='login.html'>Go to Login</a>");
        } catch(Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
            e.printStackTrace(out);
        }
    }
}
