package cm.itac.formation.ecommerce.bean;


import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.chart.PieChartModel;

import cm.itac.formation.ecommerce.dao.Account_TransactionDao;
import cm.itac.formation.ecommerce.dao.ArticleDao;
import cm.itac.formation.ecommerce.dao.BuyDao;
import cm.itac.formation.ecommerce.dao.Daily_FundDao;
import cm.itac.formation.ecommerce.dao.Type_TransactionDao;
import cm.itac.formation.ecommerce.dao.entity.Account_Transaction;
import cm.itac.formation.ecommerce.dao.entity.Article;
import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Daily_Fund;
import cm.itac.formation.ecommerce.dao.entity.Type_Transaction;

@RequestScoped
@ManagedBean
public class DashboardJourBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String REQUEST_BUY = "buyBean";
    public static final String REQUEST_ARTICLE_LIST = "articleListBean";
    
    
    private Date startDate;
    private Date endDate;
    private DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
    private String dateRangeString;
    
	private Integer buyArticles;
	boolean isValid = false;
	private BigDecimal currentFund;
	private BigDecimal currentReelFund;
	private BigDecimal amountBuy;
	private BigDecimal dailyFund;
	private BigDecimal depositAccount;
	private BigDecimal withdrawallAccount;
	
	
	
	private PieChartModel pieModel3;
	private DashboardModel model;
	
	
	
	private List<Account_Transaction> lister;
	@EJB
	private Account_TransactionDao  account_TransactionDao;
	private List<Daily_Fund> liste;
	@EJB
	private Daily_FundDao  daily_FundDao;
	
	private List<Article>  list;
	@EJB
	private ArticleDao         articleDao;
	@EJB
	private BuyDao         buyDao;
	@EJB
	private Type_TransactionDao type_TransactionDao;
	

	
	
	@PostConstruct
	public void init() {
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		try{
		isValid = (boolean) session.getAttribute("isValid");}
		catch (Exception e) {
			// TODO: handle exception
		}
		if(isValid){
		endDate = (Date) session.getAttribute("endDate");
		startDate = (Date) session.getAttribute("startDate");
		isValid =false;
		session.setAttribute("isValid", isValid);
		}
		if(startDate == null){
		startDate = new Date(); 
		startDate.setHours(00);
		startDate.setMinutes(00);
		startDate.setSeconds(00);
		}
		if(endDate == null){
		endDate = new Date();
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		}
		Date dateStart = new Date();
		Date dateEnd = new Date();
		dateEnd.setHours(23);
		dateEnd.setMinutes(59);
		dateEnd.setSeconds(59);
		
		
		if(startDate != null){
		dateStart = startDate;
		dateStart.setHours(00);
		dateStart.setMinutes(00);
		dateStart.setSeconds(00);
		}
		if(endDate != null){
			dateEnd = endDate;
		dateEnd.setHours(23);
		dateEnd.setMinutes(59);
		dateEnd.setSeconds(59);
		}
		/*
		 * To convert java.util.Date to String, use SimpleDateFormat class.
		 */
		
		/*
		 * crate new SimpleDateFormat instance with desired date format.
		 * We are going to use yyyy-mm-dd hh:mm:ss here. 
		 */
		
		/*DateFormat dateFormatStart = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		DateFormat dateFormatEnd = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		//to convert Date to String, use format method of SimpleDateFormat class.
		//String dateTime = dateFormat.format(date);
		String dateTimeStart = dateFormatStart.format(dateStart);
		String dateTimeEnd = dateFormatEnd.format(dateEnd);
		
		
		
		// Stock gestion
		
		// Format for input
     	DateTimeFormatter dtfStart = DateTimeFormat.forPattern("dd-MM-yyyy hh:mm:ss");
     	DateTimeFormatter dtfEnd = DateTimeFormat.forPattern("dd-MM-yyyy hh:mm:ss");*/
		// Format for output
		//DateTime jodaTime = dtf.parseDateTime(dateTime);
		DateTime jodaTimeStart = new DateTime(dateStart);
		DateTime jodaTimeEnd = new DateTime(dateEnd);
	
		
//		DateTime jodaTime = new DateTime(dateTime);
				//DateTimeFormatter dtfOut = DateTimeFormat.forPattern("MM/dd/yyyy");
		// Parsing the date
		//DateTime jodaTime = dtfOut.parseDateTime(dateTime);
	
		List<Buy> buyList = buyDao.findByDateRange(jodaTimeStart,jodaTimeEnd);
		amountBuy = new BigDecimal(0);
		for(Buy buy : buyList ){
			if(buy.getMontant() != null)
				amountBuy = amountBuy.add(buy.getMontant()); 
		}
		
		BigDecimal amountDepositDaly = new BigDecimal(0);
		for(Buy buy : buyList ){
			if(buy.getAmount_deposit() != null)
				amountDepositDaly = amountDepositDaly.add(buy.getAmount_deposit()); 
		}
		List<Daily_Fund> daily_Funds = daily_FundDao.findDailyByDateRange(jodaTimeStart,jodaTimeEnd);
		dailyFund = new BigDecimal(0);
		for(Daily_Fund daily_Fund : daily_Funds ){
			if(daily_Fund.getAmount() != null)
				dailyFund = dailyFund.add(daily_Fund.getAmount()); 
		}
		
		Type_Transaction  deposit = new Type_Transaction();
		
		deposit = type_TransactionDao.trouver(new Long(1));
		List<Account_Transaction> account_TransactionDepositList = account_TransactionDao.getAccountTransactionByDateRangeAndByTransactioType(jodaTimeStart,jodaTimeEnd, deposit);
		depositAccount = new BigDecimal(0);
		for(Account_Transaction account_Transaction : account_TransactionDepositList ){
			if(account_Transaction.getAmount() != null)
				depositAccount = depositAccount.add(account_Transaction.getAmount()); 
		}
		
		
		Type_Transaction withdrawall = new Type_Transaction();
		withdrawall = type_TransactionDao.trouver(new Long(2));
		List<Account_Transaction> account_Transaction_ChargeList = account_TransactionDao.getAccountTransactionByDateRangeAndByTransactioType(jodaTimeStart,jodaTimeEnd, withdrawall);
		withdrawallAccount = new BigDecimal(0);
		for(Account_Transaction account_Transaction : account_Transaction_ChargeList ){
			if(account_Transaction.getAmount() != null)
				withdrawallAccount = withdrawallAccount.add(account_Transaction.getAmount()); 
		}
		
		model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        
        column1.addWidget("currentFund");
        column2.addWidget("ReelFund");
        
        model.addColumn(column1);
        model.addColumn(column2);
        
		 currentFund = new BigDecimal(0);
		 currentFund  = currentFund.add((amountBuy).add(dailyFund).add(depositAccount).subtract(withdrawallAccount)); 
			
			currentReelFund = new BigDecimal(0);
			currentReelFund  = currentReelFund.add((amountDepositDaly).add(dailyFund).add(depositAccount).subtract(withdrawallAccount));
		//load account management
		
		createPieModels();
		
	}
	
	public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
         
        addMessage(message);
    }
	public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
         
        addMessage(message);
    }
     
    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public DashboardModel getModel() {
        return model;
    }
	private void createPieModels() {
	    createPieModel3();
	    
	}
	
	public PieChartModel getPieModel3() {
	    return pieModel3;
	}
	
	private void createPieModel3() {
	    pieModel3 = new PieChartModel();
	     
	    pieModel3.set("Montant des achats", amountBuy);
	    pieModel3.set("Fond de commerce", dailyFund);
	    pieModel3.set("Depot de compte", depositAccount);
	    pieModel3.set("Compte de charge", withdrawallAccount);
	    
	     
	    pieModel3.setTitle("Vue Globale de Nos Transactions Quotidiennes");
	    pieModel3.setLegendPosition("w");
	    pieModel3.setShadow(true);
	    
	   
	    
	}
	
	public DashboardJourBean() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @return the amountBuy
	 */
	public BigDecimal getAmountBuy() {
		return amountBuy;
	}

	/**
	 * @param amountBuy the amountBuy to set
	 */
	public void setAmountBuy(BigDecimal amountBuy) {
		this.amountBuy = amountBuy;
	}

	/**
	 * @return the dailyFund
	 */
	public BigDecimal getDailyFund() {
		return dailyFund;
	}

	/**
	 * @param dailyFund the dailyFund to set
	 */
	public void setDailyFund(BigDecimal dailyFund) {
		this.dailyFund = dailyFund;
	}

	
	/**
	 * @return the depositAccount
	 */
	public BigDecimal getDepositAccount() {
		return depositAccount;
	}

	/**
	 * @param depositAccount the depositAccount to set
	 */
	public void setDepositAccount(BigDecimal depositAccount) {
		this.depositAccount = depositAccount;
	}

	/**
	 * @return the withdrawallAccount
	 */
	public BigDecimal getWithdrawallAccount() {
		return withdrawallAccount;
	}

	/**
	 * @param withdrawallAccount the withdrawallAccount to set
	 */
	public void setWithdrawallAccount(BigDecimal withdrawallAccount) {
		this.withdrawallAccount = withdrawallAccount;
	}

	/**
	 * @return the buyArticles
	 */
	public Integer getBuyArticles() {
		return buyArticles;
	}

	/**
	 * @param buyArticles the buyArticles to set
	 */
	public void setBuyArticles(Integer buyArticles) {
		this.buyArticles = buyArticles;
	}

	/**
	 * @return the list
	 */
	public List<Article> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Article> list) {
		this.list = list;
	}

	/**
	 * @return the liste
	 */
	public List<Daily_Fund> getListe() {
		return liste;
	}

	/**
	 * @param liste the liste to set
	 */
	public void setListe(List<Daily_Fund> liste) {
		this.liste = liste;
	}

	/**
	 * @return the lister
	 */
	public List<Account_Transaction> getLister() {
		return lister;
	}

	/**
	 * @param lister the lister to set
	 */
	public void setLister(List<Account_Transaction> lister) {
		this.lister = lister;
	}

	/**
	 * @return the currentFund
	 */
	public BigDecimal getCurrentFund() {
		return currentFund;
	}

	/**
	 * @param currentFund the currentFund to set
	 */
	public void setCurrentFund(BigDecimal currentFund) {
		this.currentFund = currentFund;
	}

	/**
	 * @return the currentReelFund
	 */
	public BigDecimal getCurrentReelFund() {
		return currentReelFund;
	}

	/**
	 * @param currentReelFund the currentReelFund to set
	 */
	public void setCurrentReelFund(BigDecimal currentReelFund) {
		this.currentReelFund = currentReelFund;
	}

	 public Date getStartDate() {
	        return startDate;
	    }

	    public void setStartDate(Date startDate) {
	        this.startDate = startDate;
	       
	        init();
	    }

	    public Date getEndDate() {
	        return endDate;
	    }

	    public void setEndDate(Date endDate) {
	        this.endDate = endDate;
	        if(startDate.after(endDate)){
	        	FacesContext facesContext = FacesContext.getCurrentInstance();
		        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date Selected", "Erreur selection de la date de recherche"));

	        }
	        	
	    }

	    public String getDateRangeString() {
	        return String.format("From: %s ",
	                formatter.format(startDate), formatter.format(endDate));
	    }

		/**
		 * @param dateRangeString the dateRangeString to set
		 */
		public void setDateRangeString(String dateRangeString) {
			this.dateRangeString = dateRangeString;
		}
		public void onDateStartSelect(AjaxBehaviorEvent event) {
			org.primefaces.component.calendar.Calendar date = (org.primefaces.component.calendar.Calendar) event.getSource();
			startDate = (Date) date.getValue();
	    }
		
		public void onDateEndSelect(AjaxBehaviorEvent event) {
			org.primefaces.component.calendar.Calendar date = (org.primefaces.component.calendar.Calendar) event.getSource();
			endDate = (Date) date.getValue();
			init();
	       // facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	    }
		
		
		 public String search() {
			 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			 HttpSession session = request.getSession();
			 session.setAttribute("endDate", endDate);
			 session.setAttribute("startDate", startDate);
			 isValid = true;
			 session.setAttribute("isValid", isValid);
			 if(startDate.after(endDate)){
				 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Date Selected", "Succès de la création d'un article !" );
			       FacesContext.getCurrentInstance().addMessage( null, message );
			    
		        } 
			 return "dashboardJour?faces-redirect=true";
		 }
		 
		 
		 public void reload() throws IOException {
			    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
			}

		/**
		 * @return the isValid
		 */
		public boolean isValid() {
			return isValid;
		}

		/**
		 * @param isValid the isValid to set
		 */
		public void setValid(boolean isValid) {
			this.isValid = isValid;
		}
		 
		 
		 
		 
}
