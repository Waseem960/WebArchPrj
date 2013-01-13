/**
 * 
 */
package it.unitn;

import it.unitn.dto.User;
import it.unitn.logic.UserManage;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * @author waseem
 * 
 */
@ManagedBean
@SessionScoped
public class ModifyUserB implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private User user;

	private String name;
	private String surname;
	private String username;
	private String password;
	private String email;
	private byte[] foto;
	private UploadedFile uploadedFile;

	@EJB
	UserManage userManage;

	public ModifyUserB() {
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
	 * @return the name
	 */
	public String getName() {
		if (user == null)
			populateUser();
		this.name = user.getName();
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
	 * @return the surname
	 */
	public String getSurname() {
		if (user != null)
			surname = user.getSurname();
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
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
		if (user != null)
			password = user.getPassword();
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
	 * @return the email
	 */
	public String getEmail() {
		if (user != null)
			email = user.getEmail();
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the foto
	 */
	public byte[] getFoto() {
		return foto;
	}

	/**
	 * @param foto
	 *            the foto to set
	 */
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public void handleFileUpload(FileUploadEvent event) {
		uploadedFile = event.getFile();
		// String fileName = FilenameUtils.getName(uploadedFile.getFileName());
		byte[] bytes = uploadedFile.getContents();
		setFoto(bytes);
	}

	public String modifyUser() {
		if (foto != null)
			user.setFoto(foto);
		if (user.getName() != name)
			user.setName(name);
		if (user.getSurname() != surname)
			user.setSurname(surname);
		if (user.getPassword() != password)
			user.setPassword(password);
		if (user.getEmail() != email)
			user.setEmail(email);
		
		System.out.println("User password " + password);

		userManage.modifyUser(user);
		return "Profile";
	}

	private void populateUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		HttpSession mySession = request.getSession(false);
		String username = (String) mySession.getAttribute("username");
		System.out.println(" username (username); " + username);
		if (username != null) {
			user = userManage.getUserByUsername(username);

		}
	}
}
