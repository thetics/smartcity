package com.example.smarticity.data.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "business")
public class Business extends BaseInstitution {

    private List<Review> reviews;

    public Business(String name, String information, String address, String cityId) {
        super(name, information, address, cityId);
    }

    public Business() {

    }

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id", insertable = false, updatable = false)
    @Where(clause = "institute_type = 'BUSINESS'")
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
