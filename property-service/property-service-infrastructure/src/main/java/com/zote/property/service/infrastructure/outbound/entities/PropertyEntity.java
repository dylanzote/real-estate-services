package com.zote.property.service.infrastructure.outbound.entities;

import com.zote.property.service.domain.enums.*;
import com.zote.property.service.domain.models.Agent;
import com.zote.property.service.domain.models.Property;
import com.zote.property.service.domain.models.PropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "property")
public class PropertyEntity {
    @Id
    private String propertyId;
    private String title;
    private String country;
    private Town town;
    private String address;
    private double latitude;
    private double longitude;
    private Type type;
    private Category category;
    private boolean isFeatured;
    private ModerationStatus moderationStatus;
    private double price;
    private String content;
    private PropertyDescription propertyDescription;
    private String agentId;
    private Status status;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private LocalTime createdTime;
    private LocalTime updatedTime;

    public static PropertyEntity create(Property property, Agent agent) {
        return PropertyEntity.builder()
                .agentId(agent.getAgentId())
                .address(property.address())
                .category(property.category())
                .content(property.content())
                .country(property.country())
                .createdTime(property.createdTime())
                .createdOn(property.createdOn())
                .isFeatured(property.isFeatured())
                .latitude(property.latitude())
                .longitude(property.longitude())
                .moderationStatus(property.moderationStatus())
                .price(property.price())
                .propertyDescription(property.propertyDescription())
                .propertyId(String.valueOf(UUID.randomUUID()))
                .status(property.status())
                .title(property.title())
                .town(Town.valueOf(property.town()))
                .type(property.type())
                .updatedOn(property.updatedOn())
                .updatedTime(property.updatedTime())
                .build();
    }

    public static PropertyEntity create(Property property) {
        return PropertyEntity.builder()
                .agentId(property.agentId())
                .address(property.address())
                .category(property.category())
                .content(property.content())
                .country(property.country())
                .createdTime(property.createdTime())
                .createdOn(property.createdOn())
                .isFeatured(property.isFeatured())
                .latitude(property.latitude())
                .longitude(property.longitude())
                .moderationStatus(property.moderationStatus())
                .price(property.price())
                .propertyDescription(property.propertyDescription())
                .propertyId(String.valueOf(UUID.randomUUID()))
                .status(property.status())
                .title(property.title())
                .town(Town.valueOf(property.town()))
                .type(property.type())
                .updatedOn(property.updatedOn())
                .updatedTime(property.updatedTime())
                .build();
    }

    public Property toDomain() {
        return new Property(this.propertyId, this.title, this.country, this.town.name(), this.address, this.latitude, this.longitude, this.type, this.category, this.isFeatured, this.moderationStatus, this.price, this.content, this.propertyDescription, this.agentId, this.status, this.createdOn, this.updatedOn, this.createdTime, this.updatedTime);
    }
}
