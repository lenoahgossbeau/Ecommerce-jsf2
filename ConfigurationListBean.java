/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import cm.itac.formation.ecommerce.dao.ConfigurationDao;
import cm.itac.formation.ecommerce.dao.entity.Configuration;
import cm.itac.formation.ecommerce.dao.entity.Customer;


/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class ConfigurationListBean implements Serializable{
	 private static final long serialVersionUID = 1L;
	 private List<Configuration> configurations;
	 private Configuration configuration;
	 @EJB
	 private ConfigurationDao     configurationDao;
	public ConfigurationListBean() {
		
		configurations = new ArrayList<Configuration>();
		// TODO Auto-generated constructor stub
	}
	public List<Configuration> getConfigurations() {
		configurations = configurationDao.lister();
		if(configurations == null)
			configurations = new ArrayList<Configuration>();
		return configurations;
	}
	
	public String modifier(Configuration  configuration) {
		
        this.configuration = configurationDao.modifier(configuration);

FacesMessage message = new FacesMessage(
        "Succés de la modification de notre configuration !");
FacesContext.getCurrentInstance().addMessage(null ,message);

return "configEditView";
}
	
	
	
	public String detail(Configuration  configuration) {
		
        this.configuration = configurationDao.trouver(configuration.getId());

return "configEditView";
}
	
	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}
	/**
	 * @param connfiguration the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	

}
