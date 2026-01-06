/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import cm.itac.formation.ecommerce.dao.Type_TransactionDao;
import cm.itac.formation.ecommerce.dao.entity.Type_Transaction;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Type_TransactionBean {
	
	private Type_Transaction  type_Transaction;
	
	// Injection de notre EJB (Session Bean Stateless)
    @EJB
    private Type_TransactionDao    type_TransactionDao;

    Map<String, Type_Transaction> type_Transactions;
    
    public static final String SESSION_TYPE_TRANSACTIONS   = "type_Transactions";
    public static final String REQUEST_TYPE_TRANSACTION = "type_TransactionBean";
	/**
	 * 
	 */
    
 // Initialisation de l'entité type_Transaction
	public Type_TransactionBean() {
		
		type_Transaction = new Type_Transaction();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			
		 
		 if ( type_Transaction== null)
			 type_Transaction = new Type_Transaction();
		
		// TODO Auto-generated constructor stub
	}
	public Map<String, Type_Transaction> getType_Transactions() {
		List<Type_Transaction> type_TransactionList = new ArrayList<Type_Transaction>();
//		if(type_Transaction == null){
		type_Transactions = new HashMap<String, Type_Transaction>();
		type_TransactionList = type_TransactionDao.lister();
			for(Type_Transaction type_Transaction : type_TransactionList)
				type_Transactions.put(String.valueOf(type_Transaction.getId()), type_Transaction);

		return  type_Transactions;
	}
	/**
	 * @return the type_Transaction
	 */
	public Type_Transaction getType_Transaction() {
		return type_Transaction;
	}
	/**
	 * @param type_Transaction the type_Transaction to set
	 */
	public void setType_Transaction(Type_Transaction type_Transaction) {
		this.type_Transaction = type_Transaction;
	}
	// Méthode d'action appelée lors du clic sur le bouton du formulaire
    
	  public String  creertype_Article() {
	        
	        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        Type_TransactionBean type_TransactionBean = ( Type_TransactionBean) request.getAttribute(REQUEST_TYPE_TRANSACTION)   ; 
			
			
	        return "type_Transaction";
	    }
}
