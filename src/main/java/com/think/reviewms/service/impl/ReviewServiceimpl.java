package com.think.reviewms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.think.reviewms.exception.ReviewException.NotFoundReviewException;
import com.think.reviewms.model.Review;
import com.think.reviewms.repository.ReviewRepository;
import com.think.reviewms.service.ReviewService;

@Service
public class ReviewServiceimpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceimpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review addReview(Long bookid,Review review) {
        if(bookid != null) {
            review.setBookid(bookid);
        } else {
            throw new NotFoundReviewException("Book ID not found");
        }
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
    public String updateReview(Review review) {
        if (reviewRepository.existsById(review.getReviewId())) {
            reviewRepository.save(review);
            return "Review updated successfully";
        } else {
            throw new NotFoundReviewException("Review not found with id: " + review.getReviewId());
        }
    }

    @Override
    public Review getReviewById(long reviewId,long bookid) {
        if (bookid != 0) {
            reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundReviewException("Book not found with id: " + bookid));
        }
        return reviewRepository.findById(reviewId)
        .orElseThrow(() -> new NotFoundReviewException("Review not found with id: " + reviewId));
    }

    @Override
    public List<Review> getReviewsByBookId(long bookid) {
        return reviewRepository.findByBookid(bookid);
    }
}
