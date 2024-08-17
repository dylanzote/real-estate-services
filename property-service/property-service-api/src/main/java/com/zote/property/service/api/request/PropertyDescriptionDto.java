package com.zote.property.service.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PropertyDescriptionDto {
    private int numberOfBedRooms;
    private int numberOfBaths;
    private int numberOfGarages;
    private int floorNumber;
    private int sizeOfRoom;
    private MultipartFile thumbnailImage;
    private List<MultipartFile> images;
    private List<String> facilities;
}
