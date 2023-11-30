package com.example.MyBookShopApp.data;

import java.util.Date;

public class Book {
    private Integer id;
    private String pub_date;
    private int is_bestseller;
    private String slug;
    private String title;
    private String image;
    private String description;
    private Double price;
    private Double discount;

    public Book(Integer id, String pub_date, int is_bestseller, String slug, String title, String image, String description, Double price, Double discount) {
        this.id = id;
        this.pub_date = pub_date;
        this.is_bestseller = is_bestseller;
        this.slug = slug;
        this.title = title;
        this.image = image;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", pub_date=" + pub_date +
                ", is_bestseller=" + is_bestseller +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
