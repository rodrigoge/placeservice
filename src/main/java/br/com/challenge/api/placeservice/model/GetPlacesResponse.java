package br.com.challenge.api.placeservice.model;

import br.com.challenge.api.placeservice.db.PlaceTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPlacesResponse {

    private List<PlaceTO> places;
    private int totalRecords;
}
