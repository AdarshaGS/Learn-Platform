package com.website.User.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.User.Exception.DuplicateEmailFoundException;
import com.website.User.Exception.EmailPatternException;
import com.website.User.Repository.UserRepository;
import com.website.User.data.CreateUserPayload;
import com.website.User.data.CreateUserResponse;
import com.website.User.data.GetUserResponse;

@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    private Pattern emailPattern;
    private Matcher emailMatcher;

    @Override
    public CreateUserResponse createUser(CreateUserPayload payload) {
        String message = null;
        String email = payload.getEmail();
        String emailRegex = "^(.+)@(gmail).(.+)$";
        emailPattern = Pattern.compile(emailRegex);
        emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.matches() == true) {
            throw new EmailPatternException(message);
        }
        CreateUserPayload response = CreateUserPayload.builder().firstName(payload.getFirstName())
                .lastName(payload.getLastName()).email(payload.getEmail())
                .isActive(true).build();
        String validateEmail = retrieveByEmailId(payload.getEmail()).toString();
        if (2!=validateEmail.length()) {
                throw new DuplicateEmailFoundException(message);
        }
        this.repository.save(response);
        return CreateUserResponse.builder().ResourceId(response.getId()).message("User Created").build();
    }

    @Override
    public List<CreateUserPayload> getAllUsers() {
        return this.repository.getAll();
    }

    @Override
    public List<CreateUserPayload> getUserById(Long id) {
        return this.repository.getUserById(id);
    }

    @Override
    public GetUserResponse update(Long id, CreateUserPayload payload) {
        try {
            CreateUserPayload existingUser = this.repository.findById(id).get();
            if (existingUser != null) {
                String checkForDuplicateEmail = payload.getEmail();
                String existingUserEmail = existingUser.getEmail();
                if (existingUserEmail.equals(checkForDuplicateEmail)) {
                    return GetUserResponse.builder().message("Duplicate Email Found").build();
                } else {
                    existingUser.setFirstName(payload.getFirstName());
                    existingUser.setLastName(payload.getLastName());
                    existingUser.setEmail(payload.getEmail());
                    this.repository.save(existingUser);
                    return GetUserResponse.builder().message("User Updated with " + existingUser.getFirstName()
                            + " " + existingUser.getLastName() + " " + existingUser.getEmail()).build();
                }
            }
        } catch (Throwable e) {
            return GetUserResponse.builder().message("User not found").build();
        }
        return null;
    }

    @Override
    public List<CreateUserPayload> retrieveByEmailId(String email) {
            return this.repository.getUserByEmail(email);
    }
}
