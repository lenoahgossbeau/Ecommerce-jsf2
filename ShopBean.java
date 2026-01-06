/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.dao.EntreeDao;
import cm.itac.formation.ecommerce.dao.ShopDao;
import cm.itac.formation.ecommerce.dao.entity.Entree;
import cm.itac.formation.ecommerce.dao.entity.Shop;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class ShopBean {

	  private Shop      shop;
	  
	  private Entree  entree;
	  
	  Map<String, Entree> Entrees;
	  List<Shop>   shops;
	 
       public static final String SESSION_ENTREES = "entrees";
	   public static final String SESSION_SHOPS   = "shops";
	   public static final String REQUEST_SHOP = "shopBean";
	   public static final String REQUEST_ENTREE = "EntreeBean";
	   
	   
	    // Injection de notre EJB (Session Bean Stateless)
	    @EJB
	    private ShopDao    shopDao;
        @EJB
        private EntreeDao   entreeDao;
	   
	   // Initialisation de l'entité shop
	
	public ShopBean() {
		shop = new Shop();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			 
		 shop = (Shop) request.getSession().getAttribute(SESSION_SHOPS)   ; 
		entree =(Entree) request.getSession().getAttribute(SESSION_ENTREES);	
		 if ( shop== null)
			 shop = new Shop();
		
		
		
		// TODO Auto-generated constructor stub
	}
	
	public Map<String, Entree> getEntrées() {
		return (Map<String, Entree>) Entrees;
	}


	/**
	 * @return the entree
	 */
	public Entree getEntree() {
		return entree;
	}




	/**
	 * @param entrée the entree to set
	 */
	public void setEntree(Entree entree) {
		this.entree = entree;
	}




	/**
	 * @return the shops
	 */
	public List<Shop> getShops() {
		return shops;
	}




	/**
	 * @param shops the shops to set
	 */
	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}


	public void setEntrees(Map<String, Entree> entree) {
		this.entree = (Entree) entree;
	}

	/**
	 * @return the sessionEntrees
	 */
	



	public Shop getShop(){
		return  shop;
	}
	 
		public void setShop(Shop shop) {
			this. shop = shop;
		} 
	  // Méthode d'action appelée lors du clic sur le bouton du formulaire
  // d'inscription
  public String  creershop() {
      initialiserDateShop();
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      ShopBean shopBean = (ShopBean) request.getAttribute(REQUEST_SHOP)   ; 
      EntreeBean entreeBean = (EntreeBean) request.getAttribute(REQUEST_ENTREE)   ;
      
      try {
  		Thread.sleep(10000);
  	} catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
      
      Entree  entree = new Entree();
      shop.setEntree(entree);
      
		 shop = shopDao.creer(shopBean.getShop());
       
		
      FacesMessage message = new FacesMessage( "Succès de création d'un shop !" );
      FacesContext.getCurrentInstance().addMessage( null, message );
      return "shopOverview";
  }

 


  private void  initialiserDateShop() {
      Timestamp date = new Timestamp( System.currentTimeMillis() );
      DateTime dt = new DateTime(date);
      shop.setDate(dt);
  }

}
