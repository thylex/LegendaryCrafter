/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.backend.db.entity.stat;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "SchematicIngredients.findBySchematicID", query = "SELECT s FROM SchematicIngredients s WHERE s.schematicIngredientsPK.schematicID = :schematicID"),
    @NamedQuery(name = "SchematicIngredients.findByIngredientName", query = "SELECT s FROM SchematicIngredients s WHERE s.schematicIngredientsPK.ingredientName = :ingredientName"),
    @NamedQuery(name = "SchematicIngredients.findByIngredientType", query = "SELECT s FROM SchematicIngredients s WHERE s.ingredientType = :ingredientType"),
    @NamedQuery(name = "SchematicIngredients.findByIngredientObject", query = "SELECT s FROM SchematicIngredients s WHERE s.ingredientObject = :ingredientObject"),
    @NamedQuery(name = "SchematicIngredients.findByIngredientQuantity", query = "SELECT s FROM SchematicIngredients s WHERE s.ingredientQuantity = :ingredientQuantity"),
    @NamedQuery(name = "SchematicIngredients.findByIngredientContribution", query = "SELECT s FROM SchematicIngredients s WHERE s.ingredientContribution = :ingredientContribution")})
public class SchematicIngredients implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SchematicIngredientsPK schematicIngredientsPK;
    @Column(name = "ingredientType")
    private Short ingredientType;
    @Column(name = "ingredientObject")
    private String ingredientObject;
    @Column(name = "ingredientQuantity")
    private Short ingredientQuantity;
    @Column(name = "ingredientContribution")
    private Short ingredientContribution;

    public SchematicIngredients() {
    }

    public SchematicIngredients(SchematicIngredientsPK schematicIngredientsPK) {
        this.schematicIngredientsPK = schematicIngredientsPK;
    }

    public SchematicIngredients(String schematicID, String ingredientName) {
        this.schematicIngredientsPK = new SchematicIngredientsPK(schematicID, ingredientName);
    }

    public SchematicIngredientsPK getSchematicIngredientsPK() {
        return schematicIngredientsPK;
    }

    public void setSchematicIngredientsPK(SchematicIngredientsPK schematicIngredientsPK) {
        this.schematicIngredientsPK = schematicIngredientsPK;
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
        hash += (schematicIngredientsPK != null ? schematicIngredientsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SchematicIngredients)) {
            return false;
        }
        SchematicIngredients other = (SchematicIngredients) object;
        if ((this.schematicIngredientsPK == null && other.schematicIngredientsPK != null) || (this.schematicIngredientsPK != null && !this.schematicIngredientsPK.equals(other.schematicIngredientsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.SchematicIngredients[ schematicIngredientsPK=" + schematicIngredientsPK + " ]";
    }
    
}
