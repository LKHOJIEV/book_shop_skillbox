package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.*;
import com.example.MyBookShopApp.errorHandler.EmptySearchException;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.TagService;
import com.example.MyBookShopApp.struct.tags.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {

    private final BookService bookService;
    private final TagService tagService;

    @Autowired
    public MainPageController(BookService bookService, TagService tagService) {
        this.bookService = bookService;
        this.tagService = tagService;
    }

    @GetMapping("/")
    public String mainPage0() {
        return "index";
    }


    @ModelAttribute("getPagedRecommendedBooksDataVer3")
    public List<BookSlugDto> getPagedRecommendedBooksDataVer3() {
        return bookService.getPagedRecommendedBooksDataVer3(0, 15);
    }


    @ModelAttribute("getPagedRecentBooksDataVer3")
    public List<BookSlugDto> getPagedRecentBooksDataVer3() {
        return bookService.getPagedRecentBooksDataVer3(0, 15);
    }

    @ModelAttribute("getPagedPopularBooksDataVer3")
    public List<BookSlugDto> getPagedPopularBooksData3() {
        return bookService.getPagedPopularBooksDataVer3(0, 15);
    }

    @ModelAttribute("getPagedSearchedBooksResult")
    public List<BookDataFullDto> getPagedSearchBooksData() {
        return new ArrayList<>();
    }

    @GetMapping("/index")
    public String mainPage() {
        return "index";
    }

    @ModelAttribute("getAllTags")
    public List<TagEntity> getTags() {
        return tagService.getAllTags();
    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResult(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                  Model model) throws EmptySearchException {
        if (searchWordDto != null) {
            model.addAttribute("searchedWord", searchWordDto.getSearchWord());
            model.addAttribute("getSearchedBooksCount", bookService.getSearchedBooksCount(searchWordDto.getSearchWord()));
            model.addAttribute("getPagedSearchedBooksResult",
                    bookService.getPagedSearchedBooksDataVer3(searchWordDto.getSearchWord(), 0, 5));
            return "/search/index";
        } else {
            throw new EmptySearchException("Поиск по null невозможен");
        }
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public PagedBookSlugDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit,
                                           @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                           Model model) throws EmptySearchException {
        if (searchWordDto != null) {
            model.addAttribute("searchedWord", searchWordDto.getSearchWord());
            model.addAttribute("getPagedSearchedBooksResult",
                    bookService.getPagedSearchedBooksDataVer3(searchWordDto.getSearchWord(), offset, limit));
            return new PagedBookSlugDto(bookService.getPagedSearchedBooksDataVer3(searchWordDto.getSearchWord(), offset, limit));
        } else {
            throw new EmptySearchException("Поиск по null невозможен");
        }
    }

}
