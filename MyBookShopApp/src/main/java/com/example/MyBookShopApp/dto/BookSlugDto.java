package com.example.MyBookShopApp.dto;

import com.example.MyBookShopApp.struct.author.AuthorEntity;
import com.example.MyBookShopApp.struct.book.BookEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;


@Api(description = "custom class of book")
@ApiModel("Book entity slug model")
public class BookSlugDto {

    @ApiModelProperty("book slug")
    private BookEntity book;

    @ApiModelProperty("authors of book")
    private List<AuthorEntity> authors;

    @ApiModelProperty("files of book")
    private List<BookFileDto> bookFileEntities;

    private Map<Integer,Long> ratingStatistics;

    private List<BookReviewDto> bookReviewList;

    public List<BookReviewDto> getBookReviewList() {
        return bookReviewList;
    }

    public void setBookReviewList(List<BookReviewDto> bookReviewList) {
        this.bookReviewList = bookReviewList;
    }

    public BookSlugDto(BookEntity book, List<AuthorEntity> authors, List<BookFileDto> bookFileEntities, Map<Integer, Long> ratingStatistics, List<BookReviewDto> bookReviewList) {
        this.book = book;
        this.authors = authors;
        this.bookFileEntities = bookFileEntities;
        this.ratingStatistics = ratingStatistics;
        this.bookReviewList = bookReviewList;
    }

    public BookSlugDto(BookEntity book, List<AuthorEntity> authors, List<BookFileDto> bookFileEntities, Map<Integer,Long> ratingStatistics) {
        this.book = book;
        this.authors = authors;
        this.bookFileEntities = bookFileEntities;
        this.ratingStatistics = ratingStatistics;
    }

    public BookSlugDto(BookEntity book, List<AuthorEntity> authors, List<BookFileDto> bookFileEntities) {
        this.book = book;
        this.authors = authors;
        this.bookFileEntities = bookFileEntities;
    }

    public Map<Integer,Long> getRatingStatistics() {
        return ratingStatistics;
    }

    public void setRatingStatistics(Map<Integer,Long> ratingStatistics) {
        this.ratingStatistics = ratingStatistics;
    }

    public List<BookFileDto> getBookFileEntities() {
        return bookFileEntities;
    }

    public void setBookFileEntities(List<BookFileDto> bookFileEntities) {
        this.bookFileEntities = bookFileEntities;
    }

    public BookSlugDto(BookEntity book, List<AuthorEntity> authors) {
        this.book = book;
        this.authors = authors;
    }

    public BookSlugDto() {
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }
}
