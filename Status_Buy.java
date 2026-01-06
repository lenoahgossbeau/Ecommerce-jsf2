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
public class Status_Buy implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String strCode;
	private String description;
	/**
	 * 
	 */
	public Status_Buy() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
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
	 * @return the strCode
	 */
	public String getStrCode() {
		return strCode;
	}
	/**
	 * @param strCode the strCode to set
	 */
	public void setStrCode(String strCode) {
		this.strCode = strCode;
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

}
