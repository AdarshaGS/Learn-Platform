package com.website.User.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.User.Exception.UserNotFoundException;
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
    public CreateUserResponse createUser(@RequestBody CreateUserPayload payload) {
            CreateUserResponse response = this.service.createUser(payload);
            return response;     
    }

    @GetMapping
    public List<GetUserResponse> response() {
        List<GetUserResponse> response = this.service.getAllUsers();
        return response;
    }

    @GetMapping("/{id}")
    public List<GetUserResponse> retrieveUserById(@PathVariable("id") final Long id, final String message) {
        List<GetUserResponse> getUserById = this.service.getUserById(id);
        if(!getUserById.isEmpty()){
            return getUserById;
        }else{
            throw new UserNotFoundException(message);
        }
    }

    @PutMapping("/update/{id}")
    public GetUserResponse UpdateUser(@PathVariable("id") final Long id, @RequestBody CreateUserPayload payload) {
        GetUserResponse updateUser = this.service.update(id, payload);
        return updateUser;
    }

    @GetMapping("/email/{email}")
    public List<GetUserResponse> retrieveUserByEmail(@PathVariable("email") final String email){
        List<GetUserResponse> retrieveByEmail = this.service.retrieveByEmailId(email);
        return retrieveByEmail;
    }
}
