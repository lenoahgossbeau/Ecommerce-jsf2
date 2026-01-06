package cm.itac.formation.ecommerce.security;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AppEcommerceListener
 *
 */
    @WebListener
    public class AppEcommerceListener implements ServletContextListener {

        public void contextInitialized(ServletContextEvent servletContextEvent) {
        	ServletContext ctx = servletContextEvent.getServletContext();
        	try {
				ItacManager itacManager = new ItacManager();
				String value = itacManager.get("appManager.key");
				System.out.println(value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


        	
        	      }

        public void contextDestroyed(ServletContextEvent servletContextEvent) {
        	ServletContext ctx = servletContextEvent.getServletContext();
        	 	
        }

		public AppEcommerceListener() {
			super();
			// TODO Auto-generated constructor stub
		}
	
}
