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

import cm.itac.formation.ecommerce.dao.Type_TransactionDao;
import cm.itac.formation.ecommerce.dao.entity.Type_Transaction;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Type_TransactionListBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Type_Transaction> type_Transactions;
	 private Type_Transaction type_Transaction;
	
	 
	 @EJB
	 private Type_TransactionDao     type_TransactionDao;

	/**
	 * 
	 */
	public Type_TransactionListBean() {
		// TODO Auto-generated constructor stub
		
		type_Transactions = new ArrayList<Type_Transaction>();
	}

	public List<Type_Transaction> getType_Transactions() {
		type_Transactions = type_TransactionDao.lister();
		if(type_Transactions == null)
			type_Transactions = new ArrayList<Type_Transaction>();
		return type_Transactions;
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

}
