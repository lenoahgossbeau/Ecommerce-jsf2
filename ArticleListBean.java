/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import cm.itac.formation.ecommerce.dao.ArticleDao;
import cm.itac.formation.ecommerce.dao.FournisseurDao;
import cm.itac.formation.ecommerce.dao.Type_ArticleDao;
import cm.itac.formation.ecommerce.dao.entity.Article;
import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Entree;
import cm.itac.formation.ecommerce.dao.entity.Order;
import cm.itac.formation.ecommerce.dao.entity.Shop;
import cm.itac.formation.ecommerce.dao.entity.Stock;

/**
 * @author ricky
 *
 */
@SessionScoped
@ManagedBean
public class ArticleListBean implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 private List<Article>  articles;
	 private List<Article>  articlesEntred;
	 private List<Article>  articlesSelect;
	 private Article article;
	 private List<Article>   dashboardArticles;
	 public static final String REQUEST_ENTREELISTOVERVIEW = "entreeListBean";
	 public static final String REQUEST_ENTREEBEAN = "entreeBean";
	 public static final String REQUEST_ORDERLISTOVERVIEW = "commandeListBean";
	 public static final String REQUEST_COMMANDEBEAN = "commandeBean";
	 public boolean reload;
	 private List<Article> articleChanged;
	 
	 
	 private double quantite;
	 private List<Article> articleSelected;
	 
	 @EJB
	 private ArticleDao     articleDao;
	 
	 @EJB
	 private FournisseurDao fournisseurDao;

	 @EJB
	 private Type_ArticleDao type_articeDao;
	
	 /**
	 * 
	 */
	 
	public ArticleListBean() {
		articles = new ArrayList<Article>();
		articleChanged = new ArrayList<Article>();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the article
	 */
	
	public List<Article> getArticles() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		EntreeListBean entreeListBean = (EntreeListBean) request.getAttribute(REQUEST_ENTREELISTOVERVIEW); 
		EntreeBean entreeBean = (EntreeBean) request.getAttribute(REQUEST_ENTREEBEAN);
		CommandeBean commandeBean = (CommandeBean) request.getAttribute(REQUEST_COMMANDEBEAN);
		ArticleListBean articleListBean = (ArticleListBean) request.getAttribute("articleListBean");
		String load = request.getParameter("reload");
		if(entreeListBean != null && entreeBean == null && commandeBean == null){
			articles = articleDao.getArticlesByEntree(entreeListBean.getEntree());
		}
		else if(entreeListBean == null && entreeBean != null && commandeBean == null){
			String type_entree = entreeBean.getTypeType_Entree();
		    System.out.println("New value: " + type_entree);
		    if("2".equals(type_entree)){
				articles = articleDao.getArticlesByParameters(null, new Stock(), new Shop(), new Order(), new Buy());
			} else {
				articles = articleDao.getArticlesByParameters(new Entree(), new Stock(), null, new Order(), new Buy());
			}
			//articles = articleDao.getArticlesByParameters(null, new Stock(), new Shop(), new Order(), new Buy());
		} else if(entreeListBean == null && entreeBean == null && commandeBean != null){
			articles = articleDao.getArticlesByParameters(new Entree(), new Stock(), new Shop(), null, new Buy());
		} else if (articles == null || (articles.size() == 0 || isReload()) || "true".equals(load)){
			
				articles = articleDao.lister();
				setReload(false);
				
		}
		if(articles == null )
			articles = new ArrayList<Article>();
		return articles;
	}
	public List<Article> getArticlesEntred() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		EntreeListBean entreeListBean = (EntreeListBean) request.getAttribute(REQUEST_ENTREELISTOVERVIEW); 
		EntreeBean entreeBean = (EntreeBean) request.getAttribute(REQUEST_ENTREEBEAN);
		CommandeBean commandeBean = (CommandeBean) request.getAttribute(REQUEST_COMMANDEBEAN);
		if(entreeListBean != null && entreeBean == null && commandeBean == null){
		articlesEntred = articleDao.getArticlesByEntree(entreeListBean.getEntree());
		}
		else if(entreeListBean == null && entreeBean != null && commandeBean == null){
		articlesEntred = articleDao.getArticlesByParameters(null, new Stock(), new Shop(), new Order(), new Buy());
		} else if(entreeListBean == null && entreeBean == null && commandeBean != null){
			articlesEntred = articleDao.getArticlesByParameters(new Entree(), new Stock(), new Shop(), null, new Buy());
		}
		if(articlesEntred == null)
			articlesEntred = new ArrayList<Article>();
		return articlesEntred;
	}
	
	public List<Article> getArticlesSelect() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		CommandeListBean commandeListBean = (CommandeListBean) request.getAttribute(REQUEST_ORDERLISTOVERVIEW); 
		articlesSelect = articleDao.getArticlesByOrder(commandeListBean.getCommande());
		if(articlesSelect == null)
			articlesSelect = new ArrayList<Article>();
		
		//quantity = String.valueOf(article.getQuantite()); 
		
		return articlesSelect;
	}

	public String supprimer(Article article){
		articleDao.supprimer(article);
		  FacesMessage message = new FacesMessage( "Succès de la Elimination d'un article !" );
		     
	       FacesContext.getCurrentInstance().addMessage( null, message );
	 
		return "articleList";
	}
	
	public String delete(Article article){
		articleSelected.remove(article);
		  FacesMessage message = new FacesMessage( "Succès de l'Elimination de l'article sélectionné !" );
		     
	       FacesContext.getCurrentInstance().addMessage( null, message );
	 
		return "monPanier";
	}
	
	public String dashboardDetail(String type){
		dashboardArticles = articleDao.getArticlesByDashboard(type);
		
		return "dashboardDetail";
	}
	public String detail(Article article){
		this.article = articleDao.detail(article);
		
		return "articleOverview";
	}
	

	
	public String modifier(Article article){
		this.article = articleDao.detail(article);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		ArticlesBean articleBean = (ArticlesBean) request.getAttribute("articlesBean"); 
		return "articleEdit";
	} 
	/**
	 * @param articles the articles to set
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
	 * @return the articleSelected
	 */
	public List<Article> getArticleSelected() {

		return articleSelected;
	}
	/**
	 * @param articleSelected the articleSelected to set
	 */
	public void setArticleSelected(List<Article> articleSelected) {
		this.articleSelected = articleSelected;
	}
	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	/**
	 * @param articlesEntred the articlesEntred to set
	 */
	public void setArticlesEntred(List<Article> articlesEntred) {
		this.articlesEntred = articlesEntred;
	}
	
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Article Selected", String.valueOf(((Article) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Article Unselected", String.valueOf(((Article) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    

public void handleChange(AjaxBehaviorEvent vce){  
	  String name= (String) ((UIOutput) vce.getSource()).getValue(); 
	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	EntreeBean entreeBean = (EntreeBean) request.getAttribute(REQUEST_ENTREEBEAN);
	String type_entree = entreeBean.getTypeType_Entree();
    System.out.println("New value: " + type_entree);
    if("2".equals(type_entree)){
		articles = articleDao.getArticlesByParameters(null, new Stock(), new Shop(), new Order(), new Buy());
	} else {
		articles = articleDao.getArticlesByParameters(new Entree(), new Stock(), null, new Order(), new Buy());
	} 
    
}
/**
 * @return the dashboardArticles
 */
public List<Article> getDashboardArticles() {
	return dashboardArticles;
}
/**
 * @param dashboardArticles the dashboardArticles to set
 */
public void setDashboardArticles(List<Article> dashboardArticles) {
	this.dashboardArticles = dashboardArticles;
}


public void onCellEdit(CellEditEvent event){
	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	Object oldValue = event.getOldValue();
	Object newValue = event.getNewValue();
	articleChanged = (List<Article>) request.getSession().getAttribute("articleChanged");
	if(articleChanged == null) 
	articleChanged = new ArrayList<Article>();   
	for(Article article: articleSelected){
		double i =((Double) newValue).doubleValue();
		if(i != 0){
		article.setQuantite(((Double) newValue).doubleValue());
		articleChanged.add(article);
		}
	}
	 request.getSession().setAttribute("articleChanged", articleChanged);
	for(Article article : articles){
		article.setQuantite(((Double) newValue).doubleValue());
		break;
	}
	int tmprow = event.getRowIndex();
    String  tmpcolumn = event.getColumn().getHeaderText();
	int i = 0;
	
}
	
	public String monPanier(){
		FacesMessage message = new FacesMessage( "Ci-après se trouve la liste de vos articles sélectionnés! " );
	    FacesContext.getCurrentInstance().addMessage( null, message );
		return "monPanier";
	}
	
	public List<Article> ajouterPanier(Article article){
		
		if (articleSelected == null){
			articleSelected = new ArrayList<Article>();
		}

			articleSelected.add(article);;
		
		return articleSelected;
		
	}

public void onRowEdit(RowEditEvent event) {
	   
    Article article	 = (Article) event.getObject();
   int index =  articles.indexOf(article);
    article.setQuantite(this.quantite);
    articles.set(index, article);
    
}
/**
 * @return the quantite
 */
public double getQuantite() {
	return quantite;
}
/**
 * @param quantite the quantite to set
 */
public void setQuantite(double quantite) {
	this.quantite = quantite;
}
/**
 * @return the articleChanged
 */
public List<Article> getArticleChanged() {
	return articleChanged;
}
/**
 * @param articleChanged the articleChanged to set
 */
public void setArticleChanged(List<Article> articleChanged) {
	this.articleChanged = articleChanged;
}
/**
 * @return the reload
 */
public boolean isReload() {
	return reload;
}
/**
 * @param reload the reload to set
 */
public void setReload(boolean reload) {
	this.reload = reload;
}



}
