package com.example.api.service;

import com.cohere.api.Cohere;
import com.cohere.api.requests.ChatRequest;
import com.cohere.api.types.NonStreamedChatResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SummaryService {
    private final Tika tika = new Tika();

    public String extractedText(MultipartFile file) throws IOException, TikaException {
        String data = tika.parseToString(file.getInputStream());
        return data;
    }

    public String summarize(String text) {
        Cohere cohere = Cohere.builder()
                .token("YOUR_COHERE_API_KEY") // Replace with your actual API key
                .build();

        NonStreamedChatResponse response = cohere.chat(
                ChatRequest.builder()
                        .message("Summarize : " + text)
                        .model("command-a-03-2025")

                        .build() // ✅ You missed this
        );

        return response.getText();
    }
}
