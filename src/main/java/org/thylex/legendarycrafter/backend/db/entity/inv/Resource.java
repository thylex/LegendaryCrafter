/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.backend.db.entity.inv;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Henrik
 */
@Entity
@Table(name = "resources")
public class Resource {

    //ER,CR,CD,DR,FL,HR,MA,PE,OQ,SR,UT,units
    private String name;
    private String resourceType;
    private String resourceTypeName;
    private Integer id;
    private Integer ER;
    private Integer CR;
    private Integer CD;
    private Integer DR;
    private Integer FL;
    private Integer HR;
    private Integer MA;
    private Integer PE;
    private Integer OQ;
    private Integer SR;
    private Integer UT;
    private Integer amount;
    
    public Resource() {
        this.name = "";
        this.resourceType = "";
        this.resourceTypeName = "";
        this.ER = 0;
        this.CR = 0;
        this.CD = 0;
        this.DR = 0;
        this.FL = 0;
        this.HR = 0;
        this.MA = 0;
        this.PE = 0;
        this.OQ = 0;
        this.SR = 0;
        this.UT = 0;
        this.amount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getER() {
        return ER;
    }

    public void setER(Integer ER) {
        this.ER = ER;
    }

    public Integer getCR() {
        return CR;
    }

    public void setCR(Integer CR) {
        this.CR = CR;
    }

    public Integer getCD() {
        return CD;
    }

    public void setCD(Integer CD) {
        this.CD = CD;
    }

    public Integer getDR() {
        return DR;
    }

    public void setDR(Integer DR) {
        this.DR = DR;
    }

    public Integer getFL() {
        return FL;
    }

    public void setFL(Integer FL) {
        this.FL = FL;
    }

    public Integer getHR() {
        return HR;
    }

    public void setHR(Integer HR) {
        this.HR = HR;
    }

    public Integer getMA() {
        return MA;
    }

    public void setMA(Integer MA) {
        this.MA = MA;
    }

    public Integer getPE() {
        return PE;
    }

    public void setPE(Integer PE) {
        this.PE = PE;
    }

    public Integer getOQ() {
        return OQ;
    }

    public void setOQ(Integer OQ) {
        this.OQ = OQ;
    }

    public Integer getSR() {
        return SR;
    }

    public void setSR(Integer SR) {
        this.SR = SR;
    }

    public Integer getUT() {
        return UT;
    }

    public void setUT(Integer UT) {
        this.UT = UT;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    
}
