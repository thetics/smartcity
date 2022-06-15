package com.example.smarticity.data.service.services.implementations;

import com.example.smarticity.data.model.entity.Hospital;
import com.example.smarticity.data.model.repository.HospitalRepository;
import com.example.smarticity.data.service.models.HospitalServiceModel;
import com.example.smarticity.data.service.services.HospitalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository, ModelMapper modelMapper) {
        this.hospitalRepository = hospitalRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public HospitalServiceModel findByName(String name) {
        return hospitalRepository.findByName(name)
                .map(hospital -> this.modelMapper
                        .map(hospital, HospitalServiceModel.class)).orElse(null);
    }

    @Override
    public List<HospitalServiceModel> findAllHospitals() {
        List<Hospital> hospitals = this.hospitalRepository.findAll();
        return hospitals.stream()
                .map(hospital -> modelMapper.map(hospital, HospitalServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public HospitalServiceModel createHospital(HospitalServiceModel hospitalServiceModel) {
        Hospital hospital = this.modelMapper.map(hospitalServiceModel, Hospital.class);
        return this.modelMapper.map(this.hospitalRepository.saveAndFlush(hospital), HospitalServiceModel.class);
    }

}
