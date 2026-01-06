/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import cm.itac.formation.ecommerce.dao.FournisseurDao;
import cm.itac.formation.ecommerce.dao.entity.Configuration;
import cm.itac.formation.ecommerce.dao.entity.Fournisseur;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class FournisseurBean {
	private Fournisseur    fournisseur;
	private String idFournisseur;
	private String nom1;
	private String prenom;
	private String email;
	private String siege_social;
	private String telephone;
	private String code1;
	
	 // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private FournisseurDao     fournisseurDao;
    Map<String, Fournisseur> fournisseurs;
    private List<Fournisseur>  filteredFournisseurList;
    
	    public static final String SESSION_FOURNISSEURS   = "fournisseurs";
	    public static final String REQUEST_FOURNISSEUR = "fournisseurBean";
	    public static final String REQUEST_FOURNISSEUR_LIST = "fournisseurListBean";
	   
	    // Initialisation de l'entité fournisseur
		public FournisseurBean() {
			 fournisseur = new Fournisseur();
			 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				 
//			 fournisseur = (Fournisseur) request.getSession().getAttribute(SESSION_FOURNISSEURS)   ; 
				
	//		 if ( fournisseur== null)
		//		 fournisseur = new Fournisseur();
			 
			 FournisseurListBean fournisseurListBean = (FournisseurListBean) request.getAttribute(REQUEST_FOURNISSEUR_LIST);
				if(fournisseurListBean != null){
					fournisseur  = fournisseurListBean.getFournisseur();
					request.getSession().setAttribute("fournisseur", fournisseur);
				 }
			 
				// TODO Auto-generated constructor stub
				idFournisseur = String.valueOf(fournisseur.getId());
				nom1 = fournisseur.getNom1();
				prenom = fournisseur.getPrenom();
				email = fournisseur.getEmail();	
				siege_social = fournisseur.getSiege_social();
				telephone = fournisseur.getTelephone();
				code1   = fournisseur.getCode1();
			
			
			// TODO Auto-generated constructor stub
		}
		public Map<String, Fournisseur> getFournisseurs() {
			List<Fournisseur> fournisseurList = new ArrayList<Fournisseur>();
//			if(fournisseurs == null){
				fournisseurs = new HashMap<String, Fournisseur>();
				fournisseurList = fournisseurDao.lister();
				for(Fournisseur fournisseur : fournisseurList)
					fournisseurs.put(String.valueOf(fournisseur.getId()), fournisseur);
//			}
			return  fournisseurs;
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
		
		  // Méthode d'action appelée lors du clic sur le bouton du formulaire
	    
	    public String  creerfournisseur() {
	        initialiserDateFournisseur();
	        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	      
	        FournisseurBean fournisseurBean = ( FournisseurBean) request.getAttribute(REQUEST_FOURNISSEUR)   ; 
			 
			  fournisseur = fournisseurDao.creer(fournisseurBean.getFournisseur());
			   FacesMessage message = new FacesMessage( "Succès de création d'un fournisseur!" );
	       
	        FacesContext.getCurrentInstance().addMessage( null, message );
	        return "fournisseurOverview";
	    }
	    
	    public String  modifier() {
	        initialiserDateFournisseur();
	        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        FournisseurListBean fournisseurListBean = ( FournisseurListBean) request.getAttribute(REQUEST_FOURNISSEUR_LIST); 
	        if(fournisseurListBean != null){
	        	fournisseur  = fournisseurListBean.getFournisseur();
		        request.getSession().setAttribute("fournisseur", fournisseur);
		        } else if(request.getSession().getAttribute("fournisseur") != null){
		        	Fournisseur fourn = 	(Fournisseur) request.getSession().getAttribute("fournisseur");
		        	fournisseur.setId(fourn.getId());
		        }  
		  
	        FournisseurBean fournisseurBean = ( FournisseurBean) request.getAttribute(REQUEST_FOURNISSEUR)   ; 
	        fournisseurDao.modifier(fournisseur);
	      
			 FacesMessage message = new FacesMessage( "Succès de la modification d'un fournisseur!" );
	        FacesContext.getCurrentInstance().addMessage( null, message );
	       
	        return "fournisseurEditView";
	    }
	    
	   
	    
	    /**
		 * @return the filteredFournisseurList
		 */
		public List<Fournisseur> getFilteredFournisseurList() {
			return filteredFournisseurList;
		}
		/**
		 * @param filteredFournisseurList the filteredFournisseurList to set
		 */
		public void setFilteredFournisseurList(List<Fournisseur> filteredFournisseurList) {
			this.filteredFournisseurList = filteredFournisseurList;
		}
		private void  initialiserDateFournisseur() {
	        Timestamp date = new Timestamp( System.currentTimeMillis() );
	       
	    }
		/**
		 * @return the idFournisseur
		 */
		public String getIdFournisseur() {
			return idFournisseur;
		}
		/**
		 * @param idFournisseur the idFournisseur to set
		 */
		public void setIdFournisseur(String idFournisseur) {
			this.idFournisseur = idFournisseur;
		}
		/**
		 * @return the nom1
		 */
		public String getNom1() {
			return nom1;
		}
		/**
		 * @param nom1 the nom1 to set
		 */
		public void setNom1(String nom1) {
			this.nom1 = nom1;
		}
		/**
		 * @return the prenom
		 */
		public String getPrenom() {
			return prenom;
		}
		/**
		 * @param prenom the prenom to set
		 */
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}
		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		/**
		 * @return the siege_social
		 */
		public String getSiege_social() {
			return siege_social;
		}
		/**
		 * @param siege_social the siege_social to set
		 */
		public void setSiege_social(String siege_social) {
			this.siege_social = siege_social;
		}
		/**
		 * @return the telephone
		 */
		public String getTelephone() {
			return telephone;
		}
		/**
		 * @param telephone the telephone to set
		 */
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		/**
		 * @return the code1
		 */
		public String getCode1() {
			return code1;
		}
		/**
		 * @param code1 the code1 to set
		 */
		public void setCode1(String code1) {
			this.code1 = code1;
		}
		/**
		 * @param fournisseurs the fournisseurs to set
		 */
		public void setFournisseurs(Map<String, Fournisseur> fournisseurs) {
			this.fournisseurs = fournisseurs;
		}
		
}
