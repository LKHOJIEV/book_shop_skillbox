package com.example.MyBookShopApp.dto;



public class GenreDto {

    private Long id;

    GenreDto parentGenre;

    private String slug;

    private String name;


    public GenreDto(Long id, GenreDto parentGenre, String slug, String name) {
        this.id = id;
        this.parentGenre = parentGenre;
        this.slug = slug;
        this.name = name;
    }

    public GenreDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GenreDto getGenreDto() {
        return parentGenre;
    }

    public void setGenreDto(GenreDto parentGenre) {
        this.parentGenre = parentGenre;
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
