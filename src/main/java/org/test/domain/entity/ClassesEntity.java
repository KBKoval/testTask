package org.test.domain.entity;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "classes", catalog = "geology", schema = "public")
@NamedQueries({
        @NamedQuery(name = "ClassesEntity.findAll", query = "SELECT c FROM ClassesEntity c"),
        @NamedQuery(name = "ClassesEntity.findBySectionName", query = "SELECT c FROM ClassesEntity c WHERE c.className = :className"),
        @NamedQuery(name = "ClassesEntyti.findByCode", query = "SELECT c FROM ClassesEntity c WHERE c.code = :code")})
public class ClassesEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;
    @Column(name = "class_name")
    private String className;
    @Column(name = "code")
    private String code;
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @ManyToOne
    private SectionsEntity parentId;

    public ClassesEntity() {
    }


    public UUID getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SectionsEntity getParentId() {
        return parentId;
    }

    public void setParentId(SectionsEntity parentId) {
        this.parentId = parentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ClassesEntity)) {
            return false;
        }
        ClassesEntity other = (ClassesEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClassesEntity{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", code='" + code + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}

