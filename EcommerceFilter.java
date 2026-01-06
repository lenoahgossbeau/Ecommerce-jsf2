package cm.itac.formation.ecommerce.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cm.itac.formation.ecommerce.dao.entity.User;
import cm.itac.formation.ecommerce.security.EcommerceContext;

/**
 * Servlet Filter implementation class EcommerceFilter
 */
@WebFilter("/pages/*")
public class EcommerceFilter implements Filter {

	/**
	 * primo delimitatore URL - Ruoli
	 */
	private final String URL_ROLES_AUTHS_DELEMITER = "=";
	
	/**
	 * Secondo delimitatore tra ruoli
	 */
	private final String ROLES_AUTHS_DELEMITER = ",";
	
	/**
	 * Indica tutti i ruoli permessi
	 */
	private final String ALL_ROLES = "*";
	
		/**
	 * impostazioni di configurazione per il filtro
	 */
	private FilterConfig filterConfig;
	
	/**
	 * Lista di autorizzazioni ruoli ammessi per URL
	 */
	private Collection<String> authorizations;
	
	/**
	 * Pagina di login nel caso l'utente non sia ancora autenticato
	 */
	private String loginPage="/connexion.xhtml";
	
	/**
	 * Pagina home in caso non si hanno i ruoli per accedere alla risorsa richiesta
	 */
	private String homePage;
	
	/**
	 * Path delle risorse da escludere
	 */
	private Collection<String> excludePath = new ArrayList<String>();

	/**
	 * Ottieni la pagina di login nel caso l'utente non sia ancora autenticato
	 * @return Pagina di login nel caso l'utente non sia ancora autenticato
	 */
	public String getLoginPage() {
		return loginPage;
	}

	/**
	 * Imposta la pagina di login nel caso l'utente non sia ancora autenticato
	 * @param loginPage Pagina di login nel caso l'utente non sia ancora autenticato
	 */
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	/**
	 * Ottieni la pagina home in caso non si hanno i ruoli per accedere alla risorsa richiesta
	 * @return Pagina home in caso non si hanno i ruoli per accedere alla risorsa richiesta
	 */
	public String getHomePage() {
		return homePage;
	}

	/**
	 * Imposta la pagina home in caso non si hanno i ruoli per accedere alla risorsa richiesta
	 * @param homePage Pagina home in caso non si hanno i ruoli per accedere alla risorsa richiesta
	 */
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}


    /**
     * Default constructor. 
     */
    public EcommerceFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String contextPath = httpRequest.getContextPath();
		String requestPath = httpRequest.getRequestURI();
		User user = EcommerceContext.getEcommerceContextByRequest(httpRequest).getUser();
		
		
		 /* la stategia di gestione della sicurezza è la seguente:
		 * 1) Se un utente non è loggato allora viene rediretto alla pagina di login 
		 * 2) Se l'utente è loggato o è passato per il punto 1 allora si veficano i suoi
		 * ruoli per i permessi alla risorsa richiesta, se non ha i permessi viene rediretto
		 * alla home page applicativa
		 */
		
		//se l'utente non è definito allora non ha ancora eseguito la login
		if(user == null) {
			
			//se l'utente non ha richiesto la login allora eseguo redirect alla login
			if(requestPath.toLowerCase().indexOf(getLoginPage()) == -1) {
				System.out.println("L'user is not login yet, URL: " + requestPath);
				httpResponse.sendRedirect(contextPath + getLoginPage());
			} else {
				//continuo la pipeline verso la pagina di login
				chain.doFilter(request, response);
			}
			
		} else {
			chain.doFilter(request, response);
		}
		
		
		
		
		//non considero i path da escludere, come ad esempio quelli delle risorse dinamiche
		/*if(!isExcludePath(requestPath)) {
			//se l'utente non è definito allora non ha ancora eseguito la login
			if(user == null) {
				
				//se l'utente non ha richiesto la login allora eseguo redirect alla login
				if(requestPath.toLowerCase().indexOf(getLoginPage()) != -1) {
					System.out.println("L'user is not login yet, URL: " + requestPath);
					httpResponse.sendRedirect(contextPath + getLoginPage());
				} else {
					//continuo la pipeline verso la pagina di login
					chain.doFilter(request, response);
				}
				
			} else {
				
				//se è definito un utente controllo se ha i ruoli per accedere alla risorsa specificata
				if(isUserAuthorized(user, requestPath)) {
					//se sono autorizzato è accedo alla login allora vengo rediretto alla homepage
					if(requestPath.toLowerCase().indexOf(getLoginPage()) != -1) {
						System.out.println("User " + user.getNom() + " authorized for the resource URL: " + requestPath);
						httpResponse.sendRedirect(contextPath + getHomePage());
					} else {
						chain.doFilter(request, response);
					}
				} else {
					
					//se l'utente non ha richiesto la home page allora redirect alla home page
					if(requestPath.toLowerCase().indexOf(getHomePage()) == -1) {
						System.out.print("l'User " + user.getNom() + " do not have roles to access to "
								+ requestPath);
						httpResponse.sendRedirect(contextPath + getHomePage());
					} else {
						//continuo la pipeline verso la pagina di login
						chain.doFilter(request, response);
					}
				}
			}
		} else {
			chain.doFilter(request, response);
		}*/
	
	} 
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * Indica se devo escludere il path specificato dall'operazione di filtro
	 * @param requestUrl url richiesto
	 * @return true se devo escludere il path dal filtro, false altrimenti
	 */
	protected boolean isExcludePath(String requestUrl) {
		for (String path : getExcludePath()) {
			if(requestUrl.toLowerCase().indexOf(path.toLowerCase()) != -1) {
				return true;
			}
		}
		
		return false;
	}	
		/**
		 * Ottieni il path delle risorse da escludere
		 * @return Path delle risorse da escludere
		 */
		public Collection<String> getExcludePath() {
			return excludePath;
		}
		
		
		/**
		 * Verifica se un utente ha i ruoli per accedere alla risorsa specificata
		 * @param user utente loggato che richiede la risorsa
		 * @param requestUrl url richiesto dall'utente
		 * @return true se l'utente in base ai ruoli definiti a accesso alla risorsa, false altrimenti
		 */
		protected boolean isUserAuthorized(User user, String requestUrl) {
			
			//lista di ruoli che un utente deve possedete per accedere a requestUrl
			Collection<String> rolesByRequestUrl = getRolesByRequestPath(requestUrl);
			
			//controlla se tra tutti i ruoli previsti per la risorsa l'utente ne possieda almeno uno,
			//se uso * allora vuol dire che l'utente può avere qualsiasi ruolo nel suo profilo
			for (String roleName : rolesByRequestUrl) {
				if(roleName.toLowerCase().equals(ALL_ROLES) 
				   || user != null) {
					return true;
				}
			}
			
			//se arrivo qua allora l'utente non ha diritto ad accedere alla risorsa
			return false;
		}
	

	
	/**
	 * Ottieni la lista dei ruoli in base al percorso della request
	 * @param requestUrl request relativo alla risorsa richiesta
	 * @return lista dei ruoli in base al percorso della request
	 */
	protected Collection<String> getRolesByRequestPath(String requestUrl) {
		
		//stringa che rappresenta i ruoli in formato CSV
		String rolesCsv = null;
		
	
	    	//lista di ruoli vuota
	    	return new ArrayList<String>();
	    
	}

}
