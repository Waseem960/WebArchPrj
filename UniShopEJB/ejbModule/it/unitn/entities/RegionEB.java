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
@Table(name = "region", catalog = "unishopsh", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = { "idregion" }) })
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "RegionEB.findAll", query = "SELECT r FROM RegionEB r"),
		@NamedQuery(name = "RegionEB.findByIdregion", query = "SELECT r FROM RegionEB r WHERE r.idregion = :idregion"),
		@NamedQuery(name = "RegionEB.findByName", query = "SELECT r FROM RegionEB r WHERE r.name = :name"),
		@NamedQuery(name = "RegionEB.findLastInsertID", query = "SELECT MAX(r.idregion) FROM RegionEB r") })
@SequenceGenerator(name = "seq_region", sequenceName = "SEQ_REGION", allocationSize = 1)
public class RegionEB implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "idregion", nullable = false)
	private Integer idregion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idregion")
	private List<DistrictEB> districtEBList;

	public RegionEB() {
	}

	public RegionEB(Integer idregion) {
		this.idregion = idregion;
	}

	public RegionEB(Integer idregion, String name) {
		this.idregion = idregion;
		this.name = name;
	}

	public Integer getIdregion() {
		return idregion;
	}

	public void setIdregion(Integer idregion) {
		this.idregion = idregion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public List<DistrictEB> getDistrictEBList() {
		return districtEBList;
	}

	public void setDistrictEBList(List<DistrictEB> districtEBList) {
		this.districtEBList = districtEBList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idregion != null ? idregion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof RegionEB)) {
			return false;
		}
		RegionEB other = (RegionEB) object;
		if ((this.idregion == null && other.idregion != null)
				|| (this.idregion != null && !this.idregion
						.equals(other.idregion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RegionEB[ idregion=" + idregion + " ]";
	}
}
