package com.think.reviewms.model;

public class Review {

    private long reviewId;
    private String description;
    private long bookid;


    public Review() {
    }

    public Review(String description, long reviewId, long bookid) {
        this.description = description;
        this.reviewId = reviewId;
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
}
