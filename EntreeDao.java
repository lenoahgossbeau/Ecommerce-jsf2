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
import cm.itac.formation.ecommerce.dao.entity.Type_Entree;

/**
 * @author erico
 *
 */
@Stateless
public class EntreeDao {
	//injection du manager qui s'occupe de la connexion avec la BD
	   @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;


	    
	  
	// recherche d?un  nouveau  contact

		public Entree creer(Entree entree) throws DAOException {
			// TODO Auto-generated method stub
			
			
		        try {
		             em.persist(entree);
		            
		            
		         
		        } catch ( Exception e ) {
		        	// TODO handle exception
		        	  throw new DAOException(e);
		        }
		          
		       
		        return entree;
		}

		
		public void supprimer(Entree entree) throws DAOException {
			// TODO Auto-generated method stub
		
	        try {
	         
	             em.remove(em.merge(entree));
	           
	        } catch ( Exception e ) {
	            throw new DAOException( e );
	        }


		}
		public Entree detail(Entree entree) throws DAOException {
			// TODO Auto-generated method stub
			return trouver(entree.getId());
			
		}
		
		public Entree trouver( long id ) throws DAOException {
	     
	        try {
	          return em.find( Entree.class, id);
	        }catch (Exception e){
	        	//TODO: handle exception
	            throw new DAOException( e );
	            // TODO: handle exception
	        }

	        }
		
		
		public List<Entree> lister() throws DAOException {
			// TODO Auto-generated method stub
		
	    try{
	    	TypedQuery<Entree> query = em.createQuery( "SELECT e FROM Entree e ORDER BY e.id", Entree.class );
	    	return query.getResultList();
	    } catch ( Exception e ) {
	    	throw new DAOException(e);
	    }
		}
	
		public  List<Entree> listerByType_Entree( Type_Entree type_Entree)throws DAOException {
			// TODO Auto-generated method stub
			  try{
				  
				  String queryString = "SELECT article From Entree article WHERE entree.type_Entree =: type_Entree";
				  Query requete = em.createQuery(queryString);
						  requete.setParameter("type_Entree", type_Entree);
						 
						
				  List<Entree> listEntrees = requete.getResultList();
			
				  return listEntrees;
			  }catch (Exception  e){
				  throw new DAOException(e);
			  }
			  
		  
		
}
}