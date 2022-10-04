package com.website.User.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@SuppressWarnings("unused")
public class EmailPatternException extends RuntimeException {
    private String message; 
    public EmailPatternException(String message){
    super("Email is not right");
    this.message = message;
}
}
