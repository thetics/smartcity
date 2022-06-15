package com.example.smarticity.data.model.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CityEntityTest {
    private String name = "Ivan";
    private String information = "Marti e gotin";


    @Test
    public void testGetterSetter() {
        City city = new City();
        city.setGeneralInfo(information);
        city.setName(name);

        assertEquals(city.getName(), name);
        assertEquals(city.getGeneralInfo(), information);

    }

    @Test
    public void testConstructor() {
        City city = new City(name, information);

        assertEquals(city.getName(), name);
        assertEquals(city.getGeneralInfo(), information);

    }

    @Test
    public void testConstructorNull() {
        City city = new City(null, null);

        assertEquals(city.getName(), null);
        assertEquals(city.getGeneralInfo(), null);

    }

    @Test
    public void testGetterSetterAll() {
        City city = new City();

        List<Hotel> hotels = new ArrayList<>();
        List<Hospital> hospitals = new ArrayList<>();
        List<Business> businesses = new ArrayList<>();

        city.setHospitals(hospitals);
        city.setBusinesses(businesses);
        city.setGeneralInfo(information);
        city.setName(name);
        city.setHotels(hotels);

        assertEquals(city.getName(), name);
        assertEquals(city.getGeneralInfo(), information);
        assertEquals(city.getHotels().size(), 0);
        assertEquals(city.getBusinesses().size(), 0);
        assertEquals(city.getHospitals().size(), 0);
    }
}
