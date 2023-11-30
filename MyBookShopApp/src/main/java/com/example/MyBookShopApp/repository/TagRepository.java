package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.struct.tags.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<TagEntity,Long> {



    List<TagEntity> findAllBy();

    Optional<TagEntity> findById(Long id);

}
