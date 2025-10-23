package com.example.VocabApp;

import com.example.VocabApp.domain.User;
import com.example.VocabApp.repository.UserRepository;
import com.example.VocabApp.web.UserController;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import org.springframework.test.context.ActiveProfiles;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class UserControllerTest {
     @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository repo;

    // ✅ test GET /api/users
    @Test
    void testGetAllUsers() throws Exception {
        User u1 = new User();
        u1.setUserId(1L);
        u1.setUsername("Alice");

        User u2 = new User();
        u2.setUserId(2L);
        u2.setUsername("Bob");

        Mockito.when(repo.findAll()).thenReturn(List.of(u1, u2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is("Alice")))
                .andExpect(jsonPath("$[1].username", is("Bob")));
    }

    // ✅ test GET /api/users/{id}
    @Test
    void testGetUserById_found() throws Exception {
        User u = new User();
        u.setUserId(1L);
        u.setUsername("Alice");

        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(u));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("Alice")));
    }

    @Test
    void testGetUserById_notFound() throws Exception {
        Mockito.when(repo.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/99"))
                .andExpect(status().isNotFound()); // RuntimeException thrown
    }

    // ✅ test POST /api/users
    @Test
    void testCreateUser() throws Exception {
        User u = new User();
        u.setUserId(1L);
        u.setUsername("NewUser");

        Mockito.when(repo.save(any(User.class))).thenReturn(u);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "username": "NewUser",
                            "oauthProvider": "Google",
                            "oauthProvId": "123"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is("NewUser")));
    }

    // ✅ test PUT /api/users/{id}
    @Test
    void testUpdateUser_found() throws Exception {
        User existing = new User();
        existing.setUserId(1L);
        existing.setUsername("OldName");

        User updated = new User();
        updated.setUserId(1L);
        updated.setUsername("NewName");

        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(existing));
        Mockito.when(repo.save(any(User.class))).thenReturn(updated);

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "username": "NewName",
                            "oauthProvider": "Google",
                            "oauthProvId": "123"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("NewName")));
    }

    // ✅ test DELETE /api/users/{id}
    @Test
    void testDeleteUser_found() throws Exception {
        Mockito.when(repo.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteUser_notFound() throws Exception {
        Mockito.when(repo.existsById(99L)).thenReturn(false);

        mockMvc.perform(delete("/api/users/99"))
                .andExpect(status().isNotFound());
    }
}
