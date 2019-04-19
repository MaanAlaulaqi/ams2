/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * This class was automatically created by the NetBeans IDE, however I played
 * around with the code to use it more functionally.
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
@Entity
@Table(name = "STUDENT", catalog = "", schema = "AMS")
@NamedQueries({
    @NamedQuery(name = "Student_1.findAll", query = "SELECT s FROM Student_1 s")
    , @NamedQuery(name = "Student_1.findById", query = "SELECT s FROM Student_1 s WHERE s.id = :id")
    , @NamedQuery(name = "Student_1.findByFirstName", query = "SELECT s FROM Student_1 s WHERE s.firstName = :firstName")
    , @NamedQuery(name = "Student_1.findByLastName", query = "SELECT s FROM Student_1 s WHERE s.lastName = :lastName")
    , @NamedQuery(name = "Student_1.findByBirthDate", query = "SELECT s FROM Student_1 s WHERE s.birthDate = :birthDate")
    , @NamedQuery(name = "Student_1.findByContactMobile", query = "SELECT s FROM Student_1 s WHERE s.contactMobile = :contactMobile")
    , @NamedQuery(name = "Student_1.findByContactMail", query = "SELECT s FROM Student_1 s WHERE s.contactMail = :contactMail")
    , @NamedQuery(name = "Student_1.findByStudentId", query = "SELECT s FROM Student_1 s WHERE s.studentId = :studentId")
    , @NamedQuery(name = "Student_1.findByCardId", query = "SELECT s FROM Student_1 s WHERE s.cardId = :cardId")})
public class Student_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "CONTACT_MOBILE")
    private String contactMobile;
    @Column(name = "CONTACT_MAIL")
    private String contactMail;
    @Column(name = "STUDENT_ID")
    private String studentId;
    @Column(name = "CARD_ID")
    private String cardId;

    public Student_1() {
    }

    public Student_1(Integer id) {
        this.id = id;
    }

    public Student_1(Integer id, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        String oldFirstName = this.firstName;
        this.firstName = firstName;
        changeSupport.firePropertyChange("firstName", oldFirstName, firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        changeSupport.firePropertyChange("lastName", oldLastName, lastName);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        Date oldBirthDate = this.birthDate;
        this.birthDate = birthDate;
        changeSupport.firePropertyChange("birthDate", oldBirthDate, birthDate);
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        String oldContactMobile = this.contactMobile;
        this.contactMobile = contactMobile;
        changeSupport.firePropertyChange("contactMobile", oldContactMobile, contactMobile);
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        String oldContactMail = this.contactMail;
        this.contactMail = contactMail;
        changeSupport.firePropertyChange("contactMail", oldContactMail, contactMail);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        String oldStudentId = this.studentId;
        this.studentId = studentId;
        changeSupport.firePropertyChange("studentId", oldStudentId, studentId);
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        String oldCardId = this.cardId;
        this.cardId = cardId;
        changeSupport.firePropertyChange("cardId", oldCardId, cardId);
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
        if (!(object instanceof Student_1)) {
            return false;
        }
        Student_1 other = (Student_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ams.Student_1[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
