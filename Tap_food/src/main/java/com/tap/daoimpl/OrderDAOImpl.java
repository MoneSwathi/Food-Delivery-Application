package com.tap.daoimpl;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;
import com.tap.dao.OrderDAO;
import com.tap.model.Orders;
import com.tap.utility.DBConnection;

public class OrderDAOImpl implements OrderDAO{
	private static final String INSERT_ORDER_QUERY="INSERT INTO `orders`(`userId`,`restaurantId`,`totalAmount`,`paymentMode`,`Address`)VALUES(?,?,?,?,?)";
	private static final String GET_ORDER_QUERY="SELECT * FROM `orders` WHERE `orderId`=?";
	//private static final String GET_ORDER_BY_USER_ID="SELECT * FROM `orders` WHERE `userId`=?";
	private static final String UPDATE_ORDER_QUERY="UPDATE `orders` SET `userId`=?,`restaurantId=`? ,`totalAmount`=?, `status`=?, `paymentMode`=?, Where `orderId`=?";
	private static final String DELETE_ORDER_QUERY="DELETE FROM `orders` WHERE `orderId`=?";
	private static final String GET_ALL_ORDERS="SELECT * FROM `orders` WHERE `userId`=?";
   public static Orders orders;
	@Override
	public int addOrder(Orders orders) {
		int orderId=-1;
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(INSERT_ORDER_QUERY,Statement.RETURN_GENERATED_KEYS)) {
			preparestatement.setInt(1, orders.getUserId());
			preparestatement.setInt(2, orders.getRestaurantId());
			preparestatement.setDouble(3, orders.getTotalAmount());
			preparestatement.setString(4, orders.getPaymentMode());
			preparestatement.setString(5, orders.getAddress());
			preparestatement.executeUpdate();
			ResultSet generatedKeys = preparestatement.getGeneratedKeys();
			if(generatedKeys.next()) {
				orderId = generatedKeys.getInt(1);
			}
			System.out.println("Generated orderId: " + orderId);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orderId;
		
	}

	@Override
	public Orders getOrder(int orderId) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(GET_ORDER_QUERY)) {
			preparestatement.setInt(1, orderId);
			ResultSet res=preparestatement.executeQuery();
			res.next();
		    orders=extractOrder(res);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	@Override
	public void updateOrder(Orders orders) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(UPDATE_ORDER_QUERY)) {
			preparestatement.setInt(1, orders.getUserId());
			preparestatement.setInt(2, orders.getRestaurantId());
			preparestatement.setDouble(3, orders.getTotalAmount());
			preparestatement.setString(4, orders.getStatus());
			preparestatement.setString(5, orders.getPaymentMode());
			preparestatement.setInt(6, orders.getOrderId());
			preparestatement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrder(int orderId) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(DELETE_ORDER_QUERY)) {
			preparestatement.setInt(1, orderId);
			preparestatement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Orders> getAllOrdersByUser(int userId) {
		ArrayList<Orders> orderList=new ArrayList<Orders>();
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(GET_ALL_ORDERS)) {
			preparestatement.setInt(1, userId);
			ResultSet res=preparestatement.executeQuery();
			while(res.next()) {
				orders=extractOrder(res);
				orderList.add(orders);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	   Orders extractOrder(ResultSet res) throws SQLException{
		int orderId=res.getInt("orderId");
		int userId=res.getInt("userId");
		int restaurantId=res.getInt("restaurantId");
		Timestamp orderTimestamp = null;
		try {
			orderTimestamp=res.getTimestamp("order_date");
		} catch (SQLException ignored) {}
		if(orderTimestamp==null) {
			try {
				orderTimestamp=res.getTimestamp("orderDate");
			} catch (SQLException ignored) {}
		}
		if(orderTimestamp==null) {
			orderTimestamp=new Timestamp(System.currentTimeMillis());
		}
		double totalAmount=res.getDouble("totalAmount");
		String status=null;
		try {
			status=res.getString("status");
		} catch (SQLException ignored) {}
		if(status==null||status.isBlank()) {
			status="Order Placed";
		}
		String paymentMode=null;
		String[] paymentColumns={"PaymentMode","paymentMode","payment_mode"};
		for(String column: paymentColumns) {
			if(paymentMode==null) {
				try {
					paymentMode=res.getString(column);
				} catch (SQLException ignored) {}
			}
		}
		if(paymentMode==null) {
			paymentMode="Not Specified";
		}
		String address=null;
		String[] addressColumns={"address","deliveryAddress","delivery_address"};
		for(String column: addressColumns) {
			if(address==null) {
				try {
					address=res.getString(column);
				} catch (SQLException ignored) {}
			}
		}
		if(address==null) {
			address="Address not available";
		}
		Orders orders=new Orders(userId, restaurantId, new java.util.Date(orderTimestamp.getTime()), totalAmount, status, paymentMode, address);
		orders.setOrderId(orderId);
		orders.setStatus(status);
		return orders;

	}

}

