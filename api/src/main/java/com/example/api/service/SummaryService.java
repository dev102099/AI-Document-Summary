package com.example.api.service;

import java.util.concurrent.TimeUnit;

import com.cohere.api.Cohere;
import com.cohere.api.core.RequestOptions;
import com.cohere.api.requests.ChatRequest;
import com.cohere.api.types.NonStreamedChatResponse;
import com.example.api.config.CohereConfig;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CohereConfig config;

    public String extractedText(MultipartFile file) throws IOException, TikaException {
        System.out.println("hi");
        String data = tika.parseToString(file.getInputStream());
        return data;
    }

    public String summarize(String text) {

        // 2. Pass the custom client into the Cohere builder
        Cohere cohere = Cohere.builder().token(config.getApiKey()).build();

        // 3. Build the request instruction
        ChatRequest request = ChatRequest.builder()
                .message("Please provide a clear and concise summary of the following text:\n\n" + text)
                .build();

        NonStreamedChatResponse response = cohere.chat(
                request, RequestOptions.builder().timeout(60, TimeUnit.SECONDS).build()

        );

        return response.getText();
    }
}
