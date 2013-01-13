/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.dto;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author waseem
 */
public class District implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer iddistrict;
    private String name;
    private Collection<Municipal> municipalCollection;
    private Region idregion;

    public District() {
    }

    public District(Integer iddistrict) {
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

    public Collection<Municipal> getMunicipalCollection() {
        return municipalCollection;
    }

    public void setMunicipalCollection(Collection<Municipal> municipalCollection) {
        this.municipalCollection = municipalCollection;
    }

    public Region getIdregion() {
        return idregion;
    }

    public void setIdregion(Region idregion) {
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
        if (!(object instanceof District)) {
            return false;
        }
        District other = (District) object;
        if ((this.iddistrict == null && other.iddistrict != null) || (this.iddistrict != null && !this.iddistrict.equals(other.iddistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.District[ iddistrict=" + iddistrict + " ]";
    }
}
