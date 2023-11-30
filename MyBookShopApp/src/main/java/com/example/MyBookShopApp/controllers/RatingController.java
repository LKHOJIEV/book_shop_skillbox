package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BookSlugDto;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books/rating/")
public class RatingController {

    private final RatingService ratingService;
    private final BookService bookService;

    @Autowired
    public RatingController(RatingService ratingService, BookService bookService) {
        this.ratingService = ratingService;
        this.bookService = bookService;
    }

    @ModelAttribute(name = "postponeContents")
    public List<BookSlugDto> postponeContents() {
        return new ArrayList<>();
    }


    @PostMapping("/changeBookStatus/{slug}")
    public String handleChangeBookStatus(@PathVariable("slug") String slug,
                                         @RequestParam("value") Integer value,
                                         HttpServletResponse response,
                                         Model model) {
        ratingService.saveBookRating(slug,value);
        BookSlugDto bookSlug = bookService.getBookBySlug(slug);
        model.addAttribute("slugBook", bookSlug);
        model.addAttribute("ratingChange",true);

        return "redirect:/books/" + slug;
    }


}
