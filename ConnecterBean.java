/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import cm.itac.formation.ecommerce.dao.UtilisateurDao;
import cm.itac.formation.ecommerce.dao.entity.User;
import cm.itac.formation.ecommerce.security.EcommerceContext;

/**
 * @author 
 *
 */
@RequestScoped
@ManagedBean
public class ConnecterBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS = "motDePasse";
	private EcommerceContext ecommerceContext;
	
	private static final String ALGO_CHIFFREMENT = "SHA-256";
	
	private User utilisateur;
	
	@EJB
	private UtilisateurDao utilisateurDao;
	
	public ConnecterBean() {
		 utilisateur = new User();
	}
	

		public String connecter() {
			
			 /* Récupération des champs du formulaire */
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			request.getSession().invalidate();
			request.getSession(true); 
			String email  = utilisateur.getEmail();
	        String motDePasse = utilisateur.getMotDePasse();
	        
	        User utilisateur = new User();

			
	      
			
	        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
	        passwordEncryptor.setAlgorithm(  ALGO_CHIFFREMENT );
	        passwordEncryptor.setPlainDigest( false );
	        String motDePasseChiffre = passwordEncryptor.encryptPassword( motDePasse );

	        utilisateur.setMotDePasse( motDePasseChiffre );
	        
	        utilisateur = utilisateurDao.trouver(email);
	        
	        
	       
	        

            if (utilisateur == null) {
            	FacesMessage message = new FacesMessage( "User not found !" );
                
                FacesContext.getCurrentInstance().addMessage( null, message );
                return "connexion";
                
                } else if(!passwordEncryptor.checkPassword(motDePasse, utilisateur.getMotDePasse())){
                	FacesMessage message = new FacesMessage( "Password not found !" );
                    
                    FacesContext.getCurrentInstance().addMessage( null, message );
                    return "connexion";
                    
               }
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            EcommerceContext ecommerceContext = EcommerceContext.getRequestEcommerceContext(request, response );
            ecommerceContext.setUser(utilisateur);
            FacesMessage message = new FacesMessage( "Succès de la connexion !" );
            UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
            
            FacesContext.getCurrentInstance().addMessage( null, message );
            if(userBean == null) 
            	userBean = new UserBean();
            userBean.setUtilisateur(utilisateur);
            FacesMessage message1 = new FacesMessage( "Vous etes connecté en tant que  " + utilisateur.getEmail() );
            FacesContext.getCurrentInstance().addMessage( null, message1 );
           //utilisateurDao.trouver(CHAMP_PASS);
	        return "pages/dashboard";
		
		}
		
		   public User getUtilisateur() {
			   
			 
		        return utilisateur;
		    }


	
		    

		// TODO Auto-generated constructor stub
	}


