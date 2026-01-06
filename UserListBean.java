/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import cm.itac.formation.ecommerce.dao.UtilisateurDao;
import cm.itac.formation.ecommerce.dao.entity.User;

/**
 * @author ricky
 *
 */
@RequestScoped
@ManagedBean
public class UserListBean implements Serializable{
	 private static final long serialVersionUID = 1L;
	 
	 private List<User> users;
	 private User utilisateur;
	 @EJB
	 private UtilisateurDao     utilisateurDao;
	/**
	 * 
	 */
	 
	public UserListBean() {
		users = new ArrayList<User>();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the client
	 */
	
	public List<User> getUsers() {
		users = utilisateurDao.lister();
		if(users == null)
			users = new ArrayList<User>();
		return users;
	}

	public String detail(User user){
		this.utilisateur = utilisateurDao.detail(user);
		
		return "userOverview";
	} 
	
	public String modifier(User user){
		this.utilisateur = utilisateurDao.detail(user);
		
		return "userEdit";
	} 
	
	public String supprimer(User utilisateur){
		utilisateurDao.supprimer(utilisateur);
		
		return "userList";
	}
	/**
	 * @param clients the clients to set
	 */

	public User getUtilisateur() {
		return utilisateur;
	}
	/**
	 * @param client the client to set
	 */
	public void setUtilisateur(User utilisateur) {
		this.utilisateur = utilisateur;
	}
}
