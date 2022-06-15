package com.example.smarticity.data.model.entity;

import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.model.entity.enumer.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation extends BaseEntity {

    private String description;
    private Status status;
    private Date dateTo;
    private Date dateFrom;
    private InstituteType instituteType;
    private IBaseInstitution instituteId;

    public Reservation() {
    }

    public Reservation(String description, Status status, Date dateTo, Date dateFrom, InstituteType instituteType, IBaseInstitution instituteId) {
        this.description = description;
        this.status = status;
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
        this.instituteType = instituteType;
        this.instituteId = instituteId;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "institute_id")
    @JsonManagedReference
    public IBaseInstitution getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(IBaseInstitution instituteId) {
        this.instituteId = instituteId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Column(name = "dateTo")
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Basic
    @Column(name = "dateFrom")
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }
}
