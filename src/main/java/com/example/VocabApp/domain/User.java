package com.example.VocabApp.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;




@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    private String oauthProvider;
    private String oauth_prov_Id;
    private String username;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserWord> userWords = new HashSet<>();

    public void addUserWord(UserWord uw) {
        userWords.add(uw);
        uw.setUser(this);
    }

    public void removeUserWord(UserWord uw) {
        userWords.remove(uw);
        uw.setUser(null);
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getOauthProvider() {
        return oauthProvider;
    }
    public void setOauthProvider(String oauthProvider) {
        this.oauthProvider = oauthProvider;
    }
    public String getOauth_prov_Id() {
        return oauth_prov_Id;
    }
    public void setOauth_prov_Id(String oauth_prov_Id) {
        this.oauth_prov_Id = oauth_prov_Id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Set<UserWord> getUserWords() { return userWords; }
    public void setUserWords(Set<UserWord> userWords) { this.userWords = userWords; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
    


}
