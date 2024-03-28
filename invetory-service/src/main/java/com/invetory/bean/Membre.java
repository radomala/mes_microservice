package com.invetory.bean;


public class Membre {
	
	private String nom;
	private String prenom;
	
	public Membre() {
	
	}

	public Membre(String nom, String prenom) {
		this.nom = nom;
		this.setPrenom(prenom);
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
	
	
	
	

}


