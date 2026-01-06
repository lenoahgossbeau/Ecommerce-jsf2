/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author erico
 *
 */
@ManagedBean
@RequestScoped
public class BonjourBean  implements Serializable{
	  
	private static final long serialVersionUID = 1;
	private String      nom;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	

	
}
