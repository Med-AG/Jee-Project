package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.connection.DB_con;
import web.dao.Etudiant_dao;

/**
 * Servlet implementation class Ajouter
 */
@WebServlet("/Ajouter")
public class Ajouter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
 		HttpSession session = request.getSession();
 		String login = (String) session.getAttribute("login");
 		try {
 			if (login != null) {
 				String nom = request.getParameter("nom");
 				String date = request.getParameter("date");
 				String sexe = request.getParameter("sexe");
 				
 				Etudiant_dao eDao = new Etudiant_dao(DB_con.getConnection());
 				Boolean result = eDao.ajouterEtudiant(nom, date, sexe);
 				if (result) {
					response.sendRedirect("index.jsp");
				}
 			}else {
 				response.sendRedirect("auth.jsp");
 			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		
	}

}
