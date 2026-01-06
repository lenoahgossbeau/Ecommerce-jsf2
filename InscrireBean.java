package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import cm.itac.formation.ecommerce.dao.ProfileDao;
import cm.itac.formation.ecommerce.dao.User_ActionDao;
import cm.itac.formation.ecommerce.dao.UtilisateurDao;
import cm.itac.formation.ecommerce.dao.entity.Profile;
import cm.itac.formation.ecommerce.dao.entity.User;
import cm.itac.formation.ecommerce.dao.entity.User_Action;


@RequestScoped
@ManagedBean
public class InscrireBean implements Serializable {
    private static final long serialVersionUID = 1L;

	private static final String ALGO_CHIFFREMENT = "SHA-256";

    private User       utilisateur;
    private Profile profile;
    private User_Action user_Action;
    
    private boolean selection;
    private String nameProfile;
    private String action;
    private String idUser;
    private String email;
    private String nom;
    
   
    Map<String, Profile> profiles;
    Map<String, User_Action> actions;
    List<User> users;
    private List<Profile> profileList;
    private List<User_Action> userActionList;
    
    
    @PostConstruct
    public void init() {
    	
    	
        profileList = new ArrayList<Profile>();
    }
    
    
    
    
    public static final String SESSION_PROFILE   = "profiles";
    public static final String SESSION_USERACTION   = "actions";
    public static final String SESSION_USERS = "users";
    public static final String REQUEST_PROFILE = "profileBean";
    public static final String REQUEST_USER_ACTION = "userActionBean";
    public static final String REQUEST_USER = "userBean";

	public static final String REQUEST_USER_LIST = "userListBean";

    // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private UtilisateurDao    utilisateurDao;
    @EJB
    private ProfileDao profileDao;
    @EJB
    private User_ActionDao user_ActionDao;
    

    // Initialisation de l'entité utilisateur
    public InscrireBean() {
        utilisateur = new User();
        
     
     
	 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	 if( profile == null)
		 profile = new Profile();
	 
	 UserListBean userListBean = (UserListBean) request.getAttribute(REQUEST_USER_LIST)   ;
	 if(userListBean != null){
	 utilisateur  = userListBean.getUtilisateur();
	 request.getSession().setAttribute("user", utilisateur);
	 }
	 
	 action = utilisateur.getUser_Action().getAction();
	 nameProfile = utilisateur.getProfile().getName();
	 nom = utilisateur.getNom();
	 email = utilisateur.getEmail();
	 idUser = String.valueOf(utilisateur.getId());
	 
	 
}



    public List<User> getUsers() {
    	//HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
       // UserListBean userListBean = (UserListBean) request.getAttribute(REQUEST_USER_LIST);
		//UserBean userBean = (UserBean) request.getAttribute(REQUEST_USER);
		return users;
	}



	public void setProfiles(Map<String, Profile> profiles) {
		this.profiles = profiles;
	}
	
	public void setActions(Map<String, User_Action> actions) {
		this.actions = actions;
	}



	public void setUsers(List<User> users) {
		this.users = users;
	}



	public Map<String, Profile> getProfiles() {
	return profiles;
}
	public Map<String, User_Action> getActions() {
		return actions;
	}
	

	public Profile getProfile() {
		return profile;
	}



	public void setProfile(Profile profile) {
		this.profile = profile;
	}


	

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}



	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}



	public void setUtilisateur(User utilisateur) {
		this.utilisateur = utilisateur;
	}



	// Méthode d'action appelée lors du clic sur le bouton du formulaire
    // d'inscription
    public String inscrire() {
        initialiserDateInscription(); 
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        ProfileBean profileBean = (ProfileBean) request.getAttribute(REQUEST_PROFILE)   ;
		UserBean userBean = (UserBean) request.getAttribute(REQUEST_USER);
		
        String motDePasse = utilisateur.getMotDePasse();
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
        passwordEncryptor.setPlainDigest( false );
        String motDePasseChiffre = passwordEncryptor.encryptPassword( motDePasse );

        String codeSelected = nameProfile;
        
        
        Profile profile = new Profile();
        profile.setId(Long.valueOf(nameProfile));
        utilisateur.setProfile(profile);
        
        User_Action user_Action = new User_Action();
        user_Action.setId(Long.valueOf(action));
        utilisateur.setUser_Action(user_Action);
        
        utilisateur.setMotDePasse( motDePasseChiffre );
        
        utilisateurDao.creer( utilisateur );
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
		return "pages/inscription";
    }

 // Méthode d'action appelée lors du clic sur le bouton du formulaire
    // d'inscription
    public String modifier() {
        initialiserDateInscription(); 
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        UserListBean userListBean = (UserListBean) request.getAttribute(REQUEST_USER_LIST)   ;
        if(userListBean != null){
        utilisateur  = userListBean.getUtilisateur();
        request.getSession().setAttribute("user", utilisateur);
        } else if(request.getSession().getAttribute("user") != null){
        	User user = 	(User) request.getSession().getAttribute("user");
        	utilisateur.setId(user.getId());
        	utilisateur.setMotDePasse(user.getMotDePasse());
        }
        ProfileBean profileBean = (ProfileBean) request.getAttribute(REQUEST_PROFILE)   ;
		UserBean userBean = (UserBean) request.getAttribute(REQUEST_USER);

        utilisateurDao.modifier( utilisateur );
        FacesMessage message = new FacesMessage( "Succès de la modification !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
		return "userEdit";
    }


    
    
    public void setSelection(boolean valeur) {

        selection = valeur;

        }
        public boolean getSelection() {

        return selection;
        }
    
    public User getUtilisateur() {
        return utilisateur;
    }

    private void initialiserDateInscription() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        utilisateur.setDateInscription( date );
    }



	public User_Action getUser_Action() {
		return user_Action;
	}



	public void setUser_Action(User_Action user_Action) {
		this.user_Action = user_Action;
	}



	public String getNameProfile() {
		return nameProfile;
	}



	public void setNameProfile(String nameProfile) {
		this.nameProfile = nameProfile;
	}



	/**
	 * @return the idUser
	 */
	public String getIdUser() {
		return idUser;
	}



	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}



	public List<Profile> getProfileList() {
		return profileList;
	}



	public void setProfileList(List<Profile> profileList) {
		this.profileList = profileList;
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}



	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
    
    
}
