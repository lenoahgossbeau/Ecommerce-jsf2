/**
 * 
 */
package cm.itac.formation.ecommerce.dao.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Company implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id; 
	@Column( columnDefinition = "TIMESTAMP")
	@Converter( name= "dateTimeConverter", converterClass =JodaDateTimeConverter.class)
    @Convert( "dateTimeConverter" )		
  	private DateTime activated_date;
	@Column( columnDefinition = "TIMESTAMP")
	@Converter( name= "dateTimeConverter", converterClass =JodaDateTimeConverter.class)
    @Convert( "dateTimeConverter" )		
  	private DateTime expired_date;
	private String name;
	private String email;
	private String code;
	private String type;
	private String key;
	@ManyToOne
	@JoinColumn(name = "idCompanyGroup")
	private CompanyGroup companyGroup;
	
	/**
	 * 
	 */
	public Company() {
		// TODO Auto-generated constructor stub
	}

	public Company(Long id, String name, String email, String code,
			String key, CompanyGroup companyGroup,DateTime activated_date, DateTime expired_date) {
		super();
		this.id = id;
		this.activated_date = activated_date;
		this.expired_date = expired_date;
		this.name = name;
		this.email = email;
		this.code = code;
		this.key = key;
		this.companyGroup = companyGroup;
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
	 * @return the activated_date
	 */
	public DateTime getActivated_date() {
		return activated_date;
	}

	/**
	 * @param activated_date the activated_date to set
	 */
	public void setActivated_date(DateTime activated_date) {
		this.activated_date = activated_date;
	}

	/**
	 * @return the expired_date
	 */
	public DateTime getExpired_date() {
		return expired_date;
	}

	/**
	 * @param expired_date the expired_date to set
	 */
	public void setExpired_date(DateTime expired_date) {
		this.expired_date = expired_date;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	

	/**
	 * @return the companyGroup
	 */
	public CompanyGroup getCompanyGroup() {
		return companyGroup;
	}

	/**
	 * @param companyGroup the companyGroup to set
	 */
	public void setCompanyGroup(CompanyGroup companyGroup) {
		this.companyGroup = companyGroup;
	}
	
	

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", activated_date=" + activated_date + ", expired_date=" + expired_date + ", name="
				+ name + ", email=" + email + ", code=" + code + ", key=" + key + ", companyGroup=" + companyGroup
				+ "]";
	}

}
