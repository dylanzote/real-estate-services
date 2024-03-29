package com.zote.agent.service.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zote.agent.service.domain.models.Agent;
import org.springframework.data.domain.Page;

import java.util.List;

public record AgentPageResponse(List<Agent> data, long totalElements, int totalPages, int currentPage, @JsonProperty("isFirst") boolean isFirst, @JsonProperty("isLast") boolean isLast, boolean hasNext, boolean hasPrevious) {
    public AgentPageResponse(Page<Agent> propertyPage) {
        this(propertyPage.getContent(), propertyPage.getTotalElements(), propertyPage.getTotalPages(), propertyPage.getNumber() + 1, propertyPage.isFirst(), propertyPage.isLast(), propertyPage.hasNext(), propertyPage.hasPrevious());
    }
}
