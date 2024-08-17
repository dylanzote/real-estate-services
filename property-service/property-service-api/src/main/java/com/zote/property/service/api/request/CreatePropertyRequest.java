package com.zote.property.service.api.request;

import com.zote.property.service.domain.enums.Category;
import com.zote.property.service.domain.enums.Type;

import com.zote.property.service.domain.models.Property;
import com.zote.property.service.domain.models.PropertyDescription;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static com.zote.property.service.domain.enums.ModerationStatus.PENDING;
import static com.zote.property.service.domain.enums.Status.FREE;


public record CreatePropertyRequest(
        String title,
        String country,
        String town,
        String city,
        String address,
        double latitude,
        double longitude,
        Type type,
        Category category,
        double price,
        String content,
        int numberOfBedRooms,
        int numberOfBaths,
        int numberOfGarages,
        int floorNumber,
        int sizeOfRoom,
        @RequestPart(value = "thumbnailImage", required = true) MultipartFile thumbnailImage,
        @RequestPart(value = "images", required = true)  List<MultipartFile> images,
        List<String> facilities,
        String agentId,
        String agentName) {

    public Property toProperty() {

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

        return new Property(UUID.randomUUID().toString(), this.title(), this.country(), this.town(),
                this.address(), this.latitude(), this.longitude(), type,
                category, false, PENDING, this.price(), this.content(), propertyDescription, this.agentId(),
                FREE, LocalDate.now(), null, LocalTime.now(), null);
    }
}
