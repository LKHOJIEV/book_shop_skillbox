package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.struct.author.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @Query(nativeQuery = true, value = "select * from author  a order by a.name")
    List<AuthorEntity> findAuthorEntities();

    @Query(value =
            "select new com.example.MyBookShopApp.struct.author.AuthorEntity(a.id,a.photo,a.slug,a.name,a.description) " +
                    "from AuthorEntity a,Book2AuthorEntity ba " +
                    "where ba.authorId = a.id " +
                    "and ba.bookId = ?1 " +
                    "order by a.id ")
    List<AuthorEntity> findAuthorEntityByBookId(Integer id);
}
