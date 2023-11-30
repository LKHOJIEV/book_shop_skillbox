package com.example.MyBookShopApp.struct.tags;

import javax.persistence.*;

@Entity
@Table(name = "tag2book")
public class Tag2BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer tag_id;

    private Integer book_id;

    public Tag2BookEntity(Long id, Integer tag_id, Integer book_id) {
        this.id = id;
        this.tag_id = tag_id;
        this.book_id = book_id;
    }

    public Tag2BookEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
