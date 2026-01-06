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

import cm.itac.formation.ecommerce.dao.entity.Article;
import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Customer;
import cm.itac.formation.ecommerce.dao.entity.Entree;
import cm.itac.formation.ecommerce.dao.entity.Order;
import cm.itac.formation.ecommerce.dao.entity.Shop;
import cm.itac.formation.ecommerce.dao.entity.Stock;

/**
 * @author erico
 *
 */
@Stateless
public class OrderDao {

   // injection de notre manager qui s'occupe de la connexion avec notre BD
    @PersistenceContext( unitName = "ecommerce_PU")
      private EntityManager  em;

    //enregistrement d'un utilisateur appartir de son id
    
	public Order creer(Order order) throws DAOException {
	
    try{
    	em.persist(order);
    }catch ( Exception e) {
    	// TODO: handle exception
    	throw new DAOException(e);
    	
    }
    return order;
	}	
	public void supprimer(Order order) throws DAOException {
		// TODO Auto-generated method stub
	    try {
	           
            em.remove( em.merge(order));
          
       } catch ( Exception e ) {
           throw new DAOException( e );
       }      
	}
	public Order detail(Order order) throws DAOException {
		// TODO Auto-generated method stub
		return trouver(order.getId());
		
	}
	
	public Order trouver(Long id ) throws DAOException {
       
	// TODO Auto-generated method stub
    	
            try {
            	return em.find(Order.class, id);            
            } catch ( Exception e ) {
                throw new DAOException( e );
                // TODO: handle exception
            }
    }
     
	public List<Order> lister() throws DAOException {
		try{
			TypedQuery<Order> query = em.createQuery("SELECT c FROM Order c ORDER BY c.date DESC", Order.class);
			  return query.getResultList();
		} catch ( Exception e) {
			throw new DAOException(e);
		}				       
	}
	
	public List<Order> listerByCustomerAndBuy(Customer  customer, Buy  buy  ) throws DAOException {
		try{
			String queryString = "SELECT o From Order o WHERE o.customer=:customer  AND article.buy=:buy ";
			Query requete =em.createQuery(queryString);
			   requete.setParameter("customer", customer);
			   requete.setParameter("buy", buy);
			List<Order> listOrders = requete.getResultList();
 			 return listOrders;
		} catch ( Exception e) {
			throw new DAOException(e);
		}				       
	}
	
	public List<Order> getOrderByParameters( Entree entree, Stock stock, Shop shop, Order order, Buy buy  ) throws DAOException {
		try{
			String queryString = "SELECT o From Order o WHERE o.buy IS NULL ORDER BY o.date DESC";
			Query requete =em.createQuery(queryString);
			  
			 //  requete.setParameter("stock", stock);
			   
			  
				List<Order> listOrders = requete.getResultList();
 			 return listOrders;
		} catch ( Exception e) {
			throw new DAOException(e);
		}			
		
	}
	
	
	public List<Order> getOrdersByBuy( Buy  buy  ) throws DAOException {
		try{
			String queryString = "SELECT o From Order o WHERE o.buy=:buy   ";
			Query requete =em.createQuery(queryString);
			   requete.setParameter("buy", buy);
			   
			List<Order> listOrder = requete.getResultList();
 			 return listOrder;
		} catch ( Exception e) {
			throw new DAOException(e);
		}			
		
	}
	
	}

