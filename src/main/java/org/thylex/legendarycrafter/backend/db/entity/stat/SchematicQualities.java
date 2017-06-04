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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Henrik
 */
@Entity
@Table(name = "tSchematicQualities")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchematicQualities.findAll", query = "SELECT s FROM SchematicQualities s"),
    @NamedQuery(name = "SchematicQualities.findByExpQualityID", query = "SELECT s FROM SchematicQualities s WHERE s.expQualityID = :expQualityID"),
    @NamedQuery(name = "SchematicQualities.findBySchematicID", query = "SELECT s FROM SchematicQualities s WHERE s.schematicID = :schematicID"),
    @NamedQuery(name = "SchematicQualities.findByExpProperty", query = "SELECT s FROM SchematicQualities s WHERE s.expProperty = :expProperty"),
    @NamedQuery(name = "SchematicQualities.findByExpGroup", query = "SELECT s FROM SchematicQualities s WHERE s.expGroup = :expGroup"),
    @NamedQuery(name = "SchematicQualities.findByWeightTotal", query = "SELECT s FROM SchematicQualities s WHERE s.weightTotal = :weightTotal")})
public class SchematicQualities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "expQualityID")
    private Integer expQualityID;
    @Column(name = "schematicID")
    private String schematicID;
    @Column(name = "expProperty")
    private String expProperty;
    @Column(name = "expGroup")
    private String expGroup;
    @Column(name = "weightTotal")
    private Short weightTotal;
    @OneToMany(mappedBy = "expQualityID", fetch = FetchType.LAZY)
    private List<SchematicResWeights> resWeights;

    public SchematicQualities() {
    }

    public SchematicQualities(Integer expQualityID) {
        this.expQualityID = expQualityID;
    }

    public Integer getExpQualityID() {
        return expQualityID;
    }

    public void setExpQualityID(Integer expQualityID) {
        this.expQualityID = expQualityID;
    }

    public List<SchematicResWeights> getResWeights() {
        return resWeights;
    }

    public void setResWeights(List<SchematicResWeights> resWeights) {
        this.resWeights = resWeights;
    }

    public String getSchematicID() {
        return schematicID;
    }

    public void setSchematicID(String schematicID) {
        this.schematicID = schematicID;
    }

    public String getExpProperty() {
        return expProperty;
    }

    public void setExpProperty(String expProperty) {
        this.expProperty = expProperty;
    }

    public String getExpGroup() {
        return expGroup;
    }

    public void setExpGroup(String expGroup) {
        this.expGroup = expGroup;
    }

    public Short getWeightTotal() {
        return weightTotal;
    }

    public void setWeightTotal(Short weightTotal) {
        this.weightTotal = weightTotal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (expQualityID != null ? expQualityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SchematicQualities)) {
            return false;
        }
        SchematicQualities other = (SchematicQualities) object;
        if ((this.expQualityID == null && other.expQualityID != null) || (this.expQualityID != null && !this.expQualityID.equals(other.expQualityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.SchematicQualities[ expQualityID=" + expQualityID + " ]";
    }
    
}
