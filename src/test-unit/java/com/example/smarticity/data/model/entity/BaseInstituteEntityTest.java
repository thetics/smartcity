package com.example.smarticity.data.model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseInstituteEntityTest {
    private String name="Ivan";
    private String information="Marti e gotin";
    private String address="ul1";
    private String cityId="dsdasdsadsa";

    @Test
    void constructorTest(){

        Hotel hotel = new Hotel(name, information, address,cityId);
        assertEquals(hotel.getName(), name);
        assertEquals(hotel.getInformation(), information);
        assertEquals(hotel.getAddress(),address);
        assertEquals(hotel.getCityId(),cityId);
    }

    @Test
    void getterSetterTest(){

        BaseInstitution baseInstitution=new BaseInstitution();
        baseInstitution.setAddress(address);
        baseInstitution.setName(name);
        baseInstitution.setInformation(information);
        baseInstitution.setCityId("gosho");
        baseInstitution.setId("id");


        assertEquals(baseInstitution.getName(), name);
        assertEquals(baseInstitution.getInformation(), information);
        assertEquals(baseInstitution.getAddress(),address);
        assertEquals(baseInstitution.getCityId(),"gosho");
        assertEquals(baseInstitution.getId(),"id");
    }
}
