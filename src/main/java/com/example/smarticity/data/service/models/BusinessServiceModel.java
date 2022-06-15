package com.example.smarticity.data.service.models;


import com.example.smarticity.data.model.entity.Reservation;
import com.example.smarticity.data.model.entity.Review;

import java.util.List;

public class BusinessServiceModel extends BaseInstituteServiceModel{
    private List<Reservation> reservations;
    private List<Review> reviews;


    public BusinessServiceModel(String name, String information, String address, String cityId) {
        super(name, information, address, cityId);
    }

    public BusinessServiceModel() {
    }


    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
