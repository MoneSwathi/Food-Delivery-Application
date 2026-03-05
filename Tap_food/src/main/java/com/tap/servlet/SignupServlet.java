package com.tap.servlet;
import java.io.IOException;


import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		
		String name=req.getParameter("name");
		String userName=req.getParameter("username");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String phone=req.getParameter("phone");
		String address=req.getParameter("address");
		String role=req.getParameter("role");
		User user = new User(0,name,userName,password,email,phone,address,role);
		userDAOImpl.addUser(user);
		
		boolean isUserAdded = userDAOImpl.addUser(user); 

        if (isUserAdded) {
            req.setAttribute("successMessage", "Account created successfully! You can now sign in.");
        } else {
            req.setAttribute("errorMessage", "Failed to create account. Please try again.");
        }

        req.getRequestDispatcher("signup.jsp").forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
