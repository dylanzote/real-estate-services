package com.zote.property.service.domain.ports.outbound;


import com.zote.property.service.domain.models.Agent;
import com.zote.property.service.domain.models.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PropertyRepositoryPort {

    Property saveProperty(Property property, Agent agent);

    Page<Property> findPropertyByPage(Pageable pageable);

    Property findPropertyById(String propertyId);

    Property updateProperty(Property property);

    Boolean existsById(String propertyId);

    void deletePropertyById(String propertyId);
}
