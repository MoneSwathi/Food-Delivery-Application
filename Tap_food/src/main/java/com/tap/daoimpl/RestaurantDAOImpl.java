package com.tap.daoimpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {
	private static final String INSERT_RESTAURANT_QUERY= "INSERT INTO `restaurant`(`name`,`address`,`phone`,`rating`,`cusineType`,`isActive`,`eta`,`adminUserId``imagePath=?) VALUES(?,?,?,?,?,?,?,?)";
	private static final String GET_RESTAURANT_QUERY="SELECT * FROM `restaurant` WHERE `restaurantId`=?";
	private static final String UPDATE_RESTAURANT_QUERY="UPDATE `restaurant` SET `name`=? `address`=? `phone`=? `rating`=? `cusineType`=? `isActive`=? `eta`=? `adminUserId=? WHERE `restaurantId`=? ";
	private static final String DELETE_RESTAURANT_QUERY="DELETE FROM `restaurant` WHERE `restaurantId`=?";
	private static final String GET_ALL_RESTAURANTS="SELECT * FROM `restaurant`";
	@Override
	public void addRestaurant(Restaurant restaurant) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(INSERT_RESTAURANT_QUERY)) {
			preparestatement.setString(1,restaurant.getName());
			preparestatement.setString(2,restaurant.getAddress());
			preparestatement.setLong(3,restaurant.getPhone());
			preparestatement.setFloat(4,restaurant.getRating());
			preparestatement.setString(5,restaurant.getCusineType());
			preparestatement.setBoolean(6,restaurant.isActive());
			preparestatement.setString(7,restaurant.getEta());
			preparestatement.setInt(8,restaurant.getAdminUserId());
			preparestatement.setString(9, restaurant.getImagePath());
			preparestatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
	    Restaurant restaurant = null;
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT_QUERY)) {
	        
	        preparedStatement.setInt(1, restaurantId);
	        ResultSet res = preparedStatement.executeQuery();

	        if (res.next()) { // Check if a row is present
	            restaurant = extractRestaurant(res);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return restaurant; // Returns null if no restaurant found
	}


	@Override
	public void updateRestaurant(Restaurant restaurant) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(UPDATE_RESTAURANT_QUERY)) {
			preparestatement.setString(1,restaurant.getName());
			preparestatement.setString(2,restaurant.getAddress());
			preparestatement.setLong(3,restaurant.getPhone());
			preparestatement.setFloat(4,restaurant.getRating());
			preparestatement.setString(5,restaurant.getCusineType());
			preparestatement.setBoolean(6,restaurant.isActive());
			preparestatement.setString(7,restaurant.getEta());
			preparestatement.setInt(8,restaurant.getAdminUserId());
			preparestatement.setInt(9, restaurant.getRestaurantId());
			preparestatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(DELETE_RESTAURANT_QUERY)) {
			preparestatement.setInt(1, restaurantId);
			preparestatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		Restaurant restaurant=null;
		ArrayList<Restaurant> restaurantsList=new ArrayList<Restaurant>();
		try(Connection connection=DBConnection.getConnection();
				Statement statement=connection.createStatement()) {

			ResultSet res=statement.executeQuery(GET_ALL_RESTAURANTS);
			while(res.next()) {
				restaurant=extractRestaurant(res);
				restaurantsList.add(restaurant);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantsList;
	}
	Restaurant extractRestaurant(ResultSet res) throws SQLException {
		int restaurantId=res.getInt("restaurantId");
	    String name=res.getString("name");
	    String address=res.getString("address");
	    long phone=res.getLong("phone");
	    float rating=res.getFloat("rating");
	    String cusineType=res.getString("cusineType");
	    boolean isActive=res.getBoolean("isActive");
	    String eta=res.getString("eta");
	    int adminUserId=res.getInt("AdminUserId");
	    String imagePath=res.getString("imagePath");
	    Restaurant restaurant=new Restaurant(restaurantId, name, address, phone, rating, cusineType, isActive, eta, adminUserId, imagePath);
	    return restaurant;
	}


}