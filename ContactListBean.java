/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import cm.itac.formation.ecommerce.dao.ContactDao;
import cm.itac.formation.ecommerce.dao.entity.Contact;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class ContactListBean {
	 private static final long serialVersionUID = 1L;
	 private List<Contact> contacts;
	 private Contact contact;
	 @EJB
	 private ContactDao     contactDao;
	/**
	 * 
	 */
	 
	public ContactListBean() {
		contacts = new ArrayList<Contact>();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the contact
	 */
	
	public List<Contact> getContacts() {
		contacts = contactDao.lister();
		if(contacts == null)
			contacts = new ArrayList<Contact>();
		return contacts;
	}

	public String supprimer(Contact contact){
		contactDao.supprimer(contact);
		
		return "customerList";
	}
	/**
	 * @param contact the contacts to set
	 */

	public Contact getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
