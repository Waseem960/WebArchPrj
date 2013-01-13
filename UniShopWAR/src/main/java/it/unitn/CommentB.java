/**
 * 
 */
package it.unitn;

import it.unitn.dto.Comment;
import it.unitn.dto.Shop;
import it.unitn.logic.CommentEBManage;
import it.unitn.logic.LoginManage;
import it.unitn.logic.ShopEBManage;
import it.unitn.logic.UserManage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author waseem
 * 
 */
@ManagedBean
@SessionScoped
public class CommentB implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String userComment;
	private String autore;
	private Integer idshop;
	private Shop shop;
	private String shopName;
	private String date;

	private List<Comment> comments = new ArrayList<Comment>();

	@EJB
	LoginManage loginManage;

	@EJB
	CommentEBManage commentEBManage;

	@EJB
	UserManage userManage;

	@EJB
	LoginB loginB;

	@EJB
	ShopEBManage shopEBManage;

	public CommentB() {
		idshop = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("idshop"));
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpSession mySession = myRequest.getSession(true);
		autore = (String) mySession.getAttribute("username");

	}

	/**
	 * @return the userComment
	 */
	public String getUserComment() {
		return userComment;
	}

	/**
	 * @param userComment
	 *            the userComment to set
	 */
	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	/**
	 * @return the autore
	 */
	public String getAutore() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpSession mySession = myRequest.getSession(true);
		autore = (String) mySession.getAttribute("username");
		return autore;
	}

	/**
	 * @param autore
	 *            the autore to set
	 */
	public void setAutore(String autore) {
		this.autore = autore;
	}

	/**
	 * @return the idshop
	 */
	public Integer getIdshop() {
		return idshop;
	}

	/**
	 * @param idshop
	 *            the idshop to set
	 */
	public void setIdshop(Integer idshop) {
		this.idshop = idshop;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat(
				"EEEE, dd/MM/yyyy, HH:mm:ss");
		String today = formatter.format(date);
//		System.out.println("String today = formatter.format(date): " + today);
		return today;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		idshop = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("idshop"));
		//System.out.println(" Integer.valueOf(FacesContext.getCurrentInstance() = getComments: "+ idshop);

		//System.out.println(" Integer.valueOf(FacesContext.getCurrentInstance() = getComments comments1 : "+ comments);
		if (comments.isEmpty()) {
			comments = commentEBManage.getCommentByIdShop(idshop);
			//System.out.println(" Integer.valueOf(FacesContext.getCurrentInstance() = getComments comments2 : "+ comments);
		}
		//System.out.println("  = getComments comments2 size : "+ comments.size());
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String addComment() {
		// Integer idUser = userManage.getIdUserByUsername(this.autore);
		Comment comment = new Comment();
		comment.setDate(this.getDate());
		System.out.println("comment.setDate(this.date): " + comment.getDate());
		comment.setUsercomment(userComment);
		comment.setUsername(autore);
		comment.setIdshop(idshop);
		comment.setIdparent(0);
		commentEBManage.addComment(comment);
		return "HomePage";
	}

	/**
	 * @return the shop
	 */
	public Shop getShop() {
		idshop = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("idshop"));
		shop = shopEBManage.getShopByIdShop(idshop);
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
	 * @return the shopName
	 */
	public String getShopName() {
		idshop = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("idshop"));
		shop = shopEBManage.getShopByIdShop(idshop);
		shopName = shop.getName();
		return shopName;
	}

	/**
	 * @param shopName
	 *            the shopName to set
	 */
	public void setShopName(String shopName) {

		this.shopName = shopName;
	}
}