package cn.techtutorial.connection;

import java.sql.*;

public class DBCon {

	private static Connection connection = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/ecommerce_cart";
        String userName = "root";
        String pass = "2004";
		if (connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,userName,pass);
			System.out.println("Connected");
		}
		return connection;
	}
	
	
}
