package com.example.smarticity.data.services;

import com.example.smarticity.data.service.models.BusinessServiceModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusinessServiceModelTest {

    private String name="Ivan";
    private String information="Marti e gotin";
    private String address="ul1";
    private String cityId="dasdas";


    @Test
    void setParametersTest(){

        BusinessServiceModel hospital = new BusinessServiceModel(name, information, address,cityId);

        assertEquals(hospital.getName(), name);
        assertEquals(hospital.getInformation(), information);
        assertEquals(hospital.getAddress(),address);
        assertEquals(hospital.getCityId(),cityId);
    }
    @Test
    void setNameTest(){

        BusinessServiceModel hospital = new BusinessServiceModel();
        hospital.setName(name);
        assertEquals(hospital.getName(), name);

    }
    @Test
    void allGettersSetters(){

        BusinessServiceModel baseInstituteServiceModel = new BusinessServiceModel();
                baseInstituteServiceModel.setAddress(address);
                baseInstituteServiceModel.setName(name);
                baseInstituteServiceModel.setCityId(cityId);
                baseInstituteServiceModel.setId("id");
                baseInstituteServiceModel.setInformation(information);



        assertEquals(baseInstituteServiceModel.getName(), name);
        assertEquals(baseInstituteServiceModel.getInformation(), information);
        assertEquals(baseInstituteServiceModel.getAddress(),address);
        assertEquals(baseInstituteServiceModel.getCityId(),cityId);
        assertEquals(baseInstituteServiceModel.getId(),"id");
    }

}
