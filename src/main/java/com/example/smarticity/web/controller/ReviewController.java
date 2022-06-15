package com.example.smarticity.web.controller;

import com.example.smarticity.Annotations.DefinePermissions;
import com.example.smarticity.data.model.entity.Review;
import com.example.smarticity.data.service.models.ReviewServiceModel;
import com.example.smarticity.data.service.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static com.example.smarticity.web.controller.BaseController.*;

@DefinePermissions(permissions = {PERM_GET, PERM_CREATE, PERM_EDIT, PERM_DELETE})
@Controller
@RequestMapping(path = "/reviews",produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewController extends BaseController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @Transactional
    @GetMapping("/all")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_GET)")
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<>(this.reviewService.getAllReviews(), HttpStatus.OK);
    }


    @Transactional
    @PostMapping("/create" )
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_CREATE)")
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewServiceModel reviewServiceModel, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        this.reviewService.createReview(reviewServiceModel);
        return new ResponseEntity<>(reviewServiceModel, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasPermission(this.getClass().getSimpleName(),this.PERM_DELETE)")
    public ResponseEntity<?> deleteReview(@PathVariable String id) {


        this.reviewService.deleteReview(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
