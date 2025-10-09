package com.example.VocabApp.repository;

import com.example.VocabApp.domain.UserWord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface UserWordRepository extends JpaRepository<UserWord, Long> {
  //some query methods we might need: 
  List<UserWord> findByUserUserId(Long userId); // fetch all words associated with a user
  List<UserWord> findByUserUserIdAndStatus(Long userId, String status); // filter user's words by status (e.g., "learning", "mastered")
  Optional<UserWord> findByUserUserIdAndWordWordId(Long userId, Long wordId); //prevent duplicates: check if a user already has progress on a given word.
  List<UserWord> findByUserUserIdOrderByLastReviewedDesc(Long userId); // fetch user's words ordered by last reviewed date, most recent first
}