package com.example.VocabApp.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "words")
public class Word {
    
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long wordId;

  @Column(nullable = false, unique = true)
  private String word;

  @Column(columnDefinition = "TEXT")
  private String definition;

  private String partOfSpeech;

  @JsonIgnore
  @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserWord> userWords = new HashSet<>();

  // getters/setters
  public Long getWordId() { return wordId; }
  public String getWord() { return word; }
  public String getDefinition() { return definition; }
  public String getPartOfSpeech() { return partOfSpeech; }
  public void setWordId(Long wordId) { this.wordId = wordId; }
  public void setWord(String word) { this.word = word; }
  public void setDefinition(String definition) { this.definition = definition; }
  public void setPartOfSpeech(String partOfSpeech) { this.partOfSpeech = partOfSpeech; }
}
