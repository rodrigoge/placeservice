package br.com.challenge.api.placeservice.mapper;

import br.com.challenge.api.placeservice.enums.SortByEnum;

public class SortEnumMapper {

    public static SortBy sortByEnum(SortByEnum sortByEnum) {
        return switch (sortByEnum) {
            case NAME -> new SortByName();
            case SLUG -> new SortBySlug();
            case CITY -> new SortByCity();
            case STATE -> new SortByState();
        };
    }
}
