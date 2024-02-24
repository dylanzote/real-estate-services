package com.zote.agent.service.api.enums;

import lombok.Getter;

@Getter
public enum SortField {

    STATUS("agentStatus"),
    CREATED_ON("createdOn");

    private final String fieldName;

    SortField(String fieldName) {
        this.fieldName = fieldName;
    }
}
