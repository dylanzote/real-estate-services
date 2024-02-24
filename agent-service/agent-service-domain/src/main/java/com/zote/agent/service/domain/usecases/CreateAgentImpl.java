package com.zote.agent.service.domain.usecases;

import com.zote.agent.service.domain.models.Agent;
import com.zote.agent.service.domain.ports.inbound.CreateAgent;
import com.zote.agent.service.domain.ports.outbound.AgentRepositoryPort;
import com.zote.common.utls.exceptions.FunctionalError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateAgentImpl implements CreateAgent {

    private final AgentRepositoryPort agentRepository;
    @Override
    public Agent createAgent(Agent agent) {
        log.info("creating agent with data {}", agent);
        // TODO: 2/16/2024 add agent password 
        verifyIfAgentExist(agent);
        return agentRepository.createAgent(agent);
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
