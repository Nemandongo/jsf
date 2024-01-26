/**
 * 
 */
package com.gestionetudiant.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 * 
 */
@RequestScoped
@Named
public class EtudiantBean {
	private  Etudiant etudiant;
	private List<Etudiant> listeEtudiant;
	private Date date;
    private boolean modifier= false;
    private static int etudId;
	public EtudiantBean() {
		// TODO Auto-generated constructor stub
		etudiant = new Etudiant();
	}
	
	public Connection connect() {
		try {
			//choisir notre driver 
			 
			 Class.forName("com.mysql.jdbc.Driver");
			 
			//CONNECTION à la base de donnée
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_etudiant","root","");
			 return con;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Connection con = null;
			return con;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			Connection con = null;
			return con;
		}
		
	}
	
	public List<Etudiant> afficherEtudiant(){
		listeEtudiant = new ArrayList<Etudiant>();
		String req = "select * from etudiant";
		try {
			PreparedStatement ps = connect().prepareStatement(req);
			ResultSet res = ps.executeQuery();
			 while (res.next()) {
				 //on cree une instance de personnel
				Etudiant e = new Etudiant();
				e.setId(res.getInt("id"));
				e.setNom(res.getString("nom"));
				e.setPrenom(res.getString("prenom"));
				e.setDateNais(res.getDate("dateNais"));
				// ajout de Etudiant
				listeEtudiant.add(e);
			}
			return listeEtudiant;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return listeEtudiant;
		}
		
	}
	public void ajouterEtudiant(){
		String req = "insert into etudiant(nom,prenom,dateNais) value (?,?,?)";
		etudiant.setDateNais(convDate(date));
		
		
		try {
			//preparer la requete sql
			PreparedStatement ps = connect().prepareStatement(req);
			ps.setString(1, etudiant.getNom());
			ps.setString(2, etudiant.getPrenom());
			ps.setDate(3, (java.sql.Date) etudiant.getDateNais());
			// POUR EXECUTER LA REQUETE
			ps.execute();
			afficherEtudiant();
			etudiant = new Etudiant();
			date= null;
			
			
		} catch (SQLException err) {
			// TODO: handle exception
			err.printStackTrace();
			
			
		}
	}
	// FUNCTION POUR SUPPRIMER UN ETUDIANT
	public void suppEtudiant(Etudiant etud) {
		String req = "delete from etudiant where  id=?";
	    try {
	    	PreparedStatement ps = connect().prepareStatement(req);
	    	ps.setInt(1, etud.getId());
	    	ps.execute();
			
		} catch (SQLException err) {
			// TODO: handle exception
			err.printStackTrace();
		}   	
	}
	//POUR AFFICHER UN ETUDIANT ET LE MODIFIER
	public void afficher(Etudiant etud) {
		etudId = etud.getId();
		date = etud.getDateNais();
		modifier = true;
		
	}
	//FONCTION POUR MODIFIER UN ETUDIANT
	public void modifierEtudiant() {
		etudiant.setDateNais(convDate(date));
		try {
			String req = "UPDATE etudiant SET nom = ?, prenom = ?, dateNais = ? WHERE id = ?";
			PreparedStatement ps = connect().prepareStatement(req);
			ps.setString(1,etudiant.getNom());
			ps.setString(2, etudiant.getPrenom());
			ps.setDate(3, (java.sql.Date) etudiant.getDateNais());
			ps.setInt(4, etudId);
			System.out.println(ps);
			
			ps.executeUpdate();
			
			afficherEtudiant();
			etudiant = new Etudiant();
			date = null;
		} catch (SQLException err) {
			// TODO: handle exception
			err.printStackTrace();
		}
	}
	
	public java.sql.Date convDate(java.util.Date calendarDate){
		return new java.sql.Date(calendarDate.getTime());
	}
	

	/**
	 * @return the etudId
	 */
	public int getEtudId() {
		return etudId;
	}

	/**
	 * @param etudId the etudId to set
	 */
	public void setEtudId(int etudId) {
		EtudiantBean.etudId = etudId;
	}

	/**
	 * @return the modifier
	 */
	public boolean isModifier() {
		return modifier;
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(boolean modifier) {
		this.modifier = modifier;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the pisteEtudiant
	 */
	public List<Etudiant> getPisteEtudiant() {
		
		return afficherEtudiant();
	}
	/**
	 * @param pisteEtudiant the pisteEtudiant to set
	 */
	public void setPisteEtudiant(List<Etudiant> pisteEtudiant) {
		
	}
	/**
	 * @return the etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}
	/**
	 * @param etudiant the etudiant to set
	 */
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	/**
	 * @return the listeEtudiant
	 */
	public List<Etudiant> getListeEtudiant() {
		return listeEtudiant;
	}
	/**
	 * @param listeEtudiant the listeEtudiant to set
	 */
	public void setListeEtudiant(List<Etudiant> listeEtudiant) {
		this.listeEtudiant = listeEtudiant;
	}

}
