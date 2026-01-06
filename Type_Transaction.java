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
public class Type_Transaction implements Serializable {

	/**
	 * 
	 */
	public Type_Transaction() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String str_type;
	private String str_description;
	

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
	 * @return the str_type
	 */
	public String getStr_type() {
		return str_type;
	}


	/**
	 * @param str_type the str_type to set
	 */
	public void setStr_type(String str_type) {
		this.str_type = str_type;
	}


	/**
	 * @return the str_description
	 */
	public String getStr_description() {
		return str_description;
	}


	/**
	 * @param str_description the str_description to set
	 */
	public void setStr_description(String str_description) {
		this.str_description = str_description;
	}

	

}
