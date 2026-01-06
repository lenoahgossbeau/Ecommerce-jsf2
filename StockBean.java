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
import cm.itac.formation.ecommerce.dao.SortirDao;
import cm.itac.formation.ecommerce.dao.StockDao;
import cm.itac.formation.ecommerce.dao.entity.Entree;
import cm.itac.formation.ecommerce.dao.entity.Sortir;
import cm.itac.formation.ecommerce.dao.entity.Stock;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class StockBean {

	  private Stock      stock;
	  
	  private Entree  entree;
	  
	  private Sortir  sortir;
	  private List<Stock> filteredStockList;
      
	  
	  Map<String, Entree> Entrees;
	  Map<String, Sortir> Sortirs;
	  
	  List<Stock>   stocks;
	 
       public static final String SESSION_ENTREES = "entrees";
	   public static final String SESSION_STOCKS   = "stocks";
	   public static final String SESSION_SORTIRS   = "sortir";
	   
	   public static final String REQUEST_SORTIR = "sortirBean";
	   public static final String REQUEST_STOCK = "stockBean";
	   public static final String REQUEST_ENTREE = "EntreeBean";
	   
	   
	    // Injection de notre EJB (Session Bean Stateless)
	    @EJB
	    private StockDao    stockDao;
        @EJB
        private EntreeDao   entreeDao;
        @EJB
        private SortirDao   sortirDao;
	   
	   // Initialisation de l'entité shop
	
	public StockBean() {
		stock = new Stock();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			 
//		 stock = (Stock) request.getSession().getAttribute(SESSION_STOCKS)   ; 
//		entrée =(Entrée) request.getSession().getAttribute(SESSION_ENTREES);	
//		sortir =(Sortir) request.getSession().getAttribute(SESSION_SORTIRS);	
		if ( stock== null)
			 stock = new Stock();
		
		
		
		// TODO Auto-generated constructor stub
	}
	
	public Map<String, Entree> getEntrées() {
		return (Map<String, Entree>) Entrees;
	}
	public Map<String, Sortir> getSortirs() {
		return (Map<String, Sortir>) Sortirs;
	}
	/**
	 * @return the stocks
	 */
	public List<Stock> getStocks() {
		return stocks;
	}

	/**
	 * @param stocks the stocks to set
	 */
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	/**
	 * @param sortirs the sortirs to set
	 */
	public void setSortirs(Map<String, Sortir> sortirs) {
		Sortirs = sortirs;
	}

	public void setEntrees(Map<String, Entree> entree) {
		this.entree = (Entree) entree;
	}
		
	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	  // Méthode d'action appelée lors du clic sur le bouton du formulaire
  // d'inscription
  public String  creerstock() {
      initialiserDateStock();
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      StockBean stockBean = (StockBean) request.getAttribute(REQUEST_STOCK)   ; 
      EntreeBean entreeBean = (EntreeBean) request.getAttribute(REQUEST_ENTREE)   ;
      SortirBean sortirBean = (SortirBean) request.getAttribute(REQUEST_SORTIR)   ;
      try {
  		Thread.sleep(10000);
  	} catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
      
      Entree  entree = new Entree();
      stock.setEntree(entree);
      Sortir  sortir = new Sortir();
      stock.setSortir(sortir);
      
		 stock = stockDao.creer(stockBean.getStock());
       
		
      FacesMessage message = new FacesMessage( "Succès de création d'un stock !" );
      FacesContext.getCurrentInstance().addMessage( null, message );
      return "stockOverview";
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
	 * @return the sortir
	 */
	public Sortir getSortir() {
		return sortir;
	}

	/**
	 * @param sortir the sortir to set
	 */
	public void setSortir(Sortir sortir) {
		this.sortir = sortir;
	}
	

  /**
	 * @return the filteredStockList
	 */
	public List<Stock> getFilteredStockList() {
		return filteredStockList;
	}

	/**
	 * @param filteredStockList the filteredStockList to set
	 */
	public void setFilteredStockList(List<Stock> filteredStockList) {
		this.filteredStockList = filteredStockList;
	}

private void  initialiserDateStock() {
      Timestamp date = new Timestamp( System.currentTimeMillis() );
      DateTime dt = new DateTime(date);
      stock.setDate(dt);
  }
  
}
