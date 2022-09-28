package com.website.User.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.website.User.Repository.UserRepository;
import com.website.User.data.CreateUserPayload;
import com.website.User.data.CreateUserResponse;
import com.website.User.data.GetUserResponse;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public UserServiceImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public CreateUserResponse createUser(CreateUserPayload payload) {
        CreateUserPayload response = CreateUserPayload.builder().firstName(payload.getFirstName())
                .lastName(payload.getLastName()).email(payload.getEmail())
                .isActive(true).build();
        this.repository.save(response);
        return CreateUserResponse.builder().ResourceId(response.getId()).message("User Created").build();
    }

    @Override
    public List<GetUserResponse> getAllUsers() {
        String sql = "select * from user";
        return this.jdbcTemplate.query(sql, new GetAllUsersMapper());
    }

    private final static class GetAllUsersMapper implements RowMapper<GetUserResponse> {
        @Override
        public GetUserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Long id = rs.getLong("id");
            final String firstName = rs.getString("firstname");
            ;
            final String lastName = rs.getString("lastname");
            final String email = rs.getString("email");
            final boolean isActive = rs.getBoolean("is_active");
            return GetUserResponse.builder().id(id).firstName(firstName).lastName(lastName)
                    .email(email).isActive(isActive).build();
        }
    }

    @Override
    public List<GetUserResponse> getUserById(Long id) {
        String sql = "select * from user where id = ?";
            return this.jdbcTemplate.query(sql, new GetUserResponseMapper(), new Object[] { id });
    }

    private final static class GetUserResponseMapper implements RowMapper<GetUserResponse> {
        @Override
        public GetUserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Long id = rs.getLong("id");
            final String firstName = rs.getString("firstname");
            ;
            final String lastName = rs.getString("lastname");
            final String email = rs.getString("email");
            final boolean isActive = rs.getBoolean("is_active");
            return GetUserResponse.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .isActive(isActive)
                    .build();
        }
    }

    @Override
    public GetUserResponse update(Long id, CreateUserPayload payload) { 
        try {
            CreateUserPayload existingUser = this.repository.findById(id).get();
            if (existingUser != null) {
                existingUser.setFirstName(payload.getFirstName());
                existingUser.setLastName(payload.getLastName());
                existingUser.setEmail(payload.getEmail());
                this.repository.save(existingUser);
                return GetUserResponse.builder().message("User Updated with " + existingUser.getFirstName()
                        + " " + existingUser.getLastName() + " " + existingUser.getEmail()).build();
            }
        } catch (Throwable e) {
            return GetUserResponse.builder().message("User not found").build();
        }
        return null;
    }

}
