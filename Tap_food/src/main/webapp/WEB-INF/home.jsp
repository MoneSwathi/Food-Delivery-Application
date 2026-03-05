<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="com.tap.model.Restaurant"%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodHunt - Restaurants</title>
    <meta name="theme-color" content="#0d6efd">
    <link rel="manifest" href="manifest.json">
    <link rel="icon" href="favicon.svg" type="image/svg+xml">
    <link rel="stylesheet" href="main.css">
    <link rel="stylesheet" href="home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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
        <a href="signout.jsp">SignOut</a>
        <a href="signin.jsp">SignIn</a>
        <a href="cart.jsp" class="cart-icon">
            <i class="fas fa-shopping-cart"></i>
        </a>
    </div>
  </nav>



    <main class="restaurants-container">
    <h1>Tap Foods</h1>
        <div class="restaurant-grid">
        
        <%
        List<Restaurant> restaurants=(List<Restaurant>)request.getAttribute("allRestaurants");
        if(restaurants != null && !restaurants.isEmpty()){
            for(Restaurant r:restaurants){
        %>
                <div class="restaurant-card">
                    <div class="restaurant-image">
                        <img src="<%=r.getImagePath()%>" alt="Restaurant Image">
                    </div>
                    <div class="restaurant-info">
                        <h2><%=r.getName() %></h2>
                        <div class="rating">
                            <i class="fas fa-star"></i>
                            <span><%=r.getRating() %></span><br>
                            <span><%=r.getEta() %></span>
                        </div>
                        <p><%=r.getCusineType() %></p>
                        <a href="menu?restaurantId=<%=r.getRestaurantId()%>" class="view-menu-btn">View Menu</a>
                    </div>
                </div>
        <%
            }
        } else {
        %>
            <div style="grid-column: 1 / -1; text-align:center; padding: 2rem;">
                <p>No restaurants to display.</p>
                <p><a href="home">Reload restaurants</a></p>
            </div>
        <%
        }
        %>
            
            </div>
    </main>
    <footer>
        <p>&copy; 2025 FoodHunt. All rights reserved.</p>
    </footer>
</body>
</html>