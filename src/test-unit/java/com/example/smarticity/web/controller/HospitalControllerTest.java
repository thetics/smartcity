package com.example.smarticity.web.controller;

import com.example.smarticity.data.service.models.HospitalServiceModel;
import com.example.smarticity.data.service.services.implementations.HospitalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class HospitalControllerTest {

    @Mock
    HospitalServiceImpl hospitalService;

    @Mock
    Errors errors;

    @InjectMocks
    HospitalController hospitalController;


    @Test
    void view() {

        List<HospitalServiceModel> models = new ArrayList<>();
        HospitalServiceModel model = new HospitalServiceModel("test1", "test2", "test3", "test4");
        HospitalServiceModel model1 = new HospitalServiceModel("test1", "test2", "test3", "test4");
        HospitalServiceModel model2 = new HospitalServiceModel("test1", "test2", "test3", "test4");
        models.add(model);
        models.add(model1);
        models.add(model2);



        Mockito.when(hospitalService.findAllHospitals()).thenReturn(models);

        ResponseEntity<List<HospitalServiceModel>> result = hospitalController.loadAllHospitals();

        assertEquals(result.getBody().size(),models.size());

    }

    @Test
    void createHospital_validationTest() {
        HospitalServiceModel model = new HospitalServiceModel("test1", "test2", "test3", "test4");

        List<ObjectError> errorList = new ArrayList<>();
        errorList.add(new ObjectError("name", "invalid length."));

        Mockito.when(errors.hasErrors()).thenReturn(true);
        Mockito.when(errors.getAllErrors()).thenReturn(errorList);

        ResponseEntity<?> result = hospitalController.createHospital(model, errors);
        assertNotNull(result);
        assertEquals(result.getBody(), errorList);
    }
    @Test
    void createHospital() {
        HospitalServiceModel model = new HospitalServiceModel("test1", "test2", "test3", "test4");

        Mockito.when(hospitalService.createHospital(Mockito.any(HospitalServiceModel.class)))
                .thenReturn(model);

        HospitalServiceModel result = (HospitalServiceModel) hospitalController.createHospital(model, errors).getBody();

        assertNotNull(result);
        assertEquals(model.getName(), result.getName());
        assertEquals(model.getInformation(), result.getInformation());
        assertEquals(model.getAddress(), result.getAddress());
        assertEquals(model.getCityId(), result.getCityId());
    }

}
