package com.example.smarticity.web.controller;

import com.example.smarticity.base.AbstractTest;
import com.example.smarticity.data.model.entity.Review;
import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.model.repository.ReviewRepository;
import com.example.smarticity.data.service.services.implementations.ReviewServiceImpl;
import com.nimbusds.jose.util.StandardCharset;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @RunWith(SpringRunner.class)
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @ActiveProfiles("test")
    @WithMockUser("admin")

public class ReviewControllerIntegrationtest extends AbstractTest {

    private String description = "description";
    private InstituteType instituteType = InstituteType.valueOf("HOTEL");
    private String iBaseInstitution1 = "instituteId";
    private String getName = "name";
    private String getInformation = "info";
    private String getAddress = "adress";
    private String getCityId = "cityId";

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewServiceImpl reviewService;

    @BeforeEach
    @Override
    public void setup() {
       this.mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @Transactional
    void shouldThrow403ifNotLogged() throws Exception {
        mockMvc.perform(get("/reviews/all"))
                .andExpect(status().isForbidden());
    }
    @Test
    @Transactional
    void shouldReturnOkIfLogged() throws Exception {

        mockMvc.perform(get("/users/login?username=user&password=1234"))

                .andExpect(status().isOk());
    }
        @Test
        @WithMockUser(value = "user", password = "1234")
        @Transactional
        void shouldReturnAllReviewsIflogged() throws Exception {

            mockMvc.perform(get("/reviews/all"))

                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk());
        }

    @Test
    @Transactional
    public void getUsersLoginPage() throws Exception {

        List<Review> balances = reviewRepository.findAll();
        assertEquals(9, balances.size());

        this.mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/html;charset=UTF-8")))

                .andDo(print());
//                .andExpect(jsonPath("$[*]", hasSize(balances.size())))
//                .andExpect(jsonPath("$.[0].userId", is(balances.get(0).getUserId())))
//                .andExpect(jsonPath("$.[0].investId", is(balances.get(0).getInvestId())))
//                .andExpect(jsonPath("$.[0].capital", is(balances.get(0).getCapital())));
    }
}
