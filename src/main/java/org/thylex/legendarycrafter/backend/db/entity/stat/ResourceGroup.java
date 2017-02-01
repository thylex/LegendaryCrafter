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
@Table(name = "tResourceGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResourceGroup.findAll", query = "SELECT r FROM ResourceGroup r"),
    @NamedQuery(name = "ResourceGroup.findByResourceGroup", query = "SELECT r FROM ResourceGroup r WHERE r.resourceGroup = :resourceGroup"),
    @NamedQuery(name = "ResourceGroup.findByGroupName", query = "SELECT r FROM ResourceGroup r WHERE r.groupName = :groupName"),
    @NamedQuery(name = "ResourceGroup.findByGroupLevel", query = "SELECT r FROM ResourceGroup r WHERE r.groupLevel = :groupLevel"),
    @NamedQuery(name = "ResourceGroup.findByGroupOrder", query = "SELECT r FROM ResourceGroup r WHERE r.groupOrder = :groupOrder"),
    @NamedQuery(name = "ResourceGroup.findByContainerType", query = "SELECT r FROM ResourceGroup r WHERE r.containerType = :containerType")})
public class ResourceGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "resourceGroup")
    private String resourceGroup;
    @Column(name = "groupName")
    private String groupName;
    @Column(name = "groupLevel")
    private Short groupLevel;
    @Column(name = "groupOrder")
    private Short groupOrder;
    @Column(name = "containerType")
    private String containerType;

    public ResourceGroup() {
    }

    public ResourceGroup(String resourceGroup) {
        this.resourceGroup = resourceGroup;
    }

    public String getResourceGroup() {
        return resourceGroup;
    }

    public void setResourceGroup(String resourceGroup) {
        this.resourceGroup = resourceGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Short getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(Short groupLevel) {
        this.groupLevel = groupLevel;
    }

    public Short getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(Short groupOrder) {
        this.groupOrder = groupOrder;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resourceGroup != null ? resourceGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResourceGroup)) {
            return false;
        }
        ResourceGroup other = (ResourceGroup) object;
        if ((this.resourceGroup == null && other.resourceGroup != null) || (this.resourceGroup != null && !this.resourceGroup.equals(other.resourceGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.ResourceGroup[ resourceGroup=" + resourceGroup + " ]";
    }
    
}
