package com.formations.models;

public class Formation {
	private int id;
	private int code;
	private String libelle;
	private String description;
	
	
	
	public Formation(int code, String libelle, String description) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.description = description;
	}
	
	public Formation(int id, int code, String libelle, String description) {
		super();
		this.id = id++;
		this.code = code;
		this.libelle = libelle;
		this.description = description;
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




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	@Override
	public String toString() {
		return "Formation [code=" + code + ", libelle=" + libelle + ", description=" + description + "]";
	}
	
	
}
