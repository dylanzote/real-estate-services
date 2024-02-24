package com.zote.property.service.domain.usecases;

import com.zote.property.service.domain.models.Property;
import com.zote.property.service.domain.ports.inbound.GetAllPropertyByPage;
import com.zote.property.service.domain.ports.outbound.PropertyRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class GetAllPropertiesImpl implements GetAllPropertyByPage {

    private final PropertyRepositoryPort propertyRepository;

    @Override
    public Page<Property> getAllPropertiesByPage(int page, int sizePerPage, String sortField, Sort.Direction sortDirection) {
        var pageNo = page < 0 ? 0 : page - 1;
        var pageable = PageRequest.of(pageNo, sizePerPage, sortDirection, sortField);
        return propertyRepository.findPropertyByPage(pageable);
    }
}
