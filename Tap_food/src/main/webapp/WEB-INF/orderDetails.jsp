<%@page import="com.tap.daoimpl.RestaurantDAOImpl"%>
<%@page import="com.tap.daoimpl.MenuDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.tap.daoimpl.OrderItemDAOImpl"%>
<%@page import="com.tap.daoimpl.OrderDAOImpl"%>
<%@page import="com.tap.model.Orders"%>
<%@page import="com.tap.model.OrderItem"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Menu"%>
<%@ page import="com.tap.model.Restaurant"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details</title>
    <link rel="stylesheet" href="orderDetails.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="container">
    <form action="orderHistory">
        <div class="back-arrow">
            <a href="orderHistory"><i class="fas fa-arrow-left"></i> Back to Orders</a>
        </div>
        </form>
        <%
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        	
        	    OrderDAOImpl orderDAO = new OrderDAOImpl();
        	    Orders order = orderDAO.getOrder(orderId);
        	    RestaurantDAOImpl rdi=new RestaurantDAOImpl();
        	    OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl();
        	    List<OrderItem> orderItems = orderItemDAO.getOrderItemsByOrder(orderId);
        %>
        
        <div class="order-details">
            <h1>Order Details</h1>
            <div class="order-id">
                <span>Order ID:</span>
                <span>#ORD300<%=orderId %></span>
            </div>
            <% 
                if (orderItems != null && !orderItems.isEmpty()) {
                    for (OrderItem item : orderItems) {
                    	MenuDAOImpl mdi=new MenuDAOImpl();
                    	Menu menu=mdi.getMenu(item.getMenuId());
            %>
            <div class="order-item">
                <div class="item-image">
                    <img src=<%=menu.getImagePath() %> alt="Menu Item">
                </div>
                <div class="item-details">
                    <h2><%=menu.getItemName() %></h2>
                    <div class="price">₹<%= String.format("%.2f", menu.getPrice()) %></div>
                    <%Restaurant restaurant=rdi.getRestaurant(menu.getRestaurantId()); %>
                    <p style="margin-bottom: 15px; font-size: 16px;"><%= restaurant.getName() %></p>
                    
                </div>
            </div>
            <% 
                    }
                }
            %>
            <div class="order-summary">
                <h3>Order Summary</h3>
                <div class="summary-item">
                    <span>Subtotal</span>
                   <span>₹<%= String.format("%.2f", order.getTotalAmount()) %></span>
                </div>
                <div class="summary-item">
                    <span>Delivery Fee</span>
                    <span>₹<%= String.format("%.2f", 5.0) %></span>
                </div>
                <div class="divider"></div>
                <div class="summary-item total">
                    <span>Total</span>
                    <span>₹<%= String.format("%.2f", order.getTotalAmount() + 5.0) %></span>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
    