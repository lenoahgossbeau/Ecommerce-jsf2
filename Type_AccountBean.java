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

import cm.itac.formation.ecommerce.dao.Type_AccountDao;
import cm.itac.formation.ecommerce.dao.entity.Type_Account;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Type_AccountBean {
	
	private  Type_Account   type_Account;
	
	@EJB
	private  Type_AccountDao   type_AccountDao;
	

    Map<String, Type_Account> type_Accounts;
    
    public static final String SESSION_TYPE_ACCOUNTS   = "type_Account";
    public static final String REQUEST_TYPE_ACCOUNT = "type_AccountBean";

	/**
	 * 
	 */
 // Initialisation de l'entité type_Account
	public Type_AccountBean() {
		

		type_Account = new Type_Account();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			
		 
		 if ( type_Account== null)
			 type_Account = new Type_Account();
		
		// TODO Auto-generated constructor stub
	}
	public Map<String, Type_Account> getType_Accounts() {
		List<Type_Account> type_AccountList = new ArrayList<Type_Account>();
//		if(type_Transaction == null){
		type_Accounts = new HashMap<String, Type_Account>();
		type_AccountList = type_AccountDao.lister();
			for(Type_Account type_Account : type_AccountList)
				type_Accounts.put(String.valueOf(type_Account.getId()), type_Account);

		return  type_Accounts;
	}
	/**
	 * @return the type_Account
	 */
	public Type_Account getType_Account() {
		return type_Account;
	}
	/**
	 * @param type_Account the type_Account to set
	 */
	public void setType_Account(Type_Account type_Account) {
		this.type_Account = type_Account;
	}
	
}
