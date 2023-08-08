package br.com.challenge.api.placeservice.api;

import br.com.challenge.api.placeservice.db.Place;
import br.com.challenge.api.placeservice.db.PlaceTO;
import br.com.challenge.api.placeservice.model.GetPlacesRequest;
import br.com.challenge.api.placeservice.model.GetPlacesResponse;
import br.com.challenge.api.placeservice.service.GetPlaceService;
import br.com.challenge.api.placeservice.service.PlaceService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/place")
@Log4j2
public class PlaceControllerApi {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private GetPlaceService getPlaceService;

    @PostMapping("/create")
    public ResponseEntity<PlaceTO> createPlace(@Valid @RequestBody Place place) {
        log.info("Entering in the create place flow.");
        var response = placeService.createPlace(place);
        log.info("Finishing the create place flow.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-by-id/{placeId}")
    public ResponseEntity<PlaceTO> getPlaceById(@PathVariable Long placeId) {
        log.info("Entering in the get a place flow.");
        var response = placeService.getPlace(placeId);
        log.info("Finishing the get a place flow.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<GetPlacesResponse> getAllPlaces(@RequestParam(required = false) String name,
                                                          @RequestParam(required = false) String slug,
                                                          @RequestParam(required = false) String city,
                                                          @RequestParam(required = false) String state,
                                                          @RequestParam Integer offset,
                                                          @RequestParam Integer limit,
                                                          @RequestParam String order,
                                                          @RequestParam String sort) {
        log.info("Entering in the get all places flow.");
        var request = GetPlacesRequest
                .builder()
                .name(name)
                .slug(slug)
                .city(city)
                .state(state)
                .offset(offset)
                .limit(limit)
                .sort(sort)
                .order(order)
                .build();
        var response = getPlaceService.getAllPlaces(request);
        log.info("Finishing the get all places flow.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/edit-by-id/{placeId}")
    public ResponseEntity<PlaceTO> editPlace(@PathVariable Long placeId, @Valid @RequestBody Map<String, String> placeFields) {
        log.info("Entering in the edit place by id flow.");
        var response = placeService.editPlace(placeId, placeFields);
        log.info("Finishing the edit place by id flow.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
