package com.tap.servlet;
import java.io.IOException;
import java.util.List;

import com.tap.daoimpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/home")
public class HomeServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		com.tap.daoimpl.RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
		List<Restaurant> allRestaurants = restaurantDAOImpl.getAllRestaurants();
		req.setAttribute("allRestaurants", allRestaurants);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");
		requestDispatcher.forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}