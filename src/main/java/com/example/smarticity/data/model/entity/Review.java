package com.example.smarticity.data.model.entity;

import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "review")
public class Review extends BaseEntity  {

    private String description;
    private InstituteType instituteType;
    private IBaseInstitution instituteId;

    public Review() {
    }

    public Review(String description, InstituteType instituteType, IBaseInstitution instituteId) {
        this.description = description;
        this.instituteType = instituteType;
        this.instituteId = instituteId;
    }


    @NotNull
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "institute_type", nullable = false, insertable = false, updatable = false)
    public InstituteType getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(InstituteType institute_type) {
        this.instituteType = institute_type;
    }


    @Any(metaDef = "InstituteReviewsMetaDef", metaColumn = @Column(name = "institute_type", nullable = false))
//    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "institute_id")
    @JsonManagedReference
    public IBaseInstitution getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(IBaseInstitution instituteId) {
        this.instituteId = instituteId;
    }


    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
