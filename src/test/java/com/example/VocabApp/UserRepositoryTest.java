// package com.example.VocabApp;

// import com.example.VocabApp.domain.User;
// import com.example.VocabApp.repository.UserRepository;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import java.util.Optional;

// import static org.assertj.core.api.Assertions.assertThat;

// @DataJpaTest
// public class UserRepositoryTest {

//     @Autowired
//     private UserRepository userRepository;

//     @Test
//     void testSaveAndFindByUsername() {
//         User user = new User();
//         user.setUsername("alice");
//         userRepository.save(user);

//         Optional<User> found = userRepository.findByUsername("alice");
//         assertThat(found).isPresent();
//         assertThat(found.get().getUsername()).isEqualTo("alice");
//     }
// }
