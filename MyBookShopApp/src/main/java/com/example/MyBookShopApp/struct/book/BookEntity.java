package com.example.MyBookShopApp.struct.book;

import com.example.MyBookShopApp.struct.book.file.BookFileEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@ApiModel("Book entity model")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Automatic generated id")
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiModelProperty("publication date of book")
    @Column(name = "pub_date")
    private String pubDate;

    @ApiModelProperty("")
    @Column(name = "is_bestseller")
    private int isBestseller;
    private String slug;
    @ApiModelProperty("Book's title")
    private String title;
    @ApiModelProperty("Book's image")
    private String image;
    @ApiModelProperty("Book's descriptions")
    @Column(length = 1000)
    private String description;
    @ApiModelProperty("Book's price")
    private Double price;
    @ApiModelProperty("Discount for book")
    private Double discount;

    public Double discount_sum() {
        return Math.floor(this.price * (100 - this.discount)/100);
    }

    public BookEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public int getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(int isBestseller) {
        this.isBestseller = isBestseller;
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

    public BookEntity(Long id, String pubDate, int isBestseller, String slug, String title, String image, String description, Double price, Double discount) {
        this.id = id;
        this.pubDate = pubDate;
        this.isBestseller = isBestseller;
        this.slug = slug;
        this.title = title;
        this.image = image;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }
}
