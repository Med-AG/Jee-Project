package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import cn.techtutorial.model.Order;

public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrderDao(Connection con) {
		this.con = con;
	}
	
	public boolean insertOrder(Order model) {
		boolean result = false;
		
		try {
			query = "insert into orders(p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			
			int row = pst.executeUpdate();
			System.out.println("rows affected "+row);
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Order> userOrders(int id){
		List<Order> list = new ArrayList<Order>();
		
		try {
			//query = "select * from orders where u_id=? order by o_id desc";
			query = "select * from orders join products on orders.p_id = products.id "
					+ "where u_id =? order by o_id desc";
					
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("o_id"));
				order.setId(rs.getInt("p_id"));
				order.setName(rs.getString("name"));
				order.setCategory(rs.getString("category"));
				order.setPrice(rs.getDouble("price") * rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				list.add(order);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	
	public void cancelOrder(int id) {
		
		try {
			query = "delete from orders where o_id=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			boolean deleted = pst.execute();
			if (deleted) {
				System.out.println("deleted succesfully");
			}else {
				System.out.println("not deleted !!!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
