package com.website.User.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    private String message; 
    public UserNotFoundException(String message){
    super("User not found");
    this.message = message;
}
}

