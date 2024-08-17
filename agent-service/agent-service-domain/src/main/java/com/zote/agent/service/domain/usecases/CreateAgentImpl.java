package com.zote.agent.service.domain.usecases;

import com.zote.agent.service.domain.models.Agent;
import com.zote.agent.service.domain.models.AgentDto;
import com.zote.agent.service.domain.ports.inbound.CreateAgent;
import com.zote.agent.service.domain.ports.outbound.AgentRepositoryPort;
import com.zote.agent.service.domain.support.AgentSupport;
import com.zote.common.utils.exceptions.FunctionalError;
import com.zote.common.utils.files.MinioObjectStorage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateAgentImpl implements CreateAgent {

    private final AgentRepositoryPort agentRepository;

    private final AgentSupport agentSupport;

    private final MinioObjectStorage minioObjectStorage;

    @Override
    @SneakyThrows
    public AgentDto createAgent(Agent agent) {
        log.info("creating agent with data {}", agent);
        agentSupport.validateData(agent);
        verifyIfAgentExist(agent);
        agent.setPassword(new BCryptPasswordEncoder().encode(agent.getPassword()));
        minioObjectStorage.uploadImage(agent.getImage(), minioObjectStorage.getAgentImageName(agent.getAgentId(), agent.getFirstName()));
        var agentDto = agentRepository.createAgent(agent);
        agentDto.setImage(MinioObjectStorage.convertToBase64(minioObjectStorage.getObject(minioObjectStorage.getAgentImageName(agentDto.getAgentId(), agentDto.getFirstName()))));
        return agentDto;
    }

    private void  verifyIfAgentExist(Agent agent) {
        if (Boolean.TRUE.equals(agentRepository.existsByPhoneNumber(agent.getPhoneNumber()))) {
            throw new FunctionalError("agent already exist with phoneNumber");
        }

        if (Boolean.TRUE.equals(agentRepository.existsByEmail(agent.getEmail()))) {
            throw new FunctionalError("agent already exist with email");
        }
    }
}
