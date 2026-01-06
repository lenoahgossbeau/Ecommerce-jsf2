/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.dao.EntreeDao;
import cm.itac.formation.ecommerce.dao.Type_EntreeDao;
import cm.itac.formation.ecommerce.dao.entity.Article;
import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Entree;
import cm.itac.formation.ecommerce.dao.entity.Order;
import cm.itac.formation.ecommerce.dao.entity.Shop;
import cm.itac.formation.ecommerce.dao.entity.Stock;
import cm.itac.formation.ecommerce.dao.entity.Type_Entree;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class EntreeBean {


    private Entree      entree;
    

    private Type_Entree       type_Entree;
	
    private String   typeType_Entree;
    // Injection de notre EJB (Session Bean Stateless)
    
    Map<String, Type_Entree> type_Entrees;
   
    List<Entree>   entrees;
         
      private   List<Type_Entree>   list;     
       private List<Entree>   filteredEntreelist;
   	 
   	    @PostConstruct
   	    public void init() {
   	    	
   	        list = new ArrayList<Type_Entree>();      
   	    }	   
    
//   	 public static final String SESSION_TYPE_ENTREES   = "type_Entrees";
//   	 public static final String SESSION_ENTREES   = "entrees";  
//   
   	 public static final String REQUEST_TYPE_ENTREE = "type_EntreeBean";
     public static final String REQUEST_ENTREE = "entreeBean";
     public static final String REQUEST_ARTICLE_LIST = "articleListBean";
    // Initialisation de l'entité entré
    
    @EJB
    private EntreeDao    entreeDao;
    
    @EJB
    private   Type_EntreeDao      type_EntreeDao;

public EntreeBean() {
	entree = new Entree();
	 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 
//	 entrée = (Entrée) request.getSession().getAttribute(SESSION_ENTREES)   ; 
	
	 if ( entree== null)
		 entree = new Entree();

	
	
	// TODO Auto-generated constructor stub
	 
}
public Map<String, Type_Entree> getType_Entrees() {
	return (Map<String, Type_Entree>) type_Entrees;
}

public void setType_Entrees(Map<String, Type_Entree> type_Entrees) {
	this.type_Entrees = type_Entrees;
}
/**
 * @return the list
 */
public List<Type_Entree> getList() {
	return list;
}

/**
 * @param list the list to set
 */
public void setList(List<Type_Entree> list) {
	this.list = list;
}


public Entree getEntree(){
	return  entree;
}
 
	public void setEntree(Entree entree) {
		this. entree = entree;
	} 
  // Méthode d'action appelée lors du clic sur le bouton du formulaire
// d'inscription
public String  creerEntree() {
    initialiserDateEntree();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    Type_EntreeBean type_EntreeBean = (Type_EntreeBean) request.getAttribute(REQUEST_TYPE_ENTREE); 
    ArticleListBean articleListBean = (ArticleListBean) request.getSession().getAttribute(REQUEST_ARTICLE_LIST)   ; 
    EntreeBean entreeBean = (EntreeBean) request.getAttribute(REQUEST_ENTREE)   ; 
    String  typeSelected = typeType_Entree;

    Type_Entree type_Entree = new Type_Entree();
    
    type_Entree.setId(Long.valueOf(typeType_Entree));

    entree.setDate(new DateTime(new Date().getTime()));
	entree.setType_Entree(type_Entree);
	List<Article> articles = articleListBean.getArticleSelected();	
	if(2 == type_Entree.getId()){
	Stock stock = new Stock();
	stock.setDate(entree.getDate());
	stock.setDescription(entree.getNom());
	stock.setQuantité(String.valueOf(articles.size()));
	stock.setArticles(articles);
	entree.setQuantité(String.valueOf(articles.size()));
	stock.setEntree(entree);
	entree.setStock(stock);
	entree.setArticles(articles);
	} else {
    Shop shop = new Shop();
    shop.setDate(entree.getDate());
    shop.setDescription(entree.getNom());
    shop.setQuantité(String.valueOf(articles.size()));
    entree.setQuantité(String.valueOf(articles.size()));
    shop.setArticles(articles);
    shop.setEntree(entree);
	entree.setShop(shop);
	entree.setArticles(articles);
	}
    entree = entreeDao.creer(entree);
    if(2 == type_Entree.getId()){
    FacesMessage message = new FacesMessage( "Succès de création d'une entree en Stock!" );
    FacesContext.getCurrentInstance().addMessage( null, message );
    } else{
    	FacesMessage message = new FacesMessage( "Succès de création d'une entree en Shop!" );
    	 FacesContext.getCurrentInstance().addMessage( null, message );
    }
   
    return "entreeOverview";
}




/**
 * @return the type_Entree
 */
public Type_Entree getType_Entree() {
	return type_Entree;
}

/**
 * @param type_Entree the type_Entree to set
 */
public void setType_Entree(Type_Entree type_Entree) {
	this.type_Entree = type_Entree;
}

/**
 * @return the typeType_Entree
 */
public String getTypeType_Entree() {
	return typeType_Entree;
}

/**
 * @param typeType_Entree the typeType_Entree to set
 */
public void setTypeType_Entree(String typeType_Entree) {
	this.typeType_Entree = typeType_Entree;
}



/**
 * @return the filteredEntreelist
 */
public List<Entree> getFilteredEntreelist() {
	return filteredEntreelist;
}

/**
 * @param filteredEntreelist the filteredEntreelist to set
 */
public void setFilteredEntreelist(List<Entree> filteredEntreelist) {
	this.filteredEntreelist = filteredEntreelist;
}

private void  initialiserDateEntree() {
    Timestamp date = new Timestamp( System.currentTimeMillis() );
    DateTime dt = new DateTime(date);
    entree.setDate(dt);
}

public void handleChange(AjaxBehaviorEvent vce){  
	  String name= (String) ((UIOutput) vce.getSource()).getValue(); 
	String type_entree = name;
  System.out.println("New value: " + type_entree);
  
}

}
