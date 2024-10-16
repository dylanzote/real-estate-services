package com.zote.property.service.infrastructure.outbound.persistence;

import com.zote.property.service.infrastructure.outbound.entities.PropertyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends MongoRepository<PropertyEntity, String> {
}
