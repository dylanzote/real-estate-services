package com.zote.property.service.api.usecases;

import com.zote.property.service.api.request.CreatePropertyRequest;
import com.zote.property.service.domain.models.Property;
import com.zote.property.service.domain.models.PropertyDescription;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static com.zote.property.service.domain.enums.ModerationStatus.PENDING;
import static com.zote.property.service.domain.enums.Status.FREE;

@Component
public class CreatePropertyUseCase {

    public Property createProperty(CreatePropertyRequest createPropertyRequest) {

        var propertyDescription = PropertyDescription.builder()
                .facilities(createPropertyRequest.facilities())
                .floorNumber(createPropertyRequest.floorNumber())
                .images(createPropertyRequest.images())
                .numberOfBaths(createPropertyRequest.numberOfBaths())
                .numberOfBedRooms(createPropertyRequest.numberOfBedRooms())
                .numberOfGarages(createPropertyRequest.numberOfGarages())
                .sizeOfRoom(createPropertyRequest.sizeOfRoom())
                .thumbnailImage(createPropertyRequest.thumbnailImage())
                .build();

        return new Property(UUID.randomUUID().toString(), createPropertyRequest.title(), createPropertyRequest.country(), createPropertyRequest.town(),
                createPropertyRequest.address(), createPropertyRequest.latitude(), createPropertyRequest.longitude(), createPropertyRequest.type(),
                createPropertyRequest.category(), false, PENDING, createPropertyRequest.price(), createPropertyRequest.content(), propertyDescription, createPropertyRequest.agentId(),
                FREE, LocalDate.now(), null, LocalTime.now(), null);
    }
}
