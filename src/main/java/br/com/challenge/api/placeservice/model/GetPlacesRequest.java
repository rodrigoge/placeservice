package br.com.challenge.api.placeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPlacesRequest {

    private String name;
    private String slug;
    private String city;
    private String state;
    private int offset;
    private int limit;
    private String sort;
    private String order;
}
