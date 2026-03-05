<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tap.servlet.Cart"%>
<%@ page import="com.tap.servlet.CartItem"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="cart.css">
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
        <a href="orderHistory">Order History</a>
        <a href="signout.jsp">Sign Out</a>
        <a href="signin.jsp">Sign In</a>
        <a href="cart.jsp" class="cart-icon">
            <i class="fas fa-shopping-cart"></i>
        </a>
    </div>
  </nav>

    <div class="container">
        <div class="cart-container">
            <div class="cart-header">
                <i data-lucide="shopping-cart"></i>
                <h1>Your Cart</h1>
            </div>
            <% 
              Cart cart = (Cart) session.getAttribute("cart");
              Integer restaurantId = (Integer) session.getAttribute("restaurantId");
              if (cart != null && !cart.getItems().isEmpty()) {
                  for (CartItem item : cart.getItems().values()) {
            %>
            <div class="cart-items">
                <div class="cart-item">
                    <img src=<%=item.getImagePath()%> alt="<%= item.getName() %>">
                    <div class="item-details">
                        <h2><%= item.getName() %></h2>
                        <p class="price">Price:₹<%= String.format("%.2f", cart.getTotalPrice()) %></p>
                        <p class="price">Total Price:₹<%= String.format("%.2f", item.getPrice() * item.getQuantity())%></p>
                    </div>
                    <div class="item-controls">
                        <div class="quantity-controls">
                            <form action="cart" style="display:inline">
                                <input type="hidden" name="itemId" value="<%= item.getId() %>">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                                <input type="hidden" name="restaurantId" value="<%= item.getRestaurantId() %>">
                                <button class="quantity-btn">-</button>
                            </form>
                            <span><%= item.getQuantity() %></span>
                            <form action="cart" style="display:inline">
                                <input type="hidden" name="itemId" value="<%= item.getId() %>">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="restaurantId" value="<%= item.getRestaurantId() %>">
                                <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                                <button class="quantity-btn">+</button>
                            </form>
                        </div>
                        <form action="cart">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="itemId" value="<%= item.getId() %>">
                            <input type="hidden" name="restaurantId" value="<%= item.getRestaurantId() %>">
                            <button class="remove-btn" type="submit">
                                <i data-lucide="trash-2"></i>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <% 
                  }
              } else { 
            %>
            <div class="empty-cart">
                <h2>Your cart is empty</h2>
                <p>Add items to see them here!</p>
            </div>
            <% } %>
            <div class="cart-footer">
                <div class="total">Total Amount: ₹<%= cart != null ?  String.format("%.2f", cart.getTotalPrice()) : "0.00" %></div>
                <form action="menu">
                    <input type="hidden" name="restaurantId" value="<%= request.getParameter("restaurantId") %>">
                    <button class="add-more" type="submit">Add more Items</button>
                </form>
            </div>
            <form action="checkout.jsp">
            <button class="add-more">Proceed to checkout</button>
            </form>
        </div>
    </div>

    <script>
        // Initialize Lucide icons
        lucide.createIcons();
    </script>
</body>
</html>