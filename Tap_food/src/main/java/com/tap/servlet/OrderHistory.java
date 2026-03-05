package com.tap.servlet;

import java.io.IOException;
import java.util.List;


import com.tap.model.Orders;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/orderHistory")
public class OrderHistory extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		com.tap.daoimpl.OrderDAOImpl orderDAOImpl = new com.tap.daoimpl.OrderDAOImpl();
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("userId") == null) {
			req.setAttribute("errorMessage", "Please sign in to view your orders.");
			req.getRequestDispatcher("signin.jsp").forward(req, resp);
			return;
		}
		Integer userId = (Integer)session.getAttribute("userId");
		List<Orders> orders = orderDAOImpl.getAllOrdersByUser(userId);
		if (orders == null || orders.isEmpty()) {
			Orders recentOrder = (Orders) session.getAttribute("order");
			if (recentOrder != null && recentOrder.getUserId() == userId) {
				orders = new java.util.ArrayList<>();
				orders.add(recentOrder);
			}
		}
		System.out.println("Order history request for userId " + userId + ", total orders: " + (orders != null ? orders.size() : 0));
		req.setAttribute("orders", orders);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("orderHistory.jsp");
		requestDispatcher.forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
