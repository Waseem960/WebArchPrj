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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
@Entity
@Table(name = "district", catalog = "unishopsh", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"iddistrict"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DistrictEB.findAll", query = "SELECT d FROM DistrictEB d"),
    @NamedQuery(name = "DistrictEB.findByIddistrict", query = "SELECT d FROM DistrictEB d WHERE d.iddistrict = :iddistrict"),
    @NamedQuery(name = "DistrictEB.findByIdregion", query = "SELECT d FROM DistrictEB d WHERE d.idregion = :idregion"),
    @NamedQuery(name = "DistrictEB.findByName", query = "SELECT d FROM DistrictEB d WHERE d.name = :name"),
    @NamedQuery(name = "DistrictEB.findLastInsertedID", query = "SELECT MAX(d.iddistrict) FROM DistrictEB d")})
public class DistrictEB implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "iddistrict", nullable = false)
    private Integer iddistrict;
    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;
    
    @OneToMany(mappedBy = "iddistrict")
    private List<MunicipalEB> municipalEBList;
    @JoinColumn(name = "idregion", referencedColumnName = "idregion", nullable = false)
    @ManyToOne(optional = false)
    private RegionEB idregion;

    public DistrictEB() {
    }

    public DistrictEB(Integer iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Integer getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(Integer iddistrict) {
        this.iddistrict = iddistrict;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<MunicipalEB> getMunicipalEBList() {
        return municipalEBList;
    }

    public void setMunicipalEBList(List<MunicipalEB> municipalEBList) {
        this.municipalEBList = municipalEBList;
    }

    public RegionEB getIdregion() {
        return idregion;
    }

    public void setIdregion(RegionEB idregion) {
        this.idregion = idregion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddistrict != null ? iddistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistrictEB)) {
            return false;
        }
        DistrictEB other = (DistrictEB) object;
        if ((this.iddistrict == null && other.iddistrict != null) || (this.iddistrict != null && !this.iddistrict.equals(other.iddistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NewPkj.DistrictEB[ iddistrict=" + iddistrict + " ]";
    }
    
}
