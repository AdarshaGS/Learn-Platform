package com.website.User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.User.Repository.UserRepository;
import com.website.User.data.CreateUserPayload;
import com.website.User.data.CreateUserResponse;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public CreateUserResponse createUser(CreateUserPayload payload) {
            CreateUserPayload response = CreateUserPayload.builder().firstName(payload.getFirstName())
                                         .lastName(payload.getLastName()).email(payload.getEmail())
                                         .isActive(true).build();
            this.repository.save(response);
            return CreateUserResponse.builder().ResourceId(response.getId()).message("User Created").build();
    }
}
