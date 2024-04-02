package com.github.msundlana.randomjokeapi.controllers;

import com.github.msundlana.randomjokeapi.models.JokeDto;
import com.github.msundlana.randomjokeapi.services.JokeService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base}/joke")
public class JokeController {
    private static final Logger logger = LoggerFactory.getLogger(JokeController.class);

    @Autowired
    private JokeService jokeService;

    @GetMapping("/")
    @CircuitBreaker(name="random-joke-api")
    @Bulkhead(name="random-joke-api")
    @RateLimiter(name = "random-joke-api")
    public ResponseEntity<JokeDto> getRandomJoke() {
        logger.info("Received request to get joke");

        var joke = jokeService.getRandomJoke();

        logger.info("Joke found: {}", joke);
        return new ResponseEntity<>(joke, HttpStatus.OK);
    }

}

