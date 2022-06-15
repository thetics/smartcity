package com.example.smarticity.data.service.services;

import com.example.smarticity.data.service.models.HospitalServiceModel;

import java.util.List;

public interface HospitalService {

    HospitalServiceModel findByName(String name);

    List<HospitalServiceModel> findAllHospitals();

    HospitalServiceModel createHospital(HospitalServiceModel hospitalServiceModel);
}
