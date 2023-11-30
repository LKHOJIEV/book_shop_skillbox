package com.example.MyBookShopApp.data;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Author {

    private Integer id;
    private String photo;
    private String slug;
    private String name;
    private String description;

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", photo='" + photo + '\'' +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Author(Integer id, String photo, String slug, String name, String description) {
        this.id = id;
        this.photo = photo;
        this.slug = slug;
        this.name = name;
        this.description = description;
    }
}
