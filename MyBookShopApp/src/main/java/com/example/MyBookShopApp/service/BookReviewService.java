package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.repository.BookReviewLikesRepository;
import com.example.MyBookShopApp.repository.BookReviewRepository;
import com.example.MyBookShopApp.struct.book.review.BookReviewEntity;
import com.example.MyBookShopApp.struct.book.review.BookReviewLikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookReviewService {

    private BookReviewRepository bookReviewRepository;

    private BookService bookService;

    private BookReviewLikesRepository bookReviewLikesRepository;

    @Autowired
    public BookReviewService(BookReviewRepository bookReviewRepository, BookService bookService, BookReviewLikesRepository bookReviewLikesRepository) {
        this.bookReviewRepository = bookReviewRepository;
        this.bookService = bookService;
        this.bookReviewLikesRepository = bookReviewLikesRepository;
    }

    public String rateBookReview(int value, int reviewId) {

        BookReviewEntity bookReviewEntity = bookReviewRepository.getOne((long)reviewId);
        BookReviewLikeEntity bookReviewLikeEntity = new BookReviewLikeEntity(
                Math.toIntExact(bookReviewEntity.getId()),
                Math.toIntExact(bookReviewEntity.getUserId()),
                LocalDateTime.now(),
                value
        );
        bookReviewLikesRepository.save(bookReviewLikeEntity);

        return bookService.getBookById(bookReviewEntity.getBookId()).getSlug();
    }
}
