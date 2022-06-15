package com.example.smarticity.data.services;

import com.example.smarticity.data.service.models.CityServiceModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityServiceModelTest {
    private String name = "Varna";
    private String generalInfo = "Marti e gotin";


    @Test
    public void testSetter() {
        CityServiceModel cityServiceModel = new CityServiceModel(name, generalInfo);

        assertEquals(cityServiceModel.getName(), name);
        assertEquals(cityServiceModel.getGeneralInfo(), generalInfo);
    }

    @Test
    public void emptyConstructor() {
        CityServiceModel cityServiceModel = new CityServiceModel();

        assertEquals(cityServiceModel.getName(), null);
    }

    @Test
    public void setName() {
        CityServiceModel cityServiceModel = new CityServiceModel();
        cityServiceModel.setName("Burgas");
        cityServiceModel.setGeneralInfo("Info");
        assertEquals(cityServiceModel.getName(), "Burgas");
        assertEquals(cityServiceModel.getGeneralInfo(), "Info");
    }

//    @Test
//    void starsOutOfBoundsSHouldFail() {
//
//        HotelServiceModel hotelServiceModel = new HotelServiceModel(name, information, address, cityId, 10);
//        assertEquals(hotelServiceModel.getName(), name);
//        assertEquals(hotelServiceModel.getInformation(), information);
//        assertEquals(hotelServiceModel.getAddress(), address);
//        assertEquals(hotelServiceModel.getStars(), 10);
//    }
}
