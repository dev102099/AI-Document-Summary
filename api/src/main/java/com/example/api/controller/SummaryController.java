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
    public ResponseEntity<?> returnSummary(@RequestParam("file")MultipartFile file){
        try {
            String text = summaryService.extractedText(file);
            String summary = summaryService.summarize(text);
            return ResponseEntity.ok().body(Map.of("Summary",summary));
        }
        catch (IOException| TikaException e){
            return ResponseEntity.status(500).body("Failed to extract text: " + e.getMessage());
        }
    }
}
