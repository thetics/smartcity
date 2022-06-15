package com.example.smarticity.data.service.services;

import com.example.smarticity.data.service.models.BusinessServiceModel;
import com.example.smarticity.data.service.models.HospitalServiceModel;

import java.util.List;

public interface BusinessService {


    BusinessServiceModel findByName(String name);

    List<BusinessServiceModel> findAllBusinesess();

    BusinessServiceModel createBusiness(BusinessServiceModel businessServiceModel);
}
