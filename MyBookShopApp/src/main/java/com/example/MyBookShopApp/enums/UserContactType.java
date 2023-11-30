package com.example.MyBookShopApp.enums;

import org.omg.CORBA.PUBLIC_MEMBER;

public enum UserContactType {

    EMAIL,
    PHONE;

    public static String getUserContactType(Integer id){
        switch (id){
            case 1: return UserContactType.EMAIL.name();
            case 2: return UserContactType.PHONE.name();
            default: return "";
        }
    }

}
