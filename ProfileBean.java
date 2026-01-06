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

import cm.itac.formation.ecommerce.dao.ProfileDao;
import cm.itac.formation.ecommerce.dao.entity.Profile;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class ProfileBean {
	
	private Profile   profile;
	
	// Injection de notre EJB (Session Bean Stateless)
    @EJB
    private ProfileDao     profileDao;
    Map<String, Profile>   profiles;
    
	 //   public static final String SESSION_TYPE_ENTREES   = "type_Entrees";
	    public static final String REQUEST_PROFILE = "profileBean";
	   
	    // Initialisation de l'entité profile
	    

	/**
	 * 
	 */
	public ProfileBean() {
		
		profile = new Profile();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			 
			
		 if ( profile== null)
			 profile = new Profile();
		// TODO Auto-generated constructor stub
	}
	public Map<String, Profile> getProfiles() {
		List<Profile> profileList = new ArrayList<Profile>();
//		if(type_Entrees == null){
			profiles = new HashMap<String, Profile>();
			profileList = profileDao.lister();
			for(Profile profile : profileList)
				profiles.put(String.valueOf(profile.getId()), profile);
//		}
		return profiles;
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
	 * @param profiles the profiles to set
	 */
	public void setProfiles(Map<String, Profile> profiles) {
		this.profiles = profiles;
	}
	
}
