/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;

import cm.itac.formation.ecommerce.dao.CustomerDao;
import cm.itac.formation.ecommerce.dao.entity.Customer;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class ClientBean {
	 
	    private Customer      client;
	    private String    idCustomer;
	    private String    nom;
	    private String   prenom;
	    private  String adresse;
	    private String telephone;
	    private String email;
	    // Injection de notre EJB (Session Bean Stateless)
	    @EJB
	    private CustomerDao    customerDao;
               Map<String, Customer> clients;
               private List<Customer> filteredCustumerList;
         
	    public static final String SESSION_CLIENTS   = "clients";
	    public static final String REQUEST_CLIENT = "clientBean";


	    public static final String REQUEST_CLIENT_LIST = "clientListBean";
	    // Initialisation de l'entité customer
	
	public ClientBean() {
		 client = new Customer();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			 
//		 client = (Customer) request.getSession().getAttribute(SESSION_CLIENTS)   ; 
//			
//		 if ( client== null)
//			 client = new Customer();
//		
		
		ClientListBean clientListBean = (ClientListBean) request.getAttribute(REQUEST_CLIENT_LIST);
		if(clientListBean != null){
			client = clientListBean.getClient();
			request.getSession().setAttribute("customer", client);
		 }
	 
		// TODO Auto-generated constructor stub
		idCustomer = String.valueOf(client.getId());
		nom = client.getNom();
		prenom = client.getPrenom();
		adresse = client.getAdresse();
		telephone = client.getTelephone();
		email = client.getEmail();
		
		 
	}
	
	
	/**
	 * @param clients the clients to set
	 */
	public void setClients(Map<String, Customer> clients) {
		this.clients = clients;
	}

	public Map<String, Customer> getClients() {
		List<Customer> clientList = new ArrayList<Customer>();
		if(clients == null){
			clients = new HashMap<String, Customer>();
			
			clientList = customerDao.lister();
			for(Customer client : clientList)
				clients.put(String.valueOf(client.getId()), client);
			
		}
		    return  clients;
	}
	    public Customer getCustomer(){
		    return  client;
	}
	 
		public void setCustomer(Customer client) {
			this. client = client;
		} 
	  // Méthode d'action appelée lors du clic sur le bouton du formulaire
    // d'inscription
    public String  creerclient() {
        initialiserDateCustomer();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 ClientBean clientBean = (ClientBean) request.getAttribute(REQUEST_CLIENT)   ; 
        
		 
		 FacesMessage message = new FacesMessage( "Succès de création d'un client !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
        client = customerDao.creer(clientBean.getCustomer());
      
        
        return "customerOverview";
    }
    
    


    public String  modifier() {
        initialiserDateCustomer();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 ClientListBean clientListBean = (ClientListBean) request.getAttribute(REQUEST_CLIENT_LIST)   ; 
		 if(clientListBean != null){
		        client  = clientListBean.getClient();
		        request.getSession().setAttribute("customer", client);
		        } else if(request.getSession().getAttribute("customer") != null){
		        	Customer customer = 	(Customer) request.getSession().getAttribute("customer");
		        	client.setId(customer.getId());
		        
		        }
		 ClientBean clientBean = (ClientBean) request.getAttribute(REQUEST_CLIENT);
		 customerDao.modifier(client);
		 FacesMessage message = new FacesMessage( "Succès de la modification d'un client !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
        
      
        
        return "customerEditView";
    }

  

    /**
	 * @return the filteredCustumerList
	 */
	public List<Customer> getFilteredCustumerList() {
		return filteredCustumerList;
	}

	/**
	 * @param filteredCustumerList the filteredCustumerList to set
	 */
	public void setFilteredCustumerList(List<Customer> filteredCustumerList) {
		this.filteredCustumerList = filteredCustumerList;
	}

	private void  initialiserDateCustomer() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
       
    }

	/**
	 * @return the client
	 */
	public Customer getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Customer client) {
		this.client = client;
	}/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}


	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}


	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}


	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 * @return the idCustomer
	 */
	public String getIdCustomer() {
		return idCustomer;
	}


	/**
	 * @param idCustomer the idCustomer to set
	 */
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

}
