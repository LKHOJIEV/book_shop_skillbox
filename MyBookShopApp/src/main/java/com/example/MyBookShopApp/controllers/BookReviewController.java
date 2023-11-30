package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rateBookReview")
public class BookReviewController {

    private BookReviewService bookReviewService;

    @Autowired
    public BookReviewController(BookReviewService bookReviewService) {
        this.bookReviewService = bookReviewService;
    }

    @PostMapping
    public String rateBookReview(@RequestParam("value") int value,
                                 @RequestParam("reviewid")int reviewid) {



        return "redirect:/books/"+ bookReviewService.rateBookReview(value,reviewid);
    }


}
