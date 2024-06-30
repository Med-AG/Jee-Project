<%@page import="cn.techtutorial.model.Order"%>
<%@page import="cn.techtutorial.dao.OrderDao"%>
<%@page import="cn.techtutorial.model.User"%>
<%@page import="cn.techtutorial.connection.DBCon"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="java.util.ArrayList"%>
<%@page import="cn.techtutorial.model.Cart"%>
<%@page import="java.util.List"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.connection.DBCon"%>

	
	<%
		User auth = (User) request.getSession().getAttribute("auth");
		List<Order> orders = null;
		if(auth != null){
			request.setAttribute("auth", auth);
			OrderDao orderDao = new OrderDao(DBCon.getConnection());
			orders = orderDao.userOrders(auth.getId());
		}else{
			response.sendRedirect("login.jsp");
		}
		

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProducts = null;
if(cart_list != null){
	ProductDao pDao = new ProductDao(DBCon.getConnection());
	cartProducts = pDao.getCartProducts(cart_list);
	request.setAttribute("cart_list", cart_list);
}

	%>
<!DOCTYPE html>
<html>
<head>
<title>Orders Page</title>
<%@ include file="includes/head.jsp" %>
</head>
<body>
	<%@ include file="includes/navbar.jsp" %>
	
	<div class="container">
		<div class="card-header my-3" style="background-color: gray;">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%
				if(orders != null){
					for(Order o : orders){ %>
					<tr>
						<td><%= o.getDate() %></td>
						<td><%= o.getName() %></td>
						<td><%= o.getCategory() %></td>
						<td><%= o.getQuantity() %></td>
						<td><%= o.getPrice() %></td>
						<td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%= o.getOrderId() %>" >Cancel</a></td>
					</tr>
				<%			
					}
				}
			%>
			</tbody>
		</table>

	<%@ include file="includes/footer.jsp" %>
</body>
</html>