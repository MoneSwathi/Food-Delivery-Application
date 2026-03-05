package com.tap.servlet;

import java.io.IOException;

import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		int newRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		Object currentRestaurantIdSession=session.getAttribute("restaurantId");
		
		
		if(currentRestaurantIdSession==null) {
			session.setAttribute("restaurantId",newRestaurantId);
		}
		int currentRestaurantId = (Integer)session.getAttribute("restaurantId");
		
		if(cart==null||newRestaurantId!=currentRestaurantId) {
			cart=new Cart();
			session.setAttribute("cart", cart);
			session.setAttribute("restaurantId", newRestaurantId);
		}
		String action = req.getParameter("action");
		if(action.equals("add")) {
			addItemToCart(req,cart);
		}
		else if(action.equals("update")) {
			updateCartItem(req,cart);
		}
		else if(action.equals("remove")) {
			removeItemFromCart(req,cart);
			
		}
		RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
		rd.forward(req, resp);
	}

	private void addItemToCart(HttpServletRequest req, Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String imagePath=req.getParameter("imagePath");
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		Menu menuItem = menuDAOImpl.getMenu(itemId);
		if(menuItem!=null) {
			CartItem item = new CartItem(menuItem.getMenuId(),menuItem.getItemName(),menuItem.getPrice(),quantity,menuItem.getRestaurantId(),imagePath);
			cart.addCartItem(item);
		}
	}
	private void updateCartItem(HttpServletRequest req, Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		cart.updateCartItem(itemId, quantity);
	}
	private void removeItemFromCart(HttpServletRequest req, Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		cart.removeCartItem(itemId);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}