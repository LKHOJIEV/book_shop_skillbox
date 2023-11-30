package com.example.MyBookShopApp.errorHandler;

public class EmptySearchException extends Throwable {
    public EmptySearchException(String message) {
        super(message);
    }
}
