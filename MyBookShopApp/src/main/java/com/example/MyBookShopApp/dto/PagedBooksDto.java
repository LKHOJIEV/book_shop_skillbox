package com.example.MyBookShopApp.dto;

import java.util.List;

public class PagedBooksDto {

    private Integer counter;
    private List<BookDataFullDto> books;

    public PagedBooksDto(List<BookDataFullDto> books) {
        this.counter = books.size();
        this.books = books;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public List<BookDataFullDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDataFullDto> books) {
        this.books = books;
    }
}



