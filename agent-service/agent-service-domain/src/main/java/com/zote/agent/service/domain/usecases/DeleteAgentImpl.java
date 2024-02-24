package com.zote.agent.service.domain.usecases;

import com.zote.agent.service.domain.ports.inbound.DeleteAgent;
import com.zote.agent.service.domain.ports.outbound.AgentRepositoryPort;
import com.zote.common.utls.exceptions.AgentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteAgentImpl implements DeleteAgent {

    private final AgentRepositoryPort agentRepository;
    @Override
    public void deleteAgent(String agentId) {
        if (Boolean.FALSE.equals(agentRepository.existsById(agentId))) {
            throw new AgentNotFoundException("Agent Not found");
        }
        agentRepository.deleteAgentById(agentId);
    }
}
