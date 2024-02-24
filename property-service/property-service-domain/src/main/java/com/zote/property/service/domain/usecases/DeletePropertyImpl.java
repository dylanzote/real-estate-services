package com.zote.property.service.domain.usecases;

import com.zote.common.utls.exceptions.FunctionalError;
import com.zote.property.service.domain.ports.inbound.DeleteProperty;
import com.zote.property.service.domain.ports.outbound.PropertyRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeletePropertyImpl implements DeleteProperty {

    private final PropertyRepositoryPort propertyRepository;

    @Override
    public void deleteProperty(String propertyId) {
        if (Boolean.FALSE.equals(propertyRepository.existsById(propertyId))) {
            throw new FunctionalError("property not found");
        }
        propertyRepository.deletePropertyById(propertyId);
    }
}
