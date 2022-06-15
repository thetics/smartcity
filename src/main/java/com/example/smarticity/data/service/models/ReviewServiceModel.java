package com.example.smarticity.data.service.models;

import com.example.smarticity.data.model.entity.enumer.InstituteType;

import javax.validation.constraints.NotNull;

public class ReviewServiceModel extends BaseServiceModel {

    private String description;
    private InstituteType instituteType;
    private String instituteId;

    public ReviewServiceModel() {
    }

    public ReviewServiceModel(String description, InstituteType instituteType, String instituteId) {
        this.description = description;
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
    public InstituteType getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(InstituteType instituteType) {
        this.instituteType = instituteType;
    }

    @NotNull // todo what will happen if instituteId
    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }
}
