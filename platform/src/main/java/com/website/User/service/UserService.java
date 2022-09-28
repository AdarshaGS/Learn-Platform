package com.website.User.service;

import java.util.List;

import com.website.User.data.CreateUserPayload;
import com.website.User.data.CreateUserResponse;
import com.website.User.data.GetUserResponse;

public interface UserService {
    CreateUserResponse createUser(CreateUserPayload payload);

    List<GetUserResponse> getAllUsers();

    List<GetUserResponse> getUserById(Long id);

    GetUserResponse update(Long id, CreateUserPayload payload);
}
