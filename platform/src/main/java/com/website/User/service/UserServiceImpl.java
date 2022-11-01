package com.website.User.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.User.Exception.DuplicateEntityFoundException;
import com.website.User.Repository.UserRepository;
import com.website.User.data.CreateUserPayload;
import com.website.User.data.CreateUserResponse;
import com.website.User.data.GetUserResponse;

@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public CreateUserResponse createUser(CreateUserPayload payload) {
        String message = null;
        this.repository.validateForEmailPattern(payload.getEmail());
        CreateUserPayload response = CreateUserPayload.builder().firstName(payload.getFirstName())
                .lastName(payload.getLastName()).email(payload.getEmail()).mobileNum(payload.getMobileNum())
                .gender(payload.getGender()).isActive(false).build();
        String validateEmail = retrieveByEmailId(payload.getEmail()).toString();
        String validateMobileNumber = retrieveByMobileNumber(payload.getMobileNum()).toString();
        if (2 != validateEmail.length() || validateMobileNumber.length()!=2) {
            throw new DuplicateEntityFoundException(message);
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
        String message = null;
            CreateUserPayload existingUser = this.repository.findById(id).get();
            if (existingUser != null) {
                String validateEmail = payload.getEmail();
                String existingEmail = existingUser.getEmail();
                String validateMobileNumber = payload.getMobileNum().toString();
                String existingMobileNumber = existingUser.getMobileNum().toString();
                if (existingEmail.equals(validateEmail) || existingMobileNumber.equals(validateMobileNumber)) {
                    throw new DuplicateEntityFoundException(message);
                } else {
                    existingUser.setFirstName(payload.getFirstName());
                    existingUser.setLastName(payload.getLastName());
                    existingUser.setEmail(payload.getEmail());
                    existingUser.setMobileNum(payload.getMobileNum());
                    this.repository.save(existingUser);
                    return GetUserResponse.builder().message("User Updated with " + existingUser.getFirstName()
                            + " " + existingUser.getLastName() + " " + existingUser.getEmail()).build();
                }
            }
        return null;
    }

    @Override
    public List<CreateUserPayload> retrieveByEmailId(String email) {
        return this.repository.getUserByEmail(email);
    }

    public List<CreateUserPayload> retrieveByMobileNumber(String mobileNum) {
        return this.repository.getUserByMobileNumber(mobileNum);
    }
}
