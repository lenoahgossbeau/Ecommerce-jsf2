/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;


import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 * @author admin
 *
 */
@RequestScoped
@ManagedBean
public class DeconnexionBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String deconnexion;

	public static final String VUE = "/connexion.xhtml";

    
	public DeconnexionBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDeconnexion() {
		return deconnexion;
	}
	
public String deconnexion(){
	 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	 request.getSession().invalidate();
	 
	return "articleList";
}

public void setDeconnexion(String deconnexion) {
	this.deconnexion = deconnexion;
}



}
