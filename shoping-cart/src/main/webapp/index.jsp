<%@page import="java.io.IOException"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="cn.techtutorial.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.model.User"%>
<%@page import="cn.techtutorial.connection.DBCon"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="java.util.ArrayList"%>
<%@page import="cn.techtutorial.model.Cart"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductDao pDao = new ProductDao(DBCon.getConnection());
List<Product> products = pDao.getAllProducts();
request.setAttribute("products", products);

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProducts = null;
if(cart_list != null){
	pDao = new ProductDao(DBCon.getConnection());
	cartProducts = pDao.getCartProducts(cart_list);
	request.setAttribute("cart_list", cart_list);
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Welcome To Shoping Cart</title>
<%@ include file="includes/head.jsp"%>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<c:if test="${!products.isEmpty()}">
				<c:forEach var="p" items="${products}">
					<div class="col-md-3">
						<div class="card" style="width: 18rem;">
							<img src="product.images/<c:out value="${p.getImage()}"></c:out>"
								class="card-img-top" alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title">
									<c:out value="${p.getName()}"></c:out>
								</h5>
								<h6 class="price">
									Price:
									<c:out value="${p.getPrice()} $"></c:out>
								</h6>
								<h6 class="category">
									Category:
									<c:out value="${p.getCategory()}"></c:out>
								</h6>
								<div class="mt-3 d-flex justify-content-between">
									<a href="add-to-cart?id=${p.getId()}" class="btn btn-dark">Add to cart</a> 
									<a href="order-now?quantity=1&id=${p.getId()}" class="btn btn-primary" style="background-color: green; 
									border-color: green;">Buy now</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>


		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>