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
@Table(name = "tSkillGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SkillGroup.findAll", query = "SELECT s FROM SkillGroup s"),
    @NamedQuery(name = "SkillGroup.findByProfID", query = "SELECT s FROM SkillGroup s WHERE s.profID = :profID"),
    @NamedQuery(name = "SkillGroup.findBySkillGroup", query = "SELECT s FROM SkillGroup s WHERE s.skillGroup = :skillGroup"),
    @NamedQuery(name = "SkillGroup.findBySkillGroupName", query = "SELECT s FROM SkillGroup s WHERE s.skillGroupName = :skillGroupName")})
public class SkillGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "profID")
    private Short profID;
    @Id
    @Basic(optional = false)
    @Column(name = "skillGroup")
    private String skillGroup;
    @Column(name = "skillGroupName")
    private String skillGroupName;

    public SkillGroup() {
    }

    public SkillGroup(String skillGroup) {
        this.skillGroup = skillGroup;
    }

    public Short getProfID() {
        return profID;
    }

    public void setProfID(Short profID) {
        this.profID = profID;
    }

    public String getSkillGroup() {
        return skillGroup;
    }

    public void setSkillGroup(String skillGroup) {
        this.skillGroup = skillGroup;
    }

    public String getSkillGroupName() {
        return skillGroupName;
    }

    public void setSkillGroupName(String skillGroupName) {
        this.skillGroupName = skillGroupName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillGroup != null ? skillGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkillGroup)) {
            return false;
        }
        SkillGroup other = (SkillGroup) object;
        if ((this.skillGroup == null && other.skillGroup != null) || (this.skillGroup != null && !this.skillGroup.equals(other.skillGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.legendarycrafter.backend.db.entity.stat.SkillGroup[ skillGroup=" + skillGroup + " ]";
    }
    
}
