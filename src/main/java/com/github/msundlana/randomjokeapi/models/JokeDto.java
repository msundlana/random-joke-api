package com.github.msundlana.randomjokeapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class JokeDto {

    private Long id;

    private String randomJoke;
}
