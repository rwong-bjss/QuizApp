package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.UserDto;
import org.example.model.InitialUser;
import org.example.service.TriviaService;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class QuizControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private TriviaService triviaService;

    @Test
    public void testStartQuiz() throws Exception {
        // Arrange
        InitialUser initialUser = new InitialUser();
        initialUser.setName("TestUser");

        UserDto userDto = new UserDto();
        userDto.setName("TestUser");
        userDto.setSessionId(UUID.randomUUID());
        userDto.setCorrectAnswers(0);
        userDto.setQuestionsAnswered(0);

        when(userService.createUser(any(InitialUser.class))).thenReturn(userDto);

        // Act & Assert
        mockMvc.perform(post("/quiz/start")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(initialUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("TestUser"))
                .andExpect(jsonPath("$.sessionId").isNotEmpty())
                .andExpect(jsonPath("$.correctAnswers").value(0))
                .andExpect(jsonPath("$.questionsAnswered").value(0));
    }
}