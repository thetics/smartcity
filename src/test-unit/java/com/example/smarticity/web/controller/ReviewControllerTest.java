package com.example.smarticity.web.controller;

import com.example.smarticity.data.model.entity.IBaseInstitution;
import com.example.smarticity.data.model.entity.Review;
import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.service.models.ReviewServiceModel;
import com.example.smarticity.data.service.services.implementations.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

    private String description = "description";
    private InstituteType instituteType = InstituteType.valueOf("HOTEL");
    private String iBaseInstitution1 = "instituteId";
    private String getName = "name";

    private String getInformation = "info";

    private String getAddress = "adress";

    private String getCityId = "cityId";
    @Mock
    ReviewServiceImpl reviewService;

    @Mock
    Errors errors;

    @InjectMocks
    ReviewController reviewController;


    @Test
    void viewAllReviews() {
        IBaseInstitution iBaseInstitution = new IBaseInstitution() {
            @Override
            public String getName() {
                return getName;
            }

            @Override
            public String getInformation() {
                return getInformation;
            }

            @Override
            public String getAddress() {
                return getAddress;
            }

            @Override
            public String getCityId() {
                return getCityId;
            }
        };

        List<Review> models = new ArrayList<>();
        Review model = new Review(description, instituteType, iBaseInstitution);
        Review model1 = new Review(description, instituteType, iBaseInstitution);
        Review model2 = new Review(description, instituteType, iBaseInstitution);
        models.add(model);
        models.add(model1);
        models.add(model2);



        Mockito.when(reviewService.getAllReviews()).thenReturn(models);

        ResponseEntity<List<Review>> result = reviewController.getAllReviews();

        assertEquals(result.getBody().size(),models.size());

    }


    @Test
    void createHospital() {
        ReviewServiceModel model = new ReviewServiceModel(description, instituteType, iBaseInstitution1);

        Mockito.when(reviewService.createReview(Mockito.any(ReviewServiceModel.class)))
                .thenReturn(model);

        ReviewServiceModel result = (ReviewServiceModel) reviewController.createReview(model, errors).getBody();

        assertNotNull(result);
        assertEquals(model.getDescription(), result.getDescription());
        assertEquals(model.getInstituteType(), result.getInstituteType());
        assertEquals(model.getInstituteId(), result.getInstituteId());
    }

}
