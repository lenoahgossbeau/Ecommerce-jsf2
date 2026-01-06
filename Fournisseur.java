/**
 * 
 */
package cm.itac.formation.ecommerce.dao.entity;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author erico
 *
 */
@Entity
public class Fournisseur implements Serializable{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom1;
	private String prenom;
	private String email;
	private String siege_social;
	private String telephone;
	private String code1;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom1() {
		return nom1;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom1(String nom1) {
		this.nom1 = nom1;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the siege_social
	 */
	public String getSiege_social() {
		return siege_social;
	}

	/**
	 * @param siege_social the siege_social to set
	 */
	public void setSiege_social(String siege_social) {
		this.siege_social = siege_social;
	}
	
	/**
	 * @return the siege_social
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param siege_social the siege_social to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the siege_social
	 */
	public String getCode1() {
		return code1;
	}

	/**
	 * @param siege_social the siege_social to set
	 */
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	
	public Fournisseur() {
		// TODO Auto-generated constructor stub
	}

	public static Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	

}
