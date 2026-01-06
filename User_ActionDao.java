/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.User_Action;

/**
 * @author admin
 *
 */
@Stateless
public class User_ActionDao {
	
	
	//injection du manager qui s'occupe de la connexion avec la BD
	
	   @PersistenceContext( unitName = "ecommerce_PU")
	   
	   private EntityManager  em;

	/**
	 * 
	 */
	public User_ActionDao() {
		// TODO Auto-generated constructor stub
	}
	public void supprimer(User_Action user_Action) throws DAOException {
		// TODO Auto-generated method stub
	
        try {
         
             em.remove(em.merge(user_Action));
           
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
	}
	
	public User_Action trouver( long id ) throws DAOException {
	     
        try {
          return em.find( User_Action.class, id);
        }catch (Exception e){
        	//TODO: handle exception
            throw new DAOException( e );
            // TODO: handle exception
        }

        }
	
	public  List<User_Action> lister()throws DAOException {
		// TODO Auto-generated method stub
		  try{
			  TypedQuery<User_Action> query = em.createQuery(  "SELECT t FROM User_Action t ORDER BY t.id", User_Action.class);
			  return query.getResultList();
		  }catch (Exception  e){
			  throw new DAOException(e);
		  }
		  
		  
}
}
