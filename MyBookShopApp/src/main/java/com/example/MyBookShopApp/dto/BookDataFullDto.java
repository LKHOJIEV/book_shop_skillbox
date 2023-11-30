package com.example.MyBookShopApp.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

@Api(description = "custom class of book")
@ApiModel("Book entity full model")
public class BookDataFullDto {

    @ApiModelProperty("Automatic generated id")
    private Long id;
    @ApiModelProperty("publication date")
    private String pub_date;

    private int is_bestseller;
    private String slug;
    @ApiModelProperty("title of book")
    private String title;
    @ApiModelProperty("image of book")
    private String image;
    @ApiModelProperty("description of book")
    private String description;
    @ApiModelProperty("price of book")
    private Double price;
    @ApiModelProperty("discount for book's price")
    private Double discount;
    @ApiModelProperty("book's author name")
    private String author;
    @ApiModelProperty("discount sum of book")
    private Double disc_sum;

    public BookDataFullDto(Long id, String pub_date, int is_bestseller, String slug, String title, String image,
                           String description, Double price, Double discount, String author, Double disc_sum) {
        this.id = id;
        this.pub_date = pub_date;
        this.is_bestseller = is_bestseller;
        this.slug = slug;
        this.title = title;
        this.image = image;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.author = author;
        this.disc_sum = disc_sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public int getIs_bestseller() {
        return is_bestseller;
    }

    public void setIs_bestseller(int is_bestseller) {
        this.is_bestseller = is_bestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getDisc_sum() {
        return disc_sum;
    }

    public void setDisc_sum(Double disc_sum) {
        this.disc_sum = disc_sum;
    }


}
