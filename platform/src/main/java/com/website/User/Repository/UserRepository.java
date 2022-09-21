package com.website.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.website.User.data.CreateUserPayload;

@Repository
public interface UserRepository extends JpaRepository<CreateUserPayload, Long>, JpaSpecificationExecutor<Long> {
    
}
