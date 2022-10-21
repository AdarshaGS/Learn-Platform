package com.website.User.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.User.Exception.EmailNotFoundException;
import com.website.User.Exception.EmailPatternException;
import com.website.User.Exception.MobileNumberPatternException;
import com.website.User.Repository.UserRepository;
import com.website.User.data.CreateUserPayload;
import com.website.User.data.CreateUserResponse;
import com.website.User.data.GetUserResponse;

@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    private Pattern emailPattern, mobileNumPattern;
    private Matcher emailMatcher, mobileMatcher;

    @Override
    public CreateUserResponse createUser(CreateUserPayload payload) {
        String message = null;
        String email = payload.getEmail();
        String emailRegex = "^(.+)@(gmail).(.+)$";
        String mobileRegex = "^(\\d{3}[- .]?){2}\\d{4}$";
        emailPattern = Pattern.compile(emailRegex);
        mobileNumPattern = Pattern.compile(emailRegex);
        emailMatcher = emailPattern.matcher(email);
        mobileMatcher = mobileNumPattern.matcher(mobileRegex);
        if (!emailMatcher.matches() == true) {
            throw new EmailPatternException(message);}
        else if(!mobileMatcher.matches()){
            throw new MobileNumberPatternException(message);
        }
        CreateUserPayload response = CreateUserPayload.builder().firstName(payload.getFirstName())
                .lastName(payload.getLastName()).email(payload.getEmail()).mobileNumber(payload.getMobileNumber())
                .isActive(true).build();
        String validateEmail = retrieveByEmailId(payload.getEmail()).toString();
        if (validateEmail.isEmpty()) {
            throw new EmailNotFoundException(message);
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
