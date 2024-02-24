package com.zote.agent.service.domain.ports.inbound;

import com.zote.agent.service.domain.models.Agent;

public interface GetAgentById {

    Agent findAgentById(String agentId);
}
