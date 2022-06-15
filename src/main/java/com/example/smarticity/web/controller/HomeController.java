package com.example.smarticity.web.controller;

import com.example.smarticity.data.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller()
@RequestMapping(path = "/admin", produces = MediaType.TEXT_HTML_VALUE)
public class HomeController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public HomeController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/review")
    public String getAllReviews(Model model) {
        return "allReviews";
    }
}
