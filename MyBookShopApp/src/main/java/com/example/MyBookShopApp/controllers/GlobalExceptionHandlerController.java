package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.ApiResponse;
import com.example.MyBookShopApp.dto.BookSlugDto;
import com.example.MyBookShopApp.errorHandler.BookstoreApiWrongParameterException;
import com.example.MyBookShopApp.errorHandler.EmptySearchException;
import com.example.MyBookShopApp.errorHandler.InputValueNotPresentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandlerController {
    @ExceptionHandler(EmptySearchException.class)
    public String handleEmptySearchException(EmptySearchException e, RedirectAttributes redirectAttributes){
        Logger.getLogger(this.getClass().getSimpleName()).warning(e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("searchError",e);
        return "redirect:/";
    }

    @ExceptionHandler(BookstoreApiWrongParameterException.class)
    ResponseEntity<ApiResponse<BookSlugDto>> handleBookstoreApiWrongParameterException(Exception exception) {
        return new ResponseEntity<>(
                new ApiResponse<BookSlugDto>(
                        HttpStatus.BAD_REQUEST,
                        "Bad parameter value...",
                        exception),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InputValueNotPresentException.class)
    ResponseEntity<ApiResponse<BookSlugDto>> handleInputValueNotPresentException(Exception exception) {
        return new ResponseEntity<>(
                new ApiResponse<BookSlugDto>(
                        HttpStatus.BAD_REQUEST,
                        "input parameter not present or not valid",
                        exception),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    ResponseEntity<ApiResponse<BookSlugDto>> NullPointerException(Exception exception) {
        return new ResponseEntity<>(
                new ApiResponse<BookSlugDto>(
                        HttpStatus.BAD_REQUEST,
                        "input parameter not present",
                        exception),
                HttpStatus.BAD_REQUEST);
    }


}
