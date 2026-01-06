/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import cm.itac.formation.ecommerce.dao.SortirDao;
import cm.itac.formation.ecommerce.dao.entity.Sortir;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class SortirListBean {
	 private static final long serialVersionUID = 1L;
	 private List<Sortir> sortirs;
	 private Sortir sortir;
	 @EJB
	 private SortirDao     sortirDao;
	/**
	 * 
	 */
	 
	public SortirListBean() {
		sortirs = new ArrayList<Sortir>();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the sortir
	 */
	
	public List<Sortir> getSortirs() {
		sortirs = sortirDao.lister();
		if(sortirs == null)
			sortirs = new ArrayList<Sortir>();
		return sortirs;
	}

	public String supprimer(Sortir sortir){
		sortirDao.supprimer(sortir);
		
		return "sortirList";
	}
	/**
	 * @param sortirs the sortirs to set
	 */

	public Sortir getSortir() {
		return sortir;
	}
	/**
	 * @param sortir the sortir to set
	 */
	public void setSortir(Sortir sortir) {
		this.sortir = sortir;
	}
}
