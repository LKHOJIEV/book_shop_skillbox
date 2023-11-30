package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.RatingTitleDto;
import com.example.MyBookShopApp.struct.book.rating.BookRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<BookRatingEntity, Long> {


    List<BookRatingEntity> findAllByBookSlug(String slug);
}
