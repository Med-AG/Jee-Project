package web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.model.Etudiant;

public class Etudiant_dao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public Etudiant_dao(Connection con) {
		this.con = con;
	}
	
	public List<Etudiant> getEtudiant() {
		Etudiant etudiant = null;
		List<Etudiant> list = new ArrayList<Etudiant>();
		try {
			
			query = "select * from etudiants";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				etudiant = new Etudiant();
				etudiant.setId(rs.getInt("id"));
				etudiant.setNom(rs.getString("nom"));
				etudiant.setDate_insription(rs.getString("date_ins"));
				etudiant.setSexe(rs.getString("sexe"));
				list.add(etudiant);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean ajouterEtudiant(String nom, String date_ins, String sexe) {
		boolean result = false;
		try {
			query = "insert into etudiants(nom,date_ins,sexe) values (?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, nom);
			pst.setString(2, date_ins);
			pst.setString(3, sexe);
			int row = pst.executeUpdate();
			System.out.println(row + " row/s affected");
			if (row > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void deleteEtudiant(int id) {
		
		try {
			query = "delete from etudiants where id = ?";
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
