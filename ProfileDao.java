/**
 * 
 */
package cm.itac.formation.ecommerce.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Profile;

/**
 * @author erico
 *
 */
@Stateless
public class ProfileDao  {
	
	//injection du manager qui s'occupe de la connexion avec la BD
	
   @PersistenceContext( unitName = "ecommerce_PU")
   
   private EntityManager  em;

// recherche d?un  nouveau  type_entrée

	public void supprimer(Profile profile) throws DAOException {
		// TODO Auto-generated method stub
	
        try {
         
             em.remove(em.merge(profile));
           
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
	}
	public Profile trouver( long id ) throws DAOException {
     
        try {
          return em.find( Profile.class, id);
        }catch (Exception e){
        	//TODO: handle exception
            throw new DAOException( e );
            // TODO: handle exception
        }

        }		
	public  List<Profile> lister()throws DAOException {
		// TODO Auto-generated method stub
		  try{
			  TypedQuery<Profile> query = em.createQuery(  "SELECT t FROM Profile t ORDER BY t.id", Profile.class);
			  return query.getResultList();
		  }catch (Exception  e){
			  throw new DAOException(e);
		  }
		  
		  
}
}
