package com.zote.property.service.domain.usecases;

import com.zote.property.service.domain.models.Property;
import com.zote.property.service.domain.ports.inbound.GetPropertyById;
import com.zote.property.service.domain.ports.outbound.PropertyRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetPropertyByIdImpl implements GetPropertyById {

    private final PropertyRepositoryPort propertyRepository;

    @Override
    public Property findPropertyById(String propertyId) {
        // TODO: 2/9/2024 use controller advice here to check for null values
        return propertyRepository.findPropertyById(propertyId);
    }
}
