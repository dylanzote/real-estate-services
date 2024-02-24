package com.zote.property.service.domain.enums;

import lombok.Getter;

@Getter
public enum Town {
    DOUALA("Douala"),
    YAOUNDE("Yaoundé"),
    BAMENDA("Bamenda"),
    BUEA("Buea");

    private final String townName;

    Town(String townName) {
        this.townName = townName;
    }

}
