package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TriviaService {

    @Value("${trivia.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public TriviaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchTriviaQuestions() {
        return restTemplate.getForObject(apiUrl, String.class);
    }
}