/**
 * 
 */
package it.unitn;

import java.io.IOException;

import it.unitn.logic.LoginManage;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

/**
 * @author waseem
 * 
 */
@Stateful
@ManagedBean
@SessionScoped
public class LoginB implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	@EJB
	LoginManage loginManage = new LoginManage();

	public LoginB() {

	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public void myLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();
		HttpSession mySession = request.getSession(true);

		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = loginManage.doLogin(username, password);
		if (loggedIn) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Benvenuto",
					username);
			mySession.setAttribute("username", username);

		} else
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Errore di Login", "Credenziali invalide");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("loggedIn", loggedIn);

	}

	public String loginStatus() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		HttpSession mySession = request.getSession(false);
		if (mySession == null || mySession.getAttribute("username") == null) {
			return "Login";
		} else
			return "Logout";
	}

	public String loginCancel() {

		if (loginStatus().equals("Login")) {
			// System.out.println("I'm in loggin");
			return "Login";
		} else {
			// System.out.println("I'm in logout");
			/*
			 * FacesContext facesContext = FacesContext.getCurrentInstance();
			 * HttpServletRequest request = (HttpServletRequest) facesContext
			 * .getExternalContext().getRequest(); HttpSession mySession =
			 * request.getSession(false); mySession.removeAttribute("username");
			 */
			((HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false)).invalidate();
			return "HomePage";
		}
	}

}