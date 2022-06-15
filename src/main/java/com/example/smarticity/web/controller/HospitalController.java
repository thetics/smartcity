package com.example.smarticity.web.controller;

import com.example.smarticity.Annotations.DefinePermissions;
import com.example.smarticity.data.service.models.HospitalServiceModel;
import com.example.smarticity.data.service.services.HospitalService;
import com.example.smarticity.data.service.services.implementations.HospitalServiceImpl;
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
@RequestMapping(path = "/hospitals", produces = MediaType.APPLICATION_JSON_VALUE)
public class HospitalController extends BaseController {
    private final HospitalService hospitalService;
    private final ModelMapper modelMapper;


    public HospitalController(HospitalServiceImpl hospitalService, ModelMapper modelMapper) {
        this.hospitalService = hospitalService;
        this.modelMapper = modelMapper;
    }


    @Transactional
    @GetMapping("/all")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_GET)")
    public ResponseEntity<List<HospitalServiceModel>> loadAllHospitals() {
        return new ResponseEntity<>(hospitalService.findAllHospitals(), HttpStatus.OK);

    }

    @Transactional
    @PostMapping("/create")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_CREATE)")
    public ResponseEntity<?> createHospital(@Valid @RequestBody HospitalServiceModel hospitalServiceModel,
                                            Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        this.hospitalService.createHospital(hospitalServiceModel);
        return new ResponseEntity<>(hospitalServiceModel, HttpStatus.OK);
    }

}
