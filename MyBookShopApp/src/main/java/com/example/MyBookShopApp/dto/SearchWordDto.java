package com.example.MyBookShopApp.dto;

public class SearchWordDto {

    private  String searchWord;

    public SearchWordDto(String searchWord) {
        this.searchWord = searchWord;
    }

    public SearchWordDto() {
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
}
