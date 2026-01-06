/**
 * 
 */
package cm.itac.formation.ecommerce.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.Tools.JodaDateTimeConverter;

/**
 * @author admin
 *
 */
@Entity
public class Sortir implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @Column( columnDefinition = "TIMESTAMP")
     @Converter( name= "dateTimeConverter", converterClass =JodaDateTimeConverter.class)
	 @Convert( "dateTimeConverter" )
    private DateTime date; 
	private String nom;
	private String quantité;
	
	

	
	public Sortir() {
		// TODO Auto-generated constructor stub
	}




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
	 * @return the date
	 */
	public DateTime getDate() {
		return date;
	}




	/**
	 * @param date the date to set
	 */
	public void setDate(DateTime date) {
		this.date = date;
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
	 * @return the quantité
	 */
	public String getQuantité() {
		return quantité;
	}




	/**
	 * @param quantité the quantité to set
	 */
	public void setQuantité(String quantité) {
		this.quantité = quantité;
	}

	
}
