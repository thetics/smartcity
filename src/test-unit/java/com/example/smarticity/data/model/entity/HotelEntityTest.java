package com.example.smarticity.data.model.entity;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class HotelEntityTest {
    private String name="Ivan";
    private String information="Marti e gotin";
    private String address="ul1";
    private String cityId="dasdas";




    @Test
    void getStarsTest(){

        Hotel hotel = new Hotel(name, information, address,cityId);
        hotel.setStars(3);
        assertEquals(hotel.getName(), name);
        assertEquals(hotel.getInformation(), information);
        assertEquals(hotel.getAddress(),address);
        assertEquals(hotel.getStars(),3);
    }
    @Test
    public void testGetterSetterAll() {
        Hotel hotel = new Hotel();

        List<Reservation> reservations = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();

        hotel.setReservations(reservations);
        hotel.setReviews(reviews);

        assertEquals(hotel.getReservations().size(), 0);
        assertEquals(hotel.getReviews().size(), 0);
    }



}
