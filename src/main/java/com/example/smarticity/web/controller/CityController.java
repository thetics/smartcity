package com.example.smarticity.web.controller;

import com.example.smarticity.Annotations.DefinePermissions;
import com.example.smarticity.data.service.models.CityServiceModel;
import com.example.smarticity.data.service.services.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static com.example.smarticity.web.controller.BaseController.*;

@DefinePermissions(permissions = {PERM_GET, PERM_CREATE, PERM_EDIT, PERM_DELETE})
@Controller
@RequestMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController extends BaseController {
    private final CityService cityService;
    private final ModelMapper modelMapper;

    public CityController(CityService cityService, ModelMapper modelMapper) {
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @PostMapping("/create")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_CREATE)")
    public ResponseEntity<?> createCity(@Valid @RequestBody CityServiceModel cityServiceModel, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        this.cityService.createCity(cityServiceModel);
        return new ResponseEntity<>(cityServiceModel, HttpStatus.OK);
    }
}
