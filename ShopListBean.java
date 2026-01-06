/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import cm.itac.formation.ecommerce.dao.EntreeDao;
import cm.itac.formation.ecommerce.dao.ShopDao;
import cm.itac.formation.ecommerce.dao.entity.Shop;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class ShopListBean {
	 private static final long serialVersionUID = 1L;
	 private List<Shop> shops;
	 private Shop shop;
	 @EJB
	 private ShopDao     shopDao;
	
	 @EJB
	 private EntreeDao    entréeDao;
	 /**
	 * 
	 */
	 
	public ShopListBean() {
		shops = new ArrayList<Shop>();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the Shop
	 */
	
	public List<Shop> getShops() {
		shops = shopDao.lister();
		if(shops == null)
			shops = new ArrayList<Shop>();
		return shops;
	}

	public String supprimer(Shop shop){
		shopDao.supprimer(shop);
		
		return "shopList";
	}
	/**
	 * @param shops the shops to set
	 */

	public Shop getShop() {
		return shop;
	}
	/**
	 * @param shop the shop to set
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}


}
