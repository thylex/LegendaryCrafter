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
@Table(name = "tProfession")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profession.findAll", query = "SELECT p FROM Profession p"),
    @NamedQuery(name = "Profession.findByProfID", query = "SELECT p FROM Profession p WHERE p.profID = :profID"),
    @NamedQuery(name = "Profession.findByProfName", query = "SELECT p FROM Profession p WHERE p.profName = :profName"),
    @NamedQuery(name = "Profession.findByCraftingQuality", query = "SELECT p FROM Profession p WHERE p.craftingQuality = :craftingQuality")})
public class Profession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "profID")
    private Short profID;
    @Column(name = "profName")
    private String profName;
    @Column(name = "craftingQuality")
    private Short craftingQuality;
    @OneToMany(mappedBy="profID", fetch = FetchType.LAZY)
    private List<SkillGroup> skillGroups;

    public Profession() {
    }

    public Profession(Short profID) {
        this.profID = profID;
    }

    public Short getProfID() {
        return profID;
    }

    public void setProfID(Short profID) {
        this.profID = profID;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public Short getCraftingQuality() {
        return craftingQuality;
    }

    public void setCraftingQuality(Short craftingQuality) {
        this.craftingQuality = craftingQuality;
    }

    public List<SkillGroup> getSkillGroups() {
        return skillGroups;
    }

    public void setSkillGroups(List<SkillGroup> skillGroups) {
        this.skillGroups = skillGroups;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profID != null ? profID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profession)) {
            return false;
        }
        Profession other = (Profession) object;
        if ((this.profID == null && other.profID != null) || (this.profID != null && !this.profID.equals(other.profID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.Profession[ profID=" + profID + " ]";
    }
    
}
