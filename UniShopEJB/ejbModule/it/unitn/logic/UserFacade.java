/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.logic;
 
import java.util.logging.Logger;
import it.unitn.boundary.AbstractFacade;
import it.unitn.entities.UserEB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author waseem
 */
@Stateless
public class UserFacade extends AbstractFacade<UserEB> {

	@PersistenceContext(unitName = "MyUniShop")
	private EntityManager em;
	private static Logger log = Logger.getLogger(UserFacade.class.getName());

	@Override
	protected EntityManager getEntityManager() {

		return em;
	}

	public UserFacade() {
		super(UserEB.class);
	}

	public void create(UserEB userEB) {
		log.info("START: Inserting new user");
		// TODO Auto-generated method stub
		em.persist(userEB);
		log.info("END: Inserting new user");

	}

	public Integer getLastUserInset() {
		// TODO Auto-generated method stub
		log.info("START: Last user inserted");
		Query query = em.createNamedQuery("UserEB.findMaxIdUser");
		log.info("END: Last user inserted");
		return (Integer) query.getSingleResult();

	}

	public boolean checkLoging(String username, String password) {
		try {
			log.info("START: Check login");
			log.info("Username: " + username);
			log.info("password: " + password);

			Query query = em.createNamedQuery("UserEB.findLogin");
			query.setParameter("username", username);
			query.setParameter("password", password);
			log.info("password: " + password);
			UserEB userEB = new UserEB();

			userEB = (UserEB) query.getSingleResult();
			if (userEB != null) {
				log.info("END: Login success");
				return true;
			} else {
				log.info("END: Login fail");
				return false;
			}
		} catch (Exception e) {
			log.info("Login fail.");
			return false;
		}

	}

	public UserEB getUserByUsername(String username) {

		log.info("START: Getting User by username");

		Query query = em.createNamedQuery("UserEB.findByUsername");
		query.setParameter("username", username);
		UserEB userEB = new UserEB();
		userEB = (UserEB) query.getSingleResult();

		log.info("END: Getting User by username");
		return userEB;
	}

	public Integer getIdUserByUsername(String autore) {

		log.info("START: Getting User by username");		
		Query query = em.createNamedQuery("UserEB.findByUsername");
		query.setParameter("username", autore);
		UserEB userEB = new UserEB();
		userEB = (UserEB) query.getSingleResult();
		log.info("END: Getting User by username");
		return userEB.getIduser();
	}

	public void modifyExixtingUser(UserEB userEB) {
		log.info("START: Modifying existing user");
		// TODO Auto-generated method stub		
		UserEB userEB2=em.find(UserEB.class, userEB.getIduser());
		if(userEB2!=null)
		userEB2.setEmail(userEB.getEmail());
		userEB2.setPassword(userEB.getPassword());
		userEB2.setFoto(userEB.getFoto());
		em.merge(userEB2);
		em.flush();
		//em.persist(userEB);
		log.info("END: Modifying existing user");
			
	}

	
	
}
