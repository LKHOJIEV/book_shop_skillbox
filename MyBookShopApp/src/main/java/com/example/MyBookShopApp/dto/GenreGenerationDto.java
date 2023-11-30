package com.example.MyBookShopApp.dto;

import java.util.List;

public class GenreGenerationDto {

    private Long id;

    List<GenreGenerationDto> childGenreList;

    private String slug;

    private String name;

    public GenreGenerationDto() {
    }

    public GenreGenerationDto(Long id, List<GenreGenerationDto> childGenreList, String slug, String name) {
        this.id = id;
        this.childGenreList = childGenreList;
        this.slug = slug;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GenreGenerationDto> getChildGenreList() {
        return childGenreList;
    }

    public void setChildGenreList(List<GenreGenerationDto> childGenreList) {
        this.childGenreList = childGenreList;
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
}
