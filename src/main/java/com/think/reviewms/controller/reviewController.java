package com.think.reviewms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.think.reviewms.model.Review;
import com.think.reviewms.response.ReviewResponseHandler;
import com.think.reviewms.service.ReviewService;


@RestController
@RequestMapping("/review")
public class reviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomeMessage()
    {
        return new ResponseEntity<>("Welcome to the Book Details", HttpStatus.OK);
    }
    
    //Get All Reviews from DB

    @GetMapping
    public ResponseEntity<Object> getAllReviews()
    {
        List<Review> reviews = reviewService.getAllReviews();
        if (reviews.isEmpty()) {
            return ReviewResponseHandler.responseBuilder("No Reviews Found", HttpStatus.NOT_FOUND, null);
        } else {
            return ReviewResponseHandler.responseBuilder("Book Details with all reviews Found", HttpStatus.OK, reviews);
        }
    }


    // Get a Particular Review from DB
    @GetMapping("{reviewId}")
    public ResponseEntity<Object> getReviewbyId(@PathVariable long reviewId)
    {
        Review review = reviewService.getReviewById(reviewId,reviewId);
        return review != null
            ? ReviewResponseHandler.responseBuilder("Review Found", HttpStatus.OK, review)
            : ReviewResponseHandler.responseBuilder("Review Not Found", HttpStatus.NOT_FOUND, null);
    }

    @GetMapping("/book/{bookid}")
    public ResponseEntity<Object> getReviewsByBookId(@PathVariable long bookid) {
    List<Review> reviews = reviewService.getReviewsByBookId(bookid);
    return !reviews.isEmpty()
        ? ReviewResponseHandler.responseBuilder("Reviews Found", HttpStatus.OK, reviews)
        : ReviewResponseHandler.responseBuilder("No Reviews Found", HttpStatus.NOT_FOUND, new ArrayList<>());
}
 
    @PostMapping("")
    public ResponseEntity<Object> addReview(@RequestParam long bookid,@RequestBody Review review)
    {
        review.setBookid(bookid);
        // Assuming the book ID is part of the review object
        Review addedReview = reviewService.addReview(bookid, review);
        return ReviewResponseHandler.responseBuilder("Review Details Added Successfully", HttpStatus.CREATED, addedReview);
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<Object> updateReview(@PathVariable long reviewId, @RequestBody Review review)
    {
        review.setReviewId(reviewId);
        review.setDescription(review.getDescription());
        review.setBookid(review.getBookid());
        // Assuming the book ID is part of the review object
        String updatedReview = reviewService.updateReview(review);
        return ReviewResponseHandler.responseBuilder("Review Details Updated Successfully", HttpStatus.OK, updatedReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String>  deleteReview(@PathVariable long reviewId) {
        boolean reviewIsDeleted = reviewService.deleteReview(reviewId);
        if(reviewIsDeleted)
        {
            return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Review or Book Not Found",HttpStatus.NOT_FOUND);

        }
        
    }
        
}
