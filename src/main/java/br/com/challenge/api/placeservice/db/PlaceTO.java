package br.com.challenge.api.placeservice.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
public class PlaceTO {

    private String name;
    private String slug;
    private String city;
    private String state;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
