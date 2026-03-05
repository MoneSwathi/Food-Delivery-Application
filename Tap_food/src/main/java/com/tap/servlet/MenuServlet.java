package com.tap.servlet;
import java.io.IOException;
import java.util.List;

import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/menu")
public class MenuServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rid=Integer.parseInt(req.getParameter("restaurantId"));
		com.tap.daoimpl.MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		List<Menu> menuList = menuDAOImpl.getAllMenusByRestaurant(rid);
		req.setAttribute("menus", menuList);
		RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
