package com.example.api.controller;

import com.example.api.service.SummaryService;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class SummaryController {
    @Autowired
    private SummaryService summaryService;

    @CrossOrigin(origins = "https://ai-document-summary.onrender.com")
    @PostMapping("/summarize")
    public ResponseEntity<?> returnSummary(@RequestParam("file") MultipartFile file) {
        try {
            System.out.println("Endpoint hit! File received: " + file.getOriginalFilename());

            String text = summaryService.extractedText(file);
            System.out.println("Text extracted successfully.");

            String summary = summaryService.summarize(text);
            System.out.println("Summary generated successfully.");

            return ResponseEntity.ok().body(Map.of("Summary", summary));

        } catch (IOException | TikaException e) {
            System.err.println("File parsing error: " + e.getMessage());
            return ResponseEntity.status(500).body("Failed to extract text: " + e.getMessage());

        } catch (Exception e) {
            // This will catch AI API errors, NullPointers, etc.
            System.err.println("Unexpected Server Error: " + e.getMessage());
            e.printStackTrace(); // Prints the full stack trace to your terminal
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }
}
