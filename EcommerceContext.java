/**
 * 
 */
package cm.itac.formation.ecommerce.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cm.itac.formation.ecommerce.dao.entity.User;

/**
 * This class define the context of Ecommerce Application
 * @author dongmo
 *
 */
public class EcommerceContext {


	/**
	 * Nome tramite il quale salvare nella sessione i dati dell'utente corrente.
	 */
	public static final String ECOMMERCE_CONTEXT = "ecommerceContext";

	/**
	 * Richiesta Http
	 */
	private HttpServletRequest request;
	
	/**
	 * Risposta Http
	 */
	private HttpServletResponse response;
	
	/**
	 * Utente presente nell'anagrafica Ecommerce
	 */
	private User user;
	
	/**
	 * Ottieni l'utente presente nell'anagrafica Ecommerce	
	 * @return Utente presente nell'anagrafica Ecommerce
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Imposta l'utente presente nell'anagrafica A2E
	 * @param user Utente presente nell'anagrafica A2E
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Ottieni la richiesta Http
	 * @return Richiesta Http
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * Imposta la richiesta Http
	 * @param request Richiesta Http
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * Ottieni la risposta Http
	 * @return Risposta Http
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * Imposta la risposta Http
	 * @param response Risposta Http
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * Ottieni il contesto applicativo in base alla request
	 * 
	 * @param request
	 *            request Http
	 * @return Contesto applicativo A2E
	 */
	public static EcommerceContext getEcommerceContextByRequest(HttpServletRequest request) {
		return getRequestEcommerceContext(request, null);
	}

	/**
	 * Ottieni il contesto applicativo in base alla request e alla response
	 * @param request request Http
	 * @param response response Http
	 * @return contesto applicativo in base alla request e alla response
	 */
	public static EcommerceContext getRequestEcommerceContext(HttpServletRequest request,
			HttpServletResponse response) {
		//ottengo il potenziale contesto dalla sessione
		Object ctx = request.getSession().getAttribute(ECOMMERCE_CONTEXT);
		
		//session singleton, il contesto A2e ha solo una istanza per sessione
		if (ctx == null) {
			EcommerceContext ecommerceCtx = new EcommerceContext();
			// salvo il contesto in sessione
			request.getSession().setAttribute(ECOMMERCE_CONTEXT, ecommerceCtx);
			
			ecommerceCtx.setRequest(request);
			
			ecommerceCtx.setResponse(response);
			
			return ecommerceCtx;
		} else
		{
			return (EcommerceContext)ctx;
		}
	}

}
