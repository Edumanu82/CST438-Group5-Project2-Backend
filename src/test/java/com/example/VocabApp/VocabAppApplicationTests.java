package com.example.VocabApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")      // ← important
@SpringBootTest
class VocabAppApplicationTests {
    @Test
    void contextLoads() {}
}


