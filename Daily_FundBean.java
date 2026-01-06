/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.dao.Daily_FundDao;
import cm.itac.formation.ecommerce.dao.entity.Daily_Fund;
import cm.itac.formation.ecommerce.dao.entity.User;
import cm.itac.formation.ecommerce.security.EcommerceContext;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Daily_FundBean {
	private Daily_Fund   daily_Fund;
	private User utilisateur;
	
	private String idUser;
	private String idDailyFund;
	private String amount;
	private String description;
	
	
	 private List<User>   list;
	
	 // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private Daily_FundDao     daily_FundDao;
    Map<String, Daily_Fund> daily_Funds;
    private List<Daily_Fund>  filteredDaily_FundList;
    
   
    
	    public static final String SESSION_DAILY_FUNDS   = "daily_Funds";

	    public static final String REQUEST_DAILY_FUND = "daily_FundBean";
	    public static final String REQUEST_DAILY_FUND_LIST = "daily_FundListBean";
		public static final String REQUEST_USER = "userBean";
	   
	    // Initialisation de l'entité fournisseur
		public Daily_FundBean() {
			 daily_Fund = new Daily_Fund();
			 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				 
//			 fournisseur = (Fournisseur) request.getSession().getAttribute(SESSION_FOURNISSEURS)   ; 
				
	//		 if ( utilisateur== null)
		//		 utilisateur = new User();
			 
			 Daily_FundListBean daily_FundListBean = (Daily_FundListBean) request.getAttribute(REQUEST_DAILY_FUND_LIST);
				if(daily_FundListBean != null){
					daily_Fund  = daily_FundListBean.getDaily_Fund();
					request.getSession().setAttribute("daily_Fund", daily_Fund);
				 }
			 
				
				// TODO Auto-generated constructor stub
				idDailyFund = String.valueOf(daily_Fund.getId());
				amount = String.valueOf(daily_Fund.getAmount());
				description = daily_Fund.getDescription();
		}
		
		
			
		
	    /**
		 * @return the daily_Funds
		 */
		public Map<String, Daily_Fund> getDaily_Funds() {
			List<Daily_Fund> daily_FundList = new ArrayList<Daily_Fund>();

			daily_Funds = new HashMap<String, Daily_Fund>();
			daily_FundList = daily_FundDao.lister();
			for(Daily_Fund daily_Fund : daily_FundList)
				daily_Funds.put(String.valueOf(daily_Fund.getId()), daily_Fund);
			return daily_Funds;
		}




		/**
		 * @param daily_Funds the daily_Funds to set
		 */
		public void setDaily_Funds(Map<String, Daily_Fund> daily_Funds) {
			this.daily_Funds = daily_Funds;
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


	

		public String  creerfond() {
	        initialiserDateFond();
	        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        Daily_FundBean daily_FundBean = ( Daily_FundBean) request.getAttribute(REQUEST_DAILY_FUND)   ; 
			 UserBean userBean = (UserBean) request.getAttribute(REQUEST_USER)   ;
 
	        String codeSelected = idUser;
	        User user = EcommerceContext.getEcommerceContextByRequest(request).getUser();
	        daily_Fund.setUser(user);
			 
	        daily_Fund = daily_FundDao.creer(daily_FundBean.getDaily_Fund());
			   FacesMessage message = new FacesMessage( "Succès de création du fond journalier!" );
	       
	        FacesContext.getCurrentInstance().addMessage( null, message );
	        return "dailyFundOverview";
	    }
	    
	    public String  modifier() {
	        initialiserDateFond();
	        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        Daily_FundListBean daily_FundListBean = ( Daily_FundListBean) request.getAttribute(REQUEST_DAILY_FUND_LIST); 
	        if(daily_FundListBean != null){
	        	daily_Fund  = daily_FundListBean.getDaily_Fund();
		        request.getSession().setAttribute("daily_Fund", daily_Fund);
		        } else if(request.getSession().getAttribute("daily_Fund") != null){
		        	Daily_Fund daily = 	(Daily_Fund) request.getSession().getAttribute("daily_Fund");
		        	daily_Fund.setId(daily.getId());
		        }  
		  
	        Daily_FundBean daily_FundBean = ( Daily_FundBean) request.getAttribute(REQUEST_DAILY_FUND)   ; 
	        daily_FundDao.modifier(daily_Fund);
	      
			 FacesMessage message = new FacesMessage( "Succès de la modification du fond!" );
	        FacesContext.getCurrentInstance().addMessage( null, message );
	       
	        return "dailyFundEdit";
	    }
	    
	   
		private void  initialiserDateFond() {
	        Timestamp date = new Timestamp( System.currentTimeMillis() );
	       DateTime dt = new DateTime(date);
	       daily_Fund.setDate(dt);
	    }
		/**
		 * @return the idDailyFund
		 */
		public String getIdDailyFund() {
			return idDailyFund;
		}
		/**
		 * @param idDailyFund the idDailyFund to set
		 */
		public void setIdDailyFund(String idDailyFund) {
			this.idDailyFund = idDailyFund;
		}
		/**
		 * @return the amount
		 */
		public String getAmount() {
			return amount;
		}
		/**
		 * @param amount the amount to set
		 */
		public void setAmount(String amount) {
			this.amount = amount;
		}
		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/**
		 * @return the filteredDaily_FundList
		 */
		public List<Daily_Fund> getFilteredDaily_FundList() {
			return filteredDaily_FundList;
		}
		/**
		 * @param filteredDaily_FundList the filteredDaily_FundList to set
		 */
		public void setFilteredDaily_FundList(List<Daily_Fund> filteredDaily_FundList) {
			this.filteredDaily_FundList = filteredDaily_FundList;
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
		
		
		
}
