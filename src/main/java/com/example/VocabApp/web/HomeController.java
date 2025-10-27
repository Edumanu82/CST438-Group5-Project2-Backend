package com.example.VocabApp.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RestController 
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "Backend is running. Try hitting /api endpoints.";
    }
}
