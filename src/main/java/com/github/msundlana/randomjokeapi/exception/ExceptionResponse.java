package com.github.msundlana.randomjokeapi.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
