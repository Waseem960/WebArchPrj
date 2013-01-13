/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author waseem
 */
@Entity
@Table(name = "user", catalog = "unishopsh", schema = "")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "UserEB.findAll", query = "SELECT u FROM UserEB u"),
		@NamedQuery(name = "UserEB.findByIduser", query = "SELECT u FROM UserEB u WHERE u.iduser = :iduser"),
		@NamedQuery(name = "UserEB.findByName", query = "SELECT u FROM UserEB u WHERE u.name = :name"),
		@NamedQuery(name = "UserEB.findBySurname", query = "SELECT u FROM UserEB u WHERE u.surname = :surname"),
		@NamedQuery(name = "UserEB.findByUsername", query = "SELECT u FROM UserEB u WHERE u.username = :username"),
		@NamedQuery(name = "UserEB.findByPassword", query = "SELECT u FROM UserEB u WHERE u.password = :password"),
		@NamedQuery(name = "UserEB.findByEmail", query = "SELECT u FROM UserEB u WHERE u.email = :email"),
		@NamedQuery(name = "UserEB.findLogin", query = "SELECT u FROM UserEB u WHERE u.username = :username AND u.password = :password"),
		@NamedQuery(name = "UserEB.findMaxIdUser", query = "SELECT MAX(u.iduser) FROM UserEB u") })
public class UserEB implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "iduser", nullable = false)
	private Integer iduser;
	@Size(max = 45)
	@Column(name = "name", length = 45)
	private String name;
	@Size(max = 45)
	@Column(name = "surname", length = 45)
	private String surname;
	@Size(max = 45)
	@Column(name = "username", length = 45)
	private String username;
	@Size(max = 45)
	@Column(name = "password", length = 45)
	private String password;
	// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	// message="Invalid email")//if the field contains email address consider
	// using this annotation to enforce field validation
	@Size(max = 45)
	@Column(name = "email", length = 45)
	private String email;
	@Lob
	@Column(name = "foto")
	private byte[] foto;
	@OneToMany(mappedBy = "iduser")
	private List<CommentEB> commentList;

	public UserEB() {
	}

	public UserEB(Integer iduser) {
		this.iduser = iduser;
	}

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@XmlTransient
	public List<CommentEB> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentEB> commentList) {
		this.commentList = commentList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (iduser != null ? iduser.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof UserEB)) {
			return false;
		}
		UserEB other = (UserEB) object;
		if ((this.iduser == null && other.iduser != null)
				|| (this.iduser != null && !this.iduser.equals(other.iduser))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entities.User[ iduser=" + iduser + " ]";
	}

}
