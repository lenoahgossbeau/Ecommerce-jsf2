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

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.Tools.JodaDateTimeConverter;

/**
 * @author erico
 *
 */
@Entity
public class Buy implements Serializable{
	  /* Propriétés du bean */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "TIMESTAMP")
	@Converter( name= "dateTimeConverter", converterClass =JodaDateTimeConverter.class)
    @Convert( "dateTimeConverter" )			
  
	private DateTime date2;
	private String designation;
	@Column(name = "quantite")
	private String quantité;
	private BigDecimal montant;
	private BigDecimal remaining;
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "idBuy")
	private List<Order> commandes;
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "idBuy")
    private List<Article> articles;
	private BigDecimal  amount_deposit;
	@ManyToOne
	@JoinColumn(name = "idStatus_buy")
    private Status_Buy status_Buy;
	
	@ManyToOne
	@JoinColumn(name = "idType_buy")
    private Type_Buy type_Buy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser")
	private User user;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idBuy")
    private List<Deposit_Buy> deposit_Buy;

		
	/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}


		/**
	 * @return the status_Buy
	 */
	public Status_Buy getStatus_Buy() {
		return status_Buy;
	}


	/**
	 * @param status_Buy the status_Buy to set
	 */
	public void setStatus_Buy(Status_Buy status_Buy) {
		this.status_Buy = status_Buy;
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
		public DateTime getDate2() {
			return date2;
		}


		/**
		 * @param date the date to set
		 */
		public void setDate2(DateTime date2) {
			this.date2 = date2;
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


	public Buy() {
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
	 * @return the amount_deposit
	 */
	public BigDecimal getAmount_deposit() {
		return amount_deposit;
	}


	/**
	 * @param amount_deposit the amount_deposit to set
	 */
	public void setAmount_deposit(BigDecimal amount_deposit) {
		this.amount_deposit = amount_deposit;
	}


	/**
	 * @return the type_Buy
	 */
	public Type_Buy getType_Buy() {
		return type_Buy;
	}


	/**
	 * @param type_Buy the type_Buy to set
	 */
	public void setType_Buy(Type_Buy type_Buy) {
		this.type_Buy = type_Buy;
	}


	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * @return the deposit_Buy
	 */
	public List<Deposit_Buy> getDeposit_Buy() {
		return deposit_Buy;
	}


	/**
	 * @param deposit_Buy the deposit_Buy to set
	 */
	public void setDeposit_Buy(List<Deposit_Buy> deposit_Buy) {
		this.deposit_Buy = deposit_Buy;
	}


	/**
	 * @return the remaining
	 */
	public BigDecimal getRemaining() {
		return remaining;
	}


	/**
	 * @param remaining the remaining to set
	 */
	public void setRemaining(BigDecimal remaining) {
		this.remaining = remaining;
	}


	/**
	 * @return the commandes
	 */
	public List<Order> getCommandes() {
		return commandes;
	}


	/**
	 * @param commandes the commandes to set
	 */
	public void setCommandes(List<Order> commandes) {
		this.commandes = commandes;
	}


}
