/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import cm.itac.formation.ecommerce.dao.ArticleDao;
import cm.itac.formation.ecommerce.dao.entity.Article;

/**
 * @author erico
 *
 */
@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable{
	private static final long serialVersionUID = 1L;
private Double stockArticles;
private Double shopArticles;
private Double orderArticles;
private Double buyArticles;
private Double freeArticles;

private Article article;

private BigDecimal prixStock;
private BigDecimal prixShop;
private BigDecimal prixOrder;
private BigDecimal prixBuy;
private BigDecimal prixFreeArticles;


private PieChartModel pieModel1;
private PieChartModel pieModel2;


private List<Article>  list;
@EJB
private ArticleDao         articleDao;



@PostConstruct
public void init() {
	
	// Stock gestion
	stockArticles = new Double(0);
	List<Article> articleStockList = articleDao.getArticlesByDashboard("stock");
	for(Article article : articleStockList ){
		stockArticles = article.getDisponible() + stockArticles;
	}
	
	 prixStock = new BigDecimal(0);
	for(Article article : articleStockList ){
		prixStock = prixStock.add( article.getPrix().multiply(new BigDecimal(article.getDisponible())));

	}
	// Shop gestion
	shopArticles = new Double(0);
	List<Article> articleShopList = articleDao.getArticlesByDashboard("shop");
	for(Article article : articleShopList ){
		shopArticles = article.getDisponible() + shopArticles;
	}
	
	prixShop = new BigDecimal(0);
	for(Article article : articleShopList ){
		prixShop = prixShop.add( article.getPrix().multiply(new BigDecimal(article.getDisponible())));

	}
    	// Shop order
	orderArticles = new Double(0);
		List<Article> articleOrderList = articleDao.getArticlesByDashboard("order");
		for(Article article : articleOrderList ){
			orderArticles = article.getQuantite() + orderArticles;
		}
		
		prixOrder = new BigDecimal(0);
		for(Article article : articleOrderList ){
			prixOrder = prixOrder.add( article.getPrix().multiply(new BigDecimal(article.getQuantite())));
		}
			// Shop buy
		buyArticles = new Double(0);
				List<Article> articleBuyList = articleDao.getArticlesByDashboard("buy");
				for(Article article : articleBuyList ){
					buyArticles = article.getQuantite() + buyArticles;
				}
				
				//Creation new DashNumber
				 prixBuy = new BigDecimal(0);
				for(Article article : articleBuyList ){
					prixBuy = prixBuy.add( article.getPrix().multiply(new BigDecimal(article.getQuantite())));
					
				}
				// Shop freeArticles
				
				freeArticles = new Double(0);
				List<Article> articleFreeArticlesList = articleDao.getArticlesByDashboard("freeArticles");
				for(Article article : articleFreeArticlesList ){
					freeArticles = article.getDisponible() + freeArticles;
				}
				
				
				//Creation new DashNumber
				 prixFreeArticles = new BigDecimal(0);
				for(Article article : articleFreeArticlesList ){
					prixFreeArticles = prixFreeArticles.add( article.getPrix().multiply(new BigDecimal(article.getDisponible())));
				}

	createPieModels();
   
}
private void createPieModels() {
    createPieModel1();
    createPieModel2();
}

public PieChartModel getPieModel1() {
    return pieModel1;
}
public PieChartModel getPieModel2() {
    return pieModel2;
}
private void createPieModel1() {
    pieModel1 = new PieChartModel();
     
    pieModel1.set("Stock", stockArticles);
    pieModel1.set("Shop", shopArticles);
    pieModel1.set("Order", orderArticles);
    pieModel1.set("Buy", buyArticles);
    pieModel1.set("freeArticles", freeArticles);
     
    pieModel1.setTitle("Vue Globale de Nos Articles en Quantité");
    pieModel1.setLegendPosition("w");
    pieModel1.setShadow(true);
}

private void createPieModel2() {
    pieModel2 = new PieChartModel();
     
    pieModel2.set("Stock", prixStock);
    pieModel2.set("Shop", prixShop);
    pieModel2.set("Order", prixOrder);
    pieModel2.set("Buy", prixBuy);
    pieModel2.set("freeArticles", prixFreeArticles);
     
    pieModel2.setTitle("Vue Globale de Nos Articles en Montant");
    pieModel2.setLegendPosition("e"); 
    pieModel2.setShadow(true);
    
}

/**
 * @return the stockArticles
 */
public Double getStockArticles() {
	return stockArticles;
}
/**
 * @param stockArticles the stockArticles to set
 */
public void setStockArticles(Double stockArticles) {
	this.stockArticles = stockArticles;
}
/**
 * @return the shopArticles
 */
public Double getShopArticles() {
	return shopArticles;
}
/**
 * @param shopArticles the shopArticles to set
 */
public void setShopArticles(Double shopArticles) {
	this.shopArticles = shopArticles;
}
/**
 * @return the orderArticles
 */
public Double getOrderArticles() {
	return orderArticles;
}
/**
 * @param orderArticles the orderArticles to set
 */
public void setOrderArticles(Double orderArticles) {
	this.orderArticles = orderArticles;
}
/**
 * @return the buyArticles
 */
public Double getBuyArticles() {
	return buyArticles;
}
/**
 * @param buyArticles the buyArticles to set
 */
public void setBuyArticles(Double buyArticles) {
	this.buyArticles = buyArticles;
}
/**
 * @return the freeArticles
 */
public Double getFreeArticles() {
	return freeArticles;
}
/**
 * @param freeArticles the freeArticles to set
 */
public void setFreeArticles(Double freeArticles) {
	this.freeArticles = freeArticles;
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
 * @return the prixStock
 */
public BigDecimal getPrixStock() {
	return prixStock;
}
/**
 * @param prixStock the prixStock to set
 */
public void setPrixStock(BigDecimal prixStock) {
	this.prixStock = prixStock;
}
/**
 * @return the prixShop
 */
public BigDecimal getPrixShop() {
	return prixShop;
}
/**
 * @param prixShop the prixShop to set
 */
public void setPrixShop(BigDecimal prixShop) {
	this.prixShop = prixShop;
}
/**
 * @return the prixOrder
 */
public BigDecimal getPrixOrder() {
	return prixOrder;
}
/**
 * @param prixOrder the prixOrder to set
 */
public void setPrixOrder(BigDecimal prixOrder) {
	this.prixOrder = prixOrder;
}
/**
 * @return the prixBuy
 */
public BigDecimal getPrixBuy() {
	return prixBuy;
}
/**
 * @param prixBuy the prixBuy to set
 */
public void setPrixBuy(BigDecimal prixBuy) {
	this.prixBuy = prixBuy;
}
/**
 * @return the prixFreeArticles
 */
public BigDecimal getPrixFreeArticles() {
	return prixFreeArticles;
}
/**
 * @param prixFreeArticles the prixFreeArticles to set
 */
public void setPrixFreeArticles(BigDecimal prixFreeArticles) {
	this.prixFreeArticles = prixFreeArticles;
}
/**
 * @return the article
 */
public Article getArticle() {
	return article;
}
/**
 * @param article the article to set
 */
public void setArticle(Article article) {
	this.article = article;
}


/**
 * @return the prixStock
 */




}
