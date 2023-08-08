package br.com.challenge.api.placeservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderByEnum {

    ASC("asc"), DESC("desc");

    private String description;
}
