package com.example.VocabApp.web;

import com.example.VocabApp.domain.Word;
import com.example.VocabApp.repository.WordRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController @RequestMapping("/api/words")
public class WordController {
private final WordRepository repo;
  public WordController(WordRepository repo) { this.repo = repo; }

  @GetMapping public List<Word> all() { return repo.findAll(); }

  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public Word create(@RequestBody Word w) {
    if (!repo.existsByWord(w.getWord())) { return repo.save(w); }
    return repo.findAll().stream().filter(x -> x.getWord().equals(w.getWord())).findFirst().orElse(w);
  }

  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) { repo.deleteById(id); }

    
}
