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

/**
 * @author erico
 *
 */
@Stateless
public class BuyDao {

	//injection du manager qui s'occupe de la connexion avec la BD
	   @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;


	    
	  
	// recherche d?un  nouveau  buy

		public Buy creer(Buy buy) throws DAOException {
			// TODO Auto-generated method stub
			
			
		        try {
		             em.persist(buy);
		         
		        } catch ( Exception e ) {
		        	// TODO handle exception
		        	  throw new DAOException(e);
		        }
		        return buy;
		}

		
		public void supprimer(Buy buy) throws DAOException {
			// TODO Auto-generated method stub
		
	        try {
	         
	             em.remove(em.merge(buy));
	           
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }
		}
		
		public Buy detail(Buy buy) throws DAOException {
			// TODO Auto-generated method stub
			return trouver(buy.getId());
			
		}
	
		
		public Buy trouver( long id ) throws DAOException {
	     
	        try {
	          return em.find( Buy.class, id);
	        }catch (Exception e){
	        	//TODO: handle exception
	            throw new DAOException( e );
	            // TODO: handle exception
	        }

	        }
		
		
		public List<Buy> lister() throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Buy> query = em.createQuery( "SELECT c FROM Buy c ORDER BY c.date2 DESC", Buy.class );
	    	return query.getResultList();
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}
		
		
		public List<Buy> findByDate(DateTime date) throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Buy> query = em.createQuery( "SELECT c FROM Buy c  WHERE c.date2 =:date2 ORDER BY c.id", Buy.class );
	    	query.setParameter("date2", date);
	    	
	    	/*select u from Ordemservico u where startDate >= :date1 AND endDate <= :date2
	    	TypedQuery<Ordemservico> query = em.createQuery(query, Ordemservico.class);
	    	query.setParameter("date1", date1);
	    	query.setParameter("date2", date2);
	    	*/
	    	
	    	return query.getResultList();
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}
		
		
		public List<Buy> findByDateRange(DateTime dateStart, DateTime dateEnd) throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Buy> query = em.createQuery( "SELECT c FROM Buy c  WHERE c.date2 >=:dateStart AND c.date2 <=:dateEnd ORDER BY c.id", Buy.class );
	    	query.setParameter("dateStart", dateStart);
	    	query.setParameter("dateEnd", dateEnd);
	    	/*select u from Ordemservico u where startDate >= :date1 AND endDate <= :date2
	    	TypedQuery<Ordemservico> query = em.createQuery(query, Ordemservico.class);
	    	query.setParameter("date1", date1);
	    	query.setParameter("date2", date2);
	    	*/
	    	
	    	return query.getResultList();
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}
		
		 public void changer( Buy buy ) throws DAOException {
		        try {
		            // em.merge( buy);
		            em.remove( em.merge( buy) );
		            em.flush();
		            em.persist( buy );
		        } catch ( Exception e ) {
		            throw new DAOException( e );
		        }
		 }
		 public Buy update(Buy buy) throws DAOException {
			 try {
		         
				 buy = em.merge(buy);
	       } catch ( Exception e ) {
	           throw new DAOException( e );
	       }
			return buy;


		}
}