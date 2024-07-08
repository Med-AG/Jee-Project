package web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import web.model.User;

public class User_dao {
	
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public User_dao(Connection con) {
		this.con = con;
	}
	
	public User getUser(String username, String password) {
		User user = null;
		try {
			query = "select * from users where login=? and password=?";
			pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
			}
			/*
			rs.close();
			pst.close();
			con.close();
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
