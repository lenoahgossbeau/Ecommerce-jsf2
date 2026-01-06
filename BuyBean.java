/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.primefaces.event.RowEditEvent;

import cm.itac.formation.ecommerce.dao.BuyDao;
import cm.itac.formation.ecommerce.dao.OrderDao;
import cm.itac.formation.ecommerce.dao.Status_BuyDao;
import cm.itac.formation.ecommerce.dao.Type_BuyDao;
import cm.itac.formation.ecommerce.dao.entity.Article;
import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Deposit_Buy;
import cm.itac.formation.ecommerce.dao.entity.Order;
import cm.itac.formation.ecommerce.dao.entity.Status_Buy;
import cm.itac.formation.ecommerce.dao.entity.Type_Buy;
import cm.itac.formation.ecommerce.dao.entity.User;
import cm.itac.formation.ecommerce.security.EcommerceContext;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class BuyBean {

	//Verifier si on est dans la page Buy
	private boolean  buyPage;
    private User utilisateur;
	
	private String idUser;
	private boolean selection;
	 private Status_Buy     status_Buy;
	 private String codeStatus_Buy;
	 private Type_Buy     type_Buy;
	 private String codeType_Buy;
    private Buy      buy;
    
    private String   buyBuyOrder;
    
    // Injection de notre EJB (Session Bean Stateless)
     Map<String, Status_Buy> status_Buys;
     Map<String, Type_Buy> type_Buys;
 	
		Map<String, Buy> buys;
		 private List<Status_Buy>     list;
		 private List<Type_Buy>     list1;
		 private List<User>   list2;
			  
		    @PostConstruct
		    public void init() {
		        list = new ArrayList<Status_Buy>();   
		        list1 = new ArrayList<Type_Buy>();   
			  	  
		    }	
		    public static final String REQUEST_STATUS_BUY  = "status_BuyBean";
		    public static final String SESSION_STATUS_BUY   = "status_Buys";
		    public static final String REQUEST_TYPE_BUY  = "type_BuyBean";
		    public static final String SESSION_TYPE_BUY   = "type_Buys";
		    
            public static final String SESSION_BUYS   = "buys";
            public static final String REQUEST_BUY = "buyBean";
            public static final String REQUEST_COMMANDE_LIST_BEAN = "commandeListBean";
            public static final String REQUEST_USER = "userBean";
     	   
    // Initialisation de l'entité buy
    @EJB
    private BuyDao    buyDao;
    @EJB
    private OrderDao    orderDao;
    @EJB
    private  Status_BuyDao    status_BuyDao;
    @EJB
    private  Type_BuyDao    type_BuyDao;
   
public BuyBean() {
	 buy = new Buy();
	 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 
//	 buy = (Buy) request.getSession().getAttribute(SESSION_BUYS)   ; 
//		
	 if ( buy== null)
		buy = new Buy();
	
	buyPage = true;
	
	// TODO Auto-generated constructor stub
}

public Map<String, Buy> getBuys() {
	List<Buy> buyList = new ArrayList<Buy>();

		buys = new HashMap<String, Buy>();
		buyList = buyDao.lister();
		for(Buy buy : buyList)
			buys.put(String.valueOf(buy.getId()), buy);

	return  buys;
}
public Map<String, Status_Buy> getStatus_Buys() {
	return (Map<String, Status_Buy>) status_Buys;
}
public void setStatus_Buys(Map<String, Status_Buy> status_Buy) {
	this.status_Buy = (Status_Buy) status_Buy;
}
public Buy getBuy(){
	return  buy;
}
 
	public void setBuy(Buy buy) {
		this. buy = buy;
	} 
  // Méthode d'action appelée lors du clic sur le bouton du formulaire
// d'inscription
public String  creerbuy() {
	 initialiserDateBuy();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	 BuyBean buyBean = (BuyBean) request.getAttribute(REQUEST_BUY)   ; 
	 CommandeListBean  commandeListBean = (CommandeListBean) request.getAttribute(REQUEST_COMMANDE_LIST_BEAN)   ; 
	 Status_BuyBean status_BuyBean = (Status_BuyBean) request.getAttribute(REQUEST_STATUS_BUY)   ;
	 UserBean userBean = (UserBean) request.getAttribute(REQUEST_USER)   ;
	 
	 String codeSelected = idUser;
     User user = EcommerceContext.getEcommerceContextByRequest(request).getUser();
     buy.setUser(user);
	 String buySelected = buyBuyOrder;
	 
	 Status_Buy status_Buy = new Status_Buy();
	 status_Buy.setId(Long.valueOf(codeStatus_Buy));
     buy.setStatus_Buy(status_Buy);
     
     Type_Buy type_Buy = new Type_Buy();
     type_Buy.setId(Long.valueOf(codeType_Buy));
     buy.setType_Buy(type_Buy);
	 Article  article = new Article ();
	 
	 
	 //recuperer la liste des commandes selectionées
	 // affecter la ID de Buy creer dans la commande
	 // UIpdate la commande
	 List<Order> orderSelected = commandeListBean.getOrderSelected();
	 DateTime dt = new DateTime();
	 Double orderQuantity = new Double(0);
	 for(Order order : orderSelected){
	  orderQuantity = orderQuantity + Double.valueOf(order.getQuantité());
	 }
	 buy.setQuantité(String.valueOf(orderQuantity));
	 BigDecimal montantTotal = new BigDecimal(0);
     for(Order order : orderSelected){
    	 montantTotal = montantTotal.add(order.getMontant());
     }
     
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
	 buy  =  buyDao.creer(buy);

    FacesMessage message = new FacesMessage( "Vous avez effectués votre achat avec succès !" );
    FacesContext.getCurrentInstance().addMessage( null, message );
    return "buyOverview";
}
public void onEdit( RowEditEvent event ) {
    changer( (Buy) event.getObject() );
    FacesMessage msg = new FacesMessage( "Buy modifié", ( (Buy) event.getObject() ).getDesignation() );

    FacesContext.getCurrentInstance().addMessage( null, msg );
}

public void onCancel( RowEditEvent event ) {
    FacesMessage msg = new FacesMessage( "Buy non modifié", ( (Buy) event.getObject() ).getDesignation() );

    FacesContext.getCurrentInstance().addMessage( null, msg );
}
public void changer( Buy buy ) {
	 
    System.out.println( "Buy récupéré modif :" + buy.getDesignation() );
    buyDao.changer( buy );
}

/**
 * @return the  buyPage
 */
public boolean isBuyPage() {
	return buyPage;
}

/**
 * @param buyPage the buyPage to set
 */
public void setBuyPage(boolean buyPage) {
	this.buyPage = buyPage;
}

/**
 * @return the selection
 */
public boolean isSelection() {
	return selection;
}

/**
 * @param selection the selection to set
 */
public void setSelection(boolean selection) {
	this.selection = selection;
}



private void  initialiserDateBuy() {
    Timestamp date2 = new Timestamp( System.currentTimeMillis() );
   
   
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

/**
 * @return the list
 */
public List<Status_Buy> getList() {
	return list;
}

/**
 * @param list the list to set
 */
public void setList(List<Status_Buy> list) {
	this.list = list;
}

/**
 * @param buys the buys to set
 */
public void setBuys(Map<String, Buy> buys) {
	this.buys = buys;
}

/**
 * @return the type_Buy
 */
public Type_Buy getType_Buy() {
	return type_Buy;
}

/**
 * @param type_Buy the type_Buy to set
 */
public void setType_Buy(Type_Buy type_Buy) {
	this.type_Buy = type_Buy;
}

/**
 * @return the codeType_Buy
 */
public String getCodeType_Buy() {
	return codeType_Buy;
}

/**
 * @param codeType_Buy the codeType_Buy to set
 */
public void setCodeType_Buy(String codeType_Buy) {
	this.codeType_Buy = codeType_Buy;
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

/**
 * @return the list1
 */
public List<Type_Buy> getList1() {
	return list1;
}

/**
 * @param list1 the list1 to set
 */
public void setList1(List<Type_Buy> list1) {
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
