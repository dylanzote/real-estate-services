package com.zote.agent.service.domain.ports.outbound;

import com.zote.agent.service.domain.models.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AgentRepositoryPort {

    Agent createAgent(Agent agent);

    Page<Agent> findAgentByPage(Pageable pageable);

    Agent findAgentById(String agentId);

    Agent findAgentByPhoneNumber(String phoneNumber);

    Agent updateAgent(Agent agent);

    Boolean existsById(String agentId);

    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByEmail(String email);

    void deleteAgentById(String agentId);

    void activateAgent(String agentId);

    void deActivateAgent(String agentId);
}
