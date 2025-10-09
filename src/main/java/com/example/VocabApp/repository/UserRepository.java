package com.example.VocabApp.repository;
import com.example.VocabApp.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    //some query methods we might need
    Optional<User> findByOauthProvId(String oAuthId); // fetch user record by OAuth ID
    Optional<User> findByUsername(String username); // might be useful for testing
}
