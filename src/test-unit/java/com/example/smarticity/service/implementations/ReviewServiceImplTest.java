package com.example.smarticity.service.implementations;

import com.example.smarticity.data.model.entity.*;
import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.model.entity.enumer.Status;
import com.example.smarticity.data.model.repository.BusinessRepository;
import com.example.smarticity.data.model.repository.HospitalRepository;
import com.example.smarticity.data.model.repository.HotelRepository;
import com.example.smarticity.data.model.repository.ReviewRepository;
import com.example.smarticity.data.service.models.ReviewServiceModel;
import com.example.smarticity.data.service.services.implementations.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {

    private String description = "description";
    private Status status = Status.valueOf("CONFIRMED");
    private Date dateTo = new Date();
    private Date dateFrom = new Date();
    private InstituteType instituteType = InstituteType.valueOf("HOTEL");

    private String iBaseInstitution1 = "instituteId";

    private String getName = "name";

    private String getInformation = "info";

    private String getAddress = "adress";

    private String getCityId = "cityId";

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    HotelRepository hotelRepository;
    @Mock
    BusinessRepository businessRepository;
    @Mock
    HospitalRepository hospitalRepository;
    @InjectMocks
    ReviewServiceImpl reviewService;


    @Test
    void createReview() {
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


        Review reviewEntity = new Review(description, instituteType, iBaseInstitution);


        ReviewServiceModel model = new ReviewServiceModel(description, instituteType, iBaseInstitution1);

        Hotel hotel = new Hotel("name", "information", "address", "cityId"); // todo insert data
        Mockito.when(hotelRepository.findById(Mockito.any())).thenReturn(Optional.of(hotel)); // other repos

//        Business business = new Business("name", "information", "address", "cityId");
//        Mockito.when(businessRepository.findById(Mockito.any())).thenReturn(Optional.of(business));
//        Hospital hospital=new Hospital("name", "information", "address", "cityId");
//        Mockito.when(hospitalRepository.findById(Mockito.any())).thenReturn(Optional.of(hospital));

//        Mockito.when(modelMapper.map(Mockito.any(ReviewServiceModel.class), Mockito.any())).thenReturn(reviewEntity);
        Mockito.when(reviewRepository.saveAndFlush(Mockito.any(Review.class))).thenReturn(reviewEntity);

        Mockito.when(modelMapper.map(Mockito.any(Review.class), Mockito.any())).thenReturn(model);

        ReviewServiceModel result = reviewService.createReview(model);

        assertNotNull(result);
        assertEquals(model.getDescription(), result.getDescription());
        assertEquals(model.getInstituteType(), result.getInstituteType());
        assertEquals(model.getInstituteId(), result.getInstituteId());
    }


    @Test
    void findAllReview() {


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

        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(description, instituteType, iBaseInstitution));
        reviews.add(new Review(description, instituteType, iBaseInstitution));
        reviews.add(new Review(description, instituteType, iBaseInstitution));
        reviews.add(new Review(description, instituteType, iBaseInstitution));

        Mockito.when(reviewRepository.findAll()).thenReturn(reviews);
        List<Review> reviewsToCompare = reviewService.getAllReviews();

        assertEquals(reviewRepository.findAll().size(), reviews.size());
        for (int i = 0; i < reviews.size(); i++) {
            assertEquals(reviews.get(i).getDescription(), reviewsToCompare.get(i).getDescription());
            assertEquals(reviews.get(i).getInstituteType(), reviewsToCompare.get(i).getInstituteType());
        }
    }

}
