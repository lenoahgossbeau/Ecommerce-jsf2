/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Type_Buy;

/**
 * @author erico
 *
 */
@Stateless
public class Type_BuyDao {
	//injection du manager qui s'occupe de la connexion avec la BD
	
	   @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;


	    
	  
	
		
		public void supprimer(Type_Buy type_Buy) throws DAOException {
			// TODO Auto-generated method stub
		
	        try {
	         
	             em.remove(em.merge(type_Buy));
	           
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }


		}
		
		
		public Type_Buy trouver( long id ) throws DAOException {
	     
	        try {
	          return em.find( Type_Buy.class, id);
	        }catch (Exception e){
	        	//TODO: handle exception
	            throw new DAOException( e );
	            // TODO: handle exception
	        }

	        }
		
		
		public List<Type_Buy> lister() throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Type_Buy> query = em.createQuery( "SELECT t FROM Type_Buy t ORDER BY t.id", Type_Buy.class );
	    	return query.getResultList();                  
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}

}


