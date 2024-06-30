package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.el.ELParseException;

import cn.techtutorial.connection.DBCon;
import cn.techtutorial.dao.OrderDao;
import cn.techtutorial.model.Cart;
import cn.techtutorial.model.Order;
import cn.techtutorial.model.User;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		PrintWriter out = response.getWriter();
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		User auth = (User) request.getSession().getAttribute("auth");
		if (auth != null) {
			String productId = request.getParameter("id");
			int productQuntity = Integer.parseInt(request.getParameter("quantity"));
			if (productQuntity <= 0) {
				productQuntity = 1;
			}
			Order order = new Order();
			order.setId(Integer.parseInt(productId));
			order.setUid(auth.getId());
			order.setQuantity(productQuntity);
			order.setDate(formatter.format(date));
			
				OrderDao orderDao = new OrderDao(DBCon.getConnection());
				boolean result = orderDao.insertOrder(order);
				if (result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for(Cart c : cart_list) {
							if (c.getId() == Integer.parseInt(productId)) {
								int index = cart_list.indexOf(c);
								cart_list.remove(index);
								break;
							}
						}
					}
					response.sendRedirect("orders.jsp");
				}else {
					out.println("order failed");
				}
			
		}else {
			response.sendRedirect("login.jsp");
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	
	}

}
