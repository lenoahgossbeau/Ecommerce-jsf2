/**
 * 
 */
package cm.itac.formation.ecommerce.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author erico
 *
 */
@Entity
public class Customer implements Serializable {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* Propriétés du bean */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
	private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
	
	/**
	 * 
	 */
	public Customer() {
		// TODO Auto-generated constructor stub
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}
