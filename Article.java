/**
 * 
 */
package cm.itac.formation.ecommerce.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author erico
 *
 */
@Entity
public class Article implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "idFournisseur")
    private Fournisseur fournisseur;
	@ManyToOne
	@JoinColumn(name = "idType_Article")
    private Type_Article type_Article;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEntree")
    private Entree entree;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idOrder")
    private Order order;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBuy")
    private Buy buy;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idShop")
    private Shop shop;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idStock")
    private Stock stock;
	private String code;
	private String nom;
	private  String description;
	 @Column( name = " prix_achat")
	 private BigDecimal prixAchat;
	private BigDecimal prix;
	private double disponible; 
	@Column( name = "quantite")
	private double quantite;
	
	
	

  public Article (){
	  fournisseur = new Fournisseur();
	  type_Article = new Type_Article();
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
 * @return the fournisseur
 */
public Fournisseur getFournisseur() {
	return fournisseur;
}

/**
 * @param fournisseur the fournisseur to set
 */
public void setFournisseur(Fournisseur fournisseur) {
	this.fournisseur = fournisseur;
}

/**
 * @return the type_Article
 */
public Type_Article getType_Article() {
	return type_Article;
}

/**
 * @param type_Article the type_Article to set
 */
public void setType_Article(Type_Article type_Article) {
	this.type_Article = type_Article;
}

/**
 * @return the entree
 */
public Entree getEntree() {
	return entree;
}

/**
 * @param entrée the entree to set
 */
public void setEntree(Entree entree) {
	this.entree = entree;
}

/**
 * @return the order
 */
public Order getOrder() {
	return order;
}

/**
 * @param order the order to set
 */
public void setOrder(Order order) {
	this.order = order;
}

/**
 * @return the sortir
 */


/**
 * @return the shop
 */
public Shop getShop() {
	return shop;
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
 * @param shop the shop to set
 */
public void setShop(Shop shop) {
	this.shop = shop;
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
 * @return the code
 */
public String getCode() {
	return code;
}

/**
 * @param code the code to set
 */
public void setCode(String code) {
	this.code = code;
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
 * @return the prix
 */
public BigDecimal getPrix() {
	return prix;
}

/**
 * @param prix the prix to set
 */
public void setPrix(BigDecimal prix) {
	this.prix = prix;
}




/**
 * @return the disponible
 */
public double getDisponible() {
	return disponible;
}

/**
 * @param disponible the disponible to set
 */
public void setDisponible(double disponible) {
	this.disponible = disponible;
}

/**
 * @return the quantite
 */
public double getQuantite() {
	return quantite;
}

/**
 * @param quantite the quantite to set
 */
public void setQuantite(double quantite) {
	this.quantite = quantite;
}

/**
 * @return the prixAchat
 */
public BigDecimal getPrixAchat() {
	return prixAchat;
}

/**
 * @param prixAchat the prixAchat to set
 */
public void setPrixAchat(BigDecimal prixAchat) {
	this.prixAchat = prixAchat;
}

/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((code == null) ? 0 : code.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((nom == null) ? 0 : nom.hashCode());
	result = prime * result + ((prix == null) ? 0 : prix.hashCode());
	result = prime * result + ((prixAchat == null) ? 0 : prixAchat.hashCode());
	return result;
}

/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	Article other = (Article) obj;
	if (code == null) {
		if (other.code != null)
			return false;
	} else if (!code.equals(other.code))
		return false;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (nom == null) {
		if (other.nom != null)
			return false;
	} else if (!nom.equals(other.nom))
		return false;
	if (prix == null) {
		if (other.prix != null)
			return false;
	} else if (!prix.equals(other.prix))
		return false;
	if (prixAchat == null) {
		if (other.prixAchat != null)
			return false;
	} else if (!prixAchat.equals(other.prixAchat))
		return false;
	return true;
}


}