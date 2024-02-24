package com.zote.agent.service.domain.usecases;

import com.zote.agent.service.domain.ports.inbound.ChangeAgentStatus;
import com.zote.agent.service.domain.ports.outbound.AgentRepositoryPort;
import com.zote.common.utls.exceptions.AgentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChangeAgentStatusImpl implements ChangeAgentStatus {

    private final AgentRepositoryPort agentRepository;
    @Override
    public void activateAgent(String agentId) {
        if (Boolean.FALSE.equals(agentRepository.existsById(agentId))) {
            throw new AgentNotFoundException("Agent Not found");
        }
        agentRepository.activateAgent(agentId);
    }

    @Override
    public void deActivateAgent(String agentId) {
        if (Boolean.FALSE.equals(agentRepository.existsById(agentId))) {
            throw new AgentNotFoundException("Agent Not found");
        }
        agentRepository.deActivateAgent(agentId);
    }
}
