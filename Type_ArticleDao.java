/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Type_Article;

/**
 * @author erico
 *
 */
@Stateless
public class Type_ArticleDao {

	//injection du manager qui s'occupe de la connexion avec la BD
	   @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;


	    
	  
	
		
		public void supprimer(Type_Article type_article) throws DAOException {
			// TODO Auto-generated method stub
		
	        try {
	         
	             em.remove(em.merge(type_article));
	           
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }


		}
		
		
		public Type_Article trouver( long id ) throws DAOException {
	     
	        try {
	          return em.find( Type_Article.class, id);
	        }catch (Exception e){
	        	//TODO: handle exception
	            throw new DAOException( e );
	            // TODO: handle exception
	        }

	        }
		
		
		public List<Type_Article> lister() throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Type_Article> query = em.createQuery( "SELECT t FROM Type_Article t ORDER BY t.id", Type_Article.class );
	    	return query.getResultList();                  
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}

}
