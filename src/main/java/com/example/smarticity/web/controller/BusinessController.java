package com.example.smarticity.web.controller;

import com.example.smarticity.Annotations.DefinePermissions;
import com.example.smarticity.data.service.models.BusinessServiceModel;
import com.example.smarticity.data.service.services.BusinessService;
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
@RequestMapping(path = "/businesses", produces = MediaType.APPLICATION_JSON_VALUE)
public class BusinessController extends BaseController {
    private final BusinessService businessService;
    private final ModelMapper modelMapper; // todo useless

    public BusinessController(BusinessService businessService, ModelMapper modelMapper) {
        this.businessService = businessService;
        this.modelMapper = modelMapper;
    }


    @Transactional
    @GetMapping("/all")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_GET)")
    public ResponseEntity<List<BusinessServiceModel>> loadAllBusinesses() {
        return new ResponseEntity<>(businessService.findAllBusinesess(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/create")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_CREATE)")
    public ResponseEntity<?> createBusiness(@Valid @RequestBody BusinessServiceModel businessServiceModel,
                                            Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        this.businessService.createBusiness(businessServiceModel);
        return new ResponseEntity<>(businessServiceModel, HttpStatus.OK);
    }

}
