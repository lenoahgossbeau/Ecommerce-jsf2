/**
 * 
 */
package cm.itac.formation.ecommerce.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.dao.entity.Article;
import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Daily_Fund;
import cm.itac.formation.ecommerce.dao.entity.Fournisseur;

/**
 * @author erico
 *
 */
@Stateless
public class Daily_FundDao{
	
	private static final String WHEREFONDCOMMERCENOTNULL = "daily_Fund.daily_Fund IS NOT NULL ";
	//injection du manager qui s'occupe de la connexion avec la BD
   @PersistenceContext( unitName = "ecommerce_PU")
   private EntityManager  em;


    
  
// recherche d?un  nouveau  fournisseur

	public Daily_Fund creer(Daily_Fund daily_Fund) throws DAOException {
		// TODO Auto-generated method stub
		
		
	        try {
	             em.persist(daily_Fund);
	            
	            
	         
	        } catch ( Exception e ) {
	        	// TODO handle exception
	        	  throw new DAOException(e);
	        }
	          
	       
	        return daily_Fund;
	}

	
	public void supprimer(Daily_Fund daily_Fund) throws DAOException {
		// TODO Auto-generated method stub
	
        try {
         
             em.remove(em.merge(daily_Fund));
           
        } catch ( Exception e ) {
            throw new DAOException( e );
        }


	}
	
	
	public Daily_Fund trouver( long id ) throws DAOException {
     
        try {
          return em.find( Daily_Fund.class, id);
        }catch (Exception e){
        	//TODO: handle exception
            throw new DAOException( e );
            // TODO: handle exception
        }

        }
	
	
	public  Daily_Fund modifier(Daily_Fund daily_Fund) throws DAOException{
		try{
			daily_Fund= em.merge(daily_Fund);
		}catch (Exception e){
        	
            throw new DAOException( e );
          
            
        }
		return daily_Fund;
	}

	
	public Daily_Fund detail(Daily_Fund daily_Fund) throws DAOException {
		// TODO Auto-generated method stub
		return trouver(daily_Fund.getId());
		
	}
	public List<Daily_Fund> lister() throws DAOException {
		// TODO Auto-generated method stub
	
    try{
    	TypedQuery<Daily_Fund> query = em.createQuery( "SELECT d FROM Daily_Fund d ORDER BY d.id", Daily_Fund.class );
    	return query.getResultList();
    } catch ( Exception e ) {
    	throw new DAOException(e);
    }
	}
	
	public List<Daily_Fund> findDailyByDate(DateTime date) throws DAOException {
		// TODO Auto-generated method stub
	
    try{
    	TypedQuery<Daily_Fund> query = em.createQuery( "SELECT c FROM Daily_Fund c  WHERE c.date>=:date ORDER BY c.id", Daily_Fund.class );
    	query.setParameter("date", date);
    	return query.getResultList();
    } catch ( Exception e ) {
    	throw new DAOException(e);
    }
	}
	
	public List<Daily_Fund> findDailyByDateRange(DateTime dateStart, DateTime dateEnd) throws DAOException {
		// TODO Auto-generated method stub
	
    try{
    	TypedQuery<Daily_Fund> query = em.createQuery( "SELECT c FROM Daily_Fund c  WHERE c.date >=:dateStart AND c.date <=:dateEnd ORDER BY c.id", Daily_Fund.class );
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
	
	
	
	
	
}
