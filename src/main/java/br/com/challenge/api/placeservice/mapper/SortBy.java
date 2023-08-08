package br.com.challenge.api.placeservice.mapper;

import br.com.challenge.api.placeservice.db.Place;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

import java.util.List;

public interface SortBy {
    List<Path<Place>> getPath(Root<?> root, CriteriaBuilder criteriaBuilder);
}
