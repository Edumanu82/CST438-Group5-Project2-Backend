package com.example.VocabApp.web;

import com.example.VocabApp.domain.User;
import com.example.VocabApp.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController @RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) { this.repo = repo; }

    @GetMapping public List<User> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User u) {
        return repo.save(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User updated) {
        return repo.findById(id).map(existing -> {
            existing.setOauthProvider(updated.getOauthProvider());
            existing.setOauthProvId(updated.getOauthProvId());
            existing.setUsername(updated.getUsername());
            User saved = repo.save(existing);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) { 
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id); 
        return ResponseEntity.noContent().build();
    }

    

    
}
