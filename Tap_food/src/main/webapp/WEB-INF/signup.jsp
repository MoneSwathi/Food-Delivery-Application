<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodHunt - Sign Up</title>
    <link rel="stylesheet" href="main.css">
    <link rel="stylesheet" href="sign.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <nav class="navbar">
        <div class="logo">FoodHunt</div>
        <input type="checkbox" id="nav-toggle" class="nav-toggle">
        <label for="nav-toggle" class="nav-toggle-label">
            <span></span>
        </label>
    </nav>

    <main class="auth-container">
        <div class="auth-form">
            <h1>Sign Up</h1>
            <% if (request.getAttribute("successMessage") != null) { %>
                <p style="color:green; text-align:center;"><%= request.getAttribute("successMessage") %></p>
            <% } %>
            
            <% if (request.getAttribute("errorMessage") != null) { %>
                <p style="color:red; text-align:center;"><%= request.getAttribute("errorMessage") %></p>
            <% } %>
            <form action="signup" method="post">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" id="address" name="address" required>
                </div>
                <div class="form-group">
                    <label for="role">Role</label>
                    <input type="search" id="role" name="role" required>
                </div>
                <button type="submit" class="auth-button">Sign Up</button>
            </form>
            <p class="auth-link">Already have an account? <a href="signin.jsp">Sign In</a></p>
        </div>
    </main>
</body>
</html>