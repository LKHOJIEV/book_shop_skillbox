package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.struct.user.UserContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactRepository extends JpaRepository<UserContactEntity, Long> {

    UserContactEntity findByContact(String contact);


}
