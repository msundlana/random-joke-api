package com.github.msundlana.randomjokeapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Joke {

    private Long id;

    private String joke;

    private String category;

    private String type;

    private String lang;

    private boolean safe;

    private Flags flags;

}
