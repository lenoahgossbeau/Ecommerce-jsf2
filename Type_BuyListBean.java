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

import cm.itac.formation.ecommerce.dao.Type_BuyDao;
import cm.itac.formation.ecommerce.dao.entity.Type_Buy;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Type_BuyListBean implements Serializable{

	
private static final long serialVersionUID = 1L;
	
	private List<Type_Buy> type_Buys;
	 private Type_Buy type_Buy;
	
	 
	 @EJB
	 private Type_BuyDao     type_BuyDao;
	/**
	 * 
	 */
	public Type_BuyListBean() {
		// TODO Auto-generated constructor stub
		type_Buys = new ArrayList<Type_Buy>();
	}
	/**
	 * @return the type_Accounts
	 */
	public List<Type_Buy> getType_Buys() {
		type_Buys = type_BuyDao.lister();
		if(type_Buys == null)
			type_Buys = new ArrayList<Type_Buy>();
		return type_Buys;
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
	 * @param type_Buys the type_Buys to set
	 */
	public void setType_Buys(List<Type_Buy> type_Buys) {
		this.type_Buys = type_Buys;
	}
	
}
