package br.com.challenge.api.placeservice.mapper;

import br.com.challenge.api.placeservice.db.Place;
import br.com.challenge.api.placeservice.db.PlaceTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PlaceMapper {

    PlaceTO fromPlace(Place place);
}
