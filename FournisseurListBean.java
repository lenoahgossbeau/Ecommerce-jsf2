/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import cm.itac.formation.ecommerce.dao.FournisseurDao;
import cm.itac.formation.ecommerce.dao.entity.Fournisseur;

/**
 * @author admin
 *
 */
@RequestScoped
@ManagedBean
public class FournisseurListBean {
	 private static final long serialVersionUID = 1L;
	 private List<Fournisseur> fournisseurs;
	 private Fournisseur fournisseur;
	 @EJB
	 private FournisseurDao     fournisseurDao;

	/**
	 * 
	 */
	public FournisseurListBean() {
		fournisseurs = new ArrayList<Fournisseur>();
		// TODO Auto-generated constructor stub
	}
	public List<Fournisseur> getFournisseurs() {
		fournisseurs = fournisseurDao.lister();
		if(fournisseurs == null)
			fournisseurs = new ArrayList<Fournisseur>();
		return fournisseurs;
	}

	
	public String detail(Fournisseur fournisseur){
		this.fournisseur = fournisseurDao.detail(fournisseur);
		
		return "fournisseurOverview";
	}
	public String modifier(Fournisseur  fournisseur) {
		
        this.fournisseur = fournisseurDao.modifier(fournisseur);

            FacesMessage message = new FacesMessage(
                       "Succés de la modification du fournisseur !");
            FacesContext.getCurrentInstance().addMessage(null ,message);

        return "fournisseurEditView";
}
	public String supprimer(Fournisseur fournisseur){
		
		fournisseurDao.supprimer(fournisseur);
		
		return "fournisseurList";
	}
	/**
	 * @return the fournisseur
	 */
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	/**
	 * @param fournisseur the fournisseur to set
	 */
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

}
