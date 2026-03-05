package com.tap.daoimpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDAO;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class UserDAOImpl implements UserDAO {
	 private static final String INSERT_USER_QUERY="INSERT INTO `user`(`name`,`username`,`password`,`email`,`phone`,`address`,`role`) values(?,?,?,?,?,?,?)";
     private static final String GET_USER_QUERY="SELECT * FROM `user` WHERE `email`=?";
     private static final String GET_USER_QUERY_BY_ID="SELECT * FROM `user` WHERE `userId`=?";
     private static final String UPDATE_USER_QUERY="UPDATE `user` set `name`=?, `password`=?, `phone`=?, `address`=?, `role`=? WHERE `userId`=?";
     private static final String DELETE_USER_QUERY="DELETE FROM `user` WHERE `userId`=?";
     private static final String GET_ALL_USERS="SELECT * FROM `user`";
	@Override
	public boolean addUser(User user) {
	    boolean isAdded = false; // Track if user is successfully added
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement prepareStatement = connection.prepareStatement(INSERT_USER_QUERY)) {

	        prepareStatement.setString(1, user.getName());
	        prepareStatement.setString(2, user.getUsername());
	        prepareStatement.setString(3, user.getPassword());
	        prepareStatement.setString(4, user.getEmail());
	        prepareStatement.setString(5, user.getPhone());
	        prepareStatement.setString(6, user.getAddress());
	        prepareStatement.setString(7, user.getRole());

	        int rowsAffected = prepareStatement.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            isAdded = true; 
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return isAdded;
	}


	@Override
	public User getUser(String email) {
		User user=null;
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(GET_USER_QUERY)) 
		{
			preparestatement.setString(1, email);
			ResultSet res=preparestatement.executeQuery();
			if(res.next()) {
				user=extractUser(res);
			}
			else {
				user=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public User getUserId(int userId) {
		User user=null;
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(GET_USER_QUERY_BY_ID)) 
		{
			preparestatement.setInt(1, userId);
			ResultSet res=preparestatement.executeQuery();
			res.next();
		    user=extractUser(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public void updateUser(User user) {
		   
		try( Connection connection=DBConnection.getConnection();
			 PreparedStatement preparestatement=connection.prepareStatement(UPDATE_USER_QUERY)){
			preparestatement.setString(1, user.getName());
			preparestatement.setString(2, user.getPassword());
			preparestatement.setString(3, user.getPhone());
			preparestatement.setString(4, user.getAddress());
			preparestatement.setString(5, user.getRole());
			preparestatement.setInt(6, user.getUserId());
			preparestatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(DELETE_USER_QUERY)) {
			preparestatement.setInt(1, userId);
			preparestatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> getAllUsers() {
		ArrayList<User> usersList=new ArrayList<User>();
		try(Connection connection=DBConnection.getConnection();
			Statement statement=connection.createStatement()) {
			ResultSet res=statement.executeQuery(GET_ALL_USERS);
			while(res.next()) {
				User user=extractUser(res);
			    usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}
	public User extractUser(ResultSet res) throws SQLException {
		int userId=res.getInt("userId");
	    String name=res.getString("name");
	    String username=res.getString("username");
	    String password=res.getString("password");
	    String email=res.getString("email");
	    String phone=res.getString("phone");
	    String address=res.getString("address");
	    String role=res.getString("role");
	    User user=new User(userId,name, username, password, email, phone, address, role);
	    return user;
	}

}