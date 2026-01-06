/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cm.itac.formation.ecommerce.dao.entity.Inventaire;

/**
 * @author erico
 *
 */
@Stateless
public class InventaireDao {
	//injection du manager qui s'occupe de la bd
	  @PersistenceContext( unitName = "ecommerce_PU")
	   private EntityManager  em;

	  
	  public  List<Inventaire> lister()throws DAOException {
			// TODO Auto-generated method stub
			  try{
				  TypedQuery<Inventaire> query = em.createQuery(  " SELECT i FROM Inventaire i ORDER BY i.id", Inventaire.class);
				  return query.getResultList();
			  }catch (Exception  e){
				  throw new DAOException(e);
			  }
	  }
			 
	/**
	 * 
	 */
	public InventaireDao() {
		// TODO Auto-generated constructor stub
	}

}
