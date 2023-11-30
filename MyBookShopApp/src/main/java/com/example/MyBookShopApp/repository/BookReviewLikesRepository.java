package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.struct.book.review.BookReviewLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReviewLikesRepository extends JpaRepository<BookReviewLikeEntity, Long> {

    List<BookReviewLikeEntity> findAllByReviewId(int id);

    List<BookReviewLikeEntity> findAllByReviewIdAndValue(Long id,int value);

}
