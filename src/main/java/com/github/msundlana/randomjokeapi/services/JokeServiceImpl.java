package com.github.msundlana.randomjokeapi.services;

import com.github.msundlana.randomjokeapi.models.Joke;
import com.github.msundlana.randomjokeapi.models.JokeDto;
import com.github.msundlana.randomjokeapi.models.JokeNotFoundException;
import com.github.msundlana.randomjokeapi.repositories.JokeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokeServiceImpl implements JokeService {

    private static final Logger logger = LoggerFactory.getLogger(JokeServiceImpl.class);

    @Autowired
    private JokeRepository jokeRepository;

    @Override
    public JokeDto getRandomJoke() {

        logger.info("Retrieving joke");
        var joke = findShortestSafeJoke();

        logger.info("Joke found: {}", joke);
        return JokeDto
                .builder()
                .id(joke.getId())
                .randomJoke(joke.getJoke())
                .build();
    }

    private Joke findShortestSafeJoke() {

        var randomJoke = jokeRepository.findJokes().stream()
                .filter(joke -> joke.isSafe()
                        && !joke.getFlags().isExplicit()
                        && !joke.getFlags().isSexist()
                ).min((joke1, joke2) ->
                        joke1.getJoke().length()-joke2.getJoke().length())
                .orElseThrow(()
                        -> new JokeNotFoundException("No joke found"));

        return randomJoke;
    }


}
