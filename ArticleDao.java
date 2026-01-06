/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Article;
import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Entree;
import cm.itac.formation.ecommerce.dao.entity.Fournisseur;
import cm.itac.formation.ecommerce.dao.entity.Order;
import cm.itac.formation.ecommerce.dao.entity.Shop;
import cm.itac.formation.ecommerce.dao.entity.Stock;
import cm.itac.formation.ecommerce.dao.entity.Type_Article;

/**
 * @author erico
 */
 @Stateless
public class ArticleDao {
	 
	 private static final String WHERESTOCKNULL = "article.stock IS NULL";
	 private static final String WHEREORDERNULL = "(article.order IS NULL AND article.stock IS NOT NULL AND article.shop IS NOT NULL) OR (article.order IS NOT NULL AND article.disponible > 0)";
	 private static final String WHEREBUYNULL = "article.buy IS NULL AND article.stock IS NOT NULL AND article.order IS NOT NULL";
	 private static final String WHERESHOPNULL = "article.shop IS NULL AND article.stock IS NOT NULL";
	 private static final String WHEREENTREENULL = "article.entree IS NULL";
	 
	 private static final String WHERESTOCKNOTNULL ="article.stock IS NOT NULL AND article.buy IS NULL AND article.order IS NULL AND article.shop IS NULL" ;
	 private static final String WHERESHOPNOTNULL = "article.shop IS NOT NULL AND article.stock IS NOT NULL AND article.order IS NULL AND article.buy IS NULL";
	 private static final String WHEREORDERNOTNULL ="article.order IS NOT NULL AND article.stock IS NOT NULL AND article.shop IS NOT NULL AND article.buy IS  NULL ";
	 private static final String WHEREBUYNOTNULL = "article.buy IS NOT NULL ";
	 private static final String WHEREFREEARTICLENULL = "article.order IS NULL AND article.stock IS NULL AND article.shop IS NULL AND article.buy IS NULL";
	 
	 //injection du manager qui s'occupe de la bd
	 
	 @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;

	    //recherche d'un nouvelle article
	 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
		public Article creer(Article article) throws DAOException {
			// TODO Auto-generated method stub
			
			
		        try {
		             em.persist(article);
		            em.flush();
		            
		         
		        } catch ( Exception e ) {
		        	// TODO handle exception
		        	  throw new DAOException(e);
		        }
		          
		       
		        return article;
		}
       
		public Article detail(Article article) throws DAOException {
			// TODO Auto-generated method stub
			return trouver(article.getId());
			
		}
	
	  public Article trouver( long id) throws DAOException {
		  // TODO Auto-generate methode  stub
		  try{
			  return em.find(Article.class, id);
		  }catch (Exception e){
			  throw new DAOException(e);
		  }
		  
	  }


	public  List<Article> lister()throws DAOException {
		// TODO Auto-generated method stub
		  try{
			  TypedQuery<Article> query = em.createQuery(  "SELECT a FROM Article a ORDER BY a.id", Article.class);
			  return query.getResultList();
		  }catch (Exception  e){
			  throw new DAOException(e);
		  }
		  
		  
	}
	
	
	public  List<Article> listerByFournisseurAndTypeArticle(Fournisseur fournisseur, Type_Article type_Article, Shop  shop)throws DAOException {
		// TODO Auto-generated method stub
		  try{
			  
			  String queryString = "SELECT article From Article article WHERE article.fournisseur=:fournisseur AND article.type_Article=:type_Article AND article.shop=:shop";
			  Query requete = em.createQuery(queryString);
					  requete.setParameter("fournisseur", fournisseur);
					  requete.setParameter("type_Article", type_Article);
					  requete.setParameter("shop", shop);
			  List<Article> listArticles = requete.getResultList();
		
			  return listArticles;
		  }catch (Exception  e){
			  throw new DAOException(e);
		  }
		  
		  
	}
	public List<Article> getArticlesByEntree( Entree  entree  ) throws DAOException {
		try{
			String queryString = "SELECT article From Article article WHERE article.entree=:entree";
			Query requete =em.createQuery(queryString);
			   requete.setParameter("entree", entree);
			   
			List<Article> listArticle = requete.getResultList();
 			 return listArticle;
		} catch ( Exception e) {
			throw new DAOException(e);
		}			
		
	}
	
	
	
	public List<Article> getArticlesByOrder( Order  order  ) throws DAOException {
		try{
			String queryString = "SELECT article From Article article WHERE article.order=:order";
			Query requete =em.createQuery(queryString);
			   requete.setParameter("order", order);
			   
			List<Article> listArticle = requete.getResultList();
 			 return listArticle;
		} catch ( Exception e) {
			throw new DAOException(e);
		}			
		
	}
	
	public List<Article> getArticlesByShop( Shop  shop  ) throws DAOException {
		try{
			String queryString = "SELECT article From Article article WHERE article.shop=:shop";
			Query requete =em.createQuery(queryString);
			   requete.setParameter("shop", shop);
			   
			List<Article> listArticle = requete.getResultList();
 			 return listArticle;
		} catch ( Exception e) {
			throw new DAOException(e);
		}			
		
	}
	
	public List<Article> getArticlesByParameters( Entree entree, Stock stock, Shop shop, Order order, Buy buy  ) throws DAOException {
		try{
			String queryString = "SELECT article From Article article WHERE ";
			if(stock == null){
				queryString = queryString + (WHERESTOCKNULL);
			} else if(order == null){
				queryString = queryString + (WHEREORDERNULL);
			} else if (buy == null){
				queryString = queryString + (WHEREBUYNULL);
			} else if (entree == null){
				queryString = queryString + (WHEREENTREENULL);
			} else if (shop == null){
				queryString = queryString + (WHERESHOPNULL);
			}
			Query requete =em.createQuery(queryString);
			  
			 //  requete.setParameter("stock", stock);
			   
			  
				List<Article> listArticle = requete.getResultList();
 			 return listArticle;
		} catch ( Exception e) {
			throw new DAOException(e);
		}			
		
	}


	public  List<Type_Article> listerType_Article()throws DAOException {
		// TODO Auto-generated method stub
		  try{
			  TypedQuery<Type_Article> query = em.createQuery(  " SELECT t FROM Type_Article t ARTICLE BY t.id", Type_Article.class);
			  return query.getResultList();
		  }catch (Exception  e){
			  throw new DAOException(e);
		  }
		  
		  
	}
 	
	public void supprimer(Article article) throws DAOException {

        try {
         
             em.remove(em.merge(article));
           
        } catch ( Exception e ) {
            throw new DAOException( e );
        }

		// TODO Auto-generated method stub
		
	}

	public List<Article> getArticlesByDashboard(String type) {
		try{
			String queryString = "SELECT article From Article article WHERE ";
			if("stock".equals(type)){
				queryString = queryString + (WHERESTOCKNOTNULL);
			} else if ("shop".equals(type)){
				queryString = queryString + (WHERESHOPNOTNULL);
			}else if ("order".equals(type)){
				queryString = queryString +(WHEREORDERNOTNULL);
			}else if ("buy".equals(type)){
				queryString =queryString  +(WHEREBUYNOTNULL);
			}else if ("freeArticles".equals(type)){
				queryString =queryString  +(WHEREFREEARTICLENULL);
			}
			Query  requete  = em.createQuery(queryString);
			
			List<Article> listArticle = requete.getResultList();
			 return listArticle;
		} catch ( Exception e) {
			throw new DAOException(e);
		}
	}
	
	
	public Article modifier(Article article) throws DAOException {
		 try {
			
			 em.merge(article);
			 em.flush();
			 
          
       } catch ( Exception e ) {
           throw new DAOException( e );
       }
		return article;


	}
	
}
