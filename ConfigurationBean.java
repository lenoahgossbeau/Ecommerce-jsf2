/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cm.itac.formation.ecommerce.dao.ConfigurationDao;
import cm.itac.formation.ecommerce.dao.entity.Configuration;
import cm.itac.formation.ecommerce.dao.entity.User;
import cm.itac.formation.ecommerce.security.EcommerceContext;

/**
 * @author erico
 *
 */
@SessionScoped
@ManagedBean
public class ConfigurationBean implements Serializable{
	private static final long serialVersionUID = 1L;
	  private Configuration      configuration;
	  private  String  keyStr;
		private  String valueStr;
		private User utilisateur;
		private boolean module_achat;
		private boolean module_fournisseur;
		private boolean  module_client;
		private boolean  module_article;
		private  boolean  module_utilisateur;
		private  boolean  module_dashboard;
		private   boolean  module_entree;
		private  boolean   module_commande;
		private   boolean  module_inventaire;
		private   boolean  module_configuration;
		private boolean    admin;
		
		private EcommerceContext ecommerceContext;
		 private List<Configuration> filteredConfigurationList;
         
	  @EJB
	    private ConfigurationDao    configurationDao;
	    
	 Map<String, Configuration> configurations;
      
	   public static final String SESSION_CONFIGURATIONS   = "configurations";
	   public static final String REQUEST_CONFIGURATION = "configurationBean";
	  
	   public static final String REQUEST_CONFIGURATION_LIST = "configurationListBean";
	   
	    // Injection de notre EJB (Session Bean Stateless)
	 
	public ConfigurationBean() {
		configuration = new Configuration();
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			 
	if ( configuration== null)
			 configuration = new Configuration();
		
		// TODO Auto-generated constructor stub
	
	ConfigurationListBean configurationListBean = (ConfigurationListBean) request.getAttribute(REQUEST_CONFIGURATION_LIST);
	if(configurationListBean != null){
		configuration = configurationListBean.getConfiguration();
		request.getSession().setAttribute("configuration", configuration);
	 }
	
	ecommerceContext = EcommerceContext.getEcommerceContextByRequest(request);
	keyStr = configuration.getKeyStr();
	valueStr = configuration.getValueStr();
	
	 HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		EcommerceContext ecommerceContext = EcommerceContext.getRequestEcommerceContext(request, response );
		this.utilisateur = ecommerceContext.getUser();
	}
	
	/**
	 * @param configurations the configurations to set
	 */
	public void setConfigurations(Map<String, Configuration> configurations) {
		this.configurations = configurations;
	}

	  /**
	 * @return the configurations
	 */
	public Map<String, Configuration> getConfigurations() {
		List<Configuration> configurationList = new ArrayList<Configuration>();
		configurations = new HashMap<String, Configuration>();
		configurationList = configurationDao.lister();
		for(Configuration configuration : configurationList)
			configurations.put(String.valueOf(configuration.getId()), configuration);
		return configurations;
	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

// Méthode d'action appelée lors du clic sur le bouton du formulaire
  // d'inscription
  public String  creerconfiguration() {
     
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      ConfigurationBean configurationBean = (ConfigurationBean) request.getAttribute(REQUEST_CONFIGURATION)   ; 
    
       configurationDao.creer(configuration);
      
      FacesMessage message = new FacesMessage( "Succès de la config !" );
      FacesContext.getCurrentInstance().addMessage( null, message );
      return "configurationOverview";
  
  }

  public String  modifier() {
     
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 ConfigurationListBean configurationListBean = (ConfigurationListBean) request.getAttribute(REQUEST_CONFIGURATION_LIST)   ; 
		 if(configurationListBean != null){
		        configuration  = configurationListBean.getConfiguration();
		        request.getSession().setAttribute("configuration", configuration);
		        } else if(request.getSession().getAttribute("configuration") != null){
		        	Configuration config = 	(Configuration) request.getSession().getAttribute("configuration");
		        	configuration.setId(config.getId());
		        
		        }
		 ConfigurationBean configurationBean = (ConfigurationBean) request.getAttribute(REQUEST_CONFIGURATION)   ; 
		      configurationDao.modifier(configuration);
		      
		 FacesMessage message = new FacesMessage( "Succès de la modification  !" );
      FacesContext.getCurrentInstance().addMessage( null, message );
      
    
      
      return "configEditView";
  }
  
  
  public boolean isActive(String value){
	  Configuration config = configurationDao.trouver(value);
	  if("true".equals(config.getValueStr())){
		  return true;
	  } else {
		  return false;
	  }
  }
  

/**
 * @return the keyStr
 */
public String getKeyStr() {
	return keyStr;
}

/**
 * @param keyStr the keyStr to set
 */
public void setKeyStr(String keyStr) {
	this.keyStr = keyStr;
}

/**
 * @return the valueStr
 */
public String getValueStr() {
	return valueStr;
}

/**
 * @param valueStr the valueStr to set
 */
public void setValueStr(String valueStr) {
	this.valueStr = valueStr;
}

/**
 * @return the filteredConfigurationList
 */
public List<Configuration> getFilteredConfigurationList() {
	return filteredConfigurationList;
}

/**
 * @param filteredConfigurationList the filteredConfigurationList to set
 */
public void setFilteredConfigurationList(List<Configuration> filteredConfigurationList) {
	this.filteredConfigurationList = filteredConfigurationList;
}

/**
 * @return the module_achat
 */
public boolean isModule_achat() {
	module_achat = isActive("module.achat");
	boolean fonction_achat = isActive("fonction.achat");
	if((module_achat && "SysAdmin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			 "Admin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			"Caisse".equals(ecommerceContext.getUser().getProfile().getName())) && fonction_achat){
		module_achat = true;
		
	}else{
		module_achat = false;
		
	}
	
	return module_achat;
}

/**
 * @param module_achat the module_achat to set
 */
public void setModule_achat(boolean module_achat) {
	this.module_achat = module_achat;
}

/**
 * @return the module_fournisseur
 */
public boolean isModule_fournisseur() {
	module_fournisseur = isActive("module.fournisseur");
	if(module_fournisseur && "SysAdmin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			 "Admin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			"Facturer".equals(ecommerceContext.getUser().getProfile().getName())){
		module_fournisseur = true;
} else{
	module_fournisseur = false;
}
	
	
	return module_fournisseur;
}

/**
 * @param module_fournisseur the module_fournisseur to set
 */
public void setModule_fournisseur(boolean module_fournisseur) {
	this.module_fournisseur = module_fournisseur;
}

/**
 * @return the module_client
 */
public boolean isModule_client() {
	module_client = isActive("module.client");
	if(module_client && "SysAdmin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			 "Admin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			"Facturer".equals(ecommerceContext.getUser().getProfile().getName())){
		module_client = true;
	}else {
		module_client = false;
	}
	
	return module_client;
}

/**
 * @param module_client the module_client to set
 */
public void setModule_client(boolean module_client) {
	this.module_client = module_client;
}

/**
 * @return the module_artile
 */
public boolean isModule_article() {
	module_article = isActive("module.article");
	if(module_article && "SysAdmin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			 "Admin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			"Facturer".equals(ecommerceContext.getUser().getProfile().getName())){
		module_article = true;
	}else{
		module_article = false;
	}
	
	return module_article;
}

/**
 * @param module_artile the module_artile to set
 */
public void setModule_article(boolean module_article) {
	this.module_article = module_article;
}

/**
 * @return the module_utilisateur
 */
public boolean isModule_utilisateur() {
	module_utilisateur = isActive("module.utilisateur");
	if( module_utilisateur && "SysAdmin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			 "Admin".equals(ecommerceContext.getUser().getProfile().getName()) ){
			
			 module_utilisateur = true;
	}else{
		module_utilisateur = false;
	}
	return module_utilisateur;
}

/**
 * @param module_utilisateur the module_utilisateur to set
 */
public void setModule_utilisateur(boolean module_utilisateur) {
	this.module_utilisateur = module_utilisateur;
}

/**
 * @return the module_dashboard
 */
public boolean isModule_dashboard() {
	module_dashboard = isActive("module.dashboard");
	if(module_dashboard && "SysAdmin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			 "Admin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			"Caisse".equals(ecommerceContext.getUser().getProfile().getName())){
		module_dashboard = true;
	}else{
		module_dashboard = false;
	}
	

	return module_dashboard;
}

/**
 * @param module_dashboard the module_dashboard to set
 */
public void setModule_dashboard(boolean module_dashboard) {
	this.module_dashboard = module_dashboard;
}

/**
 * @return the module_entree
 */
public boolean isModule_entree() {
	module_entree = isActive("module.entree");
	if(module_entree && "SysAdmin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			 "Admin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			"Facturer".equals(ecommerceContext.getUser().getProfile().getName())){
		module_entree = true;
	}else{
		module_entree = false;
	}
	
	return module_entree;
}

/**
 * @param module_entree the module_entree to set
 */
public void setModule_entree(boolean module_entree) {
	this.module_entree = module_entree;
}

/**
 * @return the module_commande
 */
public boolean isModule_commande() {
	module_commande = isActive("module.commande");
	if( module_commande && "SysAdmin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			 "Admin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			"Facturer".equals(ecommerceContext.getUser().getProfile().getName()) ||
			"Caisse".equals(ecommerceContext.getUser().getProfile().getName())){
		module_commande = true;
	}else{
		module_commande = false;
	}
	
	return module_commande;
}

/**
 * @param module_commande the module_commande to set
 */
public void setModule_commande(boolean module_commande) {
	this.module_commande = module_commande;
}

/**
 * @return the module_inventaire
 */
public boolean isModule_inventaire() {
	module_inventaire = isActive("module.inventaire");
	if("SysAdmin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			 "Admin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			"Caisse".equals(ecommerceContext.getUser().getProfile().getName())){
		module_inventaire = true;
	}else{
		module_inventaire = false;
	}
	return module_inventaire;
}

/**
 * @param module_inventaire the module_inventaire to set
 */
public void setModule_inventaire(boolean module_inventaire) {
	this.module_inventaire = module_inventaire;
}

/**
 * @return the admin
 */
public boolean isAdmin() {
	admin = isActive("admin");
	return admin;
}

/**
 * @param admin the admin to set
 */
public void setAdmin(boolean admin) {
	this.admin = admin;
}

/**
 * @return the module_configuration
 */
public boolean isModule_configuration() {
	module_configuration = isActive("module.configuration");
	if( module_configuration && "SysAdmin".equals(ecommerceContext.getUser().getProfile().getName()) ||
			 "Admin".equals(ecommerceContext.getUser().getProfile().getName()) ){
			
		module_configuration = true;
	}else{
		module_configuration = false;
	}
	return module_configuration;
}

/**
 * @param module_configuration the module_configuration to set
 */
public void setModule_configuration(boolean module_configuration) {
	this.module_configuration = module_configuration;
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




}
