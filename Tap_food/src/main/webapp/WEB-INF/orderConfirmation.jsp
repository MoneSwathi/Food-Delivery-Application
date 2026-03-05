<%@page import="com.tap.daoimpl.OrderItemDAOImpl"%>
<%@page import="com.tap.daoimpl.RestaurantDAOImpl"%>
<%@page import="com.tap.dao.RestaurantDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.tap.model.Orders"%>
 <%@page import="com.tap.model.Restaurant"%>
 <%@ page import="java.util.List" %>
 <%@page import="com.tap.model.OrderItem"%>
 <%@page import="com.tap.daoimpl.MenuDAOImpl"%>
 <%@ page import="com.tap.model.Menu"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmed - Food Delivery</title>
    <link rel="stylesheet" href="orderConfirmation.css">
    <link rel="stylesheet" href="main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>
    <nav class="navbar">
    <div class="logo">FoodHunt</div>
    <input type="checkbox" id="nav-toggle" class="nav-toggle">
    <label for="nav-toggle" class="nav-toggle-label">
        <span></span>
    </label>
    <div class="nav-links">
        <a href="home">Home</a>
        <a href="orderHistory">My Orders</a>
        <a href="cart.jsp" class="cart-icon">
            <i class="fas fa-shopping-cart"></i>
        </a>
    </div>
  </nav>
    <%
    Orders order=(Orders)session.getAttribute("order");
    if(order==null){
        order=(Orders)session.getAttribute("orders"); // fallback to attribute set in CheckoutServlet
        if(order!=null){
            session.setAttribute("order", order); // normalize for future requests
        }
    }
    Integer restaurantIdObj=(Integer)session.getAttribute("restaurantId"); 
    Restaurant restaurant=null;
    if(restaurantIdObj!=null){
        RestaurantDAOImpl rdao=new RestaurantDAOImpl();
        restaurant=rdao.getRestaurant(restaurantIdObj);
    }
    %>
    
    <div class="container">
        <div class="confirmation-card">
            <!-- Success Icon -->
            <div class="success-icon">✓</div>
            
            <!-- Order Status -->
            <h1>Order Confirmed!</h1>
            <%
                if(order!=null){
            %>
                <p class="order-id">Order ID:#ORD300<%=order.getOrderId() %></p>
            <%
                } else {
            %>
                <p class="order-id">Order ID is unavailable. Please check your order history.</p>
            <%
                }
            %>
            
            <!-- Estimated Time -->
            <div class="delivery-time">
                <h2>Estimated Delivery Time</h2>
                <p><%=restaurant!=null ? restaurant.getEta() : "N/A" %> mins</p>
            </div>

            <!-- Order Details -->
            <div class="order-details">
                <h2>Order Details</h2>
                <div class="detail-row">
                    <span>Delivery Address:</span>
                    <p><%=order!=null ? order.getAddress() : "Address unavailable" %></p>
                </div>
                <div class="detail-row">
                    <span>Payment Method:</span>
                    <p><%=order!=null ? order.getPaymentMode() : "Payment info unavailable" %></p>
                </div>
                <div class="detail-row">
                    <span>Order Amount:</span>
                    <p>₹<%= order!=null ? String.format("%.2f", order.getTotalAmount() + 5.0) : "0.00" %></p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>