/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.dto;

import java.io.Serializable;

/**
 *
 * @author waseem
 */
public class Municipal implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idmunicipal;
    private String name;
    private District iddistrict;

    public Municipal() {
    }

    public Municipal(Integer idmunicipal) {
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

    public District getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(District iddistrict) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipal)) {
            return false;
        }
        Municipal other = (Municipal) object;
        if ((this.idmunicipal == null && other.idmunicipal != null) || (this.idmunicipal != null && !this.idmunicipal.equals(other.idmunicipal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Municipal[ idmunicipal=" + idmunicipal + " ]";
    }
}
