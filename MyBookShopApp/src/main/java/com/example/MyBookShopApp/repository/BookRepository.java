package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.BookDataFullDto;
import com.example.MyBookShopApp.dto.BookSlugDto;
import com.example.MyBookShopApp.struct.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    //
    Page<BookEntity> getBookEntitiesByPubDateAfterOrderByPubDateDesc(String date,Pageable pageable);

    //
    Page<BookEntity> getBookEntitiesByTitleContainingOrderByPubDateDesc(String title,Pageable pageable);

    // by title searched books
    @Query(value =
            "select new com.example.MyBookShopApp.dto.BookDataFullDto(" +
                    "b.id," +
                    "b.pubDate," +
                    "b.isBestseller," +
                    "b.slug," +
                    "b.title," +
                    "b.image," +
                    "b.description," +
                    "b.price," +
                    "b.discount," +
                    "a.name," +
                    "((100-b.discount)/100*b.price)) " +
                    "from BookEntity b left join Book2AuthorEntity ba on b.id = ba.bookId " +
                    "  join AuthorEntity a on a.id = ba.authorId " +
                    "where b.title like %?1%")
    Page<BookDataFullDto> pagedSearchedBooks(String searchWord, Pageable pageable);

    //
    Page<BookEntity> getBookEntitiesByIsBestsellerIsOrderByPubDateDesc(Integer integer,Pageable pageable);

    //
    Page<BookEntity> getBookEntitiesByPubDateBetweenOrderByPubDateDesc(String from,String to,Pageable page);

    // sorted  books by tag id
    @Query(value =
            "select new com.example.MyBookShopApp.dto.BookDataFullDto(" +
                    "b.id," +
                    "b.pubDate," +
                    "b.isBestseller," +
                    "b.slug," +
                    "b.title," +
                    "b.image," +
                    "b.description," +
                    "b.price," +
                    "b.discount," +
                    "a.name," +
                    "((100-b.discount)/100*b.price)) " +
                    "from BookEntity b, Tag2BookEntity tb, TagEntity t ,Book2AuthorEntity ba,AuthorEntity a " +
                    "where b.id = tb.book_id " +
                    "and ba.bookId = b.id " +
                    "and ba.authorId=a.id " +
                    "and tb.tag_id = t.id " +
                    "and t.name like '%?1%' ")
    Page<BookDataFullDto> pagedBooksByTagName(String tagName, Pageable pageable);

    //
    @Query(value =
            "select new com.example.MyBookShopApp.dto.BookDataFullDto(" +
                    "b.id," +
                    "b.pubDate," +
                    "b.isBestseller," +
                    "b.slug," +
                    "b.title," +
                    "b.image," +
                    "b.description," +
                    "b.price," +
                    "b.discount," +
                    "a.name," +
                    "((100-b.discount)/100*b.price)) " +
                    "from BookEntity b, Tag2BookEntity tb, TagEntity t ,Book2AuthorEntity ba,AuthorEntity a " +
                    "where b.id = tb.book_id " +
                    "and ba.bookId = b.id " +
                    "and ba.authorId=a.id " +
                    "and tb.tag_id = t.id " +
                    "and t.id = ?1 ")
    Page<BookDataFullDto> pagedBooksByTagId(Long id, Pageable pageable);

    //
    BookEntity getBookEntitiesBySlug(String slug);

    //
    List<BookEntity> findBooksBySlugIn(String[] slugs);

}
