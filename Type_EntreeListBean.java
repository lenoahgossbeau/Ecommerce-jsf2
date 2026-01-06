/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import cm.itac.formation.ecommerce.dao.Type_EntreeDao;
import cm.itac.formation.ecommerce.dao.entity.Type_Entree;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Type_EntreeListBean {
	 private static final long serialVersionUID = 1L;
	 private List<Type_Entree> type_Entrees;
	 private Type_Entree type_Entree;
	 @EJB
	 private Type_EntreeDao     type_EntreeDao;

	public Type_EntreeListBean() {
		type_Entrees = new ArrayList<Type_Entree>();
		// TODO Auto-generated constructor stub
	}
	public List<Type_Entree> getType_Entrees() {
		type_Entrees = type_EntreeDao.lister();
		if(type_Entrees == null)
			type_Entrees = new ArrayList<Type_Entree>();
		return type_Entrees;
	}

	public String supprimer(Type_Entree type_Entree){
		
		type_EntreeDao.supprimer(type_Entree);
		
		return "type_EntreeList";
	}
	/**
	 * @return the type_Entree
	 */
	public Type_Entree getType_Entree() {
		return type_Entree;
	}
	/**
	 * @param type_Entree the type_Entree to set
	 */
	public void setType_Entree(Type_Entree type_Entree) {
		this.type_Entree = type_Entree;
	}

}
