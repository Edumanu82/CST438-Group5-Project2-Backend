package com.example.VocabApp.repository;

import com.example.VocabApp.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WordRepository extends JpaRepository<Word, Long> {
  boolean existsByWord(String word);
}