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

import cm.itac.formation.ecommerce.dao.Account_TransactionDao;
import cm.itac.formation.ecommerce.dao.ArticleDao;
import cm.itac.formation.ecommerce.dao.Type_AccountDao;
import cm.itac.formation.ecommerce.dao.Type_TransactionDao;
import cm.itac.formation.ecommerce.dao.entity.Account_Transaction;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Account_TransactionListBean implements Serializable{

	 private static final long serialVersionUID = 1L;
	 private List<Account_Transaction> account_Transactions;
	 private Account_Transaction   account_Transaction;
	 
	 @EJB
	 private  Account_TransactionDao    account_TransactionDao;
	

	 @EJB
	 private Type_TransactionDao     type_TransactionDao;
	 
	 @EJB
	 private Type_AccountDao     type_AccountDao;
	 
	 public Account_TransactionListBean() {
		account_Transactions = new ArrayList<Account_Transaction>();
			// TODO Auto-generated constructor stub
		}
	 
	 public List<Account_Transaction> getAccount_Transactions() {
		 account_Transactions = account_TransactionDao.lister();
				if(account_Transactions == null)
					account_Transactions = new ArrayList<Account_Transaction>();
				return account_Transactions;
			}
	 
	 public String supprimer(Account_Transaction account_Transaction){
		 account_TransactionDao.supprimer(account_Transaction);
			
			return "account_TransactionList";
		}
	 
	 public String detail(Account_Transaction account_Transaction){
			this.account_Transaction = account_TransactionDao.detail(account_Transaction);
			
			return "account_TransactionOverview";
		}
	 
	 public String modifier(Account_Transaction account_Transaction){
			this.account_Transaction = account_TransactionDao.detail(account_Transaction);
			
			return "transactionEdit";
		}

	/**
	 * @return the account_Transaction
	 */
	public Account_Transaction getAccount_Transaction() {
		return account_Transaction;
	}

	/**
	 * @param account_Transaction the account_Transaction to set
	 */
	public void setAccount_Transaction(Account_Transaction account_Transaction) {
		this.account_Transaction = account_Transaction;
	}

	/**
	 * @param account_Transactions the account_Transactions to set
	 */
	public void setAccount_Transactions(List<Account_Transaction> account_Transactions) {
		this.account_Transactions = account_Transactions;
	} 
	 
	 
}
