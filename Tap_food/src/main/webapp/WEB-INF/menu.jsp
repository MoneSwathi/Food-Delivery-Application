<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Menu"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Menu</title>
    <link rel="stylesheet" href="main.css">
    <link rel="stylesheet" href="menu.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body><nav class="navbar">
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
     
    <div class="menu-container">
    <h1>Satisfy Your Cravings with Our Menus</h1>
        <div class="menu-grid">    
       <%
         List<Menu>menuList=(List<Menu>)request.getAttribute("menus");
         for(Menu m:menuList){	 
        %>
            <div class="menu-item">
                <img src=<%=m.getImagePath() %> alt="Margherita Pizza">
                <div class="item-details">
                    <div class="item-header">
                        <h3><%=m.getItemName() %></h3>
                        <span class="price">₹<%=m.getPrice() %></span>
                    </div>
                    <div class="rating">★ <%=m.getRatings() %></div>
                    <p><%=m.getDescription() %></p>
                    <form action="cart">
                    <input type="hidden" name="itemId" value="<%=m.getMenuId()%>">
                    <input type="hidden" name="quantity" value="1">
                    <input type="hidden" name="restaurantId" value="<%=m.getRestaurantId()%>">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="imagePath" value="<%=m.getImagePath()%>">
                    <input type="submit" value="Add to Cart" class="add-to-cart">
                    </form>
                </div>
            </div>
            <% } %>
       </div>
    </div>
</body>
</html>