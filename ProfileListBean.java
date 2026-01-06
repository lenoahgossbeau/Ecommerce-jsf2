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

import cm.itac.formation.ecommerce.dao.ProfileDao;
import cm.itac.formation.ecommerce.dao.entity.Profile;

/**
 * @author admin
 *
 */
@RequestScoped
@ManagedBean
public class ProfileListBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private List<Profile> profiles;
	 private Profile profile;
	 
	 @EJB
	 private ProfileDao     profileDao;
	 
	public ProfileListBean() {
		// TODO Auto-generated constructor stub
		profiles = new ArrayList<Profile>();
	}
	
	public List<Profile> getProfiles() {
		profiles = profileDao.lister();
		if(profiles == null)
			profiles = new ArrayList<Profile>();
		return profiles;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	

}
