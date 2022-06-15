package com.example.smarticity.data.service.services.implementations;

import com.example.smarticity.data.model.entity.Hotel;
import com.example.smarticity.data.model.repository.HotelRepository;
import com.example.smarticity.data.service.models.HotelServiceModel;
import com.example.smarticity.data.service.services.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HotelServiceModel findByName(String name) {
        return hotelRepository.findByName(name)
                .map(hotel -> this.modelMapper
                        .map(hotel, HotelServiceModel.class)).orElse(null);
    }

    @Override
    public HotelServiceModel createHotel(HotelServiceModel hotelServiceModel) {
        Hotel hotel = this.modelMapper.map(hotelServiceModel, Hotel.class);
        return this.modelMapper.map(this.hotelRepository.saveAndFlush(hotel), HotelServiceModel.class);
    }

    @Override
    public List<HotelServiceModel> findAllHotels() {
        List<Hotel> hotels = this.hotelRepository.findAll();
        return hotels.stream().map(hotel -> this.modelMapper.map(hotel, HotelServiceModel.class))
                .collect(Collectors.toList());
    }
}