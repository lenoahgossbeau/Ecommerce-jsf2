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
 * @author admin
 *
 */
@Entity
public class Type implements Serializable{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String description;
	private String qualité;
	

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the qualité
	 */
	public String getQualité() {
		return qualité;
	}


	/**
	 * @param qualité the qualité to set
	 */
	public void setQualité(String qualité) {
		this.qualité = qualité;
	}


	/**
	 * 
	 */
	public Type() {
		// TODO Auto-generated constructor stub
	}

}
