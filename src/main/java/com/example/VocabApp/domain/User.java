package com.example.VocabApp.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;




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
    


}
