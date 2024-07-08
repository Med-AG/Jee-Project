package web.connection;

import java.sql.*;

public class DB_con {
	
	private static Connection con = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/JEE_Etudiants";
        String userName = "root";
        String pass = "2004";
        if (con == null) {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	con = DriverManager.getConnection(url,userName,pass);
        	System.out.println("Connected");
		}
		return con;
	}
	
}
