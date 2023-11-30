package com.example.MyBookShopApp.struct.book.file;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "book2file")
public class Book2FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Automatic generated id")
    @Column(name = "id", nullable = false)
    private Long id;

    private Long bookId;

    private Long BookFileId;

    public Book2FileEntity(Long id, Long bookId, Long bookFileId) {
        this.id = id;
        this.bookId = bookId;
        BookFileId = bookFileId;
    }

    public Book2FileEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookFileId() {
        return BookFileId;
    }

    public void setBookFileId(Long bookFileId) {
        BookFileId = bookFileId;
    }
}
