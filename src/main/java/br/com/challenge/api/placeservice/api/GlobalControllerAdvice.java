package br.com.challenge.api.placeservice.api;

import br.com.challenge.api.placeservice.exception.ErrorInformation;
import br.com.challenge.api.placeservice.exception.FlowException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(FlowException.class)
    public ResponseEntity<ErrorInformation> handleFlowException(FlowException exception) {
        var httpStatus = exception.getHttpStatus().value();
        var errorMessage = exception.getMessage();
        return ResponseEntity
                .status(httpStatus)
                .body(ErrorInformation
                        .builder()
                        .httpStatus(httpStatus)
                        .datetime(OffsetDateTime.now(ZoneId.of("UTC")).toString())
                        .message(errorMessage)
                        .build()
                );
    }
}
