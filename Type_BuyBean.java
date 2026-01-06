/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import cm.itac.formation.ecommerce.dao.Type_BuyDao;
import cm.itac.formation.ecommerce.dao.entity.Type_Buy;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Type_BuyBean {
private  Type_Buy   type_Buy;
	
	@EJB
	private  Type_BuyDao   type_BuyDao;
	

    Map<String, Type_Buy> type_Buys;
    
    public static final String SESSION_TYPE_BUYS   = "type_Buy";
    public static final String REQUEST_TYPE_BUY = "type_BuyBean";

	/**
	 * 
	 */
 // Initialisation de l'entité type_Account
	public Type_BuyBean() {
		

		type_Buy = new Type_Buy();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			
		 
		 if ( type_Buy== null)
			 type_Buy = new Type_Buy();
		
		// TODO Auto-generated constructor stub
	}
	public Map<String, Type_Buy> getType_Buys() {
		List<Type_Buy> type_BuyList = new ArrayList<Type_Buy>();
//		if(type_Transaction == null){
		type_Buys = new HashMap<String, Type_Buy>();
		type_BuyList = type_BuyDao.lister();
			for(Type_Buy type_Buy : type_BuyList)
				type_Buys.put(String.valueOf(type_Buy.getId()), type_Buy);

		return  type_Buys;
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
	public void setType_Buys(Map<String, Type_Buy> type_Buys) {
		this.type_Buys = type_Buys;
	}
	
}
