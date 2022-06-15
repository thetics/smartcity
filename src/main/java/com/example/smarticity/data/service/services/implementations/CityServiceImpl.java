package com.example.smarticity.data.service.services.implementations;

import com.example.smarticity.data.model.entity.City;
import com.example.smarticity.data.model.repository.CityRepository;
import com.example.smarticity.data.service.models.CityServiceModel;
import com.example.smarticity.data.service.services.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CityServiceModel createCity(CityServiceModel cityServiceModel) {
        City city = this.modelMapper.map(cityServiceModel, City.class);
        return this.modelMapper.map(this.cityRepository.saveAndFlush(city), CityServiceModel.class);
    }

}
