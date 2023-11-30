package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.security.BookstoreUserRegister;
import com.example.MyBookShopApp.security.ContactConfirmationPayload;
import com.example.MyBookShopApp.security.ContactConfirmationResponse;
import com.example.MyBookShopApp.security.RegistrationForm;
import com.example.MyBookShopApp.struct.user.BookStoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final BookstoreUserRegister userRegister;

    @Autowired
    public AuthController(BookstoreUserRegister userRegister) {
        this.userRegister = userRegister;
    }


    @GetMapping("/signin")
    public String signin(){
        return "main/signin";
    }

    @GetMapping("/signup")
    public String signup(Model model){

        model.addAttribute("registerForm", new RegistrationForm());

        return "main/signup";
    }


    @PostMapping("/requestContactConfirmation")
    @ResponseBody
    public ContactConfirmationResponse handleRequestContactConfirmation(@RequestBody ContactConfirmationPayload contactConfirmationPayload) {
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult(true);
        return response;
    }

    @PostMapping("/approveContact")
    @ResponseBody
    public ContactConfirmationResponse handleApproveContact(@RequestBody ContactConfirmationPayload payload) {
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult(true);
        return response;
    }

    @PostMapping("/register")
    public String handleUserRegistration(BookStoreUser bookStoreUser, Model model) throws Exception {
        userRegister.registerNewUser(bookStoreUser);
        model.addAttribute("regOk", true);
        return "main/signin";
    }

    @PostMapping("/login")
    @ResponseBody
    public ContactConfirmationResponse handleLogin(@RequestBody ContactConfirmationPayload payload){
        return userRegister.login(payload);
    }

    @GetMapping("/my")
    public String handleMy(Model model) {
        model.addAttribute("currentUser", userRegister.getCurrentUser());
        return "main/my";
    }

    @GetMapping("/profile")
    public String handleProfile(Model model) {
        model.addAttribute("currentUser", userRegister.getCurrentUser());
        return "main/profile";
    }

    @GetMapping("/logout")
    public String handleLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SecurityContextHolder.clearContext();
        if (session != null) {
            session.invalidate();
        }

        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return "redirect:/";
    }

}
