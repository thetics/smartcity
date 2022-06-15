package com.example.smarticity.web.controller;

import com.example.smarticity.Annotations.DefinePermissions;
import com.example.smarticity.data.service.models.HotelServiceModel;
import com.example.smarticity.data.service.services.HotelService;
import com.example.smarticity.data.service.services.implementations.HotelServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static com.example.smarticity.web.controller.BaseController.*;

@DefinePermissions(permissions = {PERM_GET, PERM_CREATE, PERM_EDIT, PERM_DELETE})
@Controller
@RequestMapping(path = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController extends BaseController {
    private final HotelService hotelService;
    private final ModelMapper modelMapper;

    public HotelController(HotelServiceImpl hotelService, ModelMapper modelMapper) {
        this.hotelService = hotelService;
        this.modelMapper = modelMapper;
    }


    @Transactional
    @GetMapping("/all")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_GET)")
    public ResponseEntity<List<HotelServiceModel>> loadAllHotels() {

        return new ResponseEntity<>(hotelService.findAllHotels(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/create")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_CREATE)")
    public ResponseEntity<?> createHotel(@Valid @RequestBody HotelServiceModel hotelServiceModel, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        this.hotelService.createHotel(hotelServiceModel);
        return new ResponseEntity<>(hotelServiceModel, HttpStatus.OK);
    }

}
