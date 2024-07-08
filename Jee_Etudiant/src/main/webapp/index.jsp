<%@page import="java.util.List"%>
<%@page import="web.connection.DB_con"%>
<%@page import="web.dao.Etudiant_dao"%>
<%@page import="web.model.Etudiant"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String login = (String) session.getAttribute("login");
ArrayList<Etudiant> elist = null;
int numEtd = 0;
if(login != null){
	Etudiant_dao eDao = new Etudiant_dao(DB_con.getConnection());
	elist = (ArrayList<Etudiant>) eDao.getEtudiant();
	if(elist != null){
		session.setAttribute("list-etudiants", elist);
	}
}else {
	response.sendRedirect("auth.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>home page</title>
<style type="text/css">
	a {
		text-decoration: none;
		background-color: red;
		border: 1px solid black;
		border-radius: 3px;
		padding: 10px;
		cursor: pointer;
		color: black;
		font-weight: bold;
}
	table {
	border-collapse: collapse;
	border: 3px solid gray;
	width: 500px;
}
	table tr {
		height: 50px;
	}
	.ajouter {
		background-color: green;
}
	.supprimer{
		background-color: lightblue;
		margin-left: 15px;
}
</style>
</head>
<body>
	<h3>hello <%= login %></h3>
	<a href="Logout">Logout</a>
	
	<h1>Listes des etudiants</h1>
	
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Nom</th>
			<th>Date Inscription</th>
			<th>Sexe</th>
			<th>Action</th>
		</tr>
		<%
			if(elist != null){
				for(Etudiant etudiant : elist){ %>
				    <tr>
		                <td><%= etudiant.getId() %></td>
		                <td><%= etudiant.getNom() %></td>
		                <td><%= etudiant.getDate_insription() %></td>
		                <td><%= etudiant.getSexe() %></td>
		                <td><a class="supprimer" href="Delete?id=<%= etudiant.getId() %>">Supprimer</a></td>
		            </tr>
		<%	numEtd++;	}
			}
		%>
        
		
	</table>
	<p>Nombre des etudiants : <%= numEtd %></p>
	<a class="ajouter" href="ajouter.jsp">Ajouter</a>
</body>
</html>