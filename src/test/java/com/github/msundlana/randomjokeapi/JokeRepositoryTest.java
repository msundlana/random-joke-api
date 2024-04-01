package com.github.msundlana.randomjokeapi;

import com.github.msundlana.randomjokeapi.repositories.JokeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest({JokeRepository.class})
@ActiveProfiles(value = "test")
public class JokeRepositoryTest {
    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private JokeRepository jokeRepository;

    @AfterEach
    void resetMockServer(){
        mockRestServiceServer.reset();
    }
    @Test
    public void testFindJokes() {
        final var jokesJson = new ClassPathResource("jokes.json");
        mockRestServiceServer
                .expect(requestTo("/joke/Any?type=single&amount=16"))
                .andRespond(withSuccess(jokesJson, MediaType.APPLICATION_JSON));

        var jokes = jokeRepository.findJokes();

        assertNotNull(jokes);
        assertTrue(jokes.size()>0);
        assertEquals(204,jokes.get(0).getId());
        assertEquals("Pun",jokes.get(0).getCategory());
        assertFalse(jokes.get(0).isSafe());
    }

    @Test
    public void testNoFindJokes() {
        final var noJokesJson = new ClassPathResource("noJokes.json");
        mockRestServiceServer
                .expect(requestTo("/joke/Any?type=single&amount=16"))
                .andRespond(withSuccess(noJokesJson, MediaType.APPLICATION_JSON));

        var jokes = jokeRepository.findJokes();

        assertNotNull(jokes);
        assertTrue(jokes.isEmpty());
    }

}
