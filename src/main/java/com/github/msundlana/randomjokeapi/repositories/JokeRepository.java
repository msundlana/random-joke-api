package com.github.msundlana.randomjokeapi.repositories;

import com.github.msundlana.randomjokeapi.models.ExternalAPIJokeResponse;
import com.github.msundlana.randomjokeapi.models.Joke;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class JokeRepository {
    private static final Logger logger = LoggerFactory.getLogger(JokeRepository.class);
    private final RestTemplate restTemplate;

    public JokeRepository(RestTemplateBuilder restTemplateBuilder, @Value("${external.joke.api.baseUrl}") String baseUrl) {
        this.restTemplate = restTemplateBuilder.rootUri(baseUrl).build();
    }

    public List<Joke> findJokes() {
        logger.info("Retrieving all jokes from External Jokes API");
        ResponseEntity<ExternalAPIJokeResponse> response;
        try {
             response = restTemplate
                    .getForEntity("/joke/Any?type=single&amount=16", ExternalAPIJokeResponse.class);

            var externalApiJokeResponse = response.getBody();

            if( externalApiJokeResponse==null){
                logger.warn("External Jokes API returned an empty response body");
            }else if(externalApiJokeResponse.getAmount()==0){
                logger.warn("External Jokes API returned no jokes");
            }else {
                logger.info("Total jokes found: {}", externalApiJokeResponse.getAmount());
                return externalApiJokeResponse.getJokes();
            }

        }catch (HttpStatusCodeException e){
            logger.error("External Jokes API responding with an error : {}",e.getMessage());
        }
        return List.of();
    }
}
