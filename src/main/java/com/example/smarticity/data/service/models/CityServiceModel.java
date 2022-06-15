package com.example.smarticity.data.service.models;

import javax.validation.constraints.NotNull;

public class CityServiceModel extends BaseServiceModel {
    @NotNull
    private String name; // todo what will happen if name is "    "
    @NotNull
    private String generalInfo; // todo what will happen if generalInfo is "    "


    public CityServiceModel(String name, String general_info) {
        this.name = name;
        this.generalInfo = general_info;
    }

    public CityServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(String generalInfo) {
        this.generalInfo = generalInfo;
    }
}
