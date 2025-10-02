package com.example.VocabApp.repository;

import com.example.VocabApp.domain.UserWord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface UserWordRepository extends JpaRepository<UserWord, Long> {
  List<UserWord> findByUser_UserId(Long userId);
}