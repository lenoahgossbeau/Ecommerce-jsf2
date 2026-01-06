/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Deposit_Buy;

/**
 * @author erico
 *
 */
@Stateless
public class Deposit_BuyDao {

	//injection du manager qui s'occupe de la connexion avec la BD
	   @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;
	   

		public Deposit_Buy creer(Deposit_Buy deposit_Buy) throws DAOException {
			// TODO Auto-generated method stub
			
			
		        try {
		        	Thread.sleep(10);
		             em.persist(deposit_Buy);
		        
		        } catch ( Exception e ) {
		        	// TODO handle exception
		        	  throw new DAOException(e);
		        }
		          
		       
		        return deposit_Buy;
		}
		
		public void supprimer(Deposit_Buy deposit_Buy) throws DAOException {
			// TODO Auto-generated method stub
		
	        try {
	         
	             em.remove(em.merge(deposit_Buy));
	           
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }


		}
		
		public Deposit_Buy detail(Deposit_Buy deposit_Buy) throws DAOException {
			// TODO Auto-generated method stub
			return trouver(deposit_Buy.getId());
			
		}
		
		public Deposit_Buy trouver( long id ) throws DAOException {
		     
	        try {
	          return em.find( Deposit_Buy.class, id);
	        }catch (Exception e){
	        	//TODO: handle exception
	            throw new DAOException( e );
	            // TODO: handle exception
	        }

	        }
		
		public List<Deposit_Buy> lister() throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Deposit_Buy> query = em.createQuery( "SELECT d FROM Deposit_Buy d ORDER BY d.date DESC", Deposit_Buy.class );
	    	return query.getResultList();
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}
	/**
	 * 
	 */
	public Deposit_BuyDao() {
		// TODO Auto-generated constructor stub
	}

}
