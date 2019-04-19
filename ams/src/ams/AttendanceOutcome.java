/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
@Entity
@Table(name = "ATTENDANCE_OUTCOME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AttendanceOutcome.findAll", query = "SELECT a FROM AttendanceOutcome a")
    , @NamedQuery(name = "AttendanceOutcome.findById", query = "SELECT a FROM AttendanceOutcome a WHERE a.id = :id")
    , @NamedQuery(name = "AttendanceOutcome.findByOutcomeText", query = "SELECT a FROM AttendanceOutcome a WHERE a.outcomeText = :outcomeText")})
public class AttendanceOutcome implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "OUTCOME_TEXT")
    private String outcomeText;

    public AttendanceOutcome() {
    }

    public AttendanceOutcome(Integer id) {
        this.id = id;
    }

    public AttendanceOutcome(Integer id, String outcomeText) {
        this.id = id;
        this.outcomeText = outcomeText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutcomeText() {
        return outcomeText;
    }

    public void setOutcomeText(String outcomeText) {
        this.outcomeText = outcomeText;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttendanceOutcome)) {
            return false;
        }
        AttendanceOutcome other = (AttendanceOutcome) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ams.AttendanceOutcome[ id=" + id + " ]";
    }
    
}
