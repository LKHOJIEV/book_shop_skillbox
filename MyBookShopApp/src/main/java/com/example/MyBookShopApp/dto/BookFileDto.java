package com.example.MyBookShopApp.dto;


import com.example.MyBookShopApp.service.BookFileTypeService;
import com.example.MyBookShopApp.struct.book.BookEntity;
import com.example.MyBookShopApp.struct.book.file.BookFileEntity;
import com.example.MyBookShopApp.struct.book.file.BookFileTypeEntity;

public class BookFileDto {


    private BookFileEntity bookFile;

    private BookFileTypeEntity bookFileType;

    public BookFileDto(BookFileEntity bookFile, BookFileTypeEntity bookFileType) {
        this.bookFile = bookFile;
        this.bookFileType = bookFileType;
    }

    public BookFileDto() {
    }

    public BookFileEntity getBookFile() {
        return bookFile;
    }

    public void setBookFile(BookFileEntity bookFile) {
        this.bookFile = bookFile;
    }

    public BookFileTypeEntity getBookFileType() {
        return bookFileType;
    }

    public void setBookFileType(BookFileTypeEntity bookFileType) {
        this.bookFileType = bookFileType;
    }
}
