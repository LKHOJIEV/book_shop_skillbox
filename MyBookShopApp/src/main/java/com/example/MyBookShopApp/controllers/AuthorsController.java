package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.service.AuthorsService;
import com.example.MyBookShopApp.struct.author.AuthorEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/authors")
@Api(description = "Authors class api")
public class AuthorsController {


    private final AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }


    @ModelAttribute("Authors Map")
    public Map<String, List<AuthorEntity>> authorsMap(){
        return authorsService.getAuthorList();
    }


    @GetMapping("/index")
    @ApiOperation("method to get author profile")
    public String authors(Model model) {
        model.addAttribute("authorsData",authorsService.getAuthorList());
        return "authors/index";
    }
    @GetMapping("/slug")
    public String slug(){
        return "authors/slug";
    }


    @GetMapping("/list")
    @ResponseBody
    public Map<String,List<AuthorEntity>> AuthorList(){
        return authorsService.getAuthorList();
    }



}
