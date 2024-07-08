package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.connection.DB_con;
import web.dao.User_dao;
import web.model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String login = request.getParameter("login");
		String password = request.getParameter("pwd");
		
		HttpSession session = request.getSession();
		
			User_dao uDao = new User_dao(DB_con.getConnection());
			User user = uDao.getUser(login, password);
			if (user.getLogin() != null) {
				session.setAttribute("login", user.getLogin());
				response.sendRedirect("index.jsp");
			}
			else {
				response.sendRedirect("auth.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
