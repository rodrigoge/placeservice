package br.com.challenge.api.placeservice.util;

import br.com.challenge.api.placeservice.db.PlaceTO;
import br.com.challenge.api.placeservice.exception.FlowException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PlaceUtil {

    public void validatePlaceMapper(PlaceTO placeTO) {
        if (placeTO == null) throw FlowException
                .builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Error during the mapping place flow.")
                .build();
    }
}
