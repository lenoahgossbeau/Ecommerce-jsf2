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
import cm.itac.formation.ecommerce.dao.SortirDao;
import cm.itac.formation.ecommerce.dao.StockDao;
import cm.itac.formation.ecommerce.dao.entity.Stock;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class StockListBean {
	 private static final long serialVersionUID = 1L;
	 private List<Stock> stocks;
	 private Stock stock;
	 @EJB
	 private StockDao     stockDao;
	 
	 @EJB
	 private   SortirDao    sortirDao;
	 
	 @EJB
	 private   EntreeDao     entréeDao;
	/**
	 * 
	 */
	 
	public StockListBean() {
		stocks = new ArrayList<Stock>();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the stock
	 */
	
	public List<Stock> getStocks() {
		stocks = stockDao.lister();
		if(stocks == null)
			stocks = new ArrayList<Stock>();
		return stocks;
	}

	public String supprimer(Stock stock){
		stockDao.supprimer(stock);
		
		return "stockList";
	}
	/**
	 * @param stocks the stock to set
	 */

	public Stock getStock() {
		return stock;
	}
	/**
	 * @param stocks the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
