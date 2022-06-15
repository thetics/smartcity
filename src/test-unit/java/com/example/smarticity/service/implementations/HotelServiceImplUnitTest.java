package com.example.smarticity.service.implementations;

import com.example.smarticity.data.model.entity.Hotel;
import com.example.smarticity.data.model.repository.HotelRepository;
import com.example.smarticity.data.service.models.HotelServiceModel;
import com.example.smarticity.data.service.services.implementations.HotelServiceImpl;
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
public class HotelServiceImplUnitTest {

    @Mock
    HotelRepository hotelRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    HotelServiceImpl hotelService;


    @Test
    void createHotel() {
        HotelServiceModel hotelServiceModel = new HotelServiceModel("hotel", "info", "address", "dasdas",4);
        Hotel hotelEntity = new Hotel("hotel", "info", "address","dasdas");

        Mockito.when(modelMapper.map(Mockito.any(HotelServiceModel.class), Mockito.any())).thenReturn(hotelEntity);
        Mockito.when(hotelRepository.saveAndFlush(Mockito.any(Hotel.class))).thenReturn(hotelEntity);
        Mockito.when(modelMapper.map(Mockito.any(Hotel.class), Mockito.any())).thenReturn(hotelServiceModel);

        HotelServiceModel result = hotelService.createHotel(hotelServiceModel);
        assertEquals(result.getStars(), hotelServiceModel.getStars());
        assertEquals(result.getAddress(), hotelServiceModel.getAddress());
    }

    @Test
    void findByName() {
        HotelServiceModel hotelServiceModel = new HotelServiceModel("hotel", "info", "address", "dasdas",4);
        Optional<Hotel> hotelEntity = Optional.of(new Hotel("hotel", "info", "address","dasdas"));
        String name = "hotel";

        Mockito.when(hotelRepository.findByName(Mockito.any())).thenReturn(hotelEntity);
        Mockito.when(modelMapper.map(Mockito.any(Hotel.class), Mockito.any())).thenReturn(hotelServiceModel);

        HotelServiceModel result = hotelService.findByName("hotel");

        assertEquals(result.getName(), hotelServiceModel.getName());
    }

    @Test
    void findAllHotels() {
        HotelServiceModel hotelServiceModel = new HotelServiceModel("hotel", "info", "address",  "dasdas", 4);

        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("hotel", "info", "address","dasdas"));
        hotels.add(new Hotel("hotel", "info", "address","dasdas"));
        hotels.add(new Hotel("hotel", "info", "address","dasdas"));
        hotels.add(new Hotel("hotel", "info", "address","dasdas"));

        Mockito.when(hotelRepository.findAll()).thenReturn(hotels);
        Mockito.when(modelMapper.map(Mockito.any(Hotel.class), Mockito.any())).thenReturn(hotelServiceModel);
        List<HotelServiceModel> hotelModels = hotelService.findAllHotels();

        assertEquals(hotelRepository.findAll().size(), hotels.size());

        for (int i = 0; i < hotels.size(); i++) {
            assertEquals(hotels.get(i).getName(), hotelModels.get(i).getName());
            assertEquals(hotels.get(i).getInformation(), hotelModels.get(i).getInformation());
            assertEquals(hotels.get(i).getAddress(), hotelModels.get(i).getAddress());
        }
    }

}
