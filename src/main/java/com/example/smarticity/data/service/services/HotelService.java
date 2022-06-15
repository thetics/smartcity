package com.example.smarticity.data.service.services;

import com.example.smarticity.data.service.models.HotelServiceModel;

import java.util.List;

public interface HotelService {
    HotelServiceModel findByName(String name);

    HotelServiceModel createHotel(HotelServiceModel hotel);

    List<HotelServiceModel> findAllHotels();
}
