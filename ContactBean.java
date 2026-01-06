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

import cm.itac.formation.ecommerce.dao.ContactDao;
import cm.itac.formation.ecommerce.dao.entity.Contact;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class ContactBean {
	 
    private Contact      contact;
    // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private ContactDao    contactDao;
           Map<String, Contact> contacts;
    
    public static final String SESSION_CONTACTS   = "contacts";
    public static final String REQUEST_CONTACT = "contactBean";
    // Initialisation de l'entité contact

public ContactBean() {
	 contact = new Contact();
	 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 
	 contact = (Contact) request.getSession().getAttribute(SESSION_CONTACTS)   ; 
		
	 if ( contact== null)
		 contact = new Contact();
	
	
	
	// TODO Auto-generated constructor stub
}

public Map<String, Contact> getContacts() {
	return (Map<String, Contact>) contacts;
}
public Contact getContact(){
	return  contact;
}
 
	public void setContact(Contact contact) {
		this. contact = contact;
	} 
  // Méthode d'action appelée lors du clic sur le bouton du formulaire
// d'inscription
public String  creercontact() {
    initialiserDateContact();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	 ContactBean contactBean = (ContactBean) request.getAttribute(REQUEST_CONTACT)   ; 
	 contact = contactDao.creer(contactBean.getContact());
     
	 contactDao.creer( contact );
    FacesMessage message = new FacesMessage( "Succès de création d'un client !" );
    FacesContext.getCurrentInstance().addMessage( null, message );
    return "contact";
}




private void  initialiserDateContact() {
    Timestamp date = new Timestamp( System.currentTimeMillis() );
   
}


}
