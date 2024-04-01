package com.github.msundlana.randomjokeapi;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.msundlana.randomjokeapi.models.JokeDto;
import com.github.msundlana.randomjokeapi.models.JokeNotFoundException;
import com.github.msundlana.randomjokeapi.services.JokeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class JokeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JokeService jokeService;

    private final String basePath = "/api/joke/";

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRandomJoke() throws Exception {
        var joke = JokeDto.builder()
                .id(1L)
                .randomJoke("This is a random joke")
                .build();

        when(jokeService.getRandomJoke()).thenReturn(joke);

        mockMvc.perform(get(basePath)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.randomJoke").value("This is a random joke"));
    }

    @Test
    public void testNoRandomJoke() throws Exception {

        when(jokeService.getRandomJoke()).thenThrow(new JokeNotFoundException("No joke found"));

        mockMvc.perform(get(basePath)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRandomJokeThrowInternalServerError() throws Exception {

        when(jokeService.getRandomJoke()).thenThrow(new IllegalAccessError());

        mockMvc.perform(get(basePath)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }



}

