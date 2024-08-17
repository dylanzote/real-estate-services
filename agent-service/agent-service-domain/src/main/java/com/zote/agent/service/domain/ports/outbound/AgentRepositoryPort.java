package com.zote.agent.service.domain.ports.outbound;

import com.zote.agent.service.domain.models.Agent;
import com.zote.agent.service.domain.models.AgentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AgentRepositoryPort {

    AgentDto createAgent(Agent agent);

    Page<AgentDto> findAgentByPage(Pageable pageable);

    AgentDto findAgentById(String agentId);

    AgentDto findAgentByPhoneNumber(String phoneNumber);

    AgentDto updateAgent(Agent agent);

    Boolean existsById(String agentId);

    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByEmail(String email);

    void deleteAgentById(String agentId);

    void activateAgent(String agentId);

    void deActivateAgent(String agentId);
}
