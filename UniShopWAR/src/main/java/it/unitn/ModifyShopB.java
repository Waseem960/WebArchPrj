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

import javassist.bytecode.Mnemonic;

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
public class ModifyShopB implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ModifyShopB.class.getName());

	// private Integer idshop;
	private String name;
	private String description;
	private String address;
	private Integer idCity;
	private String municipal;
	private Shop shop;
	private Integer idshop;

	@EJB
	ShopEBManage shopEBManage;

	public ModifyShopB() {

	}

	/**
	 * @return the name
	 */
	public String getName() {
		if (shop == null)
			populateShop();
		name = shop.getName();
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
		if (shop != null)
			description = shop.getDescription();
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
		if (shop != null)
			address = shop.getAddress();
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

	/**
	 * @return the shop
	 */
	public Shop getShop() {
		return shop;
	}

	/**
	 * @param shop
	 *            the shop to set
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String modifiyShop() {

		if (name != shop.getName())
			shop.setName(name);
		if (address != shop.getAddress())
			shop.setAddress(address);
		if (description != shop.getDescription())
			shop.setDescription(description);

		String mun = getMunicipal();
		// System.out.println(" mun " + mun);
		if (mun != "") {
			idCity = Integer.valueOf(mun);
			if (idCity != shop.getIdCity())
				shop.setIdCity(idCity);
		}
		shopEBManage.modifyShop(shop);
		return "HomePage";
	}

	private void populateShop() {
		idshop = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("idshop"));
		if (idshop != null) {
			shop = shopEBManage.getShopByIdShop(idshop);

		}
	}

	public String deleteShop() {
		idshop = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("idshop"));
		if (idshop != null) {
			shopEBManage.deleteShopById(idshop);
		}
		return "Profile";
	}
}
