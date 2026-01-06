/**
 * 
 */
package cm.itac.formation.ecommerce.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Customer;

/**
 * @author erico
 *
 */
@Stateless
public class CustomerDao{
	//injection du manager qui s'occupe de la connexion avec la BD
   @PersistenceContext( unitName = "ecommerce_PU")
   private EntityManager  em;


    
  
// recherche d?un  nouveau  customer

	public Customer creer(Customer customer) throws DAOException {
		// TODO Auto-generated method stub
		
		
	        try {
	        	Thread.sleep(10);
	             em.persist(customer);
	            
	            
	         
	        } catch ( Exception e ) {
	        	// TODO handle exception
	        	  throw new DAOException(e);
	        }
	          
	       
	        return customer;
	}

	
	public void supprimer(Customer customer) throws DAOException {
		// TODO Auto-generated method stub
	
        try {
         
             em.remove(em.merge(customer));
           
        } catch ( Exception e ) {
            throw new DAOException( e );
        }


	}
	
	
	public Customer trouver( long id ) throws DAOException {
     
        try {
          return em.find( Customer.class, id);
        }catch (Exception e){
        	//TODO: handle exception
            throw new DAOException( e );
            // TODO: handle exception
        }

        }
	
	
	
	public Customer modifier(Customer customer) throws DAOException {
       
            try {
            	customer = em.merge(customer);
            	 
            } catch (Exception e) {
            	
                throw new DAOException(e);
            }return customer;
        }
        

	public Customer detail(Customer customer) throws DAOException {
		// TODO Auto-generated method stub
		return trouver(customer.getId());
		
	}
	
	public List<Customer> lister() throws DAOException {
		// TODO Auto-generated method stub
	
    try{
    	TypedQuery<Customer> query = em.createQuery( "SELECT c FROM Customer c ORDER BY c.id", Customer.class );
    	return query.getResultList();
    } catch ( Exception e ) {
    	throw new DAOException(e);
    }
	}
}
