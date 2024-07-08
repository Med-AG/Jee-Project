<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	
	<form action="Login" method="post">
		<table style="border: 2px solid gray; border-radius: 5px; padding: 10px;">
			<tr>
				<td>Username</td>
				<td><input type="text" name="login" required="required"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="pwd" required="required"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="se connecter"></td>
			</tr>
		</table>
	</form>
	
</body>
</html>