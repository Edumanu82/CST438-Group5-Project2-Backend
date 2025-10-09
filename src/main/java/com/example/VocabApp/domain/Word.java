package com.example.VocabApp.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
@Entity
@Table(name = "words")
public class Word {
    
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "word_id")
  private Long wordId;

  @Column(nullable = false, unique = true)
  private String word;

  @Column(columnDefinition = "TEXT")
  private String definition;

  private String partOfSpeech;

  @JsonIgnore
  @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserWord> userWords = new HashSet<>();

  public void addUserWord(UserWord uw) {
    userWords.add(uw);
    uw.setWord(this);
  }
  public void removeUserWord(UserWord uw) {
    userWords.remove(uw);
    uw.setWord(null);
  }

  // getters/setters
  public Long getWordId() { return wordId; }
  public String getWord() { return word; }
  public String getDefinition() { return definition; }
  public String getPartOfSpeech() { return partOfSpeech; }
  public void setWordId(Long wordId) { this.wordId = wordId; }
  public void setWord(String word) { this.word = word; }
  public void setDefinition(String definition) { this.definition = definition; }
  public void setPartOfSpeech(String partOfSpeech) { this.partOfSpeech = partOfSpeech; }

  public Set<UserWord> getUserWords() { 
    return userWords; 
  }
  public void setUserWords(Set<UserWord> userWords) { 
    this.userWords = userWords; 
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Word word1 = (Word) o;
    return Objects.equals(wordId, word1.wordId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wordId);
  }
}
