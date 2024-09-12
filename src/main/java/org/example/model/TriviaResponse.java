package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class TriviaResponse {
    @JsonProperty("response_code")
    private int responseCode;
    
    private List<TriviaQuestion> results;
}