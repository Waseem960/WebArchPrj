package it.unitn.logic;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author waseem
 */
@Stateful
@ManagedBean(name = "LoginManage")
@SessionScoped
public class LoginManage {
	@EJB
	UserManage userManage = new UserManage();

	private String username;
	private String password;
	private boolean loginStatus;

	public LoginManage() {

	}

	public LoginManage(String userN, String pass) {
		this.username = userN;
		this.password = pass;
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

	/**
	 * @return the loginStatus
	 */
	public boolean isLoginStatus() {
		return loginStatus;
	}

	/**
	 * @param loginStatus
	 *            the loginStatus to set
	 */
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public boolean doLogin(String uName, String pWord) {
		this.username = uName;
		this.password = pWord;
		loginStatus = userManage.doLogin(uName, pWord);
		return loginStatus;
	}

}
