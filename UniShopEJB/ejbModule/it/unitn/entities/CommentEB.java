/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author waseem
 */
@Entity
@Table(name = "comment", catalog = "unishopsh", schema = "")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "CommentEB.findAll", query = "SELECT c FROM CommentEB c"),
		@NamedQuery(name = "CommentEB.findByIdcomment", query = "SELECT c FROM CommentEB c WHERE c.idcomment = :idcomment"),
		@NamedQuery(name = "CommentEB.findByUsercomment", query = "SELECT c FROM CommentEB c WHERE c.usercomment = :usercomment"),
		@NamedQuery(name = "CommentEB.findByIdparent", query = "SELECT c FROM CommentEB c WHERE c.idparent = :idparent"),
		@NamedQuery(name = "CommentEB.findByDate", query = "SELECT c FROM CommentEB c WHERE c.date = :date"),
		@NamedQuery(name = "CommentEB.findLastInsertedID", query = "SELECT MAX(c.idcomment) FROM CommentEB c"),
		@NamedQuery(name = "CommentEB.countNumberOfComments", query = "SELECT COUNT(c) FROM  CommentEB c WHERE c.idshop = :idshop"),
		@NamedQuery(name = "CommentEB.findAllCommentByIdShop", query = "SELECT c FROM  CommentEB c WHERE c.idshop = :idshop")})
public class CommentEB implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "idcomment", nullable = false)
	private Integer idcomment;
	@Size(max = 1000)
	@Column(name = "usercomment", length = 1000)
	private String usercomment;
	@Column(name = "idparent")
	private Integer idparent;
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@JoinColumn(name = "iduser", referencedColumnName = "iduser")
	@ManyToOne
	private UserEB iduser;
	@JoinColumn(name = "idshop", referencedColumnName = "idshop")
	@ManyToOne
	private ShopEB idshop;

	public CommentEB() {
	}

	public CommentEB(Integer idcomment) {
		this.idcomment = idcomment;
	}

	public Integer getIdcomment() {
		return idcomment;
	}

	public void setIdcomment(Integer idcomment) {
		this.idcomment = idcomment;
	}

	public String getUsercomment() {
		return usercomment;
	}

	public void setUsercomment(String usercomment) {
		this.usercomment = usercomment;
	}

	public Integer getIdparent() {
		return idparent;
	}

	public void setIdparent(Integer idparent) {
		this.idparent = idparent;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserEB getIduser() {
		return iduser;
	}

	public void setIduser(UserEB iduser) {
		this.iduser = iduser;
	}

	public ShopEB getIdshop() {
		return idshop;
	}

	public void setIdshop(ShopEB idshop) {
		this.idshop = idshop;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idcomment != null ? idcomment.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CommentEB)) {
			return false;
		}
		CommentEB other = (CommentEB) object;
		if ((this.idcomment == null && other.idcomment != null)
				|| (this.idcomment != null && !this.idcomment
						.equals(other.idcomment))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "NewPkj.CommentEB[ idcomment=" + idcomment + " ]";
	}

}
