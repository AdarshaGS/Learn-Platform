package com.website.User.service;

import java.util.List;
import com.website.User.api.GetUserResponse;
import com.website.User.data.CreateUserPayload;
import com.website.User.data.CreateUserResponse;

public interface UserService {
    CreateUserResponse createUser(CreateUserPayload payload);
    List<GetUserResponse> getAllUsers();
}
