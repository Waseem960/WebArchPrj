package it.unitn.logic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import it.unitn.boundary.AbstractFacade;
import it.unitn.dto.Comment;
import it.unitn.entities.CommentEB;
import it.unitn.entities.ShopEB;
import it.unitn.entities.UserEB;

@Stateless
public class CommentEBFacade extends AbstractFacade<CommentEB> {

	private static Logger log = Logger.getLogger(CommentEBFacade.class
			.getName());

	@PersistenceContext(unitName = "MyUniShop")
	private EntityManager em;

	// @EJB
	// CommentEBManage commentEBManage = new CommentEBManage();

	@EJB
	ShopEBFacade shopEBFacade = new ShopEBFacade();

	@EJB
	LoginManage loginManage = new LoginManage();

	@EJB
	UserFacade userFacade = new UserFacade();

	public CommentEBFacade() {
		// TODO Auto-generated constructor stub
		super(CommentEB.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	public void createNewComment(Comment comment) {
		log.info("START: Inserting a new comment");
		// TODO Auto-generated method stub
		CommentEB commentEB = DTOtoEB(comment);
		em.merge(commentEB);
		em.flush();

		log.info("END: Inserting a new comment");

	}

	private CommentEB DTOtoEB(Comment comment) {
		// TODO Auto-generated method stub

		CommentEB commentEB = new CommentEB();
		log.info("     DATE: " + comment.getDate());
		// commentEB.setDate(new Date());

		DateFormat formatter = new SimpleDateFormat(
				"EEEE, dd/MM/yyyy, HH:mm:ss");
		Date date;
		try {
			date = formatter.parse(comment.getDate());
		} catch (ParseException e) {
			date = new Date();
		}
		commentEB.setDate(date);

		// commentEB.setDate(null);
		commentEB.setIdparent(comment.getIdparent());

		ShopEB shopEB = new ShopEB();
		shopEB = shopEBFacade.getShopById(comment.getIdshop());
		commentEB.setIdshop(shopEB);
		log.info("ShopEB.id " + shopEB.getIdshop());
		UserEB userEB = new UserEB();
		// String userName = loginManage.getUsername();
		userEB = userFacade.getUserByUsername(comment.getUsername());
		log.info("UserEB.id " + userEB.getIduser());
		commentEB.setIduser(userEB);

		commentEB.setUsercomment(comment.getUsercomment());

		int idcomment = getLastCommentInsertedID() + 1;

		// int idcomment = 1;

		commentEB.setIdcomment(idcomment);

		return commentEB;
	}

	public Integer getLastCommentInsertedID() {

		Query query = em.createNamedQuery("CommentEB.findLastInsertedID");
		Integer ris = (Integer) query.getSingleResult();
		if (ris == null)
			return 0;
		return ris;
	}

	/*
	 * public Integer getTotalCommentsByIdShop(Integer idshop) {
	 * log.info("START: Getting total number of comments for a shop"); Query
	 * query = em.createNamedQuery("CommentEB.countNumberOfComments");
	 * log.info("END: Getting total number of comments for a shop"); return
	 * (Integer) query.getSingleResult(); }
	 */

	public List<Comment> getCommentByIdShop(Integer idshop) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		log.info("START: Getting comments by id shop " + idshop);
		List<Comment> lstComments = new ArrayList<Comment>();
		ShopEB shopEB = new ShopEB();
		Query query = em.createNamedQuery("ShopEB.findByIdshop");
		query.setParameter("idshop", idshop);

		shopEB = (ShopEB) query.getSingleResult();

		Query query2 = em.createNamedQuery("CommentEB.findAllCommentByIdShop");
		query2.setParameter("idshop", shopEB);
		List<CommentEB> resultList = query2.getResultList();
		Iterator<CommentEB> it = resultList.iterator();
		CommentEB commentEB = new CommentEB();
		Comment comment = new Comment();
		while (it.hasNext()) {
			commentEB = it.next();
			comment = EBToDTO(commentEB);
			lstComments.add(comment);
		}
		log.info("END: Getting all shops ->size: " + lstComments.size());
		return lstComments;

	}

	private Comment EBToDTO(CommentEB commentEB) {

		// TODO Auto-generated method stub
		Comment comment = new Comment();

		comment.setIdcomment(commentEB.getIdcomment());

		DateFormat formatter = new SimpleDateFormat(
				"EEEE, dd/MM/yyyy, HH:mm:ss");
		String today = formatter.format(commentEB.getDate());
		comment.setDate(today);
		comment.setUsercomment(commentEB.getUsercomment());

		ShopEB shopEB = commentEB.getIdshop();
		comment.setIdshop(shopEB.getIdshop());
		comment.setShopDescription(shopEB.getDescription());
		comment.setShopName(shopEB.getName());
		comment.setShopAddress(shopEB.getAddress());

		UserEB userEB = commentEB.getIduser();
		comment.setUsername(userEB.getUsername());

		return comment;
	}

}
