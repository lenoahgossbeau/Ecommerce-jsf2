/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.sql.Timestamp;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.dao.SortirDao;
import cm.itac.formation.ecommerce.dao.entity.Sortir;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class SortirBean {

    private Sortir      sortir;
    // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private SortirDao    sortirDao;
           Map<String, Sortir> sortirs;
    
    public static final String SESSION_SORTIRS   = "sortirs";
    public static final String REQUEST_SORTIR = "sortirBean";
    // Initialisation de l'entité sortir

public SortirBean() {
	sortir = new Sortir();
	 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 
	 sortir = (Sortir) request.getSession().getAttribute(SESSION_SORTIRS)   ; 
		
	 if ( sortir== null)
		 sortir = new Sortir();
	
	
	
	// TODO Auto-generated constructor stub
}

public Map<String, Sortir> getSortirs() {
	return (Map<String, Sortir>) sortirs;
}
public Sortir getSortir(){
	return  sortir;
}
 
	public void setSortir(Sortir sortir) {
		this. sortir = sortir;
	} 
  // Méthode d'action appelée lors du clic sur le bouton du formulaire
// d'inscription
public String  creersortir() {
    initialiserDateSortir();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	 SortirBean sortirBean = (SortirBean) request.getAttribute(REQUEST_SORTIR)   ; 
	 sortir = sortirDao.creer(sortirBean.getSortir());
     
	
    FacesMessage message = new FacesMessage( "Succès de création d'une sortir !" );
    FacesContext.getCurrentInstance().addMessage( null, message );
    return "sortirOverview";
}




private void  initialiserDateSortir() {
    Timestamp date = new Timestamp( System.currentTimeMillis() );
    DateTime dt = new DateTime(date);
    sortir.setDate(dt);
   
   
}


}
