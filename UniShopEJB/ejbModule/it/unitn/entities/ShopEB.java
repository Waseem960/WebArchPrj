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
@Table(name = "shop", catalog = "unishopsh", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = { "idshop" }) })
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "ShopEB.findAll", query = "SELECT s FROM ShopEB s"),
		@NamedQuery(name = "ShopEB.findByIdshop", query = "SELECT s FROM ShopEB s WHERE s.idshop = :idshop"),
		@NamedQuery(name = "ShopEB.findByName", query = "SELECT s FROM ShopEB s WHERE s.name = :name"),
		@NamedQuery(name = "ShopEB.findByDescription", query = "SELECT s FROM ShopEB s WHERE s.description = :description"),
		@NamedQuery(name = "ShopEB.findByAddress", query = "SELECT s FROM ShopEB s WHERE s.address = :address"),
		@NamedQuery(name = "ShopEB.findMaxIdShop", query = "SELECT MAX(s.idshop) FROM ShopEB s"),
		@NamedQuery(name = "ShopEB.findByRegionID", query = "SELECT s FROM ShopEB s, DistrictEB d, RegionEB r,MunicipalEB m  WHERE (r.idregion = :idregion)AND(r.idregion = d.idregion)AND(d.iddistrict=m.iddistrict)AND (s.idcity=m.idmunicipal)"),
		@NamedQuery(name = "ShopEB.findByDistrictID", query = "SELECT s FROM ShopEB s,RegionEB r, DistrictEB d, MunicipalEB m  WHERE (r.idregion = :idregion)AND(r.idregion = d.idregion)AND(d.iddistrict=:iddistrict)AND(d.iddistrict=m.iddistrict)AND(d.iddistrict=m.iddistrict)AND (s.idcity=m.idmunicipal)"),
		@NamedQuery(name = "ShopEB.findByMunicipalID", query = "SELECT s FROM ShopEB s,MunicipalEB m  WHERE (m.idmunicipal= :idmunicipal) AND (s.idcity=m.idmunicipal)"),
		@NamedQuery(name = "ShopEB.findByAuthor", query = "SELECT s FROM ShopEB s WHERE s.author = :author")})

public class ShopEB implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Basic(optional = false)
	@NotNull
	@Column(name = "idshop", nullable = false)
	private Integer idshop;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 1000)
	@Column(name = "name", nullable = false, length = 1000)
	private String name;
	@Size(max = 5000)
	@Column(name = "description", length = 5000)
	private String description;
	@Size(max = 200)
	@Column(name = "address", length = 200)
	private String address;
	@Size(max = 100)
    @Column(name = "author")
    private String author;
	@JoinColumn(name = "idcity", referencedColumnName = "idmunicipal")
	@ManyToOne
	private MunicipalEB idcity;
	@OneToMany(mappedBy = "idshop")
	private List<CommentEB> commentEBList;

	public ShopEB() {
	}

	public ShopEB(Integer idshop) {
		this.idshop = idshop;
	}

	public ShopEB(Integer idshop, String name) {
		this.idshop = idshop;
		this.name = name;
	}

	public Integer getIdshop() {
		return idshop;
	}

	public void setIdshop(Integer idshop) {
		this.idshop = idshop;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
	public MunicipalEB getIdcity() {
		return idcity;
	}

	public void setIdcity(MunicipalEB idcity) {
		this.idcity = idcity;
	}

	@XmlTransient
	public List<CommentEB> getCommentEBList() {
		return commentEBList;
	}

	public void setCommentEBList(List<CommentEB> commentEBList) {
		this.commentEBList = commentEBList;
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
		if (!(object instanceof ShopEB)) {
			return false;
		}
		ShopEB other = (ShopEB) object;
		if ((this.idshop == null && other.idshop != null)
				|| (this.idshop != null && !this.idshop.equals(other.idshop))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "NewPkj.ShopEB[ idshop=" + idshop + " ]";
	}

}
