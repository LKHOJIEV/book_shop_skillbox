package com.example.MyBookShopApp.dto;

import com.example.MyBookShopApp.struct.book.review.BookReviewEntity;

public class BookReviewDto {
    private BookReviewEntity bookReviewEntity;
    private String username;
    private Long countLikes;
    private Long countDisLikes;

    public BookReviewDto() {
    }

    public BookReviewDto(BookReviewEntity bookReviewEntity, String username, Long countLikes, Long countDisLikes) {
        this.bookReviewEntity = bookReviewEntity;
        this.username = username;
        this.countLikes = countLikes;
        this.countDisLikes = countDisLikes;
    }

    public BookReviewEntity getBookReviewEntity() {
        return bookReviewEntity;
    }

    public void setBookReviewEntity(BookReviewEntity bookReviewEntity) {
        this.bookReviewEntity = bookReviewEntity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(Long countLikes) {
        this.countLikes = countLikes;
    }

    public Long getCountDisLikes() {
        return countDisLikes;
    }

    public void setCountDisLikes(Long countDisLikes) {
        this.countDisLikes = countDisLikes;
    }

}
