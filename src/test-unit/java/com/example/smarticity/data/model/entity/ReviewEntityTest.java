package com.example.smarticity.data.model.entity;

import com.example.smarticity.data.model.entity.enumer.InstituteType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewEntityTest {

    private String description = "description";
    private InstituteType instituteType = InstituteType.valueOf("HOTEL");


    private String getName = "name";

    private String getInformation = "info";

    private String getAddress = "adress";

    private String getCityId = "cityId";

    @Test
    public void testGetterSetterAll() {
        Review review = new Review();

        review.setDescription(description);
        review.setInstituteType(instituteType);

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

        review.setInstituteId(iBaseInstitution);

        assertEquals(review.getInstituteId().getName(), getName);
        assertEquals(review.getDescription(), description);
        assertEquals(review.getInstituteType(), instituteType);


    }

    @Test
    public void testConstructor() {
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


        Review review = new Review(description, instituteType, iBaseInstitution);

        assertEquals(review.getInstituteId().getName(), getName);
        assertEquals(review.getDescription(), description);
        assertEquals(review.getInstituteType(), instituteType);
    }
}
