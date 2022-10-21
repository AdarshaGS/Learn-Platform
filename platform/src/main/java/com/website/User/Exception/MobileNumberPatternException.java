package com.website.User.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@SuppressWarnings("unused")
public class MobileNumberPatternException extends RuntimeException{
    private String message;
    public MobileNumberPatternException(String message){
        super("Please Enter Mobile Number in proper format");
        this.message = message;
    }
}

