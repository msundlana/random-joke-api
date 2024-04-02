package com.github.msundlana.randomjokeapi.exception;

import com.github.msundlana.randomjokeapi.models.JokeNotFoundException;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @ExceptionHandler(JokeNotFoundException.class)
    public final ResponseEntity<Object> handleJokeNotFoundException
            (JokeNotFoundException ex, WebRequest request)  {

        logger.error("Error occurred while extracting random joke: {}", ex.getMessage());

        var exceptionResponse = ExceptionResponse.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    @ExceptionHandler({CallNotPermittedException.class, ResourceAccessException.class})
    public ResponseEntity<ExceptionResponse> handleServiceUnavailableException(Exception ex, WebRequest request) {
        logger.error("Service Unavailable Error occurred while extracting jokes: {}", ex.getMessage());

        var exceptionResponse = ExceptionResponse.builder()
                .timestamp(new Date())
                .message("No jokes available.")
                .details(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(exceptionResponse);
    }

    @ExceptionHandler(BulkheadFullException.class )
    public ResponseEntity<ExceptionResponse> handleBulkheadFullException(Exception ex, WebRequest request) {
        logger.error("Service struggling to handle to many concurrent request: {}", ex.getMessage());

        var exceptionResponse = ExceptionResponse.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
                .body(exceptionResponse);
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<ExceptionResponse> handleRequestNotPermitted(Exception ex, WebRequest request) {
        logger.error("Maximum request limit reach : {}", ex.getMessage());

        var exceptionResponse = ExceptionResponse.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {

        logger.error("Internal Server Error occurred while extracting random joke: {}", ex.getMessage());

        var exceptionResponse = ExceptionResponse.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();

        return ResponseEntity.internalServerError()
                .body(exceptionResponse);
    }


}
