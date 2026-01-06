/**
 * 
 */
package cm.itac.formation.ecommerce.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Article;
import cm.itac.formation.ecommerce.dao.entity.Type_Entree;

/**
 * @author erico
 *
 */
@Stateless
public class Type_EntreeDao  {
	
	//injection du manager qui s'occupe de la connexion avec la BD
	
   @PersistenceContext( unitName = "ecommerce_PU")
   
   private EntityManager  em;

// recherche d?un  nouveau  type_entrée

	public void supprimer(Type_Entree type_Entree) throws DAOException {
		// TODO Auto-generated method stub
	
        try {
         
             em.remove(em.merge(type_Entree));
           
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
	}
	public Type_Entree trouver( long id ) throws DAOException {
     
        try {
          return em.find( Type_Entree.class, id);
        }catch (Exception e){
        	//TODO: handle exception
            throw new DAOException( e );
            // TODO: handle exception
        }

        }		
	public  List<Type_Entree> lister()throws DAOException {
		// TODO Auto-generated method stub
		  try{
			  TypedQuery<Type_Entree> query = em.createQuery(  "SELECT t FROM Type_Entree t ORDER BY t.id", Type_Entree.class);
			  return query.getResultList();
		  }catch (Exception  e){
			  throw new DAOException(e);
		  }
		  
		  
}
}
