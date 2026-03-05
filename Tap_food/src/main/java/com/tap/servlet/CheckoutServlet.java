package com.tap.servlet;

import java.io.IOException;

import com.tap.daoimpl.OrderDAOImpl;
import com.tap.daoimpl.OrderItemDAOImpl;
import com.tap.model.Orders;
import com.tap.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet{
	public OrderDAOImpl orderDAOImpl;
	public OrderItemDAOImpl orderItemDAOImpl;
	@Override
	public void init() throws ServletException {
		orderDAOImpl = new OrderDAOImpl();
	    orderItemDAOImpl = new OrderItemDAOImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		Integer userId = (Integer)session.getAttribute("userId");
		System.out.println(userId);
		if(cart!=null&& userId!=null && !cart.getItems().isEmpty()) {
			int restaurantId=(Integer)session.getAttribute("restaurantId");
			String payment = req.getParameter("payment");
			String address=req.getParameter("Street Address")+","+req.getParameter("City")+","+
			               req.getParameter("State")+","+req.getParameter("ZIP Code");
			Orders orders = new Orders();
			orders.setUserId(userId);
			orders.setRestaurantId(restaurantId);
			orders.setTotalAmount(cart.getTotalPrice());
			orders.setPaymentMode(payment);
			orders.setAddress(address);
			int orderId = orderDAOImpl.addOrder(orders);
			orders= orderDAOImpl.getOrder(orderId);
			if(orderId!=-1) {
				for(CartItem cartItem:cart.getItems().values()) {
					OrderItem orderItem = new OrderItem();
					orderItem.setOrderId(orderId);
					orderItem.setMenuId(cartItem.getId());
					orderItem.setQuantity(cartItem.getQuantity());
					orderItem.setTotalPrice(cartItem.getQuantity()*cartItem.getPrice());
					orderItemDAOImpl.addOrderItem(orderItem);
				}
				session.removeAttribute("cart");
				session.setAttribute("order", orders);
				session.setAttribute("orders", orders); // legacy support
				resp.sendRedirect("orderConfirmation.jsp");
			}
		}
		else {
			System.out.println("cart is null");
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}