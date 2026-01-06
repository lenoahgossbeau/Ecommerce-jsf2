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
 * @author erico
 *
 */
@Entity
public class Account_Transaction implements Serializable {

	/**
	 * 
	 */
	public Account_Transaction() {
		 type_Transaction = new Type_Transaction();
		 type_Account = new Type_Account();
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
	private String account_number;
	private String account_name;
	private String causal;
	@Column( columnDefinition = "TIMESTAMP")
	@Converter( name= "dateTimeConverter", converterClass =JodaDateTimeConverter.class)
	@Convert( "dateTimeConverter" )			
	private DateTime date;
	private BigDecimal amount;	
	@ManyToOne
	@JoinColumn(name = "idType_Transaction")
    private Type_Transaction type_Transaction;
	
	@ManyToOne
	@JoinColumn(name = "idType_Account")
    private Type_Account type_Account;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser")
	private User user;
	/**
	 * @return the type_Transaction
	 */
	public Type_Transaction getType_Transaction() {
		return type_Transaction;
	}
	/**
	 * @param type_Transaction the type_Transaction to set
	 */
	public void setType_Transaction(Type_Transaction type_Transaction) {
		this.type_Transaction = type_Transaction;
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
	 * @return the account_number
	 */
	public String getAccount_number() {
		return account_number;
	}
	/**
	 * @param account_number the account_number to set
	 */
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	/**
	 * @return the account_name
	 */
	public String getAccount_name() {
		return account_name;
	}
	/**
	 * @param account_name the account_name to set
	 */
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	/**
	 * @return the causal
	 */
	public String getCausal() {
		return causal;
	}
	/**
	 * @param causal the causal to set
	 */
	public void setCausal(String causal) {
		this.causal = causal;
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
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the type_Account
	 */
	public Type_Account getType_Account() {
		return type_Account;
	}
	/**
	 * @param type_Account the type_Account to set
	 */
	public void setType_Account(Type_Account type_Account) {
		this.type_Account = type_Account;
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
