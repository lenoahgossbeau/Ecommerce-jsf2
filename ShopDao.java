/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Shop;

/**
 * @author erico
 *
 */
@Stateless
public class ShopDao {
	//injection du manager qui s'occupe de la connexion avec la BD
	   @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;


	    
	  
	// recherche d?un  nouveau  shop

		public Shop creer(Shop shop) throws DAOException {
			// TODO Auto-generated method stub
			
			
		        try {
		             em.persist(shop);
		            
		            
		         
		        } catch ( Exception e ) {
		        	// TODO handle exception
		        	  throw new DAOException(e);
		        }
		          
		       
		        return shop;
		}

		
		public void supprimer(Shop shop) throws DAOException {
			// TODO Auto-generated method stub
		
	        try {
	         
	             em.remove(em.merge(shop));
	           
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }


		}
		
		
		public Shop trouver( long id ) throws DAOException {
	     
	        try {
	          return em.find( Shop.class, id);
	        }catch (Exception e){
	        	//TODO: handle exception
	            throw new DAOException( e );
	            // TODO: handle exception
	        }

	        }
		
		
		public List<Shop> lister() throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Shop> query = em.createQuery( "SELECT c FROM Shop c ORDER BY c.id", Shop.class );
	    	return query.getResultList();
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}

}
