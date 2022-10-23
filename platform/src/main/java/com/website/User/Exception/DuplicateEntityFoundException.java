package com.website.User.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
@SuppressWarnings("unused")
public class DuplicateEntityFoundException extends RuntimeException{
    private String message; 
    public DuplicateEntityFoundException(String message){
    super("Duplicate Email/Mobile Number Found");
    this.message = message;
}
}
