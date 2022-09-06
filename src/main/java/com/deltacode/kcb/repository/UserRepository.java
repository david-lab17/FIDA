package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
    //retrieve user by email

    Optional<UserApp> findByUsernameOrEmail(String username,String email);
    Optional<UserApp> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    UserApp findByResetPasswordToken(String token);
        @Query("SELECT c FROM UserApp c WHERE c.email = ?1")
    UserApp findByEmaili(String email);



}

