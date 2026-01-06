/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import cm.itac.formation.ecommerce.dao.Type_ArticleDao;
import cm.itac.formation.ecommerce.dao.entity.Type_Article;

/**
 * @author admin
 *
 */
@RequestScoped
@ManagedBean
public class Type_ArticleListBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Type_Article> type_Articles;
	 private Type_Article type_Article;
	
	 
	 @EJB
	 private Type_ArticleDao     type_ArticleDao;

	/**
	 * 
	 */
	public Type_ArticleListBean() {
		
		type_Articles = new ArrayList<Type_Article>();
	}
	
	public List<Type_Article> getType_Articles() {
		type_Articles = type_ArticleDao.lister();
		if(type_Articles == null)
			type_Articles = new ArrayList<Type_Article>();
		return type_Articles;
	}
	
	
	public String supprimer(Type_Article type_Article){
		type_ArticleDao.supprimer(type_Article);
		
		return "type_ArticleList";
	}
	/**
	 * @return the type_Article
	 */
	public Type_Article getType_Article() {
		return type_Article;
	}
	/**
	 * @param Type_Article the Type_Article to set
	 */
	public void setType_Article(Type_Article type_Article) {
		this.type_Article = type_Article;
	}
	
}
