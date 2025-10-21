package com.example.VocabApp;

import com.example.VocabApp.domain.User;
import com.example.VocabApp.domain.Word;
import com.example.VocabApp.domain.UserWord;
import com.example.VocabApp.repository.UserWordRepository;
import com.example.VocabApp.repository.UserRepository;
import com.example.VocabApp.repository.WordRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserWordRepositoryTest {

    @Autowired
    private UserWordRepository userWordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WordRepository wordRepository;

    @Test
    void testFindByUserIdAndWordId() {
        User user = new User();
        user.setUsername("alice");
        userRepository.save(user);

        Word word = new Word();
        word.setWord("apple");
        word.setDefinition("A fruit");
        word.setPartOfSpeech("noun");
        wordRepository.save(word);

        UserWord userWord = new UserWord();
        userWord.setUser(user);
        userWord.setWord(word);
        userWord.setStatus("learning");
        userWord.setTimesReviewed(1);
        userWord.setLastReviewed(LocalDateTime.now());
        userWordRepository.save(userWord);

        Optional<UserWord> found = userWordRepository.findByUserUserIdAndWordWordId(user.getUserId(), word.getWordId());
        assertThat(found).isPresent();
        assertThat(found.get().getStatus()).isEqualTo("learning");
    }

    @Test
    void testFindByUserIdOrderByLastReviewedDesc() {
        User user = new User();
        user.setUsername("bob");
        userRepository.save(user);

        Word word1 = new Word();
        word1.setWord("run");
        word1.setDefinition("To move fast");
        word1.setPartOfSpeech("verb");
        wordRepository.save(word1);

        Word word2 = new Word();
        word2.setWord("jump");
        word2.setDefinition("To leap");
        word2.setPartOfSpeech("verb");
        wordRepository.save(word2);

        UserWord uw1 = new UserWord();
        uw1.setUser(user);
        uw1.setWord(word1);
        uw1.setStatus("learning");
        uw1.setTimesReviewed(2);
        uw1.setLastReviewed(LocalDateTime.now().minusDays(1));

        UserWord uw2 = new UserWord();
        uw2.setUser(user);
        uw2.setWord(word2);
        uw2.setStatus("learning");
        uw2.setTimesReviewed(1);
        uw2.setLastReviewed(LocalDateTime.now());

        userWordRepository.save(uw1);
        userWordRepository.save(uw2);

        List<UserWord> ordered = userWordRepository.findByUserUserIdOrderByLastReviewedDesc(user.getUserId());
        assertThat(ordered.get(0).getWord().getWord()).isEqualTo("jump");
        assertThat(ordered.get(1).getWord().getWord()).isEqualTo("run");
    }
}
