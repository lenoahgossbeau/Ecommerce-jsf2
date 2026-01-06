/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.dao.ArticleDao;
import cm.itac.formation.ecommerce.dao.BuyDao;
import cm.itac.formation.ecommerce.dao.CustomerDao;
import cm.itac.formation.ecommerce.dao.OrderDao;
import cm.itac.formation.ecommerce.dao.entity.Article;
import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Customer;
import cm.itac.formation.ecommerce.dao.entity.Deposit_Buy;
import cm.itac.formation.ecommerce.dao.entity.Order;
import cm.itac.formation.ecommerce.dao.entity.Status_Buy;
import cm.itac.formation.ecommerce.dao.entity.Type_Buy;
import cm.itac.formation.ecommerce.dao.entity.User;
import cm.itac.formation.ecommerce.security.EcommerceContext;;


/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class CommandeBean implements Serializable{
	 private static final long serialVersionUID = 1L;

	    private Order       commande;
	    private Article  article;
	    
	    private String       codeCustomer;
	    private List<Customer>   list;
	    private List<Order> filteredOrderList;
	    private List<Buy>     list1;
	    private Customer    client;
	   
	    private Buy    buy;
	    
	    private Status_Buy     status_Buy;
		 private String codeStatus_Buy;
		 private Type_Buy     type_Buy;
		 private String codeType_Buy;
		 
		 Map<String, Status_Buy> status_Buys;
	     Map<String, Type_Buy> type_Buys;
		   public static final String REQUEST_STATUS_BUY  = "status_BuyBean";
		    public static final String SESSION_STATUS_BUY   = "status_Buys";
		    public static final String REQUEST_TYPE_BUY  = "type_BuyBean";
		    public static final String SESSION_TYPE_BUY   = "type_Buys";
	    
	    
	    @PostConstruct
	    public void init() {
	    	
	        list = new ArrayList<Customer>();
            
	        list1 = new ArrayList<Buy>();   
	    }	   
	    public CustomerDao getCustomerDao() {
			return customerDao;
		}
		public void setCustomerDao(CustomerDao customerDao) {
			this.customerDao = customerDao;
		}
		 Map<String, Customer> clients;
		 Map<String, Buy> buys; 
		 
		List< Order> commandes;
	    
	    public static final String SESSION_CLIENTS   = "clients";
	    public static final String SESSION_BUYS   = "buys";
	   
	    public static final String SESSION_COMMANDES = "commandes";
	    public static final String REQUEST_CLIENT = "clientBean";
	    public static final String REQUEST_BUY = "buyBean";
	    public static final String REQUEST_ARTICLE_LIST = "articleListBean";
	    public static final String REQUEST_CONFIGURATION = "configurationBean";
	      public static final String REQUEST_USER = "userBean";
		public static final String REQUEST_COMMANDE_LIST = "commandeListBean";
		  
	    // Injection de notre EJB (Session Bean Stateless)
	    @EJB
	    private OrderDao    orderDao;
	   
       @EJB
	    private CustomerDao     customerDao;
       
       @EJB
	    private ArticleDao     articleDao;
       
       @EJB
	    private BuyDao     buyDao;
       
	    // Initialisation de l'entité commande      
	public CommandeBean() {
		 commande = new Order();
		 buy = new Buy();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		 client = (Customer) request.getSession().getAttribute(SESSION_CLIENTS);
//		buy = (Buy) request.getSession().getAttribute(SESSION_BUYS);
		// TODO Auto-generated constructor stub
		 
		 ArticleListBean articleListBean = (ArticleListBean) request.getSession().getAttribute(REQUEST_ARTICLE_LIST);
			if(articleListBean != null){
				 article  = articleListBean.getArticle();
				 request.getSession().setAttribute("article", article);
				 }
			
		
		//quantity = String.valueOf(article.getQuantite());
		 
		 
	}
	/**
	 * @return the clients
	 */
	
	public Map<String, Customer> getClients() {
		return (Map<String, Customer>) clients;
	}
	
	public Map<String, Buy> getBuys() {
		return (Map<String, Buy>) buys;
	}
	
	
	/**
	 * @param clients the clients to set
	 */
	public void setClients(Map<String, Customer> client) {
		this.client = (Customer) client;
	}
	
	public void setBuys(Map<String, Buy> buys) {
		this.buys = buys;
	}
	/**
	 * @return the commande
	 */
	public Order getCommande() {
		return commande;
	}
	
	
	public Map<String, Status_Buy> getStatus_Buys() {
		return (Map<String, Status_Buy>) status_Buys;
	}
	public void setStatus_Buys(Map<String, Status_Buy> status_Buy) {
		this.status_Buy = (Status_Buy) status_Buy;
	}
	
	
	
	/**
	 * @return the type_Buys
	 */
	public Map<String, Type_Buy> getType_Buys() {
		return type_Buys;
	}

	/**
	 * @param type_Buys the type_Buys to set
	 */
	public void setType_Buys(Map<String, Type_Buy> type_Buys) {
		this.type_Buys = type_Buys;
	}
	// Méthode d'action appelée lors du clic sur le bouton du formulaire
    // d'inscription
    public String creerCommande() {
        initialiserDateOrder();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 ClientBean clientBean = (ClientBean) request.getAttribute(REQUEST_CLIENT)   ; 
		   Status_BuyBean status_BuyBean = (Status_BuyBean) request.getAttribute(REQUEST_STATUS_BUY)   ;
	       	 UserBean userBean = (UserBean) request.getAttribute(REQUEST_USER)   ;
		 ArticleListBean articleListBean = (ArticleListBean) request.getSession().getAttribute(REQUEST_ARTICLE_LIST)   ;
		 ConfigurationBean configurationBean = (ConfigurationBean) request.getSession().getAttribute(REQUEST_CONFIGURATION)   ;
		 BuyBean buyBean = (BuyBean) request.getAttribute(REQUEST_BUY)   ; 
		// client = customerDao.creer(clientBean.getCustomer());
		// client = clientBean.getCustomer();
        //    commande.setCustomer(client);
		 commande.setCustomer(getCustomer());
//            try {
//    			Thread.sleep(10000);
//    		} catch (InterruptedException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}
        
            
            String codeSelected1  = codeCustomer;
            
            
            List<Article> articleSelected = articleListBean.getArticleSelected();
            articleListBean.setQuantite(0);
            commande.setDate(new DateTime(new Date().getTime()));
           // List<Article> articleSelected = (List<Article>) request.getSession().getAttribute("articleChanged")   ; 
            
           
            
           double quantiteArticleSelected  = 0;
           double quantiteDisponibleSelected = 0;
           for(Article article: articleSelected){
        	   quantiteDisponibleSelected = 0;
        	   quantiteArticleSelected = article.getQuantite() + quantiteArticleSelected;
        	   quantiteDisponibleSelected = article.getDisponible()-article.getQuantite();
        	   
          
          
           if (  article.getQuantite() > article.getDisponible()) {
        	   FacesMessage message = new FacesMessage( "Echec de la création de la commande, la quantité ne doit pas etre supérieure a la disponibilité !" );
        	     
               FacesContext.getCurrentInstance().addMessage( null, message );
        	   
        	   return "newOrder"; 
           }
           
           if (  article.getQuantite() == 0) {
        	   FacesMessage message = new FacesMessage( "Echec de la création de la commande, la quantité ne doit pas etre 0 !" );
        	     
               FacesContext.getCurrentInstance().addMessage( null, message );
        	   
        	   return "newOrder"; 
           }
           article.setDisponible(quantiteDisponibleSelected);
           }
            commande.setQuantité(String.valueOf(quantiteArticleSelected));
            
           
            
            BigDecimal montantTotal = new BigDecimal(0);
            for(Article article : articleSelected){
            	montantTotal  = montantTotal.add( article.getPrix().multiply(new BigDecimal(article.getQuantite()))); 
              
            }
            
            User user = EcommerceContext.getEcommerceContextByRequest(request).getUser();
            
        
            
         
       	 if(!configurationBean.isModule_achat()){
       
            
            buy.setUser(user);
   
       	 
       	 Status_Buy status_Buy = new Status_Buy();
       	 codeStatus_Buy = buyBean.getCodeStatus_Buy();
       	 status_Buy.setId(Long.valueOf(codeStatus_Buy));
            buy.setStatus_Buy(status_Buy);
            
            Type_Buy type_Buy = new Type_Buy();
            codeType_Buy = buyBean.getCodeType_Buy();
            type_Buy.setId(Long.valueOf(codeType_Buy));
            buy.setType_Buy(type_Buy);
       	 
            buy.setDesignation(commande.getDesignation());
    }
            	
            commande.setMontant(montantTotal);
            
            commande.setArticles(articleSelected);
           Customer client = new Customer();
           client.setId(Long.valueOf(codeCustomer));
           commande.setCustomer(client);
           
           
           
           
           
           if(!configurationBean.isModule_achat()){
                 
        List<Order> orderSelected = new ArrayList<Order>();
        orderSelected.add(commande);
      	 DateTime dt = new DateTime();    
           
           Double orderQuantity = new Double(0);
      	 for(Order order : orderSelected){
      	  orderQuantity = orderQuantity + Double.valueOf(order.getQuantité());
      	 }
      	 buy.setQuantité(String.valueOf(orderQuantity));
      /*	 BigDecimal montantTotal = new BigDecimal(0);
           for(Order order : orderSelected){
          	 montantTotal = montantTotal.add(order.getMontant());
           }*/
           
           buy.setMontant(montantTotal);
           BigDecimal remaining_deposit = buy.getRemaining();
      	    if(remaining_deposit == null)
      	    	remaining_deposit = new BigDecimal(0);
      	     remaining_deposit = (buy.getMontant()).subtract(buy.getAmount_deposit());
            buy.setRemaining(remaining_deposit);
           
      	 buy.setCommandes(orderSelected);
      	 buy.setDate2(new DateTime(new Date().getTime()));
      	
      	 List<Article> articlesBuyed = new ArrayList<Article>(); 
      	 for(Order order : orderSelected){
      		 articlesBuyed.addAll(order.getArticles());
      	 }
      	 
      	
      	 
      	 buy.setArticles(articlesBuyed);
      	 //Create the deposit_buy in cascaded inside the entity
      	 Deposit_Buy deposit_Buy = new Deposit_Buy();
      	 deposit_Buy.setAmount_deposit(buy.getAmount_deposit());
      	 deposit_Buy.setDate(buy.getDate2());
      	 deposit_Buy.setUser(user);
      	 deposit_Buy.setBuy(buy);
      	 List<Deposit_Buy> deposit_buys = new ArrayList<Deposit_Buy>();
      	 deposit_buys.add(deposit_Buy);
      	 //insert the first deposit_buy associated on the buy
      	 buy.setDeposit_Buy(deposit_buys);

           
           
           
           
           
           
      
       	commande.setBuy(buy);
       	

           
           }
           
           
           
           
           
           
         commande = orderDao.creer(commande) ;
          
           for(Article article : articleSelected)
              articleDao.modifier(article);
           
           FacesMessage message = new FacesMessage( " Commande éxécutée avec Succès !" );
           
           FacesContext.getCurrentInstance().addMessage( null, message );
           commande.setArticles(articleListBean.getArticleSelected());
           request.getSession().setAttribute("articleChanged", null);
        
       
       return "orderOverview" ;
    }  
   
    			
	
	public String getCodeCustomer() {
		return codeCustomer;
	}
	public void setCodeCustomer(String codeCustomer) {
		this.codeCustomer = codeCustomer;
	}
	public void setCommande(Order commande) {
		this.commande = commande;
	}
	private void initialiserDateOrder() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );   
        DateTime dt = new DateTime(date);
       commande.setDate(dt);
    }
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return client;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer client) {
		this.client = client;
	}
	/**
	 * @return the buy
	 */
	public Buy getBuy() {
		return buy;
	}
	/**
	 * @param buy the buy to set
	 */
	public void setBuy(Buy buy) {
		this.buy = buy;
	}
	/**
	 * @return the list
	 */
	public List<Customer> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Customer> list) {
		this.list = list;
	}
	/**
	 * @return the list1
	 */
	public List<Buy> getList1() {
		return list1;
	}
	/**
	 * @param list1 the list1 to set
	 */
	public void setList1(List<Buy> list1) {
		this.list1 = list1;
	}
	/**
	 * @return the filteredOrderList
	 */
	public List<Order> getFilteredOrderList() {
		return filteredOrderList;
	}
	/**
	 * @param filteredOrderList the filteredOrderList to set
	 */
	public void setFilteredOrderList(List<Order> filteredOrderList) {
		this.filteredOrderList = filteredOrderList;
	}
	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}
	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}
	
	

/**
 * @return the status_Buy
 */
public Status_Buy getStatus_Buy() {
	return status_Buy;
}

/**
 * @param status_Buy the status_Buy to set
 */
public void setStatus_Buy(Status_Buy status_Buy) {
	this.status_Buy = status_Buy;
}

/**
 * @return the codeStatus_Buy
 */
public String getCodeStatus_Buy() {
	return codeStatus_Buy;
}

/**
 * @param codeStatus_Buy the codeStatus_Buy to set
 */
public void setCodeStatus_Buy(String codeStatus_Buy) {
	this.codeStatus_Buy = codeStatus_Buy;
}

	
	
}
