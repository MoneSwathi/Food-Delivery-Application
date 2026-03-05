<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.tap.daoimpl.RestaurantDAOImpl"%>
<%@page import="com.tap.daoimpl.OrderItemDAOImpl"%>

<%@page import="com.tap.model.Restaurant"%>
<%@page import="com.tap.model.Orders"%>
<%@page import="com.tap.model.OrderItem"%>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<script src="https://unpkg.com/lucide@latest"></script>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="orderHistory.css">
	</head>
<body>
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
        <a href="signout.jsp">Sign Out</a>
        <a href="signin.jsp">Sign In</a>
        <a href="cart.jsp" class="cart-icon">
            <i class="fas fa-shopping-cart"></i>
        </a>
    </div>
  </nav>
  <div class="container">
    <h1>Your Orders</h1>
    <div class="orders-grid">
    <%
    List<Orders> orders = (List<Orders>) request.getAttribute("orders");
              if (orders != null && !orders.isEmpty()) {
                  for (Orders order : orders) {
                      int rid = order.getRestaurantId();
                      RestaurantDAOImpl rdo = new RestaurantDAOImpl();
                      Restaurant restaurant = rdo.getRestaurant(rid);

                      // Fetch order items
                      OrderItemDAOImpl oido = new OrderItemDAOImpl();
                      List<OrderItem> orderItems = oido.getOrderItemsByOrder(order.getOrderId());

                      double totalAmount = 0;
    %>
      <div class="order-card">
        <div class="order-header">
          <div class="order-info">
            <span class="info-label">Order ID</span>
            <span class="info-value">#ORD300<%=order.getOrderId() %></span>
          </div>
          <div class="order-info">
            <span class="info-label">Restaurant</span>
            <span class="info-value"><%=restaurant != null ? restaurant.getName() : "Unknown Restaurant" %></span>
          </div>
          <div class="order-info">
            <span class="info-label">Status</span>
            <span class="status-badge status-delivered"><%=order.getStatus() != null ? order.getStatus() : "Order Placed" %></span>
          </div>
          <div class="order-info">
            <span class="info-label">Delivery Date</span>
            <span class="info-value"><%=order.getOrderDate() != null ? order.getOrderDate() : "N/A" %></span>
          </div>
        </div>
        <div class="order-footer">
        <form action="orderDetails.jsp">
          <input type="hidden" name="orderId" value=<%=order.getOrderId() %>>
          <button class="btn">
            Order Details
          </button>
          </form>
        </div>
      </div>
      <%  
              }
          } else {
          %>
            <h3 class="no-orders" style="text-align: center; color: red;">No orders found.</h3>
          <% 
          }
          %>
    </div>
  </div>
</body>
</body>
</html>