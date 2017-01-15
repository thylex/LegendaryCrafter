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
@Table(name = "tResourceType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResourceType.findAll", query = "SELECT r FROM ResourceType r"),
    @NamedQuery(name = "ResourceType.findByResourceType", query = "SELECT r FROM ResourceType r WHERE r.resourceType = :resourceType"),
    @NamedQuery(name = "ResourceType.findByResourceTypeName", query = "SELECT r FROM ResourceType r WHERE r.resourceTypeName = :resourceTypeName"),
    @NamedQuery(name = "ResourceType.findByResourceCategory", query = "SELECT r FROM ResourceType r WHERE r.resourceCategory = :resourceCategory"),
    @NamedQuery(name = "ResourceType.findByResourceGroup", query = "SELECT r FROM ResourceType r WHERE r.resourceGroup = :resourceGroup"),
    @NamedQuery(name = "ResourceType.findByEnterable", query = "SELECT r FROM ResourceType r WHERE r.enterable = :enterable"),
    @NamedQuery(name = "ResourceType.findByMaxTypes", query = "SELECT r FROM ResourceType r WHERE r.maxTypes = :maxTypes"),
    @NamedQuery(name = "ResourceType.findByCRmin", query = "SELECT r FROM ResourceType r WHERE r.cRmin = :cRmin"),
    @NamedQuery(name = "ResourceType.findByCRmax", query = "SELECT r FROM ResourceType r WHERE r.cRmax = :cRmax"),
    @NamedQuery(name = "ResourceType.findByCDmin", query = "SELECT r FROM ResourceType r WHERE r.cDmin = :cDmin"),
    @NamedQuery(name = "ResourceType.findByCDmax", query = "SELECT r FROM ResourceType r WHERE r.cDmax = :cDmax"),
    @NamedQuery(name = "ResourceType.findByDRmin", query = "SELECT r FROM ResourceType r WHERE r.dRmin = :dRmin"),
    @NamedQuery(name = "ResourceType.findByDRmax", query = "SELECT r FROM ResourceType r WHERE r.dRmax = :dRmax"),
    @NamedQuery(name = "ResourceType.findByFLmin", query = "SELECT r FROM ResourceType r WHERE r.fLmin = :fLmin"),
    @NamedQuery(name = "ResourceType.findByFLmax", query = "SELECT r FROM ResourceType r WHERE r.fLmax = :fLmax"),
    @NamedQuery(name = "ResourceType.findByHRmin", query = "SELECT r FROM ResourceType r WHERE r.hRmin = :hRmin"),
    @NamedQuery(name = "ResourceType.findByHRmax", query = "SELECT r FROM ResourceType r WHERE r.hRmax = :hRmax"),
    @NamedQuery(name = "ResourceType.findByMAmin", query = "SELECT r FROM ResourceType r WHERE r.mAmin = :mAmin"),
    @NamedQuery(name = "ResourceType.findByMAmax", query = "SELECT r FROM ResourceType r WHERE r.mAmax = :mAmax"),
    @NamedQuery(name = "ResourceType.findByPEmin", query = "SELECT r FROM ResourceType r WHERE r.pEmin = :pEmin"),
    @NamedQuery(name = "ResourceType.findByPEmax", query = "SELECT r FROM ResourceType r WHERE r.pEmax = :pEmax"),
    @NamedQuery(name = "ResourceType.findByOQmin", query = "SELECT r FROM ResourceType r WHERE r.oQmin = :oQmin"),
    @NamedQuery(name = "ResourceType.findByOQmax", query = "SELECT r FROM ResourceType r WHERE r.oQmax = :oQmax"),
    @NamedQuery(name = "ResourceType.findBySRmin", query = "SELECT r FROM ResourceType r WHERE r.sRmin = :sRmin"),
    @NamedQuery(name = "ResourceType.findBySRmax", query = "SELECT r FROM ResourceType r WHERE r.sRmax = :sRmax"),
    @NamedQuery(name = "ResourceType.findByUTmin", query = "SELECT r FROM ResourceType r WHERE r.uTmin = :uTmin"),
    @NamedQuery(name = "ResourceType.findByUTmax", query = "SELECT r FROM ResourceType r WHERE r.uTmax = :uTmax"),
    @NamedQuery(name = "ResourceType.findByERmin", query = "SELECT r FROM ResourceType r WHERE r.eRmin = :eRmin"),
    @NamedQuery(name = "ResourceType.findByERmax", query = "SELECT r FROM ResourceType r WHERE r.eRmax = :eRmax"),
    @NamedQuery(name = "ResourceType.findByContainerType", query = "SELECT r FROM ResourceType r WHERE r.containerType = :containerType"),
    @NamedQuery(name = "ResourceType.findByInventoryType", query = "SELECT r FROM ResourceType r WHERE r.inventoryType = :inventoryType"),
    @NamedQuery(name = "ResourceType.findBySpecificPlanet", query = "SELECT r FROM ResourceType r WHERE r.specificPlanet = :specificPlanet")})
public class ResourceType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "resourceType")
    private String resourceType;
    @Column(name = "resourceTypeName")
    private String resourceTypeName;
    @Column(name = "resourceCategory")
    private String resourceCategory;
    @Column(name = "resourceGroup")
    private String resourceGroup;
    @Column(name = "enterable")
    private Short enterable;
    @Column(name = "maxTypes")
    private Short maxTypes;
    @Column(name = "CRmin")
    private Short cRmin;
    @Column(name = "CRmax")
    private Short cRmax;
    @Column(name = "CDmin")
    private Short cDmin;
    @Column(name = "CDmax")
    private Short cDmax;
    @Column(name = "DRmin")
    private Short dRmin;
    @Column(name = "DRmax")
    private Short dRmax;
    @Column(name = "FLmin")
    private Short fLmin;
    @Column(name = "FLmax")
    private Short fLmax;
    @Column(name = "HRmin")
    private Short hRmin;
    @Column(name = "HRmax")
    private Short hRmax;
    @Column(name = "MAmin")
    private Short mAmin;
    @Column(name = "MAmax")
    private Short mAmax;
    @Column(name = "PEmin")
    private Short pEmin;
    @Column(name = "PEmax")
    private Short pEmax;
    @Column(name = "OQmin")
    private Short oQmin;
    @Column(name = "OQmax")
    private Short oQmax;
    @Column(name = "SRmin")
    private Short sRmin;
    @Column(name = "SRmax")
    private Short sRmax;
    @Column(name = "UTmin")
    private Short uTmin;
    @Column(name = "UTmax")
    private Short uTmax;
    @Column(name = "ERmin")
    private Short eRmin;
    @Column(name = "ERmax")
    private Short eRmax;
    @Column(name = "containerType")
    private String containerType;
    @Column(name = "inventoryType")
    private String inventoryType;
    @Basic(optional = false)
    @Column(name = "specificPlanet")
    private short specificPlanet;

    public ResourceType() {
    }

    public ResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceType(String resourceType, short specificPlanet) {
        this.resourceType = resourceType;
        this.specificPlanet = specificPlanet;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceTypeName() {
        return resourceTypeName;
    }

    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }

    public String getResourceCategory() {
        return resourceCategory;
    }

    public void setResourceCategory(String resourceCategory) {
        this.resourceCategory = resourceCategory;
    }

    public String getResourceGroup() {
        return resourceGroup;
    }

    public void setResourceGroup(String resourceGroup) {
        this.resourceGroup = resourceGroup;
    }

    public Short getEnterable() {
        return enterable;
    }

    public void setEnterable(Short enterable) {
        this.enterable = enterable;
    }

    public Short getMaxTypes() {
        return maxTypes;
    }

    public void setMaxTypes(Short maxTypes) {
        this.maxTypes = maxTypes;
    }

    public Short getCRmin() {
        return cRmin;
    }

    public void setCRmin(Short cRmin) {
        this.cRmin = cRmin;
    }

    public Short getCRmax() {
        return cRmax;
    }

    public void setCRmax(Short cRmax) {
        this.cRmax = cRmax;
    }

    public Short getCDmin() {
        return cDmin;
    }

    public void setCDmin(Short cDmin) {
        this.cDmin = cDmin;
    }

    public Short getCDmax() {
        return cDmax;
    }

    public void setCDmax(Short cDmax) {
        this.cDmax = cDmax;
    }

    public Short getDRmin() {
        return dRmin;
    }

    public void setDRmin(Short dRmin) {
        this.dRmin = dRmin;
    }

    public Short getDRmax() {
        return dRmax;
    }

    public void setDRmax(Short dRmax) {
        this.dRmax = dRmax;
    }

    public Short getFLmin() {
        return fLmin;
    }

    public void setFLmin(Short fLmin) {
        this.fLmin = fLmin;
    }

    public Short getFLmax() {
        return fLmax;
    }

    public void setFLmax(Short fLmax) {
        this.fLmax = fLmax;
    }

    public Short getHRmin() {
        return hRmin;
    }

    public void setHRmin(Short hRmin) {
        this.hRmin = hRmin;
    }

    public Short getHRmax() {
        return hRmax;
    }

    public void setHRmax(Short hRmax) {
        this.hRmax = hRmax;
    }

    public Short getMAmin() {
        return mAmin;
    }

    public void setMAmin(Short mAmin) {
        this.mAmin = mAmin;
    }

    public Short getMAmax() {
        return mAmax;
    }

    public void setMAmax(Short mAmax) {
        this.mAmax = mAmax;
    }

    public Short getPEmin() {
        return pEmin;
    }

    public void setPEmin(Short pEmin) {
        this.pEmin = pEmin;
    }

    public Short getPEmax() {
        return pEmax;
    }

    public void setPEmax(Short pEmax) {
        this.pEmax = pEmax;
    }

    public Short getOQmin() {
        return oQmin;
    }

    public void setOQmin(Short oQmin) {
        this.oQmin = oQmin;
    }

    public Short getOQmax() {
        return oQmax;
    }

    public void setOQmax(Short oQmax) {
        this.oQmax = oQmax;
    }

    public Short getSRmin() {
        return sRmin;
    }

    public void setSRmin(Short sRmin) {
        this.sRmin = sRmin;
    }

    public Short getSRmax() {
        return sRmax;
    }

    public void setSRmax(Short sRmax) {
        this.sRmax = sRmax;
    }

    public Short getUTmin() {
        return uTmin;
    }

    public void setUTmin(Short uTmin) {
        this.uTmin = uTmin;
    }

    public Short getUTmax() {
        return uTmax;
    }

    public void setUTmax(Short uTmax) {
        this.uTmax = uTmax;
    }

    public Short getERmin() {
        return eRmin;
    }

    public void setERmin(Short eRmin) {
        this.eRmin = eRmin;
    }

    public Short getERmax() {
        return eRmax;
    }

    public void setERmax(Short eRmax) {
        this.eRmax = eRmax;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public short getSpecificPlanet() {
        return specificPlanet;
    }

    public void setSpecificPlanet(short specificPlanet) {
        this.specificPlanet = specificPlanet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resourceType != null ? resourceType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResourceType)) {
            return false;
        }
        ResourceType other = (ResourceType) object;
        if ((this.resourceType == null && other.resourceType != null) || (this.resourceType != null && !this.resourceType.equals(other.resourceType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.ResourceType[ resourceType=" + resourceType + " ]";
    }
    
}
