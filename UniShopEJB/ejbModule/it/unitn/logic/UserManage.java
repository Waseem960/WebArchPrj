package it.unitn.logic;
 
import it.unitn.dto.User;
import it.unitn.entities.UserEB;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

/**
 * 
 * @author waseem
 */
@Stateless
@LocalBean
@ManagedBean(name = "UserManage")
public class UserManage {

	@EJB
	private UserFacade userFacade = new UserFacade();

	private User user;

	/*
	 * Create a new User
	 */
	public boolean InsertUser() {
		UserEB userEB = get_EB(user);
		userFacade.create(userEB);
		return true;
	}

	private UserEB get_EB(User user) {
		UserEB userEB = new UserEB();
		userEB.setIduser(user.getIduser());
		userEB.setName(user.getName());
		userEB.setSurname(user.getSurname());
		userEB.setEmail(user.getEmail());
		userEB.setFoto(user.getFoto());
		userEB.setUsername(user.getUsername());
		userEB.setPassword(user.getPassword());
		return userEB;
	}

	private User getDTO_EB(UserEB userEB) {
		User user = new User();
		user.setIduser(userEB.getIduser());
		user.setName(userEB.getName());
		user.setSurname(userEB.getSurname());
		user.setEmail(userEB.getEmail());
		user.setFoto(userEB.getFoto());
		user.setUsername(userEB.getUsername());
		user.setPassword(userEB.getPassword());
		return user;
	}

	public boolean InsertUser(User user) {
		// log.info("START: Inserting new user");
		int iduser = (Integer) lastRecordInsert();
		user.setIduser(iduser + 1);
		UserEB userEB = get_EB(user);
		userFacade.create(userEB);
		// log.info("END: Inserting new user");
		return true;

	}

	public Integer lastRecordInsert() {
		// log.info("ENTER: Get last record insert");
		Integer idUser = userFacade.getLastUserInset();
		// log.info("EXIT: Get last record insert");
		return idUser;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public String printName() {
		// TODO Auto-generated method stub
		System.out.println("print name");
		return null;
	}

	public Boolean doLogin(String username, String password) {
		return userFacade.checkLoging(username, password);

	}

	public Integer getIdUserByUsername(String autore) {
		// TODO Auto-generated method stub
		return userFacade.getIdUserByUsername(autore);
	}

	public User getUserByUsername(String username) {
		return getDTO_EB(userFacade.getUserByUsername(username));

	}

	public void modifyUser(User user) {
		UserEB userEB = get_EB(user);
		userFacade.modifyExixtingUser(userEB);

	}
}