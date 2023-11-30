package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.*;
import com.example.MyBookShopApp.errorHandler.BookstoreApiWrongParameterException;
import com.example.MyBookShopApp.service.AuthorsService;
import com.example.MyBookShopApp.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
@Api(description = "Book class api")

public class BooksController {

    private final BookService bookService;
    private final AuthorsService authorsService;


    @Autowired
    public BooksController(BookService bookService, AuthorsService authorsService) {
        this.bookService = bookService;
        this.authorsService = authorsService;

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

    @GetMapping("/recent")
    @ApiOperation("method  to get recent books")
    public String recent() {
        return "/books/recent";
    }

    @GetMapping("/popular")
    @ApiOperation("method to get popular books")
    public String popular() {
        return "/books/popular";
    }

    @GetMapping("/author")
    @ApiOperation("method to get author info")
    public String author() {
        return "/books/author";
    }


    @GetMapping("/{slug}")
    public String bookSlug(@PathVariable("slug") String slug, Model model) {
        BookSlugDto bookSlug = bookService.getBookBySlug(slug);
        model.addAttribute("slugBook", bookSlug);

        return "/books/slug";
    }

    @PostMapping("/commenting")
    public String addComment(@RequestParam("comment_author") String author,
                             @RequestParam("comment")String comment,
                             @RequestParam("book_id")Long book_id){
       String slug = bookService.addCommentForBook(author,comment,book_id);

        return "redirect:/books/"+ slug;
    }

    @GetMapping("/api/{slug}")
    @ResponseBody
    public ResponseEntity<ApiResponse<BookSlugDto>> bookSlugApi(@PathVariable("slug") String slug)
            throws BookstoreApiWrongParameterException {
        ApiResponse<BookSlugDto> response = new ApiResponse<>();
        List<BookSlugDto> data = new ArrayList<>();
        data.add(bookService.getBookBySlug(slug));
        response.setDebugMessage("successful request");
        response.setMessage("1");
        response.setStatus(HttpStatus.OK);
        response.setTimeStamp(LocalDateTime.now());
        response.setData(data);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/slug")
    public String slug() {
        return "/books/slug";
    }


    @GetMapping("/recommended")
    @ResponseBody
    public PagedBookSlugDto getRecommendedBooksPageVer3(@RequestParam("offset") Integer offset,
                                                 @RequestParam("limit") Integer limit) {
        return new PagedBookSlugDto(bookService.getPagedRecommendedBooksDataVer3(offset, limit));
    }


    @GetMapping("/recent/page")
    @ResponseBody
    public PagedBookSlugDto getRecentBooksPage(@RequestParam("offset") Integer offset,
                                            @RequestParam("limit") Integer limit) {
        return new PagedBookSlugDto(bookService.getPagedRecentBooksDataVer3(offset, limit));
    }

    @GetMapping("/recent/sort")
    @ResponseBody
    public PagedBookSlugDto getRecentSortedBooksPage(@RequestParam("from") String from,
                                                  @RequestParam("to") String to,
                                                  @RequestParam("offset") Integer offset,
                                                  @RequestParam("limit") Integer limit) {
        return new PagedBookSlugDto(bookService.getPagedSortedBooksDataVer3(offset, limit, from, to));
    }

    @GetMapping("/popular/page")
    @ResponseBody
    public PagedBookSlugDto getPopularBooksPage(@RequestParam("offset") Integer offset,
                                             @RequestParam("limit") Integer limit) {
        return new PagedBookSlugDto(bookService.getPagedRecentBooksDataVer3(offset, limit));
    }

    @PostMapping("/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file") MultipartFile file, @PathVariable("slug") String slug) throws IOException {

        bookService.saveNewBookImage(file, slug);

        return "redirect:/books/" + slug;
    }

    @GetMapping("/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) throws IOException {

        DownloadingBookDto downloadBook = bookService.downloadBook(hash);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + downloadBook.getPath().getFileName().toString())
                .contentType(downloadBook.getMediaType())
                .contentLength(downloadBook.getData().length)
                .body(new ByteArrayResource(downloadBook.getData()));
    }


}
