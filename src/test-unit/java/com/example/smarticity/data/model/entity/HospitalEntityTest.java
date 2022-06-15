package com.example.smarticity.data.model.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HospitalEntityTest {
    private String name = "Ivan";
    private String information = "Marti e gotin";


    @Test
    public void testGetterSetterAll() {
        Hospital hospital = new Hospital();

        List<Reservation> reservations = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();

        hospital.setReservations(reservations);
        hospital.setReviews(reviews);

        assertEquals(hospital.getReservations().size(), 0);
        assertEquals(hospital.getReviews().size(), 0);
    }
}
