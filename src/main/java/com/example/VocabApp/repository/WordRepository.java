package com.example.VocabApp.repository;

import com.example.VocabApp.domain.Word;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
public interface WordRepository extends JpaRepository<Word, Long> {
  //some query methods we might need
  boolean existsByWord(String word); 
  Optional<Word> findByWord(String word); // fetch a specific word
  List<Word> findByPartOfSpeech(String partOfSpeech); // allows frontend to filter words by type
  List<Word> findByWordContainingIgnoreCase(String term); // can search for words containing a substring, case insensitive
}