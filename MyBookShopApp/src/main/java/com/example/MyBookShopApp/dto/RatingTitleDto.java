package com.example.MyBookShopApp.dto;

public class RatingTitleDto {

    private Integer ratingTitle;

    private Long ratingCount;

    public RatingTitleDto(Integer ratingTitle, Long ratingCount) {
        this.ratingTitle = ratingTitle;
        this.ratingCount = ratingCount;
    }

    public RatingTitleDto() {
    }

    public Integer getRatingTitle() {
        return ratingTitle;
    }

    public void setRatingTitle(Integer ratingTitle) {
        this.ratingTitle = ratingTitle;
    }

    public Long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Long ratingCount) {
        this.ratingCount = ratingCount;
    }
}
