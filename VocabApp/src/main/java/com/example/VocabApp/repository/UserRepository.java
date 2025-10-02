package com.example.VocabApp.repository;
import com.example.VocabApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {}
