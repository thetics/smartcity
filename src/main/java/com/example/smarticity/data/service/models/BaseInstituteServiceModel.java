package com.example.smarticity.data.service.models;

import com.example.smarticity.data.model.entity.IBaseInstitution;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BaseInstituteServiceModel extends BaseServiceModel implements IBaseInstitution {
    @NotBlank
    private String name;
    @NotBlank
    private String information;
    private String address;
    @NotNull
    private String cityId; // todo what will happen if cityId is "    "

    public BaseInstituteServiceModel() {
    }

    public BaseInstituteServiceModel(String name, String information, String address, String cityId) {
        this.name = name;
        this.information = information;
        this.address = address;
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
