// package com.example.VocabApp.web;

// import com.example.VocabApp.domain.User;
// import com.example.VocabApp.domain.Word;
// import com.example.VocabApp.repository.UserRepository;
// import com.example.VocabApp.repository.WordRepository;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @CrossOrigin
// @RestController @RequestMapping("/api/users")
// public class UserController {

//     private final UserRepository repo;

//     public UserController(UserRepository repo) { this.repo = repo; }

//     @GetMapping public List<User> all() { return repo.findAll(); }

//     @GetMapping("/{id}")
//     public User getById(@PathVariable Long id) {
//         return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//     }

//     @PostMapping @ResponseStatus(HttpStatus.CREATED)
//     public User create(@RequestBody User u) {
//         return repo.save(u);
//     }

    
// }
