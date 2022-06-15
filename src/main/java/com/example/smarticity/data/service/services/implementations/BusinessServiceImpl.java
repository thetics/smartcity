package com.example.smarticity.data.service.services.implementations;

import com.example.smarticity.data.model.entity.Business;
import com.example.smarticity.data.model.repository.BusinessRepository;
import com.example.smarticity.data.service.models.BusinessServiceModel;
import com.example.smarticity.data.service.services.BusinessService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;
    private final ModelMapper modelMapper;

    public BusinessServiceImpl(BusinessRepository businessRepository, ModelMapper modelMapper) {
        this.businessRepository = businessRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BusinessServiceModel findByName(String name) {
        // todo I am not sure mapping will work if Business have reviews
        return businessRepository.findByName(name)
                .map(business -> this.modelMapper
                        .map(business, BusinessServiceModel.class)).orElse(null);
    }

    @Override
    public List<BusinessServiceModel> findAllBusinesess() {
        // todo Try to return List<BusinessServiceModel> from repo, or directly map List<Business> to List<BusinessServiceModel>, this will be faster than stream
        List<Business> businesses = this.businessRepository.findAll();
        return businesses.stream()
                .map(business -> modelMapper.map(business, BusinessServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public BusinessServiceModel createBusiness(BusinessServiceModel businessServiceModel) {
        Business business = this.modelMapper.map(businessServiceModel, Business.class);
        return this.modelMapper.map(this.businessRepository.saveAndFlush(business), BusinessServiceModel.class);
    }

}
