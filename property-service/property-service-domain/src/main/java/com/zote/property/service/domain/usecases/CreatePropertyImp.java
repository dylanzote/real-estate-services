package com.zote.property.service.domain.usecases;

import com.zote.common.utils.request.HttpService;
import com.zote.property.service.domain.models.Agent;
import com.zote.property.service.domain.models.Property;
import com.zote.property.service.domain.ports.inbound.CreateProperty;
import com.zote.property.service.domain.ports.outbound.PropertyRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreatePropertyImp implements CreateProperty {

    private final PropertyRepositoryPort propertyRepository;

    private final HttpService httpService;

    @Value("${agent-service.get-by-id-url}")
    private String getAgentByIdUri;


    @Override
    public Property createProperty(Property property) {
        log.info("creating property with data {}", property);
        var uri = getAgentByIdUri.concat(property.agentId());
        var agent = httpService.get(uri, Agent.class);
        log.info("response from agent api is {}", agent);
        return propertyRepository.saveProperty(property, agent);
    }
}
