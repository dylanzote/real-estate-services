package com.zote.property.service.domain.ports.inbound;

import com.zote.property.service.domain.models.Property;

public interface CreateProperty {

    Property createProperty(Property property);
}
