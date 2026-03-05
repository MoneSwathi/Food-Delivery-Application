package com.tap.utility;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	
	private static String url="jdbc:mysql://localhost:3306/food_delivery";
	private static String un="root";
	private static String psd="Swathi63@";
	static Connection connection=null;
	public static Connection getConnection(){
		
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,un,psd);
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}