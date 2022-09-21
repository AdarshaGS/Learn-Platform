package com.website.User.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.User.data.CreateUserPayload;
import com.website.User.data.CreateUserResponse;
import com.website.User.service.UserService;

@RestController
@RequestMapping("users")
public class UsersApiResource {
    
    @Autowired
    private UserService service;

    UsersApiResource(UserService userService) {
        this.service = userService;
    }

    @PostMapping("/create")
    public CreateUserResponse response(@RequestBody CreateUserPayload payload){
        CreateUserResponse response = this.service.createUser(payload);
        return response;
    }
}
