package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.GenreDto;
import com.example.MyBookShopApp.dto.GenreGenerationDto;
import com.example.MyBookShopApp.service.GenreService;
import com.example.MyBookShopApp.struct.genre.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/index")
    public String genres(Model model){

        model.addAttribute("genreGeneration",genreService.genreEntityListChild());

        return "/genres/index";
    }

    @GetMapping("/index/generation/tree")
    @ResponseBody
    public List<GenreGenerationDto> generationList(){
        return genreService.genreEntityListChild();
    }

    @GetMapping("/slug")
    public String slug(){
        return "/genres/slug";
    }




}
