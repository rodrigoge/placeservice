package br.com.challenge.api.placeservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@Log4j2
public class FlowException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final String message;

    public FlowException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
        log.error("Error: {}, Status: {}", httpStatus, message);
    }
}
