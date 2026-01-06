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
import cm.itac.formation.ecommerce.dao.InventaireDao;
import cm.itac.formation.ecommerce.dao.ShopDao;
import cm.itac.formation.ecommerce.dao.SortirDao;
import cm.itac.formation.ecommerce.dao.StockDao;
import cm.itac.formation.ecommerce.dao.entity.Inventaire;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class InventaireListBean {
	 private static final long serialVersionUID = 1L;
	 private List<Inventaire> inventaires;
	 @EJB
	 private InventaireDao    inventaireDao;
	 private Inventaire inventaire;
	 @EJB
	 private StockDao    stockDao;
	 @EJB
	 private ShopDao     shopDao;
	 @EJB
	 private EntreeDao   entréeDao;
	 
	 @EJB
	 private SortirDao   sortirDao;
	/**
	 * 
	 */
	public InventaireListBean() {
		inventaires = new ArrayList<Inventaire>();
		// TODO Auto-generated constructor stub
	}

	public List<Inventaire> getInventaires() {
		
		inventaires = inventaireDao.lister();
		if(inventaires == null)
			inventaires = new ArrayList<Inventaire>();
		return inventaires;
	}

	
	/**
	 * @param inventaires the inventaires to set
	 */

	public Inventaire getInventaire() {
		return inventaire;
	}
	/**
	 * @param inventaire the inventaire to set
	 */
	public void setInventaire(Inventaire inventaire) {
		this.inventaire = inventaire;
	}

}
