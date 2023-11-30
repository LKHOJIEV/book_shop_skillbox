package com.example.MyBookShopApp.struct.author;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "author")
@ApiModel("Author entity model")
public class AuthorEntity {
    @Id
    @ApiModelProperty("Automatic generated id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ApiModelProperty("Author photo")
    private String photo;
    @ApiModelProperty("")
    private String slug;
    @ApiModelProperty("Author name")
    private String name;
    @ApiModelProperty("Author descriptions")
    private String description;

    public AuthorEntity() {
    }

    public AuthorEntity(Long id, String photo, String slug, String name, String description) {
        this.id = id;
        this.photo = photo;
        this.slug = slug;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
