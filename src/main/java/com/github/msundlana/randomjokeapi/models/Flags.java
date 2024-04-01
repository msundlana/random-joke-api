package com.github.msundlana.randomjokeapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flags {

    private boolean nsfw;

    private boolean religious;

    private boolean political;

    private boolean racist;

    private boolean sexist;

    private boolean explicit;

}
