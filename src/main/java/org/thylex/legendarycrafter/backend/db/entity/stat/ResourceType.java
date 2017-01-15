/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.backend.db.entity.stat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Henrik
 */
@Entity
@Table(name = "tResourceType")
public class ResourceType {
    @Id
    private String resourceType;
    private String resourceTypeName;
    private String resourceCategory;
    private String resourceGroup;
    private Integer enterable;
    private Integer maxTypes;
    private Integer CRmin;
    private Integer CRmax;
    private Integer CDmin;
    private Integer CDmax;
    private Integer DRmin;
    private Integer DRmax;
    private Integer FLmin;
    private Integer FLmax;
    private Integer HRmin;
    private Integer HRmax;
    private Integer MAmin;
    private Integer MAmax;
    private Integer PEmin;
    private Integer PEmax;
    private Integer OQmin;
    private Integer OQmax;
    private Integer SRmin;
    private Integer SRmax;
    private Integer UTmin;
    private Integer UTmax;
    private Integer ERmin;
    private Integer ERmax;
    private String containerType;
    private String inventoryType;
    private Integer specificPlanet;

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

    public Integer getEnterable() {
        return enterable;
    }

    public void setEnterable(Integer enterable) {
        this.enterable = enterable;
    }

    public Integer getMaxTypes() {
        return maxTypes;
    }

    public void setMaxTypes(Integer maxTypes) {
        this.maxTypes = maxTypes;
    }

    public Integer getCRmin() {
        return CRmin;
    }

    public void setCRmin(Integer CRmin) {
        this.CRmin = CRmin;
    }

    public Integer getCRmax() {
        return CRmax;
    }

    public void setCRmax(Integer CRmax) {
        this.CRmax = CRmax;
    }

    public Integer getCDmin() {
        return CDmin;
    }

    public void setCDmin(Integer CDmin) {
        this.CDmin = CDmin;
    }

    public Integer getCDmax() {
        return CDmax;
    }

    public void setCDmax(Integer CDmax) {
        this.CDmax = CDmax;
    }

    public Integer getDRmin() {
        return DRmin;
    }

    public void setDRmin(Integer DRmin) {
        this.DRmin = DRmin;
    }

    public Integer getDRmax() {
        return DRmax;
    }

    public void setDRmax(Integer DRmax) {
        this.DRmax = DRmax;
    }

    public Integer getFLmin() {
        return FLmin;
    }

    public void setFLmin(Integer FLmin) {
        this.FLmin = FLmin;
    }

    public Integer getFLmax() {
        return FLmax;
    }

    public void setFLmax(Integer FLmax) {
        this.FLmax = FLmax;
    }

    public Integer getHRmin() {
        return HRmin;
    }

    public void setHRmin(Integer HRmin) {
        this.HRmin = HRmin;
    }

    public Integer getHRmax() {
        return HRmax;
    }

    public void setHRmax(Integer HRmax) {
        this.HRmax = HRmax;
    }

    public Integer getMAmin() {
        return MAmin;
    }

    public void setMAmin(Integer MAmin) {
        this.MAmin = MAmin;
    }

    public Integer getMAmax() {
        return MAmax;
    }

    public void setMAmax(Integer MAmax) {
        this.MAmax = MAmax;
    }

    public Integer getPEmin() {
        return PEmin;
    }

    public void setPEmin(Integer PEmin) {
        this.PEmin = PEmin;
    }

    public Integer getPEmax() {
        return PEmax;
    }

    public void setPEmax(Integer PEmax) {
        this.PEmax = PEmax;
    }

    public Integer getOQmin() {
        return OQmin;
    }

    public void setOQmin(Integer OQmin) {
        this.OQmin = OQmin;
    }

    public Integer getOQmax() {
        return OQmax;
    }

    public void setOQmax(Integer OQmax) {
        this.OQmax = OQmax;
    }

    public Integer getSRmin() {
        return SRmin;
    }

    public void setSRmin(Integer SRmin) {
        this.SRmin = SRmin;
    }

    public Integer getSRmax() {
        return SRmax;
    }

    public void setSRmax(Integer SRmax) {
        this.SRmax = SRmax;
    }

    public Integer getUTmin() {
        return UTmin;
    }

    public void setUTmin(Integer UTmin) {
        this.UTmin = UTmin;
    }

    public Integer getUTmax() {
        return UTmax;
    }

    public void setUTmax(Integer UTmax) {
        this.UTmax = UTmax;
    }

    public Integer getERmin() {
        return ERmin;
    }

    public void setERmin(Integer ERmin) {
        this.ERmin = ERmin;
    }

    public Integer getERmax() {
        return ERmax;
    }

    public void setERmax(Integer ERmax) {
        this.ERmax = ERmax;
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

    public Integer getSpecificPlanet() {
        return specificPlanet;
    }

    public void setSpecificPlanet(Integer specificPlanet) {
        this.specificPlanet = specificPlanet;
    }
    
    
}
