package com.example.smarticity.data.service.models;


import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.model.entity.enumer.Status;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ReservationServiceModel extends BaseServiceModel {

    private String description;
    private Status status;
    private Date dateTo;
    private Date dateFrom;
    private InstituteType instituteType;
    private String instituteId;

    public ReservationServiceModel() {
    }

    public ReservationServiceModel(String description,Status status, Date dateTo, Date dateFrom, InstituteType instituteType, String instituteId) {
        this.description=description;
        this.status = status;
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
        this.instituteType = instituteType;
        this.instituteId = instituteId;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @NotNull
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @NotNull
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @NotNull
    public InstituteType getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(InstituteType instituteType) {
        this.instituteType = instituteType;
    }

    @NotNull // todo what will happen if instituteId is "    "
    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }
}
