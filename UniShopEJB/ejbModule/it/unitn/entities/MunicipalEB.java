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

/**
 * 
 * @author waseem
 */
/*@Entity
@Table(name = "municipal", catalog = "unishopsh", schema = "")
@XmlRootElement*/
@Entity
@Table(name = "municipal", catalog = "unishopsh", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = { "idmunicipal" }) })
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "MunicipalEB.findAll", query = "SELECT m FROM MunicipalEB m"),
		@NamedQuery(name = "MunicipalEB.findByIdmunicipal", query = "SELECT m FROM MunicipalEB m WHERE m.idmunicipal = :idmunicipal"),
		@NamedQuery(name = "MunicipalEB.findByName", query = "SELECT m FROM MunicipalEB m WHERE m.name = :name"),
		@NamedQuery(name = "MunicipalEB.findLastInsertedID", query = "SELECT MAX(m.idmunicipal) FROM MunicipalEB m") })
public class MunicipalEB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "idmunicipal", nullable = false)
	private Integer idmunicipal;
	@Size(max = 255)
	@Column(name = "name", length = 255)
	private String name;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "idcity")
	private List<ShopEB> shopEBList;
	@JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
	@ManyToOne
	private DistrictEB iddistrict;

	public MunicipalEB() {
	}

	public MunicipalEB(Integer idmunicipal) {
		this.idmunicipal = idmunicipal;
	}

	public Integer getIdmunicipal() {
		return idmunicipal;
	}

	public void setIdmunicipal(Integer idmunicipal) {
		this.idmunicipal = idmunicipal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ShopEB> getShopEBList() {
		return shopEBList;
	}

	public void setShopEBList(List<ShopEB> shopEBList) {
		this.shopEBList = shopEBList;
	}

	public DistrictEB getIddistrict() {
		return iddistrict;
	}

	public void setIddistrict(DistrictEB iddistrict) {
		this.iddistrict = iddistrict;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idmunicipal != null ? idmunicipal.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MunicipalEB)) {
			return false;
		}
		MunicipalEB other = (MunicipalEB) object;
		if ((this.idmunicipal == null && other.idmunicipal != null)
				|| (this.idmunicipal != null && !this.idmunicipal
						.equals(other.idmunicipal))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "NewPkj.MunicipalEB[ idmunicipal=" + idmunicipal + " ]";
	}
}
