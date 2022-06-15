package com.example.smarticity.data.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class City extends BaseEntity {
    private String name;
    private String generalInfo;
    private List<Hospital> hospitals;
    private List<Hotel> hotels;
    private List<Business> businesses;

    public City() {
    }

    public City(String name, String generalInfo) {
        this.name = name;
        this.generalInfo = generalInfo;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "general_info")
    public String getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(String generalInfo) {
        this.generalInfo = generalInfo;
    }

    @OneToMany(targetEntity = Hospital.class)
    @JoinColumn(name = "city_id",
            referencedColumnName = "id")
    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    @OneToMany(targetEntity = Hotel.class)
    @JoinColumn(name = "city_id",
            referencedColumnName = "id")
    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    @OneToMany(targetEntity = Business.class)
    @JoinColumn(name = "city_id",
            referencedColumnName = "id")
    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }
}
