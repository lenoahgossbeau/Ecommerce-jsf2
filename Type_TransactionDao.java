/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Type_Transaction;


/**
 * @author erico
 *
 */
@Stateless
public class Type_TransactionDao {
	
	//injection du manager qui s'occupe de la connexion avec la BD
	   @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;


	    
	  
	
		
		public void supprimer(Type_Transaction type_transaction) throws DAOException {
			// TODO Auto-generated method stub
		
	        try {
	         
	             em.remove(em.merge(type_transaction));
	           
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }


		}
		
		
		public Type_Transaction trouver( long id ) throws DAOException {
	     
	        try {
	          return em.find( Type_Transaction.class, id);
	        }catch (Exception e){
	        	//TODO: handle exception
	            throw new DAOException( e );
	            // TODO: handle exception
	        }

	        }
		
		
		public List<Type_Transaction> lister() throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Type_Transaction> query = em.createQuery( "SELECT t FROM Type_Transaction t ORDER BY t.id", Type_Transaction.class );
	    	return query.getResultList();                  
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}
		
		public List<Type_Transaction> getWithdrawall(Long id) throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Type_Transaction> query = em.createQuery( "SELECT c FROM Type_Transaction c  WHERE c.id>=:id ORDER BY c.id", Type_Transaction.class );
	    	query.setParameter("id", id);
	    	return query.getResultList();
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}


}
