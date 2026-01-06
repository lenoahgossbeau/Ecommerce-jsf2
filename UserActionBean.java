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

import cm.itac.formation.ecommerce.dao.User_ActionDao;
import cm.itac.formation.ecommerce.dao.entity.User_Action;

/**
 * @author admin
 *
 */
@RequestScoped
@ManagedBean
public class UserActionBean {
	private User_Action user_Action ;

	/**
	 * 
	 */
	// Injection de notre EJB (Session Bean Stateless)
	@EJB
	private User_ActionDao   userActionDao ;
	Map<String, User_Action>   user_Actions ;
	
	
	public static final String REQUEST_USER_ACTION = "userActionBean";
	// Initialisation de l'entité type_Entrée
	
	public UserActionBean() {
		user_Action = new User_Action();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	
		 if ( user_Action== null)
			 user_Action = new User_Action();
		
	}

	public Map<String, User_Action> getUser_Actions() {
		List<User_Action> user_ActionList = new ArrayList<User_Action>();
//		if(type_Entrees == null){
		user_Actions = new HashMap<String, User_Action>();
		user_ActionList = userActionDao.lister();
			for(User_Action user_Action : user_ActionList)
				user_Actions.put(String.valueOf(user_Action.getId()), user_Action);
//		}
		return user_Actions;
	}
	
	public User_Action getUser_Action() {
		return user_Action;
	}
	/**
	 * @param type_Entree the type_Entree to set
	 */
	public void setUser_Action(User_Action user_Action) {
		this.user_Action = user_Action;
	}
}
