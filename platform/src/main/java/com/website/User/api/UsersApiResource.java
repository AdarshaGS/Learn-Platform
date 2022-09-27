package com.website.User.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.User.data.CreateUserPayload;
import com.website.User.data.CreateUserResponse;
import com.website.User.data.GetUserResponse;
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
    
    @GetMapping
    public List<GetUserResponse> response(){
        List<GetUserResponse> response = this.service.getAllUsers();
        return response;
    }

    @GetMapping("/{id}")
    public List<GetUserResponse> response(@PathVariable("id") final Long id){
        List<GetUserResponse> getUserById = this.service.getUserById(id);
        return getUserById;
        
    }
}
