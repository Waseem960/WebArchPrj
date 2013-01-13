/**
 * 
 */
package it.unitn;

import it.unitn.dto.Comment;
import it.unitn.dto.Shop;
import it.unitn.logic.CommentEBManage;
import it.unitn.logic.DistrictEBManage;
import it.unitn.logic.MunicipalEBManage;
import it.unitn.logic.RegionEBManage;
import it.unitn.logic.ShopEBManage;

import java.util.HashMap;
import java.util.List;
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
public class ViewShopB implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ViewShopB.class.getName());

	private Shop shop;
	private Integer idshop;
	private String city;
	private List<Comment> comments;

	@EJB
	ShopEBManage shopEBManage;
	@EJB
	MunicipalEBManage municipalEBManage;
	@EJB
	CommentEBManage commentEBManage;

	public ViewShopB() {

	}

	/**
	 * @return the shop
	 */
	public Shop getShop() {
		populateShop();
		return shop;
	}

	/**
	 * @param shop
	 *            the shop to set
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		if (shop.getIdCity() != null)
			return municipalEBManage.getMunicipalNameByIdCity(shop.getIdCity());
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		if ((comments == null))
			return commentEBManage.getCommentByIdShop(idshop);

		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	private void populateShop() {

		idshop = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("idshop"));
		System.out
				.println("idshop = Integer.valueOf(FacesContext.getCurrentInstance()"
						+ idshop);
		if (idshop != null) {
			shop = shopEBManage.getShopByIdShop(idshop);

		}
	}
}
