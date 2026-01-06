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

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.Tools.JodaDateTimeConverter;

/**
 * @author admin
 *
 */
@Entity
public class Deposit_Buy implements Serializable {

	/**
	 * 
	 */
	public Deposit_Buy() {
		
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal amount_deposit;
	@Column( columnDefinition = "TIMESTAMP")
	@Converter( name= "dateTimeConverter", converterClass =JodaDateTimeConverter.class)
	@Convert( "dateTimeConverter" )		
	private DateTime date;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBuy")
	private Buy buy;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser")
	private User user;

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
	
	

}
