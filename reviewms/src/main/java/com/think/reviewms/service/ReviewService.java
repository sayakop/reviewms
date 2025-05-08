package com.think.reviewms.service;

import java.util.List;


import com.think.reviewms.model.Review;

public interface ReviewService {

    public Review addReview(Long bookid, Review review);
    public boolean deleteReview(long reviewId);
    public Review getReviewById(long reviewId);
    public List<Review> getAllReviews();  
    public String updateReview(Review review);  
}
