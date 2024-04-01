package com.github.msundlana.randomjokeapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalAPIJokeResponse {

    private int amount;

    private List<Joke> jokes;

    private boolean error;
}
