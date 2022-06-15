package com.example.smarticity.data.services;

import com.example.smarticity.data.service.models.HotelServiceModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelServiceModelTest {


    private String name="Ivan";
    private String information="Marti e gotin";
    private String address="ul1";
    private String cityId="dasdas";


    @Test
    void starsOutOfBoundsSHouldFail(){

        HotelServiceModel hotelServiceModel = new HotelServiceModel(name, information, address,cityId,10);
        assertEquals(hotelServiceModel.getName(), name);
        assertEquals(hotelServiceModel.getInformation(), information);
        assertEquals(hotelServiceModel.getAddress(),address);
        assertEquals(hotelServiceModel.getStars(),10);
        assertEquals(hotelServiceModel.getStars(),10);
    }
    @Test
    void setStarsTest(){

        HotelServiceModel hotelServiceModel = new HotelServiceModel();
        hotelServiceModel.setStars(10);

        assertEquals(hotelServiceModel.getStars(),10);
    }
}
