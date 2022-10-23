package com.website.User.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.website.User.data.CreateUserPayload;

@Repository
public interface UserRepository extends JpaRepository<CreateUserPayload, Long>, JpaSpecificationExecutor<Long> {
    @Query(value = "select * from user", nativeQuery = true)
    List<CreateUserPayload> getAll();

    @Query(value = "select * from user where id = :id", nativeQuery = true)
    List<CreateUserPayload> getUserById(final Long id);

    @Query(value = "select * from user where email = :email", nativeQuery = true)
    List<CreateUserPayload> getUserByEmail(final String email);

    @Query(value = "select * from user where mobile_num = :mobileNum", nativeQuery = true)
    List<CreateUserPayload> getUserByMobileNumber(final Long mobileNum);

}
