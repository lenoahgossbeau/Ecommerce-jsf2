/**
 * 
 */
package cm.itac.formation.ecommerce.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Entree implements Serializable{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
    @Column( columnDefinition = "TIMESTAMP")
	@Converter( name= "dateTimeConverter", converterClass =JodaDateTimeConverter.class)
	@Convert( "dateTimeConverter" )
    private DateTime date; 
    @OneToOne
	@JoinColumn(name = "idType_Entree")
    private Type_Entree type_Entree;    
	private String nom;
	private String quantité;
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "idEntree")
    private List<Article> articles;
	 @OneToOne(mappedBy = "entree", cascade = CascadeType.ALL, 
             fetch = FetchType.LAZY, optional = false)
	private Stock stock;
	 @OneToOne(mappedBy = "entree", cascade = CascadeType.ALL, 
             fetch = FetchType.LAZY, optional = false)
	private Shop shop;
	
	
	
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
	 * @return the type_Entree
	 */
	public Type_Entree getType_Entree() {
		return type_Entree;
	}

	/**
	 * @param type_Entree the type_Entree to set
	 */
	public void setType_Entree(Type_Entree type_Entree) {
		this.type_Entree = type_Entree;
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

	public Entree() {
		// TODO Auto-generated constructor stub
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

	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * @return the shop
	 */
	public Shop getShop() {
		return shop;
	}

	/**
	 * @param shop the shop to set
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
