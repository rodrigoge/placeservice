package br.com.challenge.api.placeservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SortByEnum {

    NAME("name"),
    SLUG("slug"),
    CITY("city"),
    STATE("state");

    private String description;
}
