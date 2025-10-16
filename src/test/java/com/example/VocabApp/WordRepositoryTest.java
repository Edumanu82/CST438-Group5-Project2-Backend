package com.example.VocabApp;

import com.example.VocabApp.domain.Word;
import com.example.VocabApp.repository.WordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration.class,
        org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration.class,
        org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration.class
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class WordRepositoryTest {

    @Autowired
    private WordRepository wordRepository;

    @Test
    void testExistsByWord() {
        Word word = new Word();
        word.setWord("apple");
        word.setDefinition("A fruit");
        word.setPartOfSpeech("noun");
        wordRepository.save(word);

        assertThat(wordRepository.existsByWord("apple")).isTrue();
        assertThat(wordRepository.existsByWord("banana")).isFalse();
    }

    @Test
    void testFindByWordContainingIgnoreCase() {
        Word word1 = new Word();
        word1.setWord("Computer");
        word1.setDefinition("An electronic device");
        word1.setPartOfSpeech("noun");

        Word word2 = new Word();
        word2.setWord("compute");
        word2.setDefinition("To calculate");
        word2.setPartOfSpeech("verb");

        wordRepository.save(word1);
        wordRepository.save(word2);

        List<Word> results = wordRepository.findByWordContainingIgnoreCase("comp");
        assertThat(results).hasSize(2);
    }

    @Test
    void testFindByPartOfSpeech() {
        Word word = new Word();
        word.setWord("run");
        word.setDefinition("To move fast");
        word.setPartOfSpeech("verb");
        wordRepository.save(word);

        List<Word> verbs = wordRepository.findByPartOfSpeech("verb");
        assertThat(verbs).hasSize(1);
        assertThat(verbs.get(0).getWord()).isEqualTo("run");
    }
}
