package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.struct.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {


    @Query(value = "select * from genre b where b.parent_id not in (select gg.id from genre gg) ",
            nativeQuery = true)
    List<GenreEntity> getAllParentGenres();


    List<GenreEntity> findGenreEntitiesByParentId(Long parent_id);
}
