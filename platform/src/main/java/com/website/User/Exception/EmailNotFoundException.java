package com.website.User.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@SuppressWarnings("unused")
public class EmailNotFoundException extends RuntimeException{
    private String message; 
    public EmailNotFoundException(String message){
    super("Cannot create Account with this Email Id");
    this.message = message;
}
}
