package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.utility.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO{
	private static final String INSERT_ORDERITEM_QUERY="INSERT INTO `orderItem`(`orderId`,`menuId`,`quantity`,`totalPrice`) VALUES(?,?,?,?)";
	private static final String GET_ORDERITEM_QUERY="SELECT * FROM `orderItem` WHERE `orderItemId`=?";
	private static final String UPDATE_ORDERITEM_QUERY="UPDATE `orderItem` SET `orderId`=? `menuId`=? `quantity`=? `totalPrice`=? WHERE `orderItemId`=?";
	private static final String DELETE_ORDERITEM_QUERY="DELETE FROM `orderItem` WHERE `orderItem`=?";
    private static final String GET_ALL_ORDERITEMS_QUERY="SELECT * FROM `orderItem`WHERE `orderId`=?";
	@Override
	public void addOrderItem(OrderItem orderItem) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(INSERT_ORDERITEM_QUERY)) {
			preparestatement.setInt(1, orderItem.getOrderId());
			preparestatement.setInt(2, orderItem.getMenuId());
			preparestatement.setInt(3, orderItem.getQuantity());
			preparestatement.setDouble(4, orderItem.getTotalPrice());
			preparestatement.executeUpdate();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		OrderItem orderItem=null;
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(GET_ORDERITEM_QUERY)) {
			preparestatement.setInt(1, orderItemId);
			ResultSet res=preparestatement.executeQuery();
			while(res.next()) {
				orderItem=extractOrderItem(res);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItem;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(UPDATE_ORDERITEM_QUERY)) {
			preparestatement.setInt(1, orderItem.getOrderId());
			preparestatement.setInt(2, orderItem.getMenuId());
			preparestatement.setInt(3, orderItem.getQuantity());
			preparestatement.setDouble(4, orderItem.getTotalPrice());
			preparestatement.setInt(6, orderItem.getOrderId());
			preparestatement.executeUpdate();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderItem(int orderItemId) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(DELETE_ORDERITEM_QUERY)) {
			preparestatement.setInt(1, orderItemId);
			preparestatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderItem> getOrderItemsByOrder(int orderId) {
		ArrayList<OrderItem>orderItemList=new ArrayList();
		OrderItem orderItem=null;
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(GET_ALL_ORDERITEMS_QUERY)) {
			preparestatement.setInt(1, orderId);
			ResultSet res=preparestatement.executeQuery();
			while(res.next()) {
				orderItem=extractOrderItem(res);
				orderItemList.add(orderItem);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItemList;
	}
	OrderItem extractOrderItem(ResultSet res) throws SQLException{
		int orderItemId=res.getInt("orderItemId");
		int orderId=res.getInt("orderId");
		int menuId=res.getInt("menuId");
		int quantity=res.getInt("quantity");
		double totalPrice=res.getDouble("totalPrice");
		OrderItem orderItem=new OrderItem(orderId, menuId, quantity, totalPrice);
		return orderItem;
		
	}

}