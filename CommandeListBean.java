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
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import cm.itac.formation.ecommerce.dao.CustomerDao;
import cm.itac.formation.ecommerce.dao.OrderDao;
import cm.itac.formation.ecommerce.dao.entity.Order;

/**
 * @author erico
 *
 */
@RequestScoped
@ManagedBean
public class CommandeListBean implements Serializable{
	 private static final long serialVersionUID = 1L;
	 private List<Order> commandes;
	 private List<Order> commandesBuyed;
	 private Order commande;
	 private Order order;
	 private List< Order> orderSelected;
	 private boolean commandPage;
	 public static final String REQUEST_BUYLISTOVERVIEW = "buyListBean";
	 public static final String REQUEST_BUYBEAN = "buyBean";
	 @EJB
	 private OrderDao    orderDao;
	 
	 @EJB
	 private CustomerDao     customerDao;
	 
	 /**
	 * 
	 */
	public CommandeListBean() {
	
		commandes = new ArrayList<Order>();
		
		commandPage = true;
	}
	/**
	 * @return the commandes
	 */
	public List<Order> getCommandes() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		BuyListBean buyListBean = (BuyListBean) request.getAttribute(REQUEST_BUYLISTOVERVIEW); 
		BuyBean buyBean = (BuyBean) request.getAttribute(REQUEST_BUYBEAN); 
		if(buyBean == null && buyListBean != null){
			commandes = orderDao.getOrdersByBuy(buyListBean.getBuy());
		} else if(buyBean != null && buyListBean == null){
			commandes = orderDao.getOrderByParameters(null, null, null, null, null);
		} else {
		commandes = orderDao.lister();
		}
		if(commandes == null)
			commandes = new ArrayList<Order>();
		return commandes;
	}
	
	/**
	 * @return the commandesBuyed
	 */
	public List<Order> getCommandesBuyed() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		BuyListBean buyListBean = (BuyListBean) request.getAttribute(REQUEST_BUYLISTOVERVIEW); 
		BuyBean buyBean = (BuyBean) request.getAttribute(REQUEST_BUYBEAN); 
		if(buyBean == null && buyListBean != null){
		commandesBuyed = orderDao.getOrdersByBuy(buyListBean.getBuy());
		} else if(buyBean != null && buyListBean == null){
		 commandesBuyed = orderDao.getOrderByParameters(null, null, null, null, null);
		}
		if(commandesBuyed == null)
			commandesBuyed = new ArrayList<Order>();
		return commandesBuyed;
	}
	/**
	 * @param commandesBuyed the commandesBuyed to set
	 */
	public void setCommandesBuyed(List<Order> commandesBuyed) {
		this.commandesBuyed = commandesBuyed;
	}
	/**
	 * @param commandes the commandes to set
	 */
	public void setCommandes(List<Order> commandes) {
		this.commandes = commandes;
	}
	
	public String detail(Order commande){
		this.commande = orderDao.detail(commande);
		
		return "orderOverview";
	}
	
	public String supprimer(Order commande){
		orderDao.supprimer(commande);
		
		return "orderList";
	}
	
	/**
	 * @return the commande
	 */
	public Order getCommande() {
		return commande;
	}
	/**
	 * @param commande the commande to set
	 */
	public void setCommande(Order commande) {
		this.commande = commande;
	}
	/**
	 * @return the commandPage
	 */
	public boolean isCommandPage() {
		return commandPage;
	}
	/**
	 * @param commandPage the commandPage to set
	 */
	public void setCommandPage(boolean commandPage) {
		this.commandPage = commandPage;
	}
	/**
	 * @return the orderSelected
	 */
	public List<Order> getOrderSelected() {
		return orderSelected;
	}
	/**
	 * @param orderSelected the orderSelected to set
	 */
	public void setOrderSelected(List<Order> orderSelected) {
		this.orderSelected = orderSelected;
	}
	
	
	/**
	 * @return the order
	 */
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Order Selected", String.valueOf(((Order) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Order Unselected", String.valueOf(((Order) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	public Order getOrder() {
		// TODO Auto-generated method stub
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
