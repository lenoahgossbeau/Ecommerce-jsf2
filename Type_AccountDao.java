/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Type_Account;

/**
 * @author erico
 *
 */
@Stateless
public class Type_AccountDao {

	
		//injection du manager qui s'occupe de la connexion avec la BD
	
		   @PersistenceContext( unitName = "ecommerce_PU")
		   private EntityManager  em;


		    
		  
		
			
			public void supprimer(Type_Account type_account) throws DAOException {
				// TODO Auto-generated method stub
			
		        try {
		         
		             em.remove(em.merge(type_account));
		           
		        } catch ( Exception e ) {
		            throw new DAOException( e );
		        }


			}
			
			
			public Type_Account trouver( long id ) throws DAOException {
		     
		        try {
		          return em.find( Type_Account.class, id);
		        }catch (Exception e){
		        	//TODO: handle exception
		            throw new DAOException( e );
		            // TODO: handle exception
		        }

		        }
			
			
			public List<Type_Account> lister() throws DAOException {
				// TODO Auto-generated method stub
			
		    try{
		    	TypedQuery<Type_Account> query = em.createQuery( "SELECT t FROM Type_Account t ORDER BY t.id", Type_Account.class );
		    	return query.getResultList();                  
		    } catch ( Exception e ) {
		    	throw new DAOException(e);
		    }
			}

	}



