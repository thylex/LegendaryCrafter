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
@Table(name = "tSchematicResWeights")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchematicResWeights.findAll", query = "SELECT s FROM SchematicResWeights s"),
    @NamedQuery(name = "SchematicResWeights.findByResWeightID", query = "SELECT s FROM SchematicResWeights s WHERE s.resWeightID = :resWeightID"),
    @NamedQuery(name = "SchematicResWeights.findByExpQualityID", query = "SELECT s FROM SchematicResWeights s WHERE s.expQualityID = :expQualityID"),
    @NamedQuery(name = "SchematicResWeights.findByStatName", query = "SELECT s FROM SchematicResWeights s WHERE s.statName = :statName"),
    @NamedQuery(name = "SchematicResWeights.findByStatWeight", query = "SELECT s FROM SchematicResWeights s WHERE s.statWeight = :statWeight")})
public class SchematicResWeights implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "resWeightID")
    private Integer resWeightID;
    @Column(name = "expQualityID")
    private Integer expQualityID;
    @Column(name = "statName")
    private String statName;
    @Column(name = "statWeight")
    private Short statWeight;

    public SchematicResWeights() {
    }

    public SchematicResWeights(Integer resWeightID) {
        this.resWeightID = resWeightID;
    }

    public Integer getResWeightID() {
        return resWeightID;
    }

    public void setResWeightID(Integer resWeightID) {
        this.resWeightID = resWeightID;
    }

    public Integer getExpQualityID() {
        return expQualityID;
    }

    public void setExpQualityID(Integer expQualityID) {
        this.expQualityID = expQualityID;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public Short getStatWeight() {
        return statWeight;
    }

    public void setStatWeight(Short statWeight) {
        this.statWeight = statWeight;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resWeightID != null ? resWeightID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SchematicResWeights)) {
            return false;
        }
        SchematicResWeights other = (SchematicResWeights) object;
        if ((this.resWeightID == null && other.resWeightID != null) || (this.resWeightID != null && !this.resWeightID.equals(other.resWeightID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.SchematicResWeights[ resWeightID=" + resWeightID + " ]";
    }
    
}
