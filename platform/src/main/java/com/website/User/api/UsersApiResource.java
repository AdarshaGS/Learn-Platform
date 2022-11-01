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
    public List<CreateUserPayload> response() {
        List<CreateUserPayload> response = this.service.getAllUsers();
        return response;
    }

    @GetMapping("/{id}")
    public List<CreateUserPayload> retrieveUserById(@PathVariable("id") final Long id, final String message) {
        List<CreateUserPayload> getUserById = this.service.getUserById(id);
        if (!getUserById.isEmpty()) {
            return getUserById;
        } else {
            throw new UserNotFoundException(message);
        }
    }

    @PutMapping("/update/{id}")
    public GetUserResponse UpdateUser(@PathVariable("id") final Long id, @RequestBody CreateUserPayload payload,
            String message) {
        GetUserResponse updateUser = this.service.update(id, payload);
            return updateUser;
    }

    @GetMapping("/email/{email}")
    public List<CreateUserPayload> retrieveUserByEmail(@PathVariable("email") final String email) {
        List<CreateUserPayload> retrieveByEmail = this.service.retrieveByEmailId(email);
        return retrieveByEmail;
    }

    @GetMapping("/mobile/{mobileNum}")
    public List<CreateUserPayload> retrieveUserByMobileNumber(@PathVariable("mobileNum") final String mobileNum) {
        List<CreateUserPayload> retrieveByMobile = this.service.retrieveByMobileNumber(mobileNum);
        return retrieveByMobile;
    }
}
