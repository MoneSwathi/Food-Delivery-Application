package com.tap.daoimpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.utility.DBConnection;

public class MenuDAOImpl implements MenuDAO {
	private static final String INSERT_MENU_QUERY="INSERT INTO `menu`(restaurantId,itemName,description,price,ratings,isAvailable,imagePath)VALUES(?,?,?,?,?,?,?)";
	private static final String GET_MENU_QUERY="SELECT * FROM `menu` WHERE `menuId`=?";
	private static final String UPDATE_MENU_QUERY="UPDATE `menu` SET `restaurantId`=? `itemName`=? `description`=? `price`=? `ratings`=? `isAvailable`=? `imagePath`=? WHERE `menuId`=?";
	private static final String DELETE_MENU_QUERY="DELETE FROM `menu` WHERE `menuId`=?";
	private static final String GET_ALL_MENUS="SELECT * FROM `menu` WHERE `restaurantId`=?";
	public void addMenu(Menu menu) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(INSERT_MENU_QUERY);) {
			preparestatement.setInt(1, menu.getRestaurantId());
			preparestatement.setString(2, menu.getItemName());
			preparestatement.setString(3, menu.getDescription());
			preparestatement.setDouble(4, menu.getPrice());
			preparestatement.setFloat(5, menu.getRatings());
			preparestatement.setBoolean(6, menu.isAvailable());
			preparestatement.setString(7, menu.getImagePath());
			preparestatement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenu(int menuId) {
		Menu menu=null;
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(GET_MENU_QUERY)) {
			preparestatement.setInt(1, menuId);
			ResultSet res=preparestatement.executeQuery();
			while(res.next()) {
				menu=extractMenu(res);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(UPDATE_MENU_QUERY)) {
			preparestatement.setInt(1, menu.getRestaurantId());
			preparestatement.setString(2,menu.getItemName());
			preparestatement.setString(3, menu.getDescription());
			preparestatement.setDouble(4, menu.getPrice());
			preparestatement.setFloat(5, menu.getRatings());
			preparestatement.setBoolean(6, menu.isAvailable());
			preparestatement.setString(7, menu.getImagePath());
			preparestatement.executeUpdate();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMenu(int menuId) {
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement preparestatement=connection.prepareStatement(DELETE_MENU_QUERY)) {
			preparestatement.setInt(1, menuId);
			preparestatement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getAllMenusByRestaurant(int restaurantId) {
		ArrayList<Menu> menuList=new ArrayList();
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement preparestatement=connection.prepareStatement(GET_ALL_MENUS)) {
			preparestatement.setInt(1,restaurantId);
			ResultSet res=preparestatement.executeQuery();
			while(res.next()) {
				Menu menu=extractMenu(res);
				menuList.add(menu);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	Menu extractMenu(ResultSet res) throws SQLException{
		int menuId=res.getInt("menuId");
		int restaurantId=res.getInt("restaurantId");
		String itemName=res.getString("itemName");
		String description=res.getString("description");
		double price=res.getDouble("price");
		float ratings=res.getFloat("ratings");
		boolean isAvailable=res.getBoolean("isAvailable");
		String imagePath=res.getString("imagePath");
		Menu menu=new Menu(menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath);
		return menu;
	}
}