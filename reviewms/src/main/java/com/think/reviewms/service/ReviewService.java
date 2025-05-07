package com.think.reviewms.service;

import java.util.List;

import com.think.reviewms.model.Review;

public interface ReviewService {

    public Review addReview(Review review);
    public boolean deleteReview(long reviewId);
    public List<Review> getAllReviews();  
    public Review getReviewbyId(long reviewId);
    public String updateReview(Review review);  
}
