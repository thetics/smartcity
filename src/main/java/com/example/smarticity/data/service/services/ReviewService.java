package com.example.smarticity.data.service.services;

import com.example.smarticity.data.model.entity.Review;
import com.example.smarticity.data.service.models.ReviewServiceModel;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();

    ReviewServiceModel createReview(ReviewServiceModel reviewServiceModel);

    void deleteReview(String id);
}
