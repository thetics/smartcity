package com.example.smarticity.data.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hospital")
public class Hospital extends BaseInstitution {

    private List<Reservation> reservations;
    private List<Review> reviews;


    public Hospital(String name, String information, String address, String cityId) {
        super(name, information, address, cityId);
    }

    public Hospital() {
    }

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id", insertable = false, updatable = false)
    @Where(clause = "institute_type = 'HOSPITAL'")
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id", insertable = false, updatable = false)
    @Where(clause = "institute_type = 'HOSPITAL'")
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
