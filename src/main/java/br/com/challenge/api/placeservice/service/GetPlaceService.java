package br.com.challenge.api.placeservice.service;

import br.com.challenge.api.placeservice.db.Place;
import br.com.challenge.api.placeservice.db.PlaceTO;
import br.com.challenge.api.placeservice.enums.OrderByEnum;
import br.com.challenge.api.placeservice.enums.SortByEnum;
import br.com.challenge.api.placeservice.mapper.PlaceMapper;
import br.com.challenge.api.placeservice.mapper.SortEnumMapper;
import br.com.challenge.api.placeservice.model.GetPlacesRequest;
import br.com.challenge.api.placeservice.model.GetPlacesResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GetPlaceService {

    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    private EntityManager entityManager;

    public GetPlacesResponse getAllPlaces(GetPlacesRequest request) {
        log.info("Initializing the get all places flow.");
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = buildGetPlaceQuery(request, criteriaBuilder);
        log.info("Executing query and mapping.");
        var places = criteriaQuery.getResultList();
        var response = buildMapperPlace(places);
        var countQuery = buildCountQuery(request, criteriaBuilder);
        var count = entityManager.createQuery(countQuery).getSingleResult().intValue();
        return GetPlacesResponse
                .builder()
                .places(response)
                .totalRecords(count)
                .build();
    }

    public TypedQuery<Place> buildGetPlaceQuery(GetPlacesRequest request, CriteriaBuilder criteriaBuilder) {
        log.info("Initializing query for get all places flow.");
        var query = criteriaBuilder.createQuery(Place.class);
        var root = query.from(Place.class);
        var predicates = buildPredicates(request, criteriaBuilder, root);
        query.select(root);
        query.where(predicates.toArray(new Predicate[0]));
        sortAndOrderQuery(
                query,
                criteriaBuilder,
                root,
                SortByEnum.valueOf(request.getSort().toUpperCase()),
                OrderByEnum.valueOf(request.getOrder().toUpperCase())
        );
        var typedQuery = entityManager.createQuery(query);
        paginationQuery(typedQuery, request.getOffset(), request.getLimit());
        log.info("Finishing query for get all places flow.");
        return typedQuery;
    }

    public List<PlaceTO> buildMapperPlace(List<Place> places) {
        log.info("Initializing mapping from places.");
        return places
                .stream()
                .map(placeMapper::fromPlace)
                .collect(Collectors.toList());
    }

    public List<Predicate> buildPredicates(GetPlacesRequest placeRequest,
                                           CriteriaBuilder criteriaBuilder,
                                           Root<Place> root) {
        var predicates = new ArrayList<Predicate>();
        if (StringUtils.isNotBlank(placeRequest.getName())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), "%" + placeRequest.getName().toLowerCase() + "%")
            );
        }
        if (StringUtils.isNotBlank(placeRequest.getSlug())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("slug")), "%" + placeRequest.getSlug().toLowerCase() + "%")
            );
        }
        if (StringUtils.isNotBlank(placeRequest.getCity())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("city")), "%" + placeRequest.getCity().toLowerCase() + "%")
            );
        }
        if (StringUtils.isNotBlank(placeRequest.getState())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("state")), "%" + placeRequest.getState().toLowerCase() + "%")
            );
        }
        if (StringUtils.isNotBlank(placeRequest.getName())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), "%" + placeRequest.getName().toLowerCase() + "%")
            );
        }
        return predicates;
    }

    private void sortAndOrderQuery(CriteriaQuery<?> criteriaQuery,
                                   CriteriaBuilder criteriaBuilder,
                                   Root<?> root,
                                   SortByEnum sortByEnum,
                                   OrderByEnum orderByEnum) {
        log.info("Initializing sorting and ordering query.");
        var sortType = SortEnumMapper.sortByEnum(sortByEnum);
        var pathList = sortType.getPath(root, criteriaBuilder);
        var order = pathList
                .stream()
                .map((path) -> orderByEnum.getDescription().equals("desc") ?
                        criteriaBuilder.desc(path) :
                        criteriaBuilder.asc(path))
                .toList();
        criteriaQuery.orderBy(order);
        log.info("Finishing sorting and ordering query.");
    }

    private void paginationQuery(TypedQuery<?> typedQuery,
                                 Integer offset,
                                 Integer limit) {
        log.info("Initializing counting query.");
        if (offset != null && limit != null) typedQuery.setFirstResult(offset * limit);
        if (limit != null) typedQuery.setMaxResults(limit);
    }

    public CriteriaQuery<Long> buildCountQuery(GetPlacesRequest request, CriteriaBuilder criteriaBuilder) {
        log.info("Initializing counting query.");
        var count = criteriaBuilder.createQuery(Long.class);
        var root = count.from(Place.class);
        var predicates = buildPredicates(request, criteriaBuilder, root);
        count.select(criteriaBuilder.count(root));
        count.where(predicates.toArray(new Predicate[0]));
        log.info("Finishing counting query.");
        return count;
    }
}
