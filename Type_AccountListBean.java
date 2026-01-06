/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import cm.itac.formation.ecommerce.dao.Type_AccountDao;
import cm.itac.formation.ecommerce.dao.entity.Type_Account;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Type_AccountListBean implements Serializable{

	
private static final long serialVersionUID = 1L;
	
	private List<Type_Account> type_Accounts;
	 private Type_Account type_Account;
	
	 
	 @EJB
	 private Type_AccountDao     type_AccountDao;
	/**
	 * 
	 */
	public Type_AccountListBean() {
		// TODO Auto-generated constructor stub
		type_Accounts = new ArrayList<Type_Account>();
	}
	/**
	 * @return the type_Accounts
	 */
	public List<Type_Account> getType_Accounts() {
		type_Accounts = type_AccountDao.lister();
		if(type_Accounts == null)
			type_Accounts = new ArrayList<Type_Account>();
		return type_Accounts;
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
