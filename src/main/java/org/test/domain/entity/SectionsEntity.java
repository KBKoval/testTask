package org.test.domain.entity;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "sections", catalog = "geology", schema = "public")
@NamedQueries({@NamedQuery(name = "SectionsEntity.findAll", query = "SELECT s FROM SectionsEntity s"), @NamedQuery(name = "SectionsEntity.findBySectionName", query = "SELECT s FROM SectionsEntity s WHERE s.sectionName = :sectionName")})
public class SectionsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;
    @Column(name = "section_name")
    private String sectionName;
    @OneToMany(mappedBy = "parentId")
    private Set<ClassesEntity> classesEnitySet;

    public SectionsEntity() {
    }

    public UUID getId() {
        return id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Set<ClassesEntity> getClassesEnitySet() {
        return classesEnitySet;
    }

    public void setClassesEnitySet(Set<ClassesEntity> classesEnitySet) {
        this.classesEnitySet = classesEnitySet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SectionsEntity)) {
            return false;
        }
        SectionsEntity other = (SectionsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Sections[ id=" + id + " ]";
    }

}

