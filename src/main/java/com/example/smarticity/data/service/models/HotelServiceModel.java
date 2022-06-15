package com.example.smarticity.data.service.models;

import com.example.smarticity.data.model.entity.Reservation;
import com.example.smarticity.data.model.entity.Review;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

public class HotelServiceModel extends BaseInstituteServiceModel {


    @Min(0)
    @Max(6)
    private int stars;
    private List<Reservation> reservations;
    private List<Review> reviews;


    public HotelServiceModel(String name, String information, String address, String cityId, int stars) {
        super(name, information, address, cityId);
        this.stars = stars;
    }


    public HotelServiceModel() {
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

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
