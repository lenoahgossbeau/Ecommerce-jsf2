/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.dao.Account_TransactionDao;
import cm.itac.formation.ecommerce.dao.Type_AccountDao;
import cm.itac.formation.ecommerce.dao.Type_TransactionDao;
import cm.itac.formation.ecommerce.dao.entity.Account_Transaction;
import cm.itac.formation.ecommerce.dao.entity.Type_Account;
import cm.itac.formation.ecommerce.dao.entity.Type_Transaction;
import cm.itac.formation.ecommerce.dao.entity.User;
import cm.itac.formation.ecommerce.security.EcommerceContext;

/**
 * @author  erico
 *
 */
@RequestScoped 
@ManagedBean
public class Account_TransactionBean implements Serializable{
	 private static final long serialVersionUID = 1L;
	 
	 private  Account_Transaction    account_Transaction;
	 private Type_Transaction     type_Transaction;
	 private Type_Account     type_Account;
	 private String codeType_Transaction;
	 private String codeType_Account;
	 private User utilisateur;
		
     private String idUser; 
	 
	    private String  idAccount_Transaction;
	    private String  account_number;
	    private String  account_name;
	    private String   causal;
	    private String   amount;
	    private String typeType_Transaction;
	    private String typeType_Account;
		
	    Map<String, Type_Transaction> type_Transactions;
	    Map<String, Type_Account> type_Accounts;
		  
	    List<Account_Transaction> account_Transactions;
	   
	    private List<Account_Transaction> filteredAccount_TransactionList;
	    private List<Type_Transaction>     list;
	    private List<Type_Account>     list1;
	    private List<User>   list2;
		
	    @PostConstruct
	    public void init() {
	       
	        list = new ArrayList<Type_Transaction>();  
	        list1 = new ArrayList<Type_Account>();   
	  	  
	    }	 
		   
	    public static final String SESSION_TYPE_TRANSACTIONS   = "type_Transactions";
		      
	    public static final String REQUEST_TYPE_TRANSACTION  = "type_TransactionBean";
	    public static final String SESSION_TYPE_ACCOUNTS   = "type_Accounts";
	      
	    public static final String REQUEST_TYPE_ACCOUNT  = "type_AccountBean";
		 
	    public static final String SESSION_ACCOUNT_TRANSACTIONS = "account_Transactions";
	    public static final String REQUEST_ACCOUNT_TRANSACTION = "account_TransactionBean";
	    public static final String REQUEST_ACCOUNT_TRANSACTION_LIST = "account_TransactionListBean";
	    public static final String REQUEST_USER = "userBean";
  	    
	    // Injection de notre EJB (Session Bean Stateless)
	    @EJB
		 private  Account_TransactionDao    account_TransactionDao;
		 
	    @EJB
		 private Type_TransactionDao     type_TransactionDao;
		 
	    @EJB
		 private Type_AccountDao     type_AccountDao;
		 
		 // Initialisation de l'entité account_Transaction
		 public Account_TransactionBean() {
			 account_Transaction = new Account_Transaction();
		
			 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			 if( account_Transaction == null)
				 account_Transaction = new Account_Transaction();
			 
			 Account_TransactionListBean account_TransactionListBean = (Account_TransactionListBean) request.getAttribute(REQUEST_ACCOUNT_TRANSACTION_LIST);
				if(account_TransactionListBean != null){
					account_Transaction  = account_TransactionListBean.getAccount_Transaction();
					 request.getSession().setAttribute("account_Transaction", account_Transaction);
					 }
				
				account_number = account_Transaction.getAccount_number();
				account_name = account_Transaction.getAccount_name();
				causal = account_Transaction.getCausal();
				
				 amount = String.valueOf(account_Transaction.getAmount());
				
				idAccount_Transaction = String.valueOf(account_Transaction.getId());
				
				typeType_Transaction = account_Transaction.getType_Transaction().getStr_type();
				
				System.out.println("type "+ typeType_Transaction);
				
               typeType_Account = account_Transaction.getType_Account().getType();
				
				System.out.println("type "+ typeType_Account);
				
		 }    
		 public Map<String, Type_Transaction> getType_Transactions() {
				return (Map<String, Type_Transaction>) type_Transactions;
			}
		 public Map<String, Type_Account> getType_Accounts() {
				return (Map<String, Type_Account>) type_Accounts;
			}
			/**
			 * @return the account_Transactions
			 */
			public List<Account_Transaction> getAccount_Transactions() {
				return account_Transactions;
			}
			/**
			 * @param account_Transactions the account_Transactions to set
			 */
			public void setAccount_Transactions(List<Account_Transaction> account_Transactions) {
				this.account_Transactions = account_Transactions;
			}
			public void setType_Transactions(Map<String, Type_Transaction> type_Transaction) {
				this.type_Transaction = (Type_Transaction) type_Transaction;
			}
			public void setType_Accounts(Map<String, Type_Account> type_Account) {
				this.type_Account = (Type_Account) type_Account;
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
			
			public String creeraccount_Transaction() {
		        initialiserDateAccount_Transaction();
		        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		        Type_TransactionBean type_TransactionBean = (Type_TransactionBean) request.getAttribute(REQUEST_TYPE_TRANSACTION)   ;
		        Type_AccountBean type_AccountBean = (Type_AccountBean) request.getAttribute(REQUEST_TYPE_ACCOUNT)   ;
		    	
		        Account_TransactionBean account_TransactionsBean = ( Account_TransactionBean) request.getAttribute(REQUEST_ACCOUNT_TRANSACTION)   ;
		        UserBean userBean = (UserBean) request.getAttribute(REQUEST_USER)   ;
		   	 
		   	 String codeSelected = idUser;
		        User user = EcommerceContext.getEcommerceContextByRequest(request).getUser();
		        account_Transaction.setUser(user);
		        
		        Type_Transaction type_Transaction = new Type_Transaction();
		        type_Transaction.setId(Long.valueOf(codeType_Transaction));
		        account_Transaction.setType_Transaction(type_Transaction);
		        

		        Type_Account type_Account = new Type_Account();
		        type_Account.setId(Long.valueOf(codeType_Account));
		        account_Transaction.setType_Account(type_Account);
		        
		       		
		  		account_Transaction = account_TransactionDao.creer(account_TransactionsBean.getAccount_Transaction());
		            
		        FacesMessage message = new FacesMessage( "Succès de la création d'une transaction en compte !" );
		     
		       FacesContext.getCurrentInstance().addMessage( null, message );
		       
		       
		       return "account_TransactionOverview" ;
		    }  
			
			public String modifier() {
		    	initialiserDateAccount_Transaction();
		        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		        Account_TransactionListBean account_TransactionListBean = (Account_TransactionListBean) request.getAttribute(REQUEST_ACCOUNT_TRANSACTION_LIST);
		        
		        
		        if(account_TransactionListBean != null){
		        	account_Transaction  = account_TransactionListBean.getAccount_Transaction();
		            request.getSession().setAttribute("account_Transaction", account_Transaction);
		           
		            } else if(request.getSession().getAttribute("account_Transaction") != null) {
		            	Account_Transaction account_Transaction1 = 	(Account_Transaction) request.getSession().getAttribute("account_Transaction");
		            	account_Transaction.setId(account_Transaction1.getId());
		            	
		            }
		      account_TransactionDao.modifier( account_Transaction );
		        FacesMessage message = new FacesMessage( "Succès de la modification !" );
		        FacesContext.getCurrentInstance().addMessage( null, message );
				return "transactionEdit";
		    }
		   
			 private void initialiserDateAccount_Transaction() {
			        Timestamp date = new Timestamp( System.currentTimeMillis() );   
			        DateTime dt = new DateTime(date);
			        account_Transaction.setDate(dt);
			    }
			
			
			/**
			 * @return the account_number
			 */
			public String getAccount_number() {
				return account_number;
			}
			/**
			 * @param account_number the account_number to set
			 */
			public void setAccount_number(String account_number) {
				this.account_number = account_number;
			}
			/**
			 * @return the account_name
			 */
			public String getAccount_name() {
				return account_name;
			}
			/**
			 * @param account_name the account_name to set
			 */
			public void setAccount_name(String account_name) {
				this.account_name = account_name;
			}
			/**
			 * @return the causal
			 */
			public String getCausal() {
				return causal;
			}
			/**
			 * @param causal the causal to set
			 */
			public void setCausal(String causal) {
				this.causal = causal;
			}
			/**
			 * @return the amount
			 */
			public String getAmount() {
				return amount;
			}
			/**
			 * @param amount the amount to set
			 */
			public void setAmount(String amount) {
				this.amount = amount;
			}
			
			
		
			/**
			 * @return the idAccount_Transaction
			 */
			public String getIdAccount_Transaction() {
				return idAccount_Transaction;
			}
			/**
			 * @param idAccount_Transaction the idAccount_Transaction to set
			 */
			public void setIdAccount_Transaction(String idAccount_Transaction) {
				this.idAccount_Transaction = idAccount_Transaction;
			}
			
			/**
			 * @return the filteredAccount_TransactionList
			 */
			public List<Account_Transaction> getFilteredAccount_TransactionList() {
				return filteredAccount_TransactionList;
			}
			/**
			 * @param filteredAccount_TransactionList the filteredAccount_TransactionList to set
			 */
			public void setFilteredAccount_TransactionList(List<Account_Transaction> filteredAccount_TransactionList) {
				this.filteredAccount_TransactionList = filteredAccount_TransactionList;
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
			/**
			 * @return the codeType_Transaction
			 */
			public String getCodeType_Transaction() {
				return codeType_Transaction;
			}
			/**
			 * @param codeType_Transaction the codeType_Transaction to set
			 */
			public void setCodeType_Transaction(String codeType_Transaction) {
				this.codeType_Transaction = codeType_Transaction;
			}
			/**
			 * @return the typeType_Transaction
			 */
			public String getTypeType_Transaction() {
				return typeType_Transaction;
			}
			/**
			 * @param typeType_Transaction the typeType_Transaction to set
			 */
			public void setTypeType_Transaction(String typeType_Transaction) {
				this.typeType_Transaction = typeType_Transaction;
			}
			/**
			 * @return the list
			 */
			public List<Type_Transaction> getList() {
				return list;
			}
			/**
			 * @param list the list to set
			 */
			public void setList(List<Type_Transaction> list) {
				this.list = list;
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
			/**
			 * @return the codeType_Account
			 */
			public String getCodeType_Account() {
				return codeType_Account;
			}
			/**
			 * @param codeType_Account the codeType_Account to set
			 */
			public void setCodeType_Account(String codeType_Account) {
				this.codeType_Account = codeType_Account;
			}
			/**
			 * @return the typeType_Account
			 */
			public String getTypeType_Account() {
				return typeType_Account;
			}
			/**
			 * @param typeType_Account the typeType_Account to set
			 */
			public void setTypeType_Account(String typeType_Account) {
				this.typeType_Account = typeType_Account;
			}
			/**
			 * @return the list1
			 */
			public List<Type_Account> getList1() {
				return list1;
			}
			/**
			 * @param list1 the list1 to set
			 */
			public void setList1(List<Type_Account> list1) {
				this.list1 = list1;
			}
			/**
			 * @return the utilisateur
			 */
			public User getUtilisateur() {
				return utilisateur;
			}
			/**
			 * @param utilisateur the utilisateur to set
			 */
			public void setUtilisateur(User utilisateur) {
				this.utilisateur = utilisateur;
			}
			/**
			 * @return the idUser
			 */
			public String getIdUser() {
				return idUser;
			}
			/**
			 * @param idUser the idUser to set
			 */
			public void setIdUser(String idUser) {
				this.idUser = idUser;
			}
			/**
			 * @return the list2
			 */
			public List<User> getList2() {
				return list2;
			}
			/**
			 * @param list2 the list2 to set
			 */
			public void setList2(List<User> list2) {
				this.list2 = list2;
			}
			
}
