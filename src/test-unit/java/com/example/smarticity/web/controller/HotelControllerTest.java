package com.example.smarticity.web.controller;

import com.example.smarticity.data.service.models.HotelServiceModel;
import com.example.smarticity.data.service.services.implementations.HotelServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class HotelControllerTest {

    @Mock
    HotelServiceImpl hotelService;

    @Mock
    Errors errors;

    @InjectMocks
    HotelController hotelController;


    @Test
    void createHotel() {
        HotelServiceModel model = new HotelServiceModel("test1", "test2", "test3", "test4", 5);

        Mockito.when(hotelService.createHotel(Mockito.any(HotelServiceModel.class)))
                .thenReturn(model);

        HotelServiceModel result = (HotelServiceModel) hotelController.createHotel(model, errors).getBody();

        assertNotNull(result);
        assertEquals(model.getName(), result.getName());
        assertEquals(model.getInformation(), result.getInformation());
        assertEquals(model.getAddress(), result.getAddress());
        assertEquals(model.getCityId(), result.getCityId());
        assertEquals(model.getStars(), result.getStars());
    }

    @Test
    void createHotel_validationTest() {
        HotelServiceModel model = new HotelServiceModel("test1", "test2", "test3", "test4", 5);

        List<ObjectError> errorList = new ArrayList<>();
        errorList.add(new ObjectError("name", "invalid length."));

        Mockito.when(errors.hasErrors()).thenReturn(true);
        Mockito.when(errors.getAllErrors()).thenReturn(errorList);

        ResponseEntity<?> result = hotelController.createHotel(model, errors);
        assertNotNull(result);
        assertEquals(result.getBody(), errorList);
        String deb = "dfeb";
    }

    @Test
    public void viewAllHotels() {
        List<HotelServiceModel> hotels = new ArrayList<>();

        HotelServiceModel model1 = new HotelServiceModel("test1", "test2", "test3", "test4", 5);
        HotelServiceModel model2 = new HotelServiceModel("test1", "test2", "test3", "test4", 5);
        HotelServiceModel model3 = new HotelServiceModel("test1", "test2", "test3", "test4", 5);
        hotels.add(model1);
        hotels.add(model2);
        hotels.add(model3);

        Mockito.when(hotelService.findAllHotels()).thenReturn(hotels);

        ResponseEntity<List<HotelServiceModel>> result = hotelController.loadAllHotels();

        assertEquals(result.getBody().size(),hotels.size());

    }

}
