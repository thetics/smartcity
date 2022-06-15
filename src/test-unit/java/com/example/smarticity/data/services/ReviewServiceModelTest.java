package com.example.smarticity.data.services;

import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.service.models.ReviewServiceModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewServiceModelTest {

    private String description = "description";
    private InstituteType instituteType = InstituteType.valueOf("HOTEL");
    private String iBaseInstitution = "instituteId";


    @Test
    public void testGetterSetterAll() {
        ReviewServiceModel review = new ReviewServiceModel();

        review.setDescription(description);
        review.setInstituteType(instituteType);
        review.setInstituteId(iBaseInstitution);


        assertEquals(review.getInstituteId(), iBaseInstitution);

        assertEquals(review.getDescription(), description);

        assertEquals(review.getInstituteType(), instituteType);


    }

    @Test
    public void testConstructor() {


        ReviewServiceModel review = new ReviewServiceModel(description, instituteType, iBaseInstitution);

        assertEquals(review.getInstituteId(),iBaseInstitution);
        assertEquals(review.getDescription(), description);
        assertEquals(review.getInstituteType(), instituteType);
    }
}
