package com.example.smarticity.service.implementations;

import com.example.smarticity.data.model.entity.City;
import com.example.smarticity.data.model.repository.CityRepository;
import com.example.smarticity.data.service.models.CityServiceModel;
import com.example.smarticity.data.service.services.implementations.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CityServiceImplTest {


    @Mock
    CityRepository cityRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    CityServiceImpl hospitalService;


    @Test
    void createCity() {
        CityServiceModel cityServiceModel = new CityServiceModel("Tryavna", "info");
        City cityEntity = new City("Tryavna", "info");

        Mockito.when(modelMapper.map(Mockito.any(CityServiceModel.class), Mockito.any())).thenReturn(cityEntity);
        Mockito.when(modelMapper.map(Mockito.any(City.class), Mockito.any())).thenReturn(cityServiceModel);
        Mockito.when(cityRepository.saveAndFlush(Mockito.any(City.class))).thenReturn(cityEntity);

        CityServiceModel result = hospitalService.createCity(cityServiceModel);
        assertEquals(result.getName(), cityServiceModel.getName());
        assertEquals(result.getGeneralInfo(), cityServiceModel.getGeneralInfo());
    }







}
