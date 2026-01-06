/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.dao.entity.Account_Transaction;
import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Daily_Fund;
import cm.itac.formation.ecommerce.dao.entity.Type_Transaction;


/**
 * @author erico
 *
 */
@Stateless
public class Account_TransactionDao {
	private static final String WHEREDEPOTCOMPTENOTNULL = "daily_Fund.daily_Fund IS NOT NULL ";

	//injection du manager qui s'occupe de la connexion avec la BD
	   @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;


	   public Account_Transaction creer(Account_Transaction account_Transaction) throws DAOException {
			// TODO Auto-generated method stub
			
			
		        try {
		             em.persist(account_Transaction);
		            
		            
		         
		        } catch ( Exception e ) {
		        	// TODO handle exception
		        	  throw new DAOException(e);
		        }
		          
		       
		        return account_Transaction;
		}
      
	  
	
		
		public void supprimer(Account_Transaction account_Transaction) throws DAOException {
			// TODO Auto-generated method stub
		
	        try {
	         
	             em.remove(em.merge(account_Transaction));
	           
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }


		}
		
		
		public Account_Transaction trouver( long id ) throws DAOException {
	     
	        try {
	          return em.find( Account_Transaction.class, id);
	        }catch (Exception e){
	        	//TODO: handle exception
	            throw new DAOException( e );
	            // TODO: handle exception
	        }

	        }
		public Account_Transaction detail(Account_Transaction account_Transaction) throws DAOException {
			// TODO Auto-generated method stub
			return trouver(account_Transaction.getId());
			
		}
		
		public List<Account_Transaction> lister() throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Account_Transaction> query = em.createQuery( "SELECT t FROM Account_Transaction t ORDER BY t.date DESC", Account_Transaction.class );
	    	return query.getResultList();                  
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}

		public Account_Transaction modifier(Account_Transaction account_Transaction) throws DAOException {
			 try {
		         
				 account_Transaction = em.merge(account_Transaction);
	            
	          
	       } catch ( Exception e ) {
	           throw new DAOException( e );
	       }
			return account_Transaction;


		}
		
		public List<Account_Transaction> getAccountTransactionByDateAndByTransactioType(DateTime date, Type_Transaction str_type) {
			try{
				
				TypedQuery<Account_Transaction> query = em.createQuery( "SELECT a FROM Account_Transaction a WHERE a.date>=:date  ORDER BY a.id", Account_Transaction.class );
				query.setParameter("date", date);
				//query.setParameter("type_Transaction", str_type.getStr_type());
				List<Account_Transaction> accounts = query.getResultList();
				List<Account_Transaction> acountsFiltered = new ArrayList<Account_Transaction>();
				for(Account_Transaction account_Transaction : accounts){
					if(str_type.getStr_type().equals(account_Transaction.getType_Transaction().getStr_type()))
					acountsFiltered.add(account_Transaction);
				}
		    	return acountsFiltered;
		    } catch ( Exception e ) {
		    	throw new DAOException(e);
		    }
			}
		
		
		public List<Account_Transaction> getAccountTransactionByDateRangeAndByTransactioType(DateTime dateStart, DateTime dateEnd, Type_Transaction str_type) {
			try{
				
				TypedQuery<Account_Transaction> query = em.createQuery( "SELECT a FROM Account_Transaction a WHERE a.date>=:dateStart AND a.date<=:dateEnd  ORDER BY a.id", Account_Transaction.class );
				query.setParameter("dateStart", dateStart);
				query.setParameter("dateEnd", dateEnd);
				//query.setParameter("type_Transaction", str_type.getStr_type());
				List<Account_Transaction> accounts = query.getResultList();
				List<Account_Transaction> acountsFiltered = new ArrayList<Account_Transaction>();
				for(Account_Transaction account_Transaction : accounts){
					if(str_type.getStr_type().equals(account_Transaction.getType_Transaction().getStr_type()))
					acountsFiltered.add(account_Transaction);
				}
		    	return acountsFiltered;
		    } catch ( Exception e ) {
		    	throw new DAOException(e);
		    }
			}
		
		
		
		
}
