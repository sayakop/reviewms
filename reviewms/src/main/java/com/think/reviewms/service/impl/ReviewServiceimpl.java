package com.think.reviewms.service.impl;

import java.util.List;

import com.think.reviewms.exception.ReviewException.NotFoundReviewException;
import com.think.reviewms.model.Review;
import com.think.reviewms.repository.ReviewRepository;
import com.think.reviewms.service.ReviewService;

public class ReviewServiceimpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceimpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public boolean deleteReview(long reviewId) {
        try {
            reviewRepository.deleteById(reviewId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewbyId(long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundReviewException("Review not found with id: " + reviewId));
    }

    @Override
    public String updateReview(Review review) {
        if (reviewRepository.existsById(review.getReviewId())) {
            reviewRepository.save(review);
            return "Review updated successfully";
        } else {
            throw new NotFoundReviewException("Review not found with id: " + review.getReviewId());
        }
    }
}
