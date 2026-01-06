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

import cm.itac.formation.ecommerce.dao.Type_ArticleDao;
import cm.itac.formation.ecommerce.dao.entity.Type_Article;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Type_ArticleBean {
	private Type_Article    type_Article;

	 // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private Type_ArticleDao    type_ArticleDao;
    Map<String, Type_Article> type_Articles;
    
	    public static final String SESSION_TYPE_ARTICLES   = "type_Articles";
	    public static final String REQUEST_TYPE_ARTICLE = "type_ArticleBean";
	   
	    // Initialisation de l'entité fournisseur
		public Type_ArticleBean() {
			type_Article = new Type_Article();
			 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				 
//			 type_Article = (Type_Article) request.getSession().getAttribute(SESSION_TYPE_ARTICLES)   ; 
//				
			 if ( type_Article== null)
				 type_Article = new Type_Article();
			
			
			
			// TODO Auto-generated constructor stub
		}
		public Map<String, Type_Article> getType_Articles() {
			List<Type_Article> type_ArticleList = new ArrayList<Type_Article>();
//			if(type_Articles == null){
				type_Articles = new HashMap<String, Type_Article>();
				type_ArticleList = type_ArticleDao.lister();
				for(Type_Article type_Article : type_ArticleList)
					type_Articles.put(String.valueOf(type_Article.getId()), type_Article);
//			}
			return  type_Articles;
		}
		/**
		 * @return the type_Article
		 */
		public Type_Article getType_Article() {
			return type_Article;
		}
		/**
		 * @param type_Article the type_Article to set
		 */
		public void setType_Article(Type_Article type_Article) {
			this.type_Article = type_Article;
		}
		
		  // Méthode d'action appelée lors du clic sur le bouton du formulaire
	    
		  public String  creertype_Article() {
		        
		        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				 Type_ArticleBean type_ArticleBean = ( Type_ArticleBean) request.getAttribute(REQUEST_TYPE_ARTICLE)   ; 
				
				
		        return "type_Article";
		    }
}
