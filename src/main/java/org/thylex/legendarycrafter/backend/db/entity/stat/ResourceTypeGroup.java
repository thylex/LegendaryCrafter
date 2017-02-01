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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tResourceTypeGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResourceTypeGroup.findAll", query = "SELECT r FROM ResourceTypeGroup r"),
    @NamedQuery(name = "ResourceTypeGroup.findByTypeGroupID", query = "SELECT r FROM ResourceTypeGroup r WHERE r.typeGroupID = :typeGroupID"),
    @NamedQuery(name = "ResourceTypeGroup.findByResourceType", query = "SELECT r FROM ResourceTypeGroup r WHERE r.resourceType = :resourceType"),
    @NamedQuery(name = "ResourceTypeGroup.findByResourceGroup", query = "SELECT r FROM ResourceTypeGroup r WHERE r.resourceGroup = :resourceGroup")})
public class ResourceTypeGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "typeGroupID")
    private Integer typeGroupID;
    @Column(name = "resourceType")
    private String resourceType;
    @Column(name = "resourceGroup")
    private String resourceGroup;

    public ResourceTypeGroup() {
    }

    public ResourceTypeGroup(Integer typeGroupID) {
        this.typeGroupID = typeGroupID;
    }

    public Integer getTypeGroupID() {
        return typeGroupID;
    }

    public void setTypeGroupID(Integer typeGroupID) {
        this.typeGroupID = typeGroupID;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceGroup() {
        return resourceGroup;
    }

    public void setResourceGroup(String resourceGroup) {
        this.resourceGroup = resourceGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeGroupID != null ? typeGroupID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResourceTypeGroup)) {
            return false;
        }
        ResourceTypeGroup other = (ResourceTypeGroup) object;
        if ((this.typeGroupID == null && other.typeGroupID != null) || (this.typeGroupID != null && !this.typeGroupID.equals(other.typeGroupID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.ResourceTypeGroup[ typeGroupID=" + typeGroupID + " ]";
    }
    
}
