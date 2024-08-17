package com.zote.user.service.api.enums;

import lombok.Getter;

@Getter
public enum SortField {

    STATUS("status"),
    CREATED_ON("createdDate");

    private final String fieldName;

    SortField(String fieldName) {
        this.fieldName = fieldName;
    }
}
