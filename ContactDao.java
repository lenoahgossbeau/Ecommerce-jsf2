/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Contact;

/**
 * @author erico
 *
 */
@Stateless
public class ContactDao {
	//injection du manager qui s'occupe de la connexion avec la BD
	   @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;


	    
	  
	// recherche d?un  nouveau  client

		public Contact creer(Contact contact) throws DAOException {
			// TODO Auto-generated method stub
			
			
		        try {
		             em.persist(contact);
		            
		            
		         
		        } catch ( Exception e ) {
		        	// TODO handle exception
		        	  throw new DAOException(e);
		        }
		          
		       
		        return contact;
		}

		
		public void supprimer(Contact contact) throws DAOException {
			// TODO Auto-generated method stub
		
	        try {
	         
	             em.remove(em.merge(contact));
	           
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }


		}
		
		
		public Contact trouver( long id ) throws DAOException {
	     
	        try {
	          return em.find( Contact.class, id);
	        }catch (Exception e){
	        	//TODO: handle exception
	            throw new DAOException( e );
	            // TODO: handle exception
	        }

	        }
		
		
		public List<Contact> lister() throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Contact> query = em.createQuery( "SELECT c FROM Contact c ORDER BY c.id", Contact.class );
	    	return query.getResultList();
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}
}
