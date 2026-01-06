/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import cm.itac.formation.ecommerce.dao.BuyDao;
import cm.itac.formation.ecommerce.dao.entity.Buy;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class BuyListBean {
	 private static final long serialVersionUID = 1L;
	 private List<Buy> buys;
	 //for the radio button on datatable
	 private Buy buySelected;
	 private Buy buy;
	 private boolean buyPage;
	 
	 @EJB
	 private BuyDao     buyDao;
	 
	/**
	 * 
	 */
	 
	public BuyListBean() {
		buys = new ArrayList<Buy>();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the client
	 */
	
	public List<Buy> getBuys() {
	buys = buyDao.lister();
		if(buys == null)
			buys = new ArrayList<Buy>();
		return buys;
	}

	public String supprimer(Buy buy){
		buyDao.supprimer(buy);
		
		return "buyList";
	}
	public String detail(Buy buy){
		this.buy = buyDao.detail(buy);
		
		return "buyOverview";
	}
	
	/**
	 * @param buys the buys to set
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
	public boolean isBuyPage() {
		return buyPage;
	}
	/**
	 * @param commandPage the commandPage to set
	 */
	public void setBuyPage(boolean commandPage) {
		this.buyPage = commandPage;
	}
	public Buy getBuySelected() {
		return buySelected;
	}
	/**
	 * @param orderSelected the orderSelected to set
	 */
	public void setBuySelected(Buy buySelected) {
		this.buySelected = buySelected;
	}
}
