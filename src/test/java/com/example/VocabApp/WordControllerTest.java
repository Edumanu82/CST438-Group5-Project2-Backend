package com.example.VocabApp;
import com.example.VocabApp.domain.Word;
import com.example.VocabApp.repository.WordRepository;
import com.example.VocabApp.web.WordController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import com.example.VocabApp.config.CorsConfig;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WordController.class)
@Import(CorsConfig.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class WordControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WordRepository repo;

    private Word sampleWord;

    @BeforeEach
    void setUp() {
        sampleWord = new Word();
        sampleWord.setWordId(1L);
        sampleWord.setWord("example");
        sampleWord.setDefinition("A representative instance.");
        sampleWord.setPartOfSpeech("noun");
    }

    @Test
    void testGetAllWords() throws Exception {
        given(repo.findAll()).willReturn(Arrays.asList(sampleWord));

        mockMvc.perform(get("/api/words"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].word").value("example"))
                .andExpect(jsonPath("$[0].definition").value("A representative instance."));
    }

    @Test
    void testGetWordById_found() throws Exception {
        given(repo.findById(1L)).willReturn(Optional.of(sampleWord));

        mockMvc.perform(get("/api/words/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.word").value("example"));
    }

    @Test
    void testGetWordById_notFound() throws Exception {
        given(repo.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/words/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateWord_newWord() throws Exception {
        given(repo.existsByWord("example")).willReturn(false);
        given(repo.save(ArgumentMatchers.any(Word.class))).willReturn(sampleWord);

        String json = """
            {
                "word": "example",
                "definition": "A representative instance.",
                "partOfSpeech": "noun"
            }
        """;

        mockMvc.perform(post("/api/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.word").value("example"));
    }

    @Test
    void testUpdateWord_found() throws Exception {
        Word updated = new Word();
        updated.setWord("example");
        updated.setDefinition("Updated definition");
        updated.setPartOfSpeech("noun");

        given(repo.findById(1L)).willReturn(Optional.of(sampleWord));
        given(repo.save(ArgumentMatchers.any(Word.class))).willReturn(updated);

        String json = """
            {
                "word": "example",
                "definition": "Updated definition",
                "partOfSpeech": "noun"
            }
        """;

        mockMvc.perform(put("/api/words/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.definition").value("Updated definition"));
    }

    @Test
    void testUpdateWord_notFound() throws Exception {
        given(repo.findById(1L)).willReturn(Optional.empty());

        String json = """
            {
                "word": "example",
                "definition": "Updated definition",
                "partOfSpeech": "noun"
            }
        """;

        mockMvc.perform(put("/api/words/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteWord() throws Exception {
        willDoNothing().given(repo).deleteById(1L);

        mockMvc.perform(delete("/api/words/1"))
                .andExpect(status().isNoContent());
    }
    
    @Test
    void testInvalidPath_returnsNotFound() throws Exception {
        // Misspelled path from the request: /ap/words (should be /api/words)
        mockMvc.perform(get("/ap/words"))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void testCorsPreflight_returnsCorsHeaders() throws Exception {
    mockMvc.perform(options("/api/words")
            .header("Origin", "http://example.com")
            .header("Access-Control-Request-Method", "GET")
            .header("Access-Control-Request-Headers", "Content-Type"))
        .andExpect(status().isOk())
        .andExpect(header().exists("Access-Control-Allow-Origin"))
        .andExpect(header().exists("Access-Control-Allow-Methods"))
        .andExpect(header().string("Access-Control-Allow-Methods", containsString("GET")))
        .andExpect(header().exists("Access-Control-Allow-Headers"))
        .andExpect(header().string("Access-Control-Allow-Headers", containsString("Content-Type")));
    }
    
}
