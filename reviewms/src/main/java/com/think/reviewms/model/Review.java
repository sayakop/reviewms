package com.think.reviewms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Review {

    @Id
    private long reviewId;
    private String title;
    private String description;
    private long rating;
    private long bookid;


    public Review() {
    }

    public Review(long reviewId, String title, String description, long rating, long bookid) {
        this.reviewId = reviewId;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.bookid = bookid;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
