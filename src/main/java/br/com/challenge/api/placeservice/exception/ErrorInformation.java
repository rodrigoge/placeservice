package br.com.challenge.api.placeservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorInformation {

    private Integer httpStatus;
    private String datetime;
    private String message;
}
