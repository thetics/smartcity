package com.example.smarticity.web.controller;

import com.example.smarticity.data.service.models.CityServiceModel;
import com.example.smarticity.data.service.services.implementations.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CityControllerTest {

    @Mock
    CityServiceImpl cityService;

    @Mock
    Errors errors;

    @InjectMocks
    CityController cityController;


    @Test
    void createCity() {
        CityServiceModel model = new CityServiceModel("test1", "test2");

        Mockito.when(cityService.createCity(Mockito.any(CityServiceModel.class)))
                .thenReturn(model);

        CityServiceModel result = (CityServiceModel) cityController.createCity(model, errors).getBody();

        assertNotNull(result);
        assertEquals(model.getName(), result.getName());
        assertEquals(model.getGeneralInfo(), result.getGeneralInfo());

    }

}
