package GUI.Utilisateurs;

public class UtilisateurObj {	
	private int id; private String prenom; private String nom;
	private int niveau; private int tempsJeu; private int Nbparties; 
	private double meilleurScore;
	
	public UtilisateurObj(String[] parts){
		this.id = Integer.parseInt(parts[0]);
		this.prenom = parts[1];
		this.nom = parts[2];
		this.niveau = Integer.parseInt(parts[3]);
		this.tempsJeu = Integer.parseInt(parts[4]);
		this.Nbparties = Integer.parseInt(parts[5]);
		this.meilleurScore = Double.parseDouble(parts[6]);
	}
		
	//Getters & Setters
	public int getId(){
		return this.id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public int getTempsJeu() {
		return tempsJeu;
	}

	public void setTempsJeu(int tempsJeu) {
		this.tempsJeu = tempsJeu;
	}

	public int getNbparties() {
		return Nbparties;
	}

	public void setNbparties(int nbparties) {
		Nbparties = nbparties;
	}

	public double getMeilleurScore() {
		return meilleurScore;
	}

	public void setMeilleurScore(double meilleurScore) {
		this.meilleurScore = meilleurScore;
	}

	public void setId(int id) {
		this.id = id;
	}
}
