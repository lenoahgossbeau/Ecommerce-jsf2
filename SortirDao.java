/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Sortir;

/**
 * @author erico
 *
 */
@Stateless
public class SortirDao {

	//injection du manager qui s'occupe de la connexion avec la BD
	   @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;


	    
	  
	// recherche d?une nouvelle Sortie

		public Sortir creer(Sortir sortir) throws DAOException {
			// TODO Auto-generated method stub
			
			
		        try {
		             em.persist(sortir);
		            
		            
		         
		        } catch ( Exception e ) {
		        	// TODO handle exception
		        	  throw new DAOException(e);
		        }
		          
		       
		        return sortir;
		}

		
		public void supprimer(Sortir sortir) throws DAOException {
			// TODO Auto-generated method stub
		
	        try {
	         
	             em.remove(em.merge(sortir));
	           
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }


		}
		
		
		public Sortir trouver( long id ) throws DAOException {
	     
	        try {
	          return em.find( Sortir.class, id);
	        }catch (Exception e){
	        	//TODO: handle exception
	            throw new DAOException( e );
	            // TODO: handle exception
	        }

	        }
		
		
		public List<Sortir> lister() throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Sortir> query = em.createQuery( "SELECT c FROM Sortir c ORDER BY c.id", Sortir.class );
	    	return query.getResultList();
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}

}
