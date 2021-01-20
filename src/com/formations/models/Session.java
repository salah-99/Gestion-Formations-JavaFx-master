package com.formations.models;

public class Session {
	private int id;
	private int code;
	private String libelle;
	private String formation;
	
	public Session(int id,int code, String libelle, String formation) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.formation = formation;
	}
	
	public Session(int code, String libelle, String formation) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.formation = formation;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getFormation() {
		return formation;
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}
	
	@Override
	public String toString() {
		return "Session [code=" + code + ", libelle=" + libelle + ", formation=" + formation + "]";
	}
	
	
}
