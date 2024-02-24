package com.zote.property.service.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zote.property.service.domain.models.Property;
import org.springframework.data.domain.Page;

import java.util.List;

public record PropertyPageResponse(List<Property> data, long totalElements, int totalPages, int currentPage, @JsonProperty("isFirst") boolean isFirst, @JsonProperty("isLast") boolean isLast, boolean hasNext, boolean hasPrevious) {
    public PropertyPageResponse(Page<Property> propertyPage) {
        this(propertyPage.getContent(), propertyPage.getTotalElements(), propertyPage.getTotalPages(), propertyPage.getNumber() + 1, propertyPage.isFirst(), propertyPage.isLast(), propertyPage.hasNext(), propertyPage.hasPrevious());
    }
}
