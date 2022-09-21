package com.website.User.service;

import com.website.User.data.CreateUserPayload;
import com.website.User.data.CreateUserResponse;

public interface UserService {
    CreateUserResponse createUser(CreateUserPayload payload);
}
