package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BookSlugDto;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;


@Controller
@RequestMapping("books/postponed")
public class PostponedController {

    private BookService bookService;

    @Autowired
    public PostponedController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute(name = "postponeContents")
    public List<BookSlugDto> postponeContents() {
        return new ArrayList<>();
    }

    @GetMapping
    public String handleCartRequest(@CookieValue(value = "postponeContents", required = false) String postponeContents,
                                    Model model) {
        if (postponeContents == null || postponeContents.equals("")) {
            model.addAttribute("isPostponedEmpty", true);
        } else {
            model.addAttribute("isPostponedEmpty", false);
            postponeContents = postponeContents.startsWith("/") ? postponeContents.substring(1) : postponeContents;
            postponeContents = postponeContents.endsWith("/") ? postponeContents.substring(0, postponeContents.length() - 1) :
                    postponeContents;
            String[] cookieSlugs = postponeContents.split("/");
            List<BookSlugDto> booksFromCookieSlugs = bookService.bookEntityListBySlugIn(cookieSlugs);
            model.addAttribute("postponeContents", booksFromCookieSlugs);
        }

        return "/books/postponed";
    }

    @PostMapping("/changeBookStatus/postponed/remove/{slug}")
    public String handleRemoveBookFromCartRequest(@PathVariable("slug") String slug, @CookieValue(name =
            "postponeContents", required = false) String postponeContents, HttpServletResponse response, Model model) {
        if (postponeContents != null && !postponeContents.equals("")) {
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(postponeContents.split("/")));
            cookieBooks.remove(slug);
            Cookie cookie = new Cookie("postponeContents", String.join("/", cookieBooks));
            cookie.setPath("/books/postponed");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else {
            model.addAttribute("isPostponedEmpty", true);
        }

        return "/books/postponed";
    }

    @PostMapping("/changeBookStatus/{slug}")
    public String handleChangeBookStatus(@PathVariable("slug") String slug, @CookieValue(name = "postponeContents",
            required = false) String postponeContents, HttpServletResponse response, Model model) {

        if (postponeContents == null || postponeContents.equals("")) {
            Cookie cookie = new Cookie("postponeContents", slug);
            cookie.setPath("/books/postponed");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else if (!postponeContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(postponeContents).add(slug);
            Cookie cookie = new Cookie("postponeContents", stringJoiner.toString());
            cookie.setPath("/books/postponed");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        }

        return "redirect:/books/" + slug;
    }


}
