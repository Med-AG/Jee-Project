package cn.techtutorial.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.techtutorial.model.Cart;
import cn.techtutorial.model.Product;

public class ProductDao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	public List<Product> getAllProducts(){
		
		List<Product> products = new ArrayList<Product>();
		try {
			query = "select * from products";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			//if (cartList.size()>0) {
				for(Cart c : cartList) {
					query = "select * from products where id=?";
					pst = con.prepareStatement(query);
					pst.setInt(1, c.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						Cart cart = new Cart();
						cart.setId(rs.getInt("id"));
						cart.setName(rs.getString("name"));
						cart.setCategory(rs.getString("category"));
						cart.setPrice(rs.getDouble("price") * c.getQuantity());
						cart.setQuantity(c.getQuantity());
						products.add(cart);
					}
				}	
			//}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		
		return products;
	}
	
	/* method 1 -------------------------
	public double getTotalPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		try {
			for(Cart item : cartList) {
				query = "select price from products where id=?";
				pst = con.prepareStatement(query);
				pst.setInt(1, item.getId());
				rs = pst.executeQuery();
				while (rs.next()) {
					sum += rs.getDouble("price") * item.getQuantity();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
		return sum;
	}
	*/
	
	public double getTotalPrice(ArrayList<Cart> cartList) {
	    double sum = 0;
	    try {
	        for (Cart item : cartList) {
	            query = "select sum(price * ?) as total_price from products where id=?";
	            pst = con.prepareStatement(query);
	            pst.setInt(1, item.getQuantity());
	            pst.setInt(2, item.getId());
	            rs = pst.executeQuery();
	            if (rs.next()) {
	                sum += rs.getDouble("total_price");
	            }
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	    }
	    return sum;
	}

	
}
