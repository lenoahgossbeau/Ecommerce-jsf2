/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import cm.itac.formation.ecommerce.dao.Deposit_BuyDao;
import cm.itac.formation.ecommerce.dao.entity.Deposit_Buy;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Deposit_BuyListBean {
	 private List<Deposit_Buy> deposit_Buys;
	 private  Deposit_Buy    deposit_Buy;
	
	 @EJB
	 private  Deposit_BuyDao      deposit_BuyDao;
	 
	public Deposit_BuyListBean() {
		deposit_Buys = new ArrayList<Deposit_Buy>();
		// TODO Auto-generated constructor stub
	}

	public List<Deposit_Buy> getDeposit_Buys() {
		deposit_Buys = deposit_BuyDao.lister();
			if(deposit_Buys == null)
				deposit_Buys = new ArrayList<Deposit_Buy>();
			return deposit_Buys;
		}
	public String detail(Deposit_Buy deposit_Buy){
		this.deposit_Buy = deposit_BuyDao.detail(deposit_Buy);
		
		return "deposit_BuyOverview";
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

	/**
	 * @param deposit_Buys the deposit_Buys to set
	 */
	public void setDeposit_Buys(List<Deposit_Buy> deposit_Buys) {
		this.deposit_Buys = deposit_Buys;
	}
	
}
