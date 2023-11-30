package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.struct.book.file.BookFileTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookFileTypeRepository extends JpaRepository<BookFileTypeEntity,Long> {

    BookFileTypeEntity findById(Integer integer);

}
