package com.MBARI.repository;

import com.MBARI.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmailAddress(String emailAddress);
    //Optional<UserEntity> findByUsername(String username);
    UserEntity findByUsername(String username);
    Boolean existsByUsername(String username);
}
