/**
 * 
 */
package it.unitn;

import it.unitn.dto.Shop;
import it.unitn.logic.DistrictEBManage;
import it.unitn.logic.MunicipalEBManage;
import it.unitn.logic.RegionEBManage;
import it.unitn.logic.ShopEBManage;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author waseem
 * 
 */
@ManagedBean
@SessionScoped
public class ShopB implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ShopB.class.getName());

	// private Integer idshop;
	private String name;
	private String description;
	private String address;
	private Integer idCity;
	private String municipal;

	@EJB
	ShopEBManage shopEBManage;

	public ShopB() {

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the idCity
	 */
	public Integer getIdCity() {
		return idCity;
	}

	/**
	 * @param idCity
	 *            the idCity to set
	 */
	public void setIdCity(Integer idCity) {
		this.idCity = idCity;
	}

	/**
	 * @return the municipal
	 */
	public String getMunicipal() {
		return municipal;
	}

	/**
	 * @param municipal
	 *            the municipal to set
	 */
	public void setMunicipal(String municipal) {
		this.municipal = municipal;
	}

	public String addShop() {

		Shop shop = new Shop();
		shop.setName(name);
		shop.setAddress(address);
		shop.setDescription(description);
		String mun = getMunicipal();
		System.out.println(" mun " + mun);
		idCity = Integer.valueOf(mun);
		shop.setIdCity(idCity);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		HttpSession mySession = request.getSession(false);
		String username = (String) mySession.getAttribute("username");

		shop.setAuthor(username);
		shopEBManage.addShop(shop);
		return "HomePage";

	}
}
