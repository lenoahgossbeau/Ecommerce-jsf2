/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import cm.itac.formation.ecommerce.dao.EntreeDao;
import cm.itac.formation.ecommerce.dao.Type_EntreeDao;
import cm.itac.formation.ecommerce.dao.entity.Buy;
import cm.itac.formation.ecommerce.dao.entity.Entree;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class EntreeListBean {

	 private static final long serialVersionUID = 1L;
	 private List<Entree> entrees;
	 private List< Entree> entreeSelected;
	 private Entree   entree;
	 @EJB
	 private EntreeDao     entreeDao;
	
	 @EJB
	 private Type_EntreeDao type_EntreeDao;
	/**
	 * 
	 */
	 
	public EntreeListBean() {
		entrees = new ArrayList<Entree>();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the entree
	 */
	
	public List<Entree> getEntrees() {
		entrees = entreeDao.lister();
		if(entrees == null)
			entrees = new ArrayList<Entree>();
		return entrees;
	}

	public String supprimer(Entree entrée){
		entreeDao.supprimer(entree);
		
		return "entreeList";
	}
	
	
	public String detail(Entree entree){
		this.entree = entreeDao.detail(entree);
		
		return "entreeOverview";
	}
	/**
	 * @param entrees the entrees to set
	 */

	public Entree getEntree() {
		return entree;
	}
	/**
	 * @param entrée the entree to set
	 */
	public void setEntree(Entree entree) {
		this.entree = entree;
	}
	/**
	 * @return the entreeSelected
	 */
	public List<Entree> getEntreeSelected() {
		return entreeSelected;
	}
	/**
	 * @param entreeSelected the entreeSelected to set
	 */
	public void setEntreeSelected(List<Entree> entreeSelected) {
		this.entreeSelected = entreeSelected;
	}
	/**
	 * @param entrees the entrees to set
	 */
	public void setEntrees(List<Entree> entrees) {
		this.entrees = entrees;
	}

}
