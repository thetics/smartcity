package com.example.smarticity.web.controller;

import com.example.smarticity.Annotations.DefinePermissions;
import com.example.smarticity.data.service.models.ReservationServiceModel;
import com.example.smarticity.data.service.models.UserServiceModel;
import com.example.smarticity.data.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.example.smarticity.web.controller.BaseController.*;

@DefinePermissions(permissions = {PERM_GET, PERM_CREATE, PERM_EDIT, PERM_DELETE})
@Controller
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @GetMapping("/all")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_GET)")
    public ResponseEntity<List<UserServiceModel>> loadAllUsers() {

        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/name")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_GET)")
    public ResponseEntity<UserServiceModel> loadUserByUserName(Principal principal) {

        return new ResponseEntity<>(userService.findUserByUserName(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_DELETE)")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {


        this.userService.deleteUser(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @PostMapping("/set-user/{id}")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_EDIT)")
    public ResponseEntity<?> setUser(@PathVariable String id) {

        this.userService.setUserRole(id, "USER");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/set-admin/{id}")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_EDIT)")
    public ResponseEntity<?> setAdmin(@PathVariable String id) {

        this.userService.setUserRole(id, "ADMIN");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/set-moderator/{id}")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_EDIT)")
    public ResponseEntity<?> setModerator(@PathVariable String id) {

        this.userService.setUserRole(id, "MODERATOR");

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
