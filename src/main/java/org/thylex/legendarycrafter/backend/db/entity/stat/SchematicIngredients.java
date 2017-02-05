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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Henrik
 */
@Entity
@Table(name = "tSchematicIngredients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchematicIngredients.findAll", query = "SELECT s FROM SchematicIngredients s"),
    @NamedQuery(name = "SchematicIngredients.findByIngredientID", query = "SELECT s FROM SchematicIngredients s WHERE s.ingredientID = :ingredientID"),
    @NamedQuery(name = "SchematicIngredients.findBySchematicID", query = "SELECT s FROM SchematicIngredients s WHERE s.schematicID = :schematicID"),
    @NamedQuery(name = "SchematicIngredients.findByIngredientName", query = "SELECT s FROM SchematicIngredients s WHERE s.ingredientName = :ingredientName"),
    @NamedQuery(name = "SchematicIngredients.findByIngredientType", query = "SELECT s FROM SchematicIngredients s WHERE s.ingredientType = :ingredientType"),
    @NamedQuery(name = "SchematicIngredients.findByIngredientObject", query = "SELECT s FROM SchematicIngredients s WHERE s.ingredientObject = :ingredientObject"),
    @NamedQuery(name = "SchematicIngredients.findByIngredientQuantity", query = "SELECT s FROM SchematicIngredients s WHERE s.ingredientQuantity = :ingredientQuantity"),
    @NamedQuery(name = "SchematicIngredients.findByIngredientContribution", query = "SELECT s FROM SchematicIngredients s WHERE s.ingredientContribution = :ingredientContribution")})
public class SchematicIngredients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ingredientID")
    private Integer ingredientID;
    @Basic(optional = false)
    @Column(name = "schematicID")
    private String schematicID;
    @Basic(optional = false)
    @Column(name = "ingredientName")
    private String ingredientName;
    @Column(name = "ingredientType")
    private Short ingredientType;
    @Column(name = "ingredientObject")
    private String ingredientObject;
    @Column(name = "ingredientQuantity")
    private Short ingredientQuantity;
    @Column(name = "ingredientContribution")
    private Short ingredientContribution;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schematicID", insertable = false, updatable = false)
    private Schematic schematic;

    public SchematicIngredients() {
    }

    public SchematicIngredients(Integer ingredientID) {
        this.ingredientID = ingredientID;
    }

    public SchematicIngredients(Integer ingredientID, String schematicID, String ingredientName) {
        this.ingredientID = ingredientID;
        this.schematicID = schematicID;
        this.ingredientName = ingredientName;
    }

    public Integer getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(Integer ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getSchematicID() {
        return schematicID;
    }

    public void setSchematicID(String schematicID) {
        this.schematicID = schematicID;
    }

    public Schematic getSchematic() {
        return schematic;
    }

    public void setSchematic(Schematic schematic) {
        this.schematic = schematic;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Short getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(Short ingredientType) {
        this.ingredientType = ingredientType;
    }

    public String getIngredientObject() {
        return ingredientObject;
    }

    public void setIngredientObject(String ingredientObject) {
        this.ingredientObject = ingredientObject;
    }

    public Short getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(Short ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public Short getIngredientContribution() {
        return ingredientContribution;
    }

    public void setIngredientContribution(Short ingredientContribution) {
        this.ingredientContribution = ingredientContribution;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingredientID != null ? ingredientID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SchematicIngredients)) {
            return false;
        }
        SchematicIngredients other = (SchematicIngredients) object;
        if ((this.ingredientID == null && other.ingredientID != null) || (this.ingredientID != null && !this.ingredientID.equals(other.ingredientID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.SchematicIngredients[ ingredientID=" + ingredientID + " ]";
    }
    
}
