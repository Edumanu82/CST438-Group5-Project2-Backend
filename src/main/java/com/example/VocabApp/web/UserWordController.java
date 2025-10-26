package com.example.VocabApp.web;

import com.example.VocabApp.domain.*;
import com.example.VocabApp.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController @RequestMapping("/api/users/{userId}/words")
public class UserWordController {
  record CreateReq(Long wordId, String status){}
  record UpdateReq(String status, Integer timesReviewed){}

  private final UserRepository users;
  private final WordRepository words;
  private final UserWordRepository links;

  public UserWordController(UserRepository users, WordRepository words, UserWordRepository links) {
    this.users = users; this.words = words; this.links = links;
  }

  @GetMapping
  public List<UserWord> list(@PathVariable Long userId) {
    return links.findByUserUserId(userId);
  }

  @GetMapping("/{userWordId}")
  public ResponseEntity<UserWord> getById(@PathVariable Long userId, @PathVariable Long userWordId) {
    return links.findById(userWordId)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public UserWord attach(@PathVariable Long userId, @RequestBody CreateReq req) {
    User u = users.findById(userId).orElseThrow();
    Word w = words.findById(req.wordId()).orElseThrow();
    UserWord uw = new UserWord();
    uw.setUser(u); uw.setWord(w);
    uw.setStatus(req.status() == null ? "not started" : req.status());
    uw.setTimesReviewed(0);
    uw.setLastReviewed(null);
    return links.save(uw);
  }

  @PutMapping("/{userWordId}")
  public UserWord update(@PathVariable Long userId, @PathVariable Long userWordId, @RequestBody UpdateReq req) {
    UserWord uw = links.findById(userWordId).orElseThrow();
    if (req.status() != null) uw.setStatus(req.status());
    if (req.timesReviewed() != null) uw.setTimesReviewed(req.timesReviewed());
    uw.setLastReviewed(LocalDateTime.now());
    return links.save(uw);
  }

  @DeleteMapping("/{userWordId}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void detach(@PathVariable Long userId, @PathVariable Long userWordId) {
    links.deleteById(userWordId);
  }
}
