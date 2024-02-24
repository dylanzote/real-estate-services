package com.zote.property.service.domain.ports.inbound;

import com.zote.property.service.domain.models.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;


public interface GetAllPropertyByPage {

    Page<Property> getAllPropertiesByPage(int pageNo, int sizePerPage, String sortField, Sort.Direction sortDirection);
}
