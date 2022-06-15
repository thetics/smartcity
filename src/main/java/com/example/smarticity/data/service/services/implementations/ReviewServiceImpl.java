package com.example.smarticity.data.service.services.implementations;

import com.example.smarticity.data.model.entity.IBaseInstitution;
import com.example.smarticity.data.model.entity.Review;
import com.example.smarticity.data.model.entity.User;
import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.model.repository.BusinessRepository;
import com.example.smarticity.data.model.repository.HospitalRepository;
import com.example.smarticity.data.model.repository.HotelRepository;
import com.example.smarticity.data.model.repository.ReviewRepository;
import com.example.smarticity.data.service.models.ReviewServiceModel;
import com.example.smarticity.data.service.services.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final HotelRepository hotelRepository;
    private final BusinessRepository businessRepository;
    private final HospitalRepository hospitalRepository;
    private final ModelMapper modelMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, HotelRepository hotelRepository, BusinessRepository businessRepository, HospitalRepository hospitalRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.hotelRepository = hotelRepository;
        this.businessRepository = businessRepository;
        this.hospitalRepository = hospitalRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<Review> getAllReviews() {

        return this.reviewRepository.findAll();


    }

    @Override
    public ReviewServiceModel createReview(ReviewServiceModel reviewServiceModel) {

        IBaseInstitution iBaseInstitution;
        if (reviewServiceModel.getInstituteType().equals(InstituteType.HOTEL)) {
            iBaseInstitution = hotelRepository.findById(reviewServiceModel.getInstituteId()).orElse(null);
        } else if (reviewServiceModel.getInstituteType().equals(InstituteType.BUSINESS)) {
            iBaseInstitution = businessRepository.findById(reviewServiceModel.getInstituteId()).orElse(null);
        } else if (reviewServiceModel.getInstituteType().equals(InstituteType.HOSPITAL)) {
            iBaseInstitution = hospitalRepository.findById(reviewServiceModel.getInstituteId()).orElse(null);
        } else {
            return null;
            // todo throw exception
        }

        if (iBaseInstitution == null) {
            return null;
            // todo throw exception
        }

        Review review = new Review(reviewServiceModel.getDescription(), reviewServiceModel.getInstituteType(), iBaseInstitution);

        return this.modelMapper.map(this.reviewRepository.saveAndFlush(review), ReviewServiceModel.class);
    }

    @Override
    public void deleteReview(String id) {
        Review review= this.reviewRepository.findReviewById(id);
        this.reviewRepository.delete(review);
        
    }
}