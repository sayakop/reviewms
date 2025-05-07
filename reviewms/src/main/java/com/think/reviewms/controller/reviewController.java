package com.think.reviewms.controller;

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
    
    //Get All Books from DB

    @GetMapping
    public List<Review> getAllReviews()  
    {
        return reviewService.getAllReviews();
    }

    // Get a Particular Book from DB
    @GetMapping("{reviewId}")
    public ResponseEntity<Object> getReviewbyId(@PathVariable long reviewId)
    {
        return ReviewResponseHandler.responseBuilder
        ("Requested Book details are here", HttpStatus.OK, reviewService.getReviewbyId(reviewId));
    }
 
    @PostMapping("")
    public ResponseEntity<Object> addReview(@RequestBody Review review)
    {
        reviewService.addReview(review);
        return ReviewResponseHandler.responseBuilder("Book Details Added Successfully", HttpStatus.CREATED, review);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Object> updateReview(@PathVariable long reviewId, @RequestBody Review review)
    {
        review.setReviewId(reviewId); // Set the ID of the review to be updated
        String updatedReview = reviewService.updateReview(review);
        return ReviewResponseHandler.responseBuilder("Book Details Updated Successfully", HttpStatus.OK, updatedReview);
    }

   // @PutMapping("/{bookid}/assignvendor")
   // public ResponseEntity<Object> assignVendorBook(
       // @PathVariable Long bookid,@RequestParam String vendorId) {

            //Book updatedBook = bookService.assignVendorBook(bookid, vendorId); // No longer returns Object
            //return BookResponseHandler.responseBuilder(
               // "Vendor assigned to book successfully",
               // HttpStatus.OK,
               // updatedBook // Return the updated book
       // );
  //  }

    //@ExceptionHandler(EntityNotFoundException.class) // Handle the exception
    //public ResponseEntity<Object> NotFoundBookException(EntityNotFoundException ex) {
       // return BookResponseHandler.responseBuilder(
                //ex.getMessage(), // Get the message from the exception
               // HttpStatus.NOT_FOUND,
                //null // No data needed in the body for this error
      //  );
   // }

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
