package it.unitn.logic;

import java.util.List;

import it.unitn.dto.Comment;
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
@ManagedBean(name = "CommentEBManage")
public class CommentEBManage {

	@EJB
	private CommentEBFacade commentEBFacade = new CommentEBFacade();

	public void addComment(Comment comment) {
		commentEBFacade.createNewComment(comment);

	}

	public Integer getlastIdComment() {
		return commentEBFacade.getLastCommentInsertedID();
	}
	
	public List <Comment> getCommentByIdShop(Integer idshop){
		return commentEBFacade.getCommentByIdShop(idshop);
	}
/*
	public Integer getTotalComments(Integer idshop) {
		// TODO Auto-generated method stub
		return commentEBFacade.getTotalCommentsByIdShop(idshop);
	}*/
}
