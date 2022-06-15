package com.example.smarticity.service.implementations;

import com.example.smarticity.data.model.entity.Hospital;
import com.example.smarticity.data.model.repository.HospitalRepository;
import com.example.smarticity.data.service.models.HospitalServiceModel;
import com.example.smarticity.data.service.services.implementations.HospitalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HospitalServiceImplTest {


    @Mock
    HospitalRepository hospitalRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    HospitalServiceImpl hospitalService;


    @Test
    void createHospital() {
        HospitalServiceModel hospitalServiceModel = new HospitalServiceModel("hotel", "info", "address", "dasdas");
        Hospital hospitalEntity = new Hospital("hotel", "info", "address","dasdas");

        Mockito.when(modelMapper.map(Mockito.any(HospitalServiceModel.class), Mockito.any())).thenReturn(hospitalEntity);
        Mockito.when(hospitalRepository.saveAndFlush(Mockito.any(Hospital.class))).thenReturn(hospitalEntity);
        Mockito.when(modelMapper.map(Mockito.any(Hospital.class), Mockito.any())).thenReturn(hospitalServiceModel);

        HospitalServiceModel result = hospitalService.createHospital(hospitalServiceModel);
        assertEquals(result.getCityId(), hospitalServiceModel.getCityId());
        assertEquals(result.getAddress(), hospitalServiceModel.getAddress());
    }

    @Test
    void findByName() {
        HospitalServiceModel hospitalServiceModel = new HospitalServiceModel("hotel", "info", "address", "dasdas");
        Optional<Hospital> hotelEntity = Optional.of(new Hospital("hotel", "info", "address","dasdas"));

        Mockito.when(hospitalRepository.findByName(Mockito.any())).thenReturn(hotelEntity);
        Mockito.when(modelMapper.map(Mockito.any(Hospital.class), Mockito.any())).thenReturn(hospitalServiceModel);

        HospitalServiceModel result = hospitalService.findByName("hotel");

        assertEquals(result.getName(), hospitalServiceModel.getName());
    }

    @Test
    void findAllHospitals() {
        HospitalServiceModel hospitalServiceModel = new HospitalServiceModel("hotel", "info", "address",  "dasdas");

        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(new Hospital("hotel", "info", "address","dasdas"));
        hospitals.add(new Hospital("hotel", "info", "address","dasdas"));
        hospitals.add(new Hospital("hotel", "info", "address","dasdas"));
        hospitals.add(new Hospital("hotel", "info", "address","dasdas"));

        Mockito.when(hospitalRepository.findAll()).thenReturn(hospitals);
        Mockito.when(modelMapper.map(Mockito.any(Hospital.class), Mockito.any())).thenReturn(hospitalServiceModel);
        List<HospitalServiceModel> hospitalModels = hospitalService.findAllHospitals();

        assertEquals(hospitalRepository.findAll().size(), hospitals.size());

        for (int i = 0; i < hospitals.size(); i++) {
            assertEquals(hospitals.get(i).getName(), hospitalModels.get(i).getName());
            assertEquals(hospitals.get(i).getInformation(), hospitalModels.get(i).getInformation());
            assertEquals(hospitals.get(i).getAddress(), hospitalModels.get(i).getAddress());
        }
    }






}
