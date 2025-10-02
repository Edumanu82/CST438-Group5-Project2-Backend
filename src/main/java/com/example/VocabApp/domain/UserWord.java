package com.example.VocabApp.domain;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "user_words")
public class UserWord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userWordId;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne @JoinColumn(name = "word_id")
    private Word word;


    private String status;
    private int timesReviewed;
    private LocalDateTime lastReviewed;


    //getters and setters
    public Long getUserWordId() {
        return userWordId;  
    }
    public void setUserWordId(Long userWordId) {
        this.userWordId = userWordId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Word getWord() {
        return word;
    }
    public void setWord(Word word) {
        this.word = word;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getTimesReviewed() {
        return timesReviewed;
    }
    public void setTimesReviewed(int timesReviewed) {
        this.timesReviewed = timesReviewed;
    }
    public LocalDateTime getLastReviewed() {
        return lastReviewed;
    }
    public void setLastReviewed(LocalDateTime lastReviewed) {
        this.lastReviewed = lastReviewed;
    }

}

