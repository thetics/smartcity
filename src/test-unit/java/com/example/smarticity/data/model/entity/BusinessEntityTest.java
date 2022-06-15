package com.example.smarticity.data.model.entity;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessEntityTest {
    private String name="Ivan";
    private String information="Marti e gotin";
    private String address="ul1";
    private String cityId="dasdas";



    @Test
    public void testConstructorEmpty() {
        Business business = new Business();

        List<Review> reviews = new ArrayList<>();

        business.setReviews(reviews);

        assertEquals(business.getReviews().size(), 0);
    }


    @Test
    public void testConstructor() {
        Business business = new Business(name,information,address,cityId);

        List<Review> reviews = new ArrayList<>();

        business.setReviews(reviews);

        assertEquals(business.getInformation(),information);
        assertEquals(business.getCityId(),cityId);
        assertEquals(business.getName(),name);
        assertEquals(business.getAddress(),address);
        assertEquals(business.getReviews().size(), 0);
    }


}
