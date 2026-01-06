/**
 * 
 */
package cm.itac.formation.ecommerce.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.Tools.JodaDateTimeConverter;

/**
 * @author erico
 *
 */
@Entity
public class Shop implements Serializable{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEntree")
	private Entree entree;
	@Column( columnDefinition = "TIMESTAMP")
	@Converter( name= "dateTimeConverter", converterClass =JodaDateTimeConverter.class)
	@Convert( "dateTimeConverter" )
    private DateTime date; 
	private String description;
	
	private String quantité;
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "idShop")
    private List<Article> articles;
	


	public Shop() {
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
	 * @return the entree
	 */
	public Entree getEntree() {
		return entree;
	}



	/**
	 * @param entree the entree to set
	 */
	public void setEntree(Entree entree) {
		this.entree = entree;
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



	/**
	 * @return the articles
	 */
	public List<Article> getArticles() {
		return articles;
	}



	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
