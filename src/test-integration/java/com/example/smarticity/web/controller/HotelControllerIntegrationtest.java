package com.example.smarticity.web.controller;

import com.example.smarticity.base.AbstractTest;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
//@WithMockUser("admin")

public class HotelControllerIntegrationtest extends AbstractTest {


    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;


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
        mockMvc.perform(get("/hotels/all"))
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

        mockMvc.perform(get("/hotels/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(value = "user", password = "1234")
    @Transactional
    public void createNewHotel() throws Exception {


        this.mockMvc.perform(MockMvcRequestBuilders.post("/hotels/create")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{" +
                                        "\"name\": Vitosha" + "," +
                                        "\"information\":  big city" + "," +
                                        "\"address\":  Filip Totyo" + "," +
                                        "\"cityId\":  city1" + "," +
                                        "\"stars\": 3" +
                                        "}")
//                                .content("{ \"name\":\"xyz\"," +
//                                        " \"name\":\"Vitosha\"," +
//                                        " \"information\":\"This is the perfect big city\"" +
//                                        ",\"address\":\"ul. Filip Totyo 15\", " +
//                                        "\"cityId\":\"7f77d891-48f4-43be-8439-f6e41b9b1865\"," +
//                                        " \"stars\":\"3\"}")
                                .characterEncoding("utf-8")

                )
                .andDo(print())
                .andExpect(status().isOk());

    }


//    @Test
//    @WithMockUser(value = "user", password = "1234")
//    @Transactional
//    public void createNewReview() throws Exception {
////        User user =findById(1000).orElse(null);
////        Assert.assertNull(user);
//
//
//        this.mockMvc.perform(post("/review/create")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content("{" +
//                                "\"capital\": 123.0, " +
//                                "\"userId\": 1000" +
//                                "}")
//                        .characterEncoding("utf-8"))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//
//    }
}
