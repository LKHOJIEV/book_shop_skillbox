package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.dto.*;
import com.example.MyBookShopApp.repository.*;
import com.example.MyBookShopApp.struct.author.AuthorEntity;
import com.example.MyBookShopApp.struct.book.BookEntity;
import com.example.MyBookShopApp.struct.book.file.BookFileEntity;
import com.example.MyBookShopApp.struct.book.file.BookFileTypeEntity;
import com.example.MyBookShopApp.struct.book.review.BookReviewEntity;
import com.example.MyBookShopApp.struct.book.review.BookReviewLikeEntity;
import com.example.MyBookShopApp.struct.user.UserEntity;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;
    private final BookRepository bookRepository;
    private final AuthorsService authorsService;
    private final ResourceStorage storage;
    private final BookFileService bookFileService;
    private final BookFileTypeService bookFileTypeService;
    private final RatingService ratingService;
    private final UserRepository userRepository;

    private final BookReviewLikesRepository bookReviewLikesRepository;
    private final UserService userService;
    private final BookReviewRepository bookReviewRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorsService authorsService, ResourceStorage storage, BookFileService bookFileService, BookFileTypeService bookFileTypeService, RatingService ratingService,
                       RatingRepository ratingRepository, UserRepository userRepository, BookReviewLikesRepository bookReviewLikesRepository, UserService userService, BookReviewRepository bookReviewRepository) {
        this.bookRepository = bookRepository;
        this.authorsService = authorsService;
        this.storage = storage;
        this.bookFileService = bookFileService;
        this.bookFileTypeService = bookFileTypeService;
        this.ratingService = ratingService;
        this.userRepository = userRepository;
        this.bookReviewLikesRepository = bookReviewLikesRepository;
        this.userService = userService;
        this.bookReviewRepository = bookReviewRepository;
    }

    /**/
    public Integer getSearchedBooksCount(String searchWord) {
        Pageable page = PageRequest.of(0, 999999999);
        return bookRepository.pagedSearchedBooks(searchWord, page).getContent().size();

        //return bookRepository.findBookEntitiesByTitleContains(searchWord).stream().distinct().collect(Collectors.toSet()).size();
    }

    public BookEntity getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    /**/
    public List<BookSlugDto> getPagedRecommendedBooksDataVer3(Integer offset, Integer limit) {
        Pageable page = PageRequest.of(offset, limit);
        List<BookSlugDto> bookSlugDto = new ArrayList<>();
        for (BookEntity book : bookRepository.findAll(page).getContent()) {
            List<AuthorEntity> authors = authorsService.getAuthorByBookId(book.getId().intValue());
            bookSlugDto.add(new BookSlugDto(book, authors, null));
        }

        return bookSlugDto;
    }

    /**/
    public List<BookSlugDto> getPagedRecentBooksDataVer3(Integer offset, Integer limit) {
        Pageable page = PageRequest.of(offset, limit);

        String data_from = LocalDate.now().minusDays(60).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<BookSlugDto> bookSlugDto = new ArrayList<>();

        for (BookEntity book : bookRepository.getBookEntitiesByPubDateAfterOrderByPubDateDesc(data_from, page).getContent()) {
            List<AuthorEntity> authors = authorsService.getAuthorByBookId(book.getId().intValue());
            bookSlugDto.add(new BookSlugDto(book, authors, null));
        }

        return bookSlugDto;

    }

    /**/
    public List<BookSlugDto> getPagedPopularBooksDataVer3(Integer offset, Integer limit) {

        Pageable page = PageRequest.of(offset, limit);

        List<BookSlugDto> bookSlugDto = new ArrayList<>();

        for (BookEntity book : bookRepository.getBookEntitiesByIsBestsellerIsOrderByPubDateDesc(1, page).getContent()) {
            List<AuthorEntity> authors = authorsService.getAuthorByBookId(book.getId().intValue());
            bookSlugDto.add(new BookSlugDto(book, authors, null));
        }

        return bookSlugDto;
    }

    /**/
    public List<BookSlugDto> getPagedSearchedBooksDataVer3(String searchWord, Integer offset, Integer limit) {

        Pageable page = PageRequest.of(offset, limit);

        List<BookSlugDto> bookSlugDto = new ArrayList<>();

        for (BookEntity book : bookRepository.getBookEntitiesByTitleContainingOrderByPubDateDesc(searchWord, page).getContent()) {
            List<AuthorEntity> authors = authorsService.getAuthorByBookId(book.getId().intValue());
            bookSlugDto.add(new BookSlugDto(book, authors, null));
        }

        return bookSlugDto;
    }

    /**/
    public List<BookSlugDto> getPagedSortedBooksDataVer3(Integer offset, Integer limit, String from, String to) {

        Pageable page = PageRequest.of(offset, limit);

        List<BookSlugDto> bookSlugDto = new ArrayList<>();

        for (BookEntity book : bookRepository.getBookEntitiesByPubDateBetweenOrderByPubDateDesc(from, to, page).getContent()) {
            List<AuthorEntity> authors = authorsService.getAuthorByBookId(book.getId().intValue());
            bookSlugDto.add(new BookSlugDto(book, authors, null));
        }

        return bookSlugDto;
    }

    /**/
    public BookSlugDto getBookBySlug(String slug) throws NullPointerException {
        /**/
        BookEntity book = bookRepository.getBookEntitiesBySlug(slug);
        /**/
        List<AuthorEntity> authors = authorsService.getAuthorByBookId(book.getId().intValue());
        /**/
        List<BookReviewDto> bookReviews = new ArrayList<>();
        for (BookReviewEntity bookReviewEntity : bookReviewRepository.findBookReviewEntitiesByBookIdOrderByTimeDesc(book.getId())) {

            String username = userService.UserNameById(bookReviewEntity.getUserId());

            Long countLikes =bookReviewLikesRepository.
                    findAllByReviewId(bookReviewEntity.getId().intValue()).stream().filter(b -> b.getValue() == 1).count();//bookReviewLikesRepository.findAllByReviewIdAndValueIs(bookReviewEntity.getId(), (short) 1);


            Long countDisLikes = bookReviewLikesRepository.
                    findAllByReviewId(bookReviewEntity.getId().intValue()).stream().filter(b -> b.getValue() == -1).count();


            bookReviews.add(new BookReviewDto(
                    bookReviewEntity,
                    username,
                    countLikes,
                    countDisLikes
            ));
        }
        /**/
        List<BookFileEntity> bookFiles = bookFileService.bookFileEntities(book.getId());
        /**/
        Map<Integer, Long> ratingTitleDtoList = ratingService.getRatingTitleStatics(slug);
        /**/
        List<BookFileDto> bookFileDtos = new ArrayList<>();

        for (BookFileEntity bookFile : bookFiles) {

            BookFileTypeEntity bookFileType = bookFileTypeService.bookFileType(bookFile.getType_id());
            bookFileDtos.add(new BookFileDto(bookFile, bookFileType));
        }

        return new BookSlugDto(
                book,
                authors,
                bookFileDtos,
                ratingTitleDtoList,
                bookReviews
        );
    }

    /**/
    public void saveNewBookImage(MultipartFile file, String slug) throws IOException {
        String savePath = storage.saveNewBookImage(file, slug);
        BookEntity bookToUpdate = bookRepository.getBookEntitiesBySlug(slug);
        bookToUpdate.setImage(savePath);
        bookRepository.save(bookToUpdate); /*save new path in db here*/
    }

    /**/
    public DownloadingBookDto downloadBook(String hash) throws IOException {

        Path path = storage.getBookFilePath(hash);

        Logger.getLogger(this.getClass().getSimpleName()).info("book file path: " + path);

        MediaType mediaType = storage.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file mime type: " + mediaType);

        byte[] data = storage.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file data len: " + data.length);

        return new DownloadingBookDto(path, mediaType, data);
    }

    /**/
    public List<BookSlugDto> bookEntityListBySlugIn(String[] strings) {

        List<BookSlugDto> bookSlugDto = new ArrayList<>();

        for (BookEntity book : bookRepository.findBooksBySlugIn(strings)) {
            List<AuthorEntity> authors = authorsService.getAuthorByBookId(book.getId().intValue());
            bookSlugDto.add(new BookSlugDto(book, authors, null));
        }
        return bookSlugDto;

    }

    /**/
    public String addCommentForBook(String author, String comment, Long book_id) {

        UserEntity user = userRepository.findByName(author);
        Long userId;
        if (user != null) {
            userId = user.getId();
        } else {
            UserEntity userEntity = new UserEntity(
                    "unknown",
                    LocalDateTime.now(),
                    0,
                    author
            );
            userId = userRepository.save(userEntity).getId();
        }
        BookReviewEntity bookReviewEntity = new BookReviewEntity(
                book_id,
                userId,
                LocalDateTime.now(),
                comment
        );
        bookReviewRepository.save(bookReviewEntity);
        return bookRepository.findById(book_id).get().getSlug();
    }
}
