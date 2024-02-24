package com.zote.property.service.domain.models;


import com.zote.property.service.domain.enums.Category;
import com.zote.property.service.domain.enums.ModerationStatus;
import com.zote.property.service.domain.enums.Status;
import com.zote.property.service.domain.enums.Type;

import java.time.LocalDate;
import java.time.LocalTime;

public record Property(String propertyId,
                       String title,
                       String country,
                       String town,
                       String address,
                       int latitude,
                       int longitude,
                       Type type,
                       Category category,
                       boolean isFeatured,
                       ModerationStatus moderationStatus,
                       double price,
                       String content,
                       PropertyDescription propertyDescription,
                       String agentId,
                       Status status,
                       LocalDate createdOn,
                       LocalDate updatedOn,
                       LocalTime createdTime,
                       LocalTime updatedTime) {

}
