package web.model;

public class Etudiant {
	
	private int id;
	private String nom;
	private String date_insription;
	private String sexe;
	
	public Etudiant() {
		
	}
	
	public Etudiant(int id, String nom, String date_insription, String sexe) {
		super();
		this.id = id;
		this.nom = nom;
		this.date_insription = date_insription;
		this.sexe = sexe;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDate_insription() {
		return date_insription;
	}
	public void setDate_insription(String date_insription) {
		this.date_insription = date_insription;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", date_insription=" + date_insription + ", sexe=" + sexe + "]";
	}
	
	
	
}
