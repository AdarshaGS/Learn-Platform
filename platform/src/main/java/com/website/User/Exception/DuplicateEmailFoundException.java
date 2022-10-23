package com.website.User.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
@SuppressWarnings("unused")
public class DuplicateEmailFoundException extends RuntimeException{
    private String message; 
    public DuplicateEmailFoundException(String message){
    super("Duplicate email Found");
    this.message = message;
}
}
