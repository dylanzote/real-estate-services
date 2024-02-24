package com.zote.property.service.api.request;

import com.zote.common.utls.exceptions.FunctionalError;
import com.zote.property.service.domain.models.Property;
import com.zote.property.service.domain.models.PropertyDescription;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.zote.property.service.domain.enums.Category.*;
import static com.zote.property.service.domain.enums.ModerationStatus.PENDING;
import static com.zote.property.service.domain.enums.Status.FREE;
import static com.zote.property.service.domain.enums.Type.RENT;
import static com.zote.property.service.domain.enums.Type.SALE;

public record UpdatePropertyRequest(String propertyId,
                                    String title,
                                    String country,
                                    String town,
                                    String city,
                                    String address,
                                    int latitude,
                                    int longitude,
                                    String type,
                                    String category,
                                    double price,
                                    String content,
                                    int numberOfBedRooms,
                                    int numberOfBaths,
                                    int numberOfGarages,
                                    int floorNumber,
                                    int sizeOfRoom,
                                    String thumbnailImage,
                                    List<String> images,
                                    List<String> facilities,
                                    String agentId,
                                    String agentName) {

    public Property toDomain() {

        var type = switch (this.type()) {
            case "sale" -> SALE;
            case "rent" -> RENT;
            default -> throw new FunctionalError("Unexpected value: " + this.type());
        };

        var category = switch (this.category()) {
            case "residential" -> RESIDENTIAL;
            case "commercial" -> COMMERCIAL;
            case "villa" -> VILLA;
            case "guest house" -> GUEST_HOUSE;
            case "apartment" -> APARTMENT;
            default -> throw new FunctionalError("Unexpected value: " + this.category());
        };

        var propertyDescription = PropertyDescription.builder()
                .facilities(this.facilities())
                .floorNumber(this.floorNumber())
                .images(this.images())
                .numberOfBaths(this.numberOfBaths())
                .numberOfBedRooms(this.numberOfBedRooms())
                .numberOfGarages(this.numberOfGarages())
                .sizeOfRoom(this.sizeOfRoom())
                .thumbnailImage(this.thumbnailImage())
                .build();

        return new Property(this.propertyId(), this.title(), this.country(), this.town(),
                this.address(), this.latitude(), this.longitude(), type,
                category, false, PENDING, this.price(), this.content(), propertyDescription, this.agentId(),
                FREE, LocalDate.now(), null, LocalTime.now(), null);
    }
}
