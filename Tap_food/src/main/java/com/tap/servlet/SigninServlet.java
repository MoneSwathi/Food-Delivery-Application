package com.tap.servlet;

import java.io.IOException;

import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        User user = userDAOImpl.getUser(email);
        
        if (user != null) {
            if (password.equals(user.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("userId", user.getUserId());
                System.out.println("UserId set in session: " + user.getUserId());
                resp.sendRedirect("home");
            } else {
                req.setAttribute("errorMessage", "Invalid password. Please try again.");
                req.getRequestDispatcher("signin.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "No account found. Please create an account.");
            req.getRequestDispatcher("signin.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }
}
