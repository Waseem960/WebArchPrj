/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn;

import it.unitn.dto.Shop;
import it.unitn.dto.User;
import it.unitn.logic.ShopEBManage;
import it.unitn.logic.UserManage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.io.InputStream;
import java.io.File;

/**
 * 
 * @author waseem
 */
@ManagedBean
@SessionScoped
public class ViewUserB implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String surname;
	private String username;
	private String password;
	private String email;
	private String foto;
	private StreamedContent content;
	private List<Shop> shops;

	private User user;
	private static Logger log = Logger.getLogger(ViewUserB.class.getName());

	@EJB
	UserManage userManage;
	@EJB
	ShopEBManage shopEBManage;

	public ViewUserB() {
		// populateUser();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the content
	 */
	public StreamedContent getContent() {
		if (user.getFoto() != null)
			return new DefaultStreamedContent(new ByteArrayInputStream(
					user.getFoto()));
		return null;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(StreamedContent content) {
		this.content = content;
	}

	public String getFoto() {
		try {
			// convert byte array back to BufferedImage
			InputStream in = new ByteArrayInputStream(user.getFoto());
			BufferedImage bImageFromConvert = ImageIO.read(in);
			//System.out.println("BufferedImage bImageFromConvert = ImageIO.read(in);"+ bImageFromConvert.getType());
			ImageIO.write(bImageFromConvert, "jpg", new File(user.getUsername()
					+ ".jpg"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user.getUsername() + ".jpg";
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		populateUser();
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the shops
	 */
	public List<Shop> getShops() {
		//System.out.println(" shopEBManage.getShopByAuthor(username); "+ username); 
		if(username!=null)
			return shopEBManage.getShopByAuthor(username);		
		return shops;
	}

	/**
	 * @param shops
	 *            the shops to set
	 */
	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	private void populateUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		HttpSession mySession = request.getSession(false);
		username = (String) mySession.getAttribute("username");
		//System.out.println(" username = (String) mySession.getAttribute(username); "+ username);
		if (username != null) {
			user = userManage.getUserByUsername(username);

		}
	}
	
}
