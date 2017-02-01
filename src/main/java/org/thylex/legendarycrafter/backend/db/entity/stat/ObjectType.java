/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.backend.db.entity.stat;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Henrik
 */
@Entity
@Table(name = "tObjectType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjectType.findAll", query = "SELECT o FROM ObjectType o"),
    @NamedQuery(name = "ObjectType.findByObjectType", query = "SELECT o FROM ObjectType o WHERE o.objectType = :objectType"),
    @NamedQuery(name = "ObjectType.findByTypeName", query = "SELECT o FROM ObjectType o WHERE o.typeName = :typeName"),
    @NamedQuery(name = "ObjectType.findByCraftingTab", query = "SELECT o FROM ObjectType o WHERE o.craftingTab = :craftingTab")})
public class ObjectType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "objectType")
    private Integer objectType;
    @Column(name = "typeName")
    private String typeName;
    @Column(name = "craftingTab")
    private Integer craftingTab;

    public ObjectType() {
    }

    public ObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getCraftingTab() {
        return craftingTab;
    }

    public void setCraftingTab(Integer craftingTab) {
        this.craftingTab = craftingTab;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objectType != null ? objectType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjectType)) {
            return false;
        }
        ObjectType other = (ObjectType) object;
        if ((this.objectType == null && other.objectType != null) || (this.objectType != null && !this.objectType.equals(other.objectType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.ObjectType[ objectType=" + objectType + " ]";
    }
    
}
