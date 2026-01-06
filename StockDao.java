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

import cm.itac.formation.ecommerce.dao.entity.Entree;
import cm.itac.formation.ecommerce.dao.entity.Sortir;
import cm.itac.formation.ecommerce.dao.entity.Stock;

/**
 * @author erico
 *
 */
@Stateless
public class StockDao {
	//injection du manager qui s'occupe de la connexion avec la BD
	   @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;


	   
	// recherche d?un  nouveau  stock

		public Stock creer(Stock stock) throws DAOException {
			// TODO Auto-generated method stub
			
			
		        try {
		             em.persist(stock);
		            
		            
		         
		        } catch ( Exception e ) {
		        	// TODO handle exception
		        	  throw new DAOException(e);
		        }
		          
		       
		        return stock;
		}

		
		public void supprimer(Stock stock) throws DAOException {
			// TODO Auto-generated method stub
		
	        try {
	         
	             em.remove(em.merge(stock));
	           
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }


		}
		
		
		public Stock trouver( long id ) throws DAOException {
	     
	        try {
	          return em.find( Stock.class, id);
	        }catch (Exception e){
	        	//TODO: handle exception
	            throw new DAOException( e );
	            // TODO: handle exception
	        }

	        }
		
		
		public List<Stock> lister() throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Stock> query = em.createQuery( "SELECT c FROM Stock c ORDER BY c.id", Stock.class );
	    	return query.getResultList();
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}
		
		public  List<Stock> listerByEntreeAndSortir(Entree  entree,  Sortir  sortir)throws DAOException {
			// TODO Auto-generated method stub
			  try{
				  
				  String queryString = "SELECT stock From Stock stock WHERE stock.entrée=:entrée AND stock.sortir=:sortir";
				  Query requete = em.createQuery(queryString);
						  requete.setParameter("entree", entree);
						  requete.setParameter("sortir", sortir);
						 
				  List<Stock> listStocks = requete.getResultList();
			
				  return listStocks;
			  }catch (Exception  e){
				  throw new DAOException(e);
			  }
			  
			  
		}
}
