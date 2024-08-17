package com.zote.property.service.infrastructure.outbound.persistence;

import com.zote.common.utils.exceptions.FunctionalError;
import com.zote.property.service.domain.models.Agent;
import com.zote.property.service.domain.models.Property;
import com.zote.property.service.domain.models.PropertyDescription;
import com.zote.property.service.domain.ports.outbound.PropertyRepositoryPort;
import com.zote.property.service.infrastructure.outbound.entities.PropertyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@RequiredArgsConstructor
@Repository
public class PropertyRepositoryPortImp implements PropertyRepositoryPort {

    private final PropertyRepository propertyRepository;

    @Override
    public Property saveProperty(Property property, Agent agent) {
        return propertyRepository.save(PropertyEntity.create(property, agent)).toDomain();
    }

    @Override
    public Page<Property> findPropertyByPage(Pageable pageable) {
        return propertyRepository.findAll(pageable).map(PropertyEntity::toDomain);
    }

    @Override
    public Property findPropertyById(String propertyId) {
        return propertyRepository.findById(propertyId).map(PropertyEntity::toDomain).orElse(null);
    }

    @Override
    public Property updateProperty(Property property) {
        var optionalPropertyEntity  = propertyRepository.findById(property.propertyId());
        if (optionalPropertyEntity.isPresent()) {
            var propertyEntity = optionalPropertyEntity.get();
            return propertyRepository.save(updateProperty(propertyEntity, property)).toDomain();
        } else {
            throw new FunctionalError("Property not found");
        }
    }

    @Override
    public Boolean existsById(String propertyId) {
        return propertyRepository.existsById(propertyId);
    }

    @Override
    public void deletePropertyById(String propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    private PropertyEntity updateProperty(PropertyEntity propertyEntity, Property property){
        propertyEntity.setAddress(property.address());
        propertyEntity.setContent(property.content());
        propertyEntity.setPropertyDescription(updatePropertyDescription(propertyEntity.getPropertyDescription(), property.propertyDescription()));
        propertyEntity.setUpdatedOn(LocalDate.now());
        propertyEntity.setLatitude(property.latitude());
        propertyEntity.setLongitude(property.longitude());
        propertyEntity.setCategory(property.category());
        propertyEntity.setPrice(property.price());
        propertyEntity.setStatus(property.status());
        propertyEntity.setTitle(property.title());
        return propertyEntity;
    }

    private PropertyDescription updatePropertyDescription(PropertyDescription propertyDescription, PropertyDescription property) {
        propertyDescription.setFacilities(property.getFacilities());
        propertyDescription.setFloorNumber(property.getFloorNumber());
        propertyDescription.setNumberOfBaths(property.getNumberOfBaths());
        propertyDescription.setNumberOfGarages(property.getNumberOfGarages());
        propertyDescription.setNumberOfBedRooms(property.getNumberOfBedRooms());
        propertyDescription.setSizeOfRoom(property.getSizeOfRoom());
        return propertyDescription;
    }
}
