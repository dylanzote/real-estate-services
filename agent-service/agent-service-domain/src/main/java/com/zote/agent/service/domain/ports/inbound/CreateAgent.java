package com.zote.agent.service.domain.ports.inbound;

import com.zote.agent.service.domain.models.Agent;
import com.zote.agent.service.domain.models.AgentDto;

public interface CreateAgent {

    AgentDto createAgent(Agent agent);
}
