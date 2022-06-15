package com.example.smarticity.data.services;

import com.example.smarticity.data.service.models.BaseInstituteServiceModel;
import com.example.smarticity.data.service.models.HospitalServiceModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseInstituteServiceModelTest {

    private String name="Ivan";
    private String information="Marti e gotin";
    private String address="ul1";
    private String cityId="dasdas";


    @Test
    void setParametersTest(){

        BaseInstituteServiceModel hospital = new BaseInstituteServiceModel(name, information, address,cityId);

        assertEquals(hospital.getName(), name);
        assertEquals(hospital.getInformation(), information);
        assertEquals(hospital.getAddress(),address);
        assertEquals(hospital.getCityId(),cityId);
    }
    @Test
    void setNameTest(){

        HospitalServiceModel hospital = new HospitalServiceModel();
        hospital.setName(name);
        assertEquals(hospital.getName(), name);

    }
    @Test
    void allGettersSetters(){

        BaseInstituteServiceModel baseInstituteServiceModel = new BaseInstituteServiceModel();
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
