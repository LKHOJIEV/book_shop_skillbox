package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.struct.book.review.BookReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReviewEntity,Long> {
    List<BookReviewEntity> findBookReviewEntitiesByBookIdOrderByTimeDesc(Long id);



}
