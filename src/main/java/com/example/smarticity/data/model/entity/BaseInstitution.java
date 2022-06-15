package com.example.smarticity.data.model.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class BaseInstitution extends BaseEntity implements IBaseInstitution {

    private String name;
    private String information;
    private String address;
    private String cityId;

    public BaseInstitution(String name, String information, String address, String cityId) {
        this.name = name;
        this.information = information;
        this.address = address;
        this.cityId = cityId;
    }

    public BaseInstitution() {

    }

    @NotNull
    @Column(name = "city_id")
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "information", columnDefinition = "TEXT")
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
