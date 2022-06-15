package com.example.smarticity.web.controller;

import com.example.smarticity.Annotations.DefinePermissions;
import com.example.smarticity.data.service.models.UserServiceModel;
import com.example.smarticity.data.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.example.smarticity.web.controller.BaseController.*;

@DefinePermissions(permissions = {PERM_GET, PERM_CREATE, PERM_EDIT, PERM_DELETE})
@Controller
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginRegisterController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public LoginRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


//    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_GET)")
    @GetMapping("/register")
    public String register(Model model) {

        if(!model.containsAttribute("userServiceModel")){
            model.addAttribute("userServiceModel"
                    ,new UserServiceModel());
        }
        return "register";
    }


    //    @PostMapping("/register")
//    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_GET)")
//    public ResponseEntity<?> registerConfirm(@Valid UserServiceModel userServiceModel, Errors errors) {
//
//        if (errors.hasErrors() ||
//                !userServiceModel.getPassword().equals(userServiceModel.getConfirmPassword())) {
//            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
//        } else {
//            this.userService.register(userServiceModel);
//            return new ResponseEntity<>(HttpStatus.OK);
//
//        }
//    }
    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userServiceModel") UserServiceModel userServiceModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() ||
                !userServiceModel.getPassword().equals(userServiceModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userServiceModel", userServiceModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userServiceModel"
                    , bindingResult);

            return "redirect:/users/register";

        } else {
            this.userService.register(userServiceModel);
            return "redirect:/users/login";

        }


    }


    @GetMapping("/login")
//    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_GET)")
    public String login() {
        return "login";
    }

}
