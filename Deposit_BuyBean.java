/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.math.BigDecimal;
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

import cm.itac.formation.ecommerce.dao.BuyDao;
import cm.itac.formation.ecommerce.dao.Deposit_BuyDao;
import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Deposit_Buy;
import cm.itac.formation.ecommerce.dao.entity.Status_Buy;
import cm.itac.formation.ecommerce.dao.entity.User;
import cm.itac.formation.ecommerce.security.EcommerceContext;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Deposit_BuyBean {

private User utilisateur;
	
	private String idUser;
	private Buy     buy;
	 private String codeBuy;
	
	private Deposit_Buy      deposit_Buy;
	Map<String, Buy> buys;
	Map<String, Deposit_Buy> deposit_Buys;
	
	 private List<User>   list;
	 private List<Buy>     list1;
	 
	 @PostConstruct
	    public void init() {
	          
	        list1 = new ArrayList<Buy>();   
		  	  
	    }	
	 
	 public static final String SESSION_DEPOSIT_BUYS   = "deposit_Buys";
     public static final String REQUEST_DEPOSIT_BUY = "deposit_BuyBean";
     public static final String REQUEST_USER = "userBean";
     public static final String REQUEST_BUY  = "buyBean";
     public static final String REQUEST_BUY_LIST  = "buyListBean";
	 public static final String SESSION_BUYS   = "buys";
	    
     @EJB
     private Deposit_BuyDao    deposit_BuyDao;
     
     @EJB
     private  BuyDao    buyDao;
    
	/**
	 * 
	 */
	public Deposit_BuyBean() {
		 deposit_Buy = new Deposit_Buy();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		// TODO Auto-generated constructor stub
			
//		 if ( deposit_Buy== null)
//			 deposit_Buy = new Deposit_Buy();
//		
	}
	
	
	/**
	 * @return the buys
	 */
	public Map<String, Buy> getBuys() {
		return buys;
	}


	/**
	 * @param buys the buys to set
	 */
	public void setBuys(Map<String, Buy> buys) {
		this.buys = buys;
	}


	
	/**
	 * @return the deposit_Buy
	 */
	public Deposit_Buy getDeposit_Buy() {
		return deposit_Buy;
	}
	/**
	 * @param deposit_Buy the deposit_Buy to set
	 */
	public void setDeposit_Buy(Deposit_Buy deposit_Buy) {
		this.deposit_Buy = deposit_Buy;
	}
	
	public String  creerdeposit_Buy() {
		 initialiserDateDeposit_Buy();
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    Deposit_BuyBean deposit_BuyBean = (Deposit_BuyBean) request.getAttribute(REQUEST_DEPOSIT_BUY)   ; 
	    BuyBean buyBean = (BuyBean) request.getAttribute(REQUEST_BUY)   ;
		BuyListBean buyListBean = (BuyListBean) request.getAttribute(REQUEST_BUY_LIST)   ;
	    UserBean userBean = (UserBean) request.getAttribute(REQUEST_USER)   ;
	    
	    Buy buy = buyListBean.getBuySelected();
        BigDecimal amountTotal = new BigDecimal(0);	    
        BigDecimal amount_depositBuySelected  = buy.getAmount_deposit();
        if(amount_depositBuySelected == null)
        	amount_depositBuySelected = new BigDecimal(0);
        BigDecimal amount_deposit = deposit_Buy.getAmount_deposit();
	    amountTotal = amount_deposit.add(amount_depositBuySelected);
	    buy.setAmount_deposit(amountTotal);
	   
	    //Remaining management
	    BigDecimal amountRemaining = new BigDecimal(0);	    
	    BigDecimal amount_RemainingSelected  = buy.getRemaining();
	    if(amount_RemainingSelected ==null)
	    	 amount_RemainingSelected = new BigDecimal(0);	    
	    amountRemaining = amount_RemainingSelected.subtract(amount_deposit);
	    
	    buy.setRemaining(amountRemaining); 
	    
	    if(amountTotal.compareTo(buy.getMontant())> 0){
		FacesMessage message = new FacesMessage( "Echec de la création du depot - montant des depots superieur au prix d'achat !" );
		FacesContext.getCurrentInstance().addMessage( null, message );
		return "newDeposit_Buy";
	    }
		
		Status_Buy status_Buy = new Status_Buy();
	   status_Buy.setId(new Long(1));
		  if(amountTotal.equals(buy.getMontant()))
			 buy.setStatus_Buy(status_Buy);
	     else if(amount_deposit.compareTo(new BigDecimal(0)) == 1){
	    	 status_Buy.setId(new Long(3));
				buy.setStatus_Buy(status_Buy);
	     }
	     User user = EcommerceContext.getEcommerceContextByRequest(request).getUser();
	     deposit_Buy.setUser(user);
	     deposit_Buy.setBuy(buy);
	     deposit_Buy = deposit_BuyDao.creer(deposit_Buy);
	     buyDao.update(buy);
	    FacesMessage message = new FacesMessage( "Vous avez effectués votre depot de remboursement avec succès !" );
	    FacesContext.getCurrentInstance().addMessage( null, message );
	    return "deposit_BuyOverview";
	}
	private void  initialiserDateDeposit_Buy() {
	    Timestamp date = new Timestamp( System.currentTimeMillis() );
	    DateTime dt = new DateTime(date);
	    deposit_Buy.setDate(dt);
	   
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
	 * @return the list
	 */
	public List<User> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<User> list) {
		this.list = list;
	}
	/**
	 * @param deposit_Buys the deposit_Buys to set
	 */
	public void setDeposit_Buys(Map<String, Deposit_Buy> deposit_Buys) {
		this.deposit_Buys = deposit_Buys;
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
	 * @return the codeBuy
	 */
	public String getCodeBuy() {
		return codeBuy;
	}
	/**
	 * @param codeBuy the codeBuy to set
	 */
	public void setCodeBuy(String codeBuy) {
		this.codeBuy = codeBuy;
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
	
	
}
