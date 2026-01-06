/**
 * 
 */
package cm.itac.formation.ecommerce.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Configuration;
import cm.itac.formation.ecommerce.dao.entity.User;


/**
 * @author erico
 *
 */
@Stateless
public class ConfigurationDao{
	//injection du manager qui s'occupe de la connexion avec la BD
   @PersistenceContext( unitName = "ecommerce_PU")
   private EntityManager  em;
   private static final String JPQL_SELECT_PAR_KEYSTR = "SELECT u  FROM Configuration u WHERE u.keyStr =:keyStr";
   
   private static final String PARAM_KEYSTR     = "keyStr";

    
	public Configuration creer(Configuration configuration) throws DAOException {
		// TODO Auto-generated method stub
		
		
	        try {
	        	
	             em.persist(configuration);
	            
	            
	         
	        } catch ( Exception e ) {
	        	// TODO handle exception
	        	  throw new DAOException(e);
	        }
	          
	       
	        return configuration;
	}

	
	
	public Configuration trouver( long id ) throws DAOException {
     
        try {
          return em.find( Configuration.class, id);
        }catch (Exception e){
        	//TODO: handle exception
            throw new DAOException( e );
            // TODO: handle exception
        }

        }
	
	
	public Configuration trouver( String keyStr ) throws DAOException {
	     
		Configuration config = null;
    	Query requete = em.createQuery( JPQL_SELECT_PAR_KEYSTR );
    	requete.setParameter( PARAM_KEYSTR, keyStr);
    	try {
    		config = (Configuration) requete.getSingleResult();
    	}catch ( NoResultException e) {
    		return null;
    	}catch ( Exception e ) {
    		throw new DAOException( e );
    	}
    	return config;

        }
	
	
	
	public Configuration modifier(Configuration configuration) throws DAOException {
       
            try {
            	configuration = em.merge(configuration);
            	 
            } catch (Exception e) {
            	
                throw new DAOException(e);
            }return configuration;
        }
        

	public List<Configuration> lister() throws DAOException {
		// TODO Auto-generated method stub
	
    try{
    	TypedQuery<Configuration> query = em.createQuery( "SELECT c FROM Configuration c ORDER BY c.id", Configuration.class );
    	return query.getResultList();
    } catch ( Exception e ) {
    	throw new DAOException(e);
    }
	}
}
