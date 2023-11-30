package com.example.MyBookShopApp.dto;

import java.util.List;

public class PagedBookSlugDto {

    private Integer counter;
    private List<BookSlugDto> books;

    public PagedBookSlugDto(List<BookSlugDto> books) {
        this.counter = books.size();
        this.books = books;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public List<BookSlugDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookSlugDto> books) {
        this.books = books;
    }

}
