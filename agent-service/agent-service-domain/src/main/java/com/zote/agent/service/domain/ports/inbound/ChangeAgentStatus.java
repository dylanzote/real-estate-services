package com.zote.agent.service.domain.ports.inbound;

public interface ChangeAgentStatus {

    void activateAgent(String agentId);

    void deActivateAgent(String agentId);
}
