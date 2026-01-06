/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import cm.itac.formation.ecommerce.dao.Type_EntreeDao;
import cm.itac.formation.ecommerce.dao.entity.Type_Entree;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class Type_EntreeBean {
	
	private Type_Entree    type_Entree;

	 // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private Type_EntreeDao     type_EntreeDao;
    Map<String, Type_Entree>   type_Entrees;
    
	 //   public static final String SESSION_TYPE_ENTREES   = "type_Entrees";
	    public static final String REQUEST_TYPE_ENTREE = "type_EntreeBean";
	   
	    // Initialisation de l'entité type_Entrée
	    
		public Type_EntreeBean() {
			type_Entree = new Type_Entree();
			 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				 
//			 type_Entrée = (Type_Entree) request.getSession().getAttribute(SESSION_TYPE_ENTREES)   ; 
				
			 if ( type_Entree== null)
				 type_Entree = new Type_Entree();
			
			
			
			// TODO Auto-generated constructor stub
		}
		public Map<String, Type_Entree> getType_Entrees() {
			List<Type_Entree> type_EntreeList = new ArrayList<Type_Entree>();
//			if(type_Entrees == null){
				type_Entrees = new HashMap<String, Type_Entree>();
				type_EntreeList = type_EntreeDao.lister();
				for(Type_Entree type_Entree : type_EntreeList)
					type_Entrees.put(String.valueOf(type_Entree.getId()), type_Entree);
//			}
			return type_Entrees;
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
		
		  // Méthode d'action appelée lors du clic sur le bouton du formulaire
	    
	   
	   
}
