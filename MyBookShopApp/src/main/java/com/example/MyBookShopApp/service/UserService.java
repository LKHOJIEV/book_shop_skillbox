package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String UserNameById(Long id){

        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get().getName() : "not found";
    }



}
