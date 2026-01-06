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

import cm.itac.formation.ecommerce.dao.Status_BuyDao;
import cm.itac.formation.ecommerce.dao.entity.Status_Buy;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Status_BuyBean {

	private Status_Buy    status_Buy;
	
	
	
	@EJB
    private Status_BuyDao    status_BuyDao;
	
	Map<String, Status_Buy> Status_Buys;
	
	public static final String SESSION_STATUS_BUY   = "status_buys";
    public static final String REQUEST_STATUS_BUY = "status_BuyBean";
	/**
	 * 
	 */
	public Status_BuyBean() {
		
		status_Buy = new Status_Buy();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			
		 
		 if ( status_Buy== null)
			 status_Buy = new Status_Buy();
		// TODO Auto-generated constructor stub
	}
	
	public Map<String, Status_Buy> getStatus_Buys() {
		List<Status_Buy> status_BuyList = new ArrayList<Status_Buy>();
//		if(status_Buy == null){
		Status_Buys = new HashMap<String, Status_Buy>();
		status_BuyList = status_BuyDao.lister();
			for(Status_Buy status_Buy : status_BuyList)
				Status_Buys.put(String.valueOf(status_Buy.getId()), status_Buy);

		return  Status_Buys;
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
	 * @param status_Buys the status_Buys to set
	 */
	public void setStatus_Buys(Map<String, Status_Buy> status_Buys) {
		Status_Buys = status_Buys;
	}
	public String  creertype_Article() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Status_BuyBean status_BuyBean = ( Status_BuyBean) request.getAttribute(REQUEST_STATUS_BUY)   ; 
		
		
        return "status_Buy";
    }
}
