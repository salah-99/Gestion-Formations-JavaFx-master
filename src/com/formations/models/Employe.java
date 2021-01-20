package com.formations.models;

public class Employe {
	private int id;
	private int matricule;
	private String nomprenom;
	private String login;
	private String mdp;
	private String ville;
	
	public Employe(int id,int matricule, String nomprenom, String login, String mdp, String ville) {
		super();
		this.id = id++;
		this.matricule = matricule;
		this.nomprenom = nomprenom;
		this.login = login;
		this.mdp = mdp;
		this.ville = ville;
	}
	
	public Employe(int matricule, String nomprenom, String login, String mdp, String ville) {
		super();
		this.matricule = matricule;
		this.nomprenom = nomprenom;
		this.login = login;
		this.mdp = mdp;
		this.ville = ville;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getMatricule() {
		return matricule;
	}
	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}
	public String getNomprenom() {
		return nomprenom;
	}
	public void setNomprenom(String nomprenom) {
		this.nomprenom = nomprenom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Employe [matricule=" + matricule + ", nomprenom=" + nomprenom + ", login=" + login + ", mdp=" + mdp
				+ ", ville=" + ville + "]";
	}
	
	
	
}
