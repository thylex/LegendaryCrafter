/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.backend.db.entity.stat;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Henrik
 */
@Embeddable
public class SchematicIngredientsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "schematicID")
    private String schematicID;
    @Basic(optional = false)
    @Column(name = "ingredientName")
    private String ingredientName;

    public SchematicIngredientsPK() {
    }

    public SchematicIngredientsPK(String schematicID, String ingredientName) {
        this.schematicID = schematicID;
        this.ingredientName = ingredientName;
    }

    public String getSchematicID() {
        return schematicID;
    }

    public void setSchematicID(String schematicID) {
        this.schematicID = schematicID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schematicID != null ? schematicID.hashCode() : 0);
        hash += (ingredientName != null ? ingredientName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SchematicIngredientsPK)) {
            return false;
        }
        SchematicIngredientsPK other = (SchematicIngredientsPK) object;
        if ((this.schematicID == null && other.schematicID != null) || (this.schematicID != null && !this.schematicID.equals(other.schematicID))) {
            return false;
        }
        if ((this.ingredientName == null && other.ingredientName != null) || (this.ingredientName != null && !this.ingredientName.equals(other.ingredientName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.SchematicIngredientsPK[ schematicID=" + schematicID + ", ingredientName=" + ingredientName + " ]";
    }
    
}
