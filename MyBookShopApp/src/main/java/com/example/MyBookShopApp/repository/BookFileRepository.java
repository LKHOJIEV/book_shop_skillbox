package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.struct.book.file.BookFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookFileRepository extends JpaRepository<BookFileEntity,Long> {

    BookFileEntity findBookFileEntitiesByHash(String hash);


    List<BookFileEntity> findBookFileEntitiesByBookId(Long id);



}
