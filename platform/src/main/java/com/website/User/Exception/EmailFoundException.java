package com.website.User.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
@SuppressWarnings("unused")
public class EmailFoundException extends RuntimeException{
    private String message; 
    public EmailFoundException(String message){
    super("Duplicate Email Found");
    this.message = message;
}
}
