package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.struct.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity ,Long> {

    UserEntity findByName(String name);
    UserEntity getOne(Long id);

    Optional<UserEntity> findById(Long id);
}
