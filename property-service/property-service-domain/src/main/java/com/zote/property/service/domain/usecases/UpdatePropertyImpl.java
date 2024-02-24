package com.zote.property.service.domain.usecases;

import com.zote.common.utls.exceptions.FunctionalError;
import com.zote.property.service.domain.models.Property;
import com.zote.property.service.domain.ports.inbound.UpdateProperty;
import com.zote.property.service.domain.ports.outbound.PropertyRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdatePropertyImpl implements UpdateProperty {

    private final PropertyRepositoryPort propertyRepository;
    @Override
    public Property updateProperty(Property property) {

        if (Boolean.FALSE.equals(propertyRepository.existsById(property.propertyId()))) {
            throw new FunctionalError("property does not exist");
        }

        return propertyRepository.updateProperty(property);
    }
}
