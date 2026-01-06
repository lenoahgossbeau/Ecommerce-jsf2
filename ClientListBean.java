/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import cm.itac.formation.ecommerce.dao.CustomerDao;
import cm.itac.formation.ecommerce.dao.entity.Customer;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class ClientListBean {
	 private static final long serialVersionUID = 1L;
	 private List<Customer> clients;
	 private Customer client;
	 @EJB
	 private CustomerDao     customerDao;
	/**
	 * 
	 */
	 
	public ClientListBean() {
		clients = new ArrayList<Customer>();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the client
	 */
	
	public List<Customer> getClients() {
		clients = customerDao.lister();
		if(clients == null)
			clients = new ArrayList<Customer>();
		return clients;
	}

	public String detail(Customer customer){
		this.client = customerDao.detail(customer);
		
		return "customerOverview";
	} 
	
	
	public String supprimer(Customer client){
		customerDao.supprimer(client);
		
		return "customerList";
	}
	
 
	
	public String modifier(Customer  client) {
		
                this.client = customerDao.modifier(client);
        
        FacesMessage message = new FacesMessage(
                "Succés de la modification du client !");
        FacesContext.getCurrentInstance().addMessage(null ,message);
        
        return "customerEditView";
    }
	/**
	 * 
	 * @param clients the clients to set
	 */

	public Customer getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(Customer client) {
		this.client = client;
	}
}
