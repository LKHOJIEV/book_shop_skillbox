package com.example.MyBookShopApp.security;

import com.example.MyBookShopApp.repository.BookStoreUserRepository;
import com.example.MyBookShopApp.repository.UserContactRepository;
import com.example.MyBookShopApp.repository.UserRepository;
import com.example.MyBookShopApp.struct.user.BookStoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookStoreUserDetailsService implements UserDetailsService {

    private final UserContactRepository userContactRepository;
    private  final UserRepository userRepository;

    private final BookStoreUserRepository bookStoreUserRepository;

    @Autowired
    public BookStoreUserDetailsService(UserContactRepository userContactRepository, UserRepository userRepository, BookStoreUserRepository bookStoreUserRepository) {
        this.userContactRepository = userContactRepository;
        this.userRepository = userRepository;
        this.bookStoreUserRepository = bookStoreUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        BookStoreUser bookStoreUser = bookStoreUserRepository.findBookstoreUserByEmail(s);
        if (bookStoreUser != null){
            return  new BookStoreUserDetails(bookStoreUser);

        } else {
            throw new UsernameNotFoundException("user not found :((");
        }

    }
}
