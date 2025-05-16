package com.think.reviewms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.think.reviewms.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReviewId(long reviewId);
    List<Review> findByBookId(long bookid);
}
