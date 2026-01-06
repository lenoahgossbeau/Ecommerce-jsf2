/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import cm.itac.formation.ecommerce.dao.ProfileDao;
import cm.itac.formation.ecommerce.dao.UtilisateurDao;
import cm.itac.formation.ecommerce.dao.entity.Profile;
import cm.itac.formation.ecommerce.dao.entity.User;
import cm.itac.formation.ecommerce.dao.entity.User_Action;

/**
 * @author 
 *
 */
@ManagedBean
@SessionScoped
public class UserBean  implements Serializable{
	  	
	
	private static final long serialVersionUID = 1L;

	private User utilisateur;
	private Profile profile;
	private User_Action user_Action;
	
	private boolean selection;
	
	private String action;
	private String nameProfile;
	
	Map<String, Profile> profiles;
	
	Map<String, User_Action> user_Actions;
	List<User> utilisateurs;
	
	private List<Profile> list;
	private List<User> filteredUserlist;
	private List<User_Action> list1;
	
	
	@PostConstruct
	    public void init() {
	    	
	        list = new ArrayList<Profile>(); 
	        list1 = new ArrayList<User_Action>();
	    }	
	
    public static final String SESSION_PROFILES   = "profiles";
    public static final String SESSION_USER_ACTION   = "user_Actions";
    public static final String SESSION_USERS   = "utilisateurs";

	
	 public static final String REQUEST_PROFILE = "profileBean";
     public static final String REQUEST_USER = "userBean";
     public static final String REQUEST_USER_ACTION = "userActionBean";
	
     
     @EJB
     private UtilisateurDao    utilisateurDao;
     
     @EJB
     private   ProfileDao      profileDao;
     
	
	public UserBean() {
		utilisateur= new User();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		 if ( profile == null )
			 profile = new Profile();
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the profiles
	 */
	public Map<String, Profile> getProfiles() {
		return profiles;
	}

	/**
	 * @param profiles the profiles to set
	 */
	public void setProfiles(Map<String, Profile> profiles) {
		this.profiles = profiles;
	}
	
	

	public Map<String, User_Action> getUser_Actions() {
		return user_Actions;
	}

	public void setUser_Actions(Map<String, User_Action> user_Actions) {
		this.user_Actions = user_Actions;
	}

	

	public User_Action getUser_Action() {
		return user_Action;
	}

	public void setUser_Action(User_Action user_Action) {
		this.user_Action = user_Action;
	}

	 public void setSelection(boolean valeur) {

		    selection = valeur;

		    }
		    public boolean getSelection() {

		    return selection;
		    }

	

	public String getAction() {
				return action;
			}

			public void setAction(String action) {
				this.action = action;
			}

	public List<Profile> getList() {
		return list;
	}

	public void setList(List<Profile> list) {
		this.list = list;
	}

	public List<User_Action> getList1() {
		return list1;
	}

	public void setList1(List<User_Action> list1) {
		this.list1 = list1;
	}

	/**
	 * @return the utilisateur
	 */
	public User getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(User utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * @return the nameNameProfile
	 */
	public String getNameProfile() {
		return nameProfile;
	}

	/**
	 * @param nameNameProfile the nameNameProfile to set
	 */
	public void setNameProfile(String nameProfile) {
		this.nameProfile = nameProfile;
	}

	/**
	 * @return the utilisateurs
	 */
	public List<User> getUtilisateurs() {
		return utilisateurs;
	}

	/**
	 * @param utilisateurs the utilisateurs to set
	 */
	public void setUtilisateurs(List<User> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	
	

	/**
	 * @return the filteredUserlist
	 */
	public List<User> getFilteredUserlist() {
		return filteredUserlist;
	}

	/**
	 * @param filteredUserlist the filteredUserlist to set
	 */
	public void setFilteredUserlist(List<User> filteredUserlist) {
		this.filteredUserlist = filteredUserlist;
	}
	

}