package com.github.msundlana.randomjokeapi;

import com.github.msundlana.randomjokeapi.models.Flags;
import com.github.msundlana.randomjokeapi.models.Joke;

import com.github.msundlana.randomjokeapi.models.JokeNotFoundException;
import com.github.msundlana.randomjokeapi.repositories.JokeRepository;
import com.github.msundlana.randomjokeapi.services.JokeService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


import com.github.msundlana.randomjokeapi.services.JokeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
public class JokeServiceTest {

    @Mock
    private JokeRepository jokeRepository;
    @InjectMocks
    private JokeService jokeService = new JokeServiceImpl();


    @Test
    public void testGetRandomJoke() {
        var jokes = List.of(
                Joke.builder()
                        .id(1L)
                        .joke("This is a joke test")
                        .safe(true)
                        .flags(Flags.builder().build())
                        .build()

        );

        when(jokeRepository.findJokes()).thenReturn(jokes);
        var result = jokeService.getRandomJoke();

        assertNotNull(result);
        assertEquals(1,jokes.size());
        assertEquals(1L, jokes.get(0).getId());
    }



    @Test
    public void testNoUnSafeRandomJoke() {
        var jokes = List.of(
                Joke.builder()
                        .id(1L)
                        .joke("This is a joke test")
                        .build()

        );

        when(jokeRepository.findJokes()).thenReturn(jokes);
        assertThrows(JokeNotFoundException.class, () ->jokeService.getRandomJoke());
    }

    @Test
    public void testNoSexistRandomJoke() {
        var jokes = List.of(
                Joke.builder()
                        .id(1L)
                        .joke("This is a joke test")
                        .safe(true)
                        .flags(Flags.builder()
                                .sexist(true)
                                .explicit(false)
                                .build())
                        .build()

        );

        when(jokeRepository.findJokes()).thenReturn(jokes);
        assertThrows(JokeNotFoundException.class, () ->jokeService.getRandomJoke());
    }

    @Test
    public void testNoExplicitRandomJoke() {
        var jokes = List.of(
                Joke.builder()
                        .id(1L)
                        .joke("This is a joke test")
                        .safe(true)
                        .flags(Flags.builder()
                                .sexist(false)
                                .explicit(true)
                                .build())
                        .build()

        );

        when(jokeRepository.findJokes()).thenReturn(jokes);
        assertThrows(JokeNotFoundException.class, () ->jokeService.getRandomJoke());
    }

    @Test
    public void testNoUnsafeSexistAndExplicitRandomJoke() {
        var jokes = List.of(
                Joke.builder()
                        .id(1L)
                        .joke("This is a joke test")
                        .safe(false)
                        .flags(Flags.builder()
                                .sexist(true)
                                .explicit(true)
                                .build())
                        .build()

        );

        when(jokeRepository.findJokes()).thenReturn(jokes);
        assertThrows(JokeNotFoundException.class, () ->jokeService.getRandomJoke());
    }

    @Test
    public void testNoSexistAndExplicitRandomJoke() {
        var jokes = List.of(
                Joke.builder()
                        .id(1L)
                        .joke("This is a joke test")
                        .safe(true)
                        .flags(Flags.builder()
                                .sexist(true)
                                .explicit(true)
                                .build())
                        .build()

        );

        when(jokeRepository.findJokes()).thenReturn(jokes);
        assertThrows(JokeNotFoundException.class, () ->jokeService.getRandomJoke());
    }

}
