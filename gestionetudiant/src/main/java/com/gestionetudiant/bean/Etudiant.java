/**
 * 
 */
package com.gestionetudiant.bean;

import java.util.Date;

/**
 * 
 */
public class Etudiant {
	private int id ;
	private String nom;
	private String prenom;
	private Date dateNais;
	
	
	public Etudiant() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param dateNais
	 */
	public Etudiant(int id, String nom, String prenom, Date dateNais) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNais = dateNais;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}


	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	/**
	 * @return the dateNais
	 */
	public Date getDateNais() {
		return dateNais;
	}


	/**
	 * @param dateNais the dateNais to set
	 */
	public void setDateNais(Date dateNais) {
		this.dateNais = dateNais;
	}
	
	
}