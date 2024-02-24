package com.zote.agent.service.domain.usecases;

import com.zote.agent.service.domain.models.Agent;
import com.zote.agent.service.domain.ports.inbound.UpdateAgent;
import com.zote.agent.service.domain.ports.outbound.AgentRepositoryPort;
import com.zote.common.utls.exceptions.FunctionalError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateAgentImpl implements UpdateAgent {

    private final AgentRepositoryPort agentRepository;
    @Override
    public Agent updateAgent(Agent agent) {

        if (Boolean.FALSE.equals(agentRepository.existsById(agent.getAgentId()))) {
            throw new FunctionalError("Agent does not exist");
        }
        return agentRepository.updateAgent(agent);
    }
}
