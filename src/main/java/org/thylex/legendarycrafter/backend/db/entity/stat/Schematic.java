/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.backend.db.entity.stat;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Henrik
 */
@Entity
@Table(name = "tSchematic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schematic.findAll", query = "SELECT s FROM Schematic s"),
    @NamedQuery(name = "Schematic.findBySchematicID", query = "SELECT s FROM Schematic s WHERE s.schematicID = :schematicID"),
    @NamedQuery(name = "Schematic.findBySchematicName", query = "SELECT s FROM Schematic s WHERE s.schematicName = :schematicName"),
    @NamedQuery(name = "Schematic.findByCraftingTab", query = "SELECT s FROM Schematic s WHERE s.craftingTab = :craftingTab"),
    @NamedQuery(name = "Schematic.findBySkillGroup", query = "SELECT s FROM Schematic s WHERE s.skillGroup = :skillGroup"),
    @NamedQuery(name = "Schematic.findByObjectType", query = "SELECT s FROM Schematic s WHERE s.objectType = :objectType"),
    @NamedQuery(name = "Schematic.findByComplexity", query = "SELECT s FROM Schematic s WHERE s.complexity = :complexity"),
    @NamedQuery(name = "Schematic.findByObjectSize", query = "SELECT s FROM Schematic s WHERE s.objectSize = :objectSize"),
    @NamedQuery(name = "Schematic.findByXpType", query = "SELECT s FROM Schematic s WHERE s.xpType = :xpType"),
    @NamedQuery(name = "Schematic.findByXpAmount", query = "SELECT s FROM Schematic s WHERE s.xpAmount = :xpAmount"),
    @NamedQuery(name = "Schematic.findByObjectPath", query = "SELECT s FROM Schematic s WHERE s.objectPath = :objectPath"),
    @NamedQuery(name = "Schematic.findByObjectGroup", query = "SELECT s FROM Schematic s WHERE s.objectGroup = :objectGroup")})
public class Schematic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "schematicID")
    private String schematicID;
    @Column(name = "schematicName")
    private String schematicName;
    @Column(name = "craftingTab")
    private Integer craftingTab;
    @Column(name = "skillGroup")
    private String skillGroup;
    @Column(name = "objectType")
    private Integer objectType;
    @Column(name = "complexity")
    private Short complexity;
    @Column(name = "objectSize")
    private Short objectSize;
    @Column(name = "xpType")
    private String xpType;
    @Column(name = "xpAmount")
    private Short xpAmount;
    @Column(name = "objectPath")
    private String objectPath;
    @Column(name = "objectGroup")
    private String objectGroup;
    @OneToMany(mappedBy="schematicID", fetch = FetchType.LAZY)
    @Basic(optional = true)
    private List<SchematicIngredients> Ingredients;
    @OneToMany(mappedBy = "schematicID", fetch = FetchType.LAZY)
    @Basic(optional = true)
    private List<SchematicQualities> schematicQualities;

    public Schematic() {
    }

    public Schematic(String schematicID) {
        this.schematicID = schematicID;
    }

    public String getSchematicID() {
        return schematicID;
    }

    public void setSchematicID(String schematicID) {
        this.schematicID = schematicID;
    }

    public List<SchematicIngredients> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<SchematicIngredients> Ingredients) {
        this.Ingredients = Ingredients;
    }

    public List<SchematicQualities> getSchematicQualities() {
        return schematicQualities;
    }

    public void setSchematicQualities(List<SchematicQualities> schematicQualities) {
        this.schematicQualities = schematicQualities;
    }

    public String getSchematicName() {
        return schematicName;
    }

    public void setSchematicName(String schematicName) {
        this.schematicName = schematicName;
    }

    public Integer getCraftingTab() {
        return craftingTab;
    }

    public void setCraftingTab(Integer craftingTab) {
        this.craftingTab = craftingTab;
    }

    public String getSkillGroup() {
        return skillGroup;
    }

    public void setSkillGroup(String skillGroup) {
        this.skillGroup = skillGroup;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Short getComplexity() {
        return complexity;
    }

    public void setComplexity(Short complexity) {
        this.complexity = complexity;
    }

    public Short getObjectSize() {
        return objectSize;
    }

    public void setObjectSize(Short objectSize) {
        this.objectSize = objectSize;
    }

    public String getXpType() {
        return xpType;
    }

    public void setXpType(String xpType) {
        this.xpType = xpType;
    }

    public Short getXpAmount() {
        return xpAmount;
    }

    public void setXpAmount(Short xpAmount) {
        this.xpAmount = xpAmount;
    }

    public String getObjectPath() {
        return objectPath;
    }

    public void setObjectPath(String objectPath) {
        this.objectPath = objectPath;
    }

    public String getObjectGroup() {
        return objectGroup;
    }

    public void setObjectGroup(String objectGroup) {
        this.objectGroup = objectGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schematicID != null ? schematicID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schematic)) {
            return false;
        }
        Schematic other = (Schematic) object;
        if ((this.schematicID == null && other.schematicID != null) || (this.schematicID != null && !this.schematicID.equals(other.schematicID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.Schematic[ schematicID=" + schematicID + " ]";
    }
    
}
