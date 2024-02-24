package com.zote.property.service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PropertyDescription {
    private int numberOfBedRooms;
    private int numberOfBaths;
    private int numberOfGarages;
    private int floorNumber;
    private int sizeOfRoom;
    private String thumbnailImage;
    private List<String> images;
    private List<String> facilities;
}
