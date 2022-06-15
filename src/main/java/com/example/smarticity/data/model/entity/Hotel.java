package com.example.smarticity.data.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel extends BaseInstitution {
    private Integer Stars;
    private List<Reservation> reservations;
    private List<Review> reviews;

    public Hotel(String name, String information, String address, String cityId) {
        super(name, information, address, cityId);
    }

    public Hotel() {

    }

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id", insertable = false, updatable = false)
    @Where(clause = "institute_type = 'HOTEL'")
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id", insertable = false, updatable = false)
    @Where(clause = "institute_type = 'HOTEL'")
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Column(name = "stars")
    public Integer getStars() {
        return Stars;
    }

    public void setStars(Integer stars) {
        Stars = stars;
    }
}
