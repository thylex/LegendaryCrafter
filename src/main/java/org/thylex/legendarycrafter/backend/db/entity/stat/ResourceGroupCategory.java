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
@Table(name = "tResourceGroupCategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResourceGroupCategory.findAll", query = "SELECT r FROM ResourceGroupCategory r"),
    @NamedQuery(name = "ResourceGroupCategory.findById", query = "SELECT r FROM ResourceGroupCategory r WHERE r.id = :id"),
    @NamedQuery(name = "ResourceGroupCategory.findByResourceGroup", query = "SELECT r FROM ResourceGroupCategory r WHERE r.resourceGroup = :resourceGroup"),
    @NamedQuery(name = "ResourceGroupCategory.findByResourceCategory", query = "SELECT r FROM ResourceGroupCategory r WHERE r.resourceCategory = :resourceCategory")})
public class ResourceGroupCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "resourceGroup")
    private String resourceGroup;
    @Column(name = "resourceCategory")
    private String resourceCategory;

    public ResourceGroupCategory() {
    }

    public ResourceGroupCategory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceGroup() {
        return resourceGroup;
    }

    public void setResourceGroup(String resourceGroup) {
        this.resourceGroup = resourceGroup;
    }

    public String getResourceCategory() {
        return resourceCategory;
    }

    public void setResourceCategory(String resourceCategory) {
        this.resourceCategory = resourceCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResourceGroupCategory)) {
            return false;
        }
        ResourceGroupCategory other = (ResourceGroupCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.ResourceGroupCategory[ id=" + id + " ]";
    }
    
}
