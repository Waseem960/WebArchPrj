/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author waseem
 */
public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idshop;
	private String name;
	private String description;
	private String address;
	private Integer idCity;	
	private Integer numberComments;
	private String author;
	private List<Comment> commentList;
	
	

	public Shop() {
		
	}

	public Shop(Integer idshop) {
		this.idshop = idshop;
	}

	public Shop(String name, Integer idCity, String description, String address) {
		this.name = name;
		this.description = description;
		this.address = address;
		this.idCity = idCity;
	}

	public Integer getIdshop() {
		return idshop;
	}

	public void setIdshop(Integer idshop) {
		this.idshop = idshop;
	}

	public Integer getIdCity() {
		return idCity;
	}

	public void setIdCity(Integer idCity) {
		this.idCity = idCity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the numberComments
	 */
	public Integer getNumberComments() {
		return numberComments;
	}

	/**
	 * @param numberComments the numberComments to set
	 */
	public void setNumberComments(Integer numberComments) {
		this.numberComments = numberComments;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idshop != null ? idshop.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Shop)) {
			return false;
		}
		Shop other = (Shop) object;
		if ((this.idshop == null && other.idshop != null)
				|| (this.idshop != null && !this.idshop.equals(other.idshop))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entities.Shop[ idshop=" + idshop + " ]";
	}

}
