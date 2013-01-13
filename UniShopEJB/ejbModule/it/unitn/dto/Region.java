package it.unitn.dto;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author waseem
 */
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idregion;
    private String name;
    private Collection<District> districtCollection;

    public Region() {
    }

    public Region(Integer idregion) {
        this.idregion = idregion;
    }

    public Region(Integer idregion, String name) {
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

    public Collection<District> getDistrictCollection() {
        return districtCollection;
    }

    public void setDistrictCollection(Collection<District> districtCollection) {
        this.districtCollection = districtCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregion != null ? idregion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.idregion == null && other.idregion != null) || (this.idregion != null && !this.idregion.equals(other.idregion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Region[ idregion=" + idregion + " ]";
    }
}
