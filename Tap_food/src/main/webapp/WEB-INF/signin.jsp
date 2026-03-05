<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodHunt - Sign In</title>
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
            <h1>Sign In</h1>
            <% if (request.getAttribute("errorMessage") != null) { %>
               <p style="color:red; text-align:center"><%= request.getAttribute("errorMessage") %></p>
            <% } %>
             <% if (request.getAttribute("message") != null) { %>
               <p style="color:red; text-align:center"><%= request.getAttribute("message") %></p>
            <% } %>
            <form action="signin">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="auth-button">Sign In</button>
            </form>
            <p class="auth-link">Don't have an account? <a href="signup.jsp">Sign Up</a></p>
        </div>
    </main>
</body>
</html>