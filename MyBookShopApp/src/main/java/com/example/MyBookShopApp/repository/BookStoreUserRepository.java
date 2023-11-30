package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.struct.user.BookStoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStoreUserRepository extends JpaRepository<BookStoreUser, Integer> {
    BookStoreUser findBookstoreUserByEmail(String email);
}
