/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import cm.itac.formation.ecommerce.dao.Daily_FundDao;
import cm.itac.formation.ecommerce.dao.entity.Article;
import cm.itac.formation.ecommerce.dao.entity.Daily_Fund;

/**
 * @author admin
 *
 */
@RequestScoped
@ManagedBean
public class Daily_FundListBean implements Serializable{
	 private static final long serialVersionUID = 1L;
	 private List<Daily_Fund>   dashboardDailyFund;
	 private List<Daily_Fund> daily_Funds;
	 private Daily_Fund daily_Fund;
	 @EJB
	 private Daily_FundDao     daily_FundDao;

	/**
	 * 
	 */
	public Daily_FundListBean() {
		daily_Funds = new ArrayList<Daily_Fund>();
		// TODO Auto-generated constructor stub
	}
	public List<Daily_Fund> getDaily_Funds() {
		daily_Funds = daily_FundDao.lister();
		if(daily_Funds == null)
			daily_Funds = new ArrayList<Daily_Fund>();
		return daily_Funds;
	}

	
	
	public String detail(Daily_Fund daily_Fund){
		this.daily_Fund = daily_FundDao.detail(daily_Fund);
		
		return "dailyFundOverview";
	}
	public String modifier(Daily_Fund  daily_Fund) {
		
        this.daily_Fund = daily_FundDao.modifier(daily_Fund);

            FacesMessage message = new FacesMessage(
                       "Succés de la modification du fond !");
            FacesContext.getCurrentInstance().addMessage(null ,message);

        return "dailyFundEdit";
}
	public String supprimer(Daily_Fund daily_Fund){
		
		daily_FundDao.supprimer(daily_Fund);
		
		return "dailyFundList";
	}
	/**
	 * @return the daily_Fund
	 */
	public Daily_Fund getDaily_Fund() {
		return daily_Fund;
	}
	/**
	 * @param daily_Fund the daily_Fund to set
	 */
	public void setDaily_Fund(Daily_Fund daily_Fund) {
		this.daily_Fund = daily_Fund;
	}
	
	
}
