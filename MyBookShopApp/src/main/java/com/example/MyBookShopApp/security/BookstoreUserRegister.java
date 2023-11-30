package com.example.MyBookShopApp.security;

import com.example.MyBookShopApp.repository.BookStoreUserRepository;
import com.example.MyBookShopApp.repository.UserContactRepository;
import com.example.MyBookShopApp.repository.UserRepository;
import com.example.MyBookShopApp.struct.user.BookStoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BookstoreUserRegister {

    private final UserContactRepository userContactRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final BookStoreUserRepository bookStoreUserRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public BookstoreUserRegister(UserContactRepository userContactRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, BookStoreUserRepository bookStoreUserRepository, AuthenticationManager authenticationManager) {
        this.userContactRepository = userContactRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookStoreUserRepository = bookStoreUserRepository;
        this.authenticationManager = authenticationManager;
    }

    public void registerNewUser(BookStoreUser bookStoreUser) throws Exception {

        try {
            bookStoreUserRepository.save(new BookStoreUser(
                    bookStoreUser.getName(),
                    bookStoreUser.getEmail(),
                    bookStoreUser.getPhone(),
                    passwordEncoder.encode(bookStoreUser.getPassword())
            ));


        } catch (Exception e) {
            throw new Exception("register error");
        }
    }

    public ContactConfirmationResponse login(ContactConfirmationPayload payload){


            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getContact(),
                            payload.getCode()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            ContactConfirmationResponse response = new ContactConfirmationResponse();
            response.setResult(true);

            return response;


    }

    public Object getCurrentUser() {

        UserDetails userDetails =
                (BookStoreUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ((BookStoreUserDetails) userDetails).getBookStoreUser();

    }
}
