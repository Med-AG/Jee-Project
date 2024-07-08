<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.util.List"%>
<%@page import="web.connection.DB_con"%>
<%@page import="web.dao.Etudiant_dao"%>
<%@page import="web.model.Etudiant"%>
<%@page import="java.util.ArrayList"%>
        

<%
String login = (String) session.getAttribute("login");
ArrayList<Etudiant> elist = (ArrayList<Etudiant>) session.getAttribute("list-etudiants");
if(login != null){
	if(elist != null){
		
	}
}else {
	response.sendRedirect("auth.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter etudiant</title>
<style type="text/css">
	div {
	border: 2px solid lightgreen;
	padding: 10px;
	width: fit-content;
}
</style>
</head>
<body>
	<div>
	<form action="Ajouter" method="post">
		<label id="nom">Nom etudiant :
		<input type="text" name="nom" id="nom" required="required">
		 </label>
		<br><br>
		<label id="date">Date incription :
		<input type="date" name="date" id="date" required="required">
		 </label>
		<br><br>
		Sexe :
		<label id="Homme">
		<input type="radio" name="sexe" id="Homme" value="Homme" checked="checked"> Homme
		</label>
		<label id="Femme">
		<input type="radio" name="sexe" id="Femme" value="Femme"> Femme
		</label>
		<br><br>
		<input type="submit" value="Ajouter">
		<input type="reset" value="Retablir">
	</form>
	</div>
</body>
</html>