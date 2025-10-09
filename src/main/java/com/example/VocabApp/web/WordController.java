package com.example.VocabApp.web;

import com.example.VocabApp.domain.Word;
import com.example.VocabApp.repository.WordRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController @RequestMapping("/api/words")
public class WordController {
  private final WordRepository repo;
  public WordController(WordRepository repo) { this.repo = repo; }

  @GetMapping public List<Word> all() { return repo.findAll(); }

  @GetMapping("/{id}")
  public ResponseEntity<Word> getWordById(@PathVariable("id") Long id){
    Optional<Word> word = repo.findById(id);
    return word.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public Word create(@RequestBody Word w) {
    if (!repo.existsByWord(w.getWord())) { return repo.save(w); }
    return repo.findAll().stream().filter(x -> x.getWord().equals(w.getWord())).findFirst().orElse(w);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateWord(@PathVariable("id") Long id, @RequestBody Word updated) {
    return repo.findById(id).map(existing -> {
      existing.setWord(updated.getWord());
      existing.setDefinition(updated.getDefinition());
      existing.setPartOfSpeech(updated.getPartOfSpeech());
      repo.save(existing);
      return ResponseEntity.ok(existing);
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id) { repo.deleteById(id); }

    
}
