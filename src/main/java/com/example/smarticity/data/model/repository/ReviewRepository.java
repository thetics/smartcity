package com.example.smarticity.data.model.repository;

import com.example.smarticity.data.model.entity.Review;
import com.example.smarticity.data.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    Review findReviewById(String id);
}
