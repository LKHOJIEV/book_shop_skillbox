package com.example.MyBookShopApp.struct.book.rating;

import javax.persistence.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "book_rating_entity")
public class BookRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "book_rating_seq")
    @SequenceGenerator(
            name = "book_rating_seq",
            initialValue = 11,
            allocationSize = 1

    )
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "add_date")
    private String date;
    @Column(name = "book_slug")
    private String bookSlug;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "value")
    private Integer value;

    public BookRatingEntity() {
    }

    public String getString() {
        return date;
    }

    public void setString(String date) {
        this.date = date;
    }

    public BookRatingEntity(Long id, String date, String bookSlug, Long userId, Integer value) {
        this.id = id;
        this.date = date;
        this.bookSlug = bookSlug;
        this.userId = userId;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookSlug() {
        return bookSlug;
    }

    public void setBookSlug(String bookSlug) {
        this.bookSlug = bookSlug;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
