package com.zote.property.service.api.enums;

import lombok.Getter;

@Getter
public enum SortField {

    PRICE("price"),
    CREATED_ON("createdOn");

    private final String fieldName;

    SortField(String fieldName) {
        this.fieldName = fieldName;
    }
}
