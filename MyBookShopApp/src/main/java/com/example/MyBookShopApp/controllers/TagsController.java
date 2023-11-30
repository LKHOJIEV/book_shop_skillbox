package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.TagDto;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tags")
public class TagsController {

    private final BookService bookService;
    private final TagService tagService;

    @Autowired
    public TagsController(BookService bookService, TagService tagService) {
        this.bookService = bookService;
        this.tagService = tagService;
    }
    @GetMapping("/index")
    public String tag(){
        return "/tags/index";
    }

    @GetMapping(value = "/{tagId}")
    public String booksByTag(@PathVariable(value ="tagId") Long tagDto, Model model){
        model.addAttribute("tag",tagService.getTagById(tagDto));
        model.addAttribute("pagedBooksByTag",tagService.getPagedBooksByTagId(tagDto,0,15));

        return "/tags/index";
    }

    @GetMapping("/page")
    public String booksByTagMain(@RequestParam("tagName") String tagName, Model model){

        model.addAttribute("pagedBooksByTag",tagService.getPagedBooksByTagName(tagName,0,15).getContent());

        return "/tags/index";
    }

}
