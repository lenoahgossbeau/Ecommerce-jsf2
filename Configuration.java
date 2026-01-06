/**
 * 
 */
package cm.itac.formation.ecommerce.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author erico
 *
 */
@Entity
public class Configuration {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long  id;
	private  String  keyStr;
	private  String valueStr;
	

	/**
	 * 
	 */
	public Configuration() {
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
	 * @return the keyStr
	 */
	public String getKeyStr() {
		return keyStr;
	}


	/**
	 * @param keyStr the keyStr to set
	 */
	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}


	/**
	 * @return the valueStr
	 */
	public String getValueStr() {
		return valueStr;
	}


	/**
	 * @param valueStr the valueStr to set
	 */
	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}



}
