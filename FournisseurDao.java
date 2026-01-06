/**
 * 
 */
package cm.itac.formation.ecommerce.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Fournisseur;

/**
 * @author erico
 *
 */
@Stateless
public class FournisseurDao{
	//injection du manager qui s'occupe de la connexion avec la BD
   @PersistenceContext( unitName = "ecommerce_PU")
   private EntityManager  em;


    
  
// recherche d?un  nouveau  fournisseur

	public Fournisseur creer(Fournisseur fournisseur) throws DAOException {
		// TODO Auto-generated method stub
		
		
	        try {
	             em.persist(fournisseur);
	            
	            
	         
	        } catch ( Exception e ) {
	        	// TODO handle exception
	        	  throw new DAOException(e);
	        }
	          
	       
	        return fournisseur;
	}

	
	public void supprimer(Fournisseur fournisseur) throws DAOException {
		// TODO Auto-generated method stub
	
        try {
         
             em.remove(em.merge(fournisseur));
           
        } catch ( Exception e ) {
            throw new DAOException( e );
        }


	}
	
	
	public Fournisseur trouver( long id ) throws DAOException {
     
        try {
          return em.find( Fournisseur.class, id);
        }catch (Exception e){
        	//TODO: handle exception
            throw new DAOException( e );
            // TODO: handle exception
        }

        }
	
	
	public  Fournisseur modifier(Fournisseur fournisseur) throws DAOException{
		try{
			fournisseur= em.merge(fournisseur);
		}catch (Exception e){
        	
            throw new DAOException( e );
          
            
        }return fournisseur;
	}

	
	public Fournisseur detail(Fournisseur fournisseur) throws DAOException {
		// TODO Auto-generated method stub
		return trouver(fournisseur.getId());
		
	}
	public List<Fournisseur> lister() throws DAOException {
		// TODO Auto-generated method stub
	
    try{
    	TypedQuery<Fournisseur> query = em.createQuery( "SELECT f FROM Fournisseur f ORDER BY f.id", Fournisseur.class );
    	return query.getResultList();
    } catch ( Exception e ) {
    	throw new DAOException(e);
    }
	}
}
