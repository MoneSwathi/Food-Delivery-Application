<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.tap.servlet.Cart"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout Page</title>
    <link rel="stylesheet" href="checkout.css">
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
        <a href="signout.jsp">Sign Out</a>
        <a href="signin.jsp">Sign In</a>
        <a href="cart.jsp" class="cart-icon">
            <i class="fas fa-shopping-cart"></i>
        </a>
    </div>
  </nav>
    <div class="container">
        <div class="header">
            <h1>Checkout</h1>
            <p>Complete your order</p>
        </div>

        <div class="progress-bar">
            <div class="progress-step">
                <div class="progress-icon">🚚</div>
                <span>Delivery</span>
            </div>
            <div class="progress-line"></div>
            <div class="progress-step">
                <div class="progress-icon">💳</div>
                <span>Payment</span>
            </div>
        </div>
        <form action="checkout">
        <div class="main-content">
            <div class="left-column">
                <!-- Delivery Address Section -->
                <div class="card">
                    <div class="section-header">
                        <span>📍</span>
                        <h2>Delivery Address</h2>
                    </div>
                        <div class="form-grid form-grid-2">
                            <div class="form-group">
                                <label class="form-label">First Name</label>
                                <input type="text" class="form-input">
                            </div>
                            <div class="form-group">
                                <label class="form-label">Last Name</label>
                                <input type="text" class="form-input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Street Address</label>
                            <input type="text" class="form-input" name="Street Address">
                        </div>
                        <div class="form-grid form-grid-3">
                            <div class="form-group">
                                <label class="form-label">City</label>
                                <input type="text" class="form-input" name="City">
                            </div>
                            <div class="form-group">
                                <label class="form-label">State</label>
                                <input type="text" class="form-input" name="State">
                            </div>
                            <div class="form-group">
                                <label class="form-label">ZIP Code</label>
                                <input type="text" class="form-input" name="ZIP Code">
                            </div>
                        </div>
                    </form>
                </div>

                <!-- Payment Section -->
                <div class="card">
                    <div class="section-header">
                        <span>💳</span>
                        <h2>Payment Method</h2>
                    </div>
                    <div class="payment-option">
                        <input type="radio" name="payment" value="card" checked>
                        <div class="payment-option-text">
                            <label>Credit Card</label>
                            <p>Pay with Visa, Mastercard, or American Express</p>
                        </div>
                    </div>
                    <div class="payment-option">
                        <input type="radio" name="payment" value="UPI">
                        <div class="payment-option-text">
                            <label>phone pay</label>
                            <p>Pay with your Phone pay</p>
                        </div>
                    </div>
                    <div class="payment-option">
                        <input type="radio" name="payment" value="COD">
                        <div class="payment-option-text">
                            <label>Cash On Delivery</label>
                            <p>Pay by cash</p>
                        </div>
                </div>
            </div>

            <!-- Order Summary -->
            <%Cart cart=(Cart)session.getAttribute("cart"); %>
            <div class="right-column">
                <div class="card">
                    <h2 class="section-header">Order Summary</h2>
                    <div class="summary-item">
                        <span>Subtotal</span>
                        <span>Rs. <%= String.format("%.2f", cart.getTotalPrice()) %></span>
                    </div>
                    <div class="summary-item">
                        <span>Shipping</span>
                        <span>Rs.5.00</span>
                    </div>
                    <div class="summary-item summary-total">
                        <span>Total</span>
                        <span>Rs.<%= String.format("%.2f", cart.getTotalPrice()+5.00) %></span>
                    </div>
                    <button class="place-order-btn">Place Order</button>
                </div>
            </div>
        </div>
        </form>
    </div>
</body>
</html>