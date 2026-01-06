/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.User;



/**
 * @author ricky
 *
 */
@Stateless
public class UtilisateurDao  {
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u  FROM User u WHERE u.email =:email";
	private static final String PARAM_EMAIL     = "email";
	
	
	
	//injection du manager qui s'occupe de la connexion avec la BDD
	@PersistenceContext( unitName = "ecommerce_PU")
	private EntityManager      em;
	
	
	//enregistrement d'un nouvel utilisateur
	public void creer( User user ) throws DAOException {
		try{
			em.persist(user);
		}catch (Exception e) {
			throw new DAOException( e );
		}
	}


    
    //recherche d'un utilisateur a partir de son email
    public User trouver( String email ) throws DAOException {
    	User user = null;
    	Query requete = em.createQuery( JPQL_SELECT_PAR_EMAIL );
    	requete.setParameter( PARAM_EMAIL, email);
    	try {
    		user = (User) requete.getSingleResult();
    	}catch ( NoResultException e) {
    		return null;
    	}catch ( Exception e ) {
    		throw new DAOException( e );
    	}
    	return user;
    }
    
    public User detail(User user) throws DAOException {
		// TODO Auto-generated method stub
		return trouver(user.getEmail());
		
	}

    
    public List<User> lister() throws DAOException {
		// TODO Auto-generated method stub
	
    try{
    	TypedQuery<User> query = em.createQuery( "SELECT u FROM User u ORDER BY u.id", User.class );
    	return query.getResultList();
    } catch ( Exception e ) {
    	throw new DAOException(e);
    }
	
		  
		  
	}
		  
    public void supprimer(User user) throws DAOException {
		// TODO Auto-generated method stub
	
        try {
         
             em.remove(em.merge(user));
           
        } catch ( Exception e ) {
            throw new DAOException( e );
        }


	}



	public User modifier(User user) throws DAOException {
		 try {
	         
			 user = em.merge(user);
             
           
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
		return user;


	}
}