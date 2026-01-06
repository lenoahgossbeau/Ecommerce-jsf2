/**
 * 
 */
package cm.itac.formation.ecommerce.dao.entity;


import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.Tools.JodaDateTimeConverter;

/**
 * @author erico
 *
 */
@Entity
@Table(name = "ec_order")
public class Order implements Serializable{
	 /* Propriétés du bean */
   
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idBuy")
    private Buy buy;
    
   /* @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, optional = false)
	private Buy buy;
    */
    
    private String designation;
    @Column( columnDefinition = "TIMESTAMP")
    @Converter( name= "dateTimeConverter", converterClass =JodaDateTimeConverter.class)
    @Convert( "dateTimeConverter" )
    private DateTime date;
  
    @Column( name = " mode_paiement")
    private String modePaiement;
    @Column( name = " statut_paiement")
    private String statutPaiement;
    @Column( name = " mode_livraison")
    private String modeLivraison;
    @Column( name = " statut_livraison")
    private String statutLivraison;
    private String quantité;
	private BigDecimal montant;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "idOrder")
    private List<Article> articles;
    
	/**
	 * 
	 */
	public Order() {
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
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @return the buy
	 */
	public Buy getBuy() {
		return buy;
	}
	/**
	 * @param buy the buy to set
	 */
	public void setBuy(Buy buy) {
		this.buy = buy;
	}
	
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
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
	 * @return the modePaiement
	 */
	public String getModePaiement() {
		return modePaiement;
	}
	/**
	 * @param modePaiement the modePaiement to set
	 */
	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}
	/**
	 * @return the statutPaiement
	 */
	public String getStatutPaiement() {
		return statutPaiement;
	}
	/**
	 * @param statutPaiement the statutPaiement to set
	 */
	public void setStatutPaiement(String statutPaiement) {
		this.statutPaiement = statutPaiement;
	}
	/**
	 * @return the modeLivraison
	 */
	public String getModeLivraison() {
		return modeLivraison;
	}
	/**
	 * @param modeLivraison the modeLivraison to set
	 */
	public void setModeLivraison(String modeLivraison) {
		this.modeLivraison = modeLivraison;
	}
	/**
	 * @return the statutLivraison
	 */
	public String getStatutLivraison() {
		return statutLivraison;
	}
	/**
	 * @param statutLivraison the statutLivraison to set
	 */
	public void setStatutLivraison(String statutLivraison) {
		this.statutLivraison = statutLivraison;
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
	 * @return the montant
	 */
	public BigDecimal getMontant() {
		return montant;
	}
	/**
	 * @param montant the montant to set
	 */
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
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
