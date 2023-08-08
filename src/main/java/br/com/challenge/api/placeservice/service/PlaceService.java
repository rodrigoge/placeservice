package br.com.challenge.api.placeservice.service;

import br.com.challenge.api.placeservice.db.Place;
import br.com.challenge.api.placeservice.db.PlaceRepository;
import br.com.challenge.api.placeservice.db.PlaceTO;
import br.com.challenge.api.placeservice.exception.FlowException;
import br.com.challenge.api.placeservice.mapper.PlaceMapper;
import br.com.challenge.api.placeservice.util.PlaceUtil;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.time.OffsetDateTime;
import java.util.Map;

@Service
@Log4j2
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    private PlaceUtil placeUtil;

    public PlaceTO createPlace(Place request) {
        log.info("Initializing the create a place flow.");
        request.setCreatedAt(OffsetDateTime.now());
        request.setUpdatedAt(OffsetDateTime.now());
        var place = placeRepository.save(request);
        log.info("Mapping the place object.");
        var placeResponse = placeMapper.fromPlace(place);
        placeUtil.validatePlaceMapper(placeResponse);
        return placeResponse;
    }

    public PlaceTO getPlace(Long placeId) {
        log.info("Initializing the get a place flow.");
        var place = placeRepository.findById(placeId).orElseThrow(() -> FlowException
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Place is not found in database.")
                .build()
        );
        log.info("Mapping the place object.");
        var placeResponse = placeMapper.fromPlace(place);
        placeUtil.validatePlaceMapper(placeResponse);
        return placeResponse;
    }

    public PlaceTO editPlace(Long placeId, Map<String, String> placeFields) {
        log.info("Initializing the edit place by id flow.");
        var place = placeRepository.findById(placeId).orElseThrow(() -> FlowException
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Place is not found in database.")
                .build()
        );
        if(placeFields == null) throw FlowException
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Fields doesn't null.")
                .build();
        var placeMapResponse = placeMapFields(place, placeFields);
        log.info("Saving object in database.");
        var placeResponse = placeRepository.save(placeMapResponse);
        log.info("Mapping the place object.");
        var response = placeMapper.fromPlace(placeResponse);
        placeUtil.validatePlaceMapper(response);
        return response;
    }

    private Place placeMapFields(Place place, @NonNull Map<String, String> placeFields) {
        log.info("Initializing the mapper place flow.");
        placeFields.forEach((key, value) -> {
            var field = ReflectionUtils.findField(Place.class, key);
            if (field == null) throw FlowException
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("Field doesn't exist.")
                    .build();
            field.setAccessible(true);
            ReflectionUtils.setField(field, place, value);
        });
        log.info("Finishing the mapper place flow.");
        return place;
    }
}
