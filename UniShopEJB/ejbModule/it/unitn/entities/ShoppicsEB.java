/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author waseem
 */
@Entity
@Table(name = "shoppics", catalog = "unishopsh", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShoppicsEB.findAll", query = "SELECT s FROM ShoppicsEB s"),
    @NamedQuery(name = "ShoppicsEB.findByIdshoppics", query = "SELECT s FROM ShoppicsEB s WHERE s.idshoppics = :idshoppics"),
    @NamedQuery(name = "ShoppicsEB.findByName", query = "SELECT s FROM ShoppicsEB s WHERE s.name = :name"),
    @NamedQuery(name = "ShoppicsEB.findByFormatpic", query = "SELECT s FROM ShoppicsEB s WHERE s.formatpic = :formatpic"),
    @NamedQuery(name = "ShoppicsEB.findByIdshop", query = "SELECT s FROM ShoppicsEB s WHERE s.idshop = :idshop")})
public class ShoppicsEB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idshoppics", nullable = false)
    private Integer idshoppics;
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;
    @Lob
    @Column(name = "pic")
    private byte[] pic;
    @Size(max = 45)
    @Column(name = "formatpic", length = 45)
    private String formatpic;
    @Column(name = "idshop")
    private Integer idshop;

    public ShoppicsEB() {
    }

    public ShoppicsEB(Integer idshoppics) {
        this.idshoppics = idshoppics;
    }

    public Integer getIdshoppics() {
        return idshoppics;
    }

    public void setIdshoppics(Integer idshoppics) {
        this.idshoppics = idshoppics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getFormatpic() {
        return formatpic;
    }

    public void setFormatpic(String formatpic) {
        this.formatpic = formatpic;
    }

    public Integer getIdshop() {
        return idshop;
    }

    public void setIdshop(Integer idshop) {
        this.idshop = idshop;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idshoppics != null ? idshoppics.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShoppicsEB)) {
            return false;
        }
        ShoppicsEB other = (ShoppicsEB) object;
        if ((this.idshoppics == null && other.idshoppics != null) || (this.idshoppics != null && !this.idshoppics.equals(other.idshoppics))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NewPkj.ShoppicsEB[ idshoppics=" + idshoppics + " ]";
    }
    
}
