package com.zote.property.service.domain.usecases;

import com.zote.common.utls.request.HttpService;
import com.zote.property.service.domain.models.Agent;
import com.zote.property.service.domain.models.Property;
import com.zote.property.service.domain.ports.inbound.CreateProperty;
import com.zote.property.service.domain.ports.outbound.PropertyRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreatePropertyImp implements CreateProperty {

    private final PropertyRepositoryPort propertyRepository;

    private final HttpService httpService;


    @Override
    public Property createProperty(Property property) {
        log.info("creating property with data {}", property);
        var uri = "http://localhost:8081/agent/getById/".concat(property.agentId());
        var agent = httpService.get(uri, Agent.class);
        log.info("response from agent api is {}", agent);
        return propertyRepository.saveProperty(property);
    }
}
