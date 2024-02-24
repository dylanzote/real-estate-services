package com.zote.agent.service.domain.ports.inbound;

import com.zote.agent.service.domain.models.Agent;

public interface CreateAgent {

    Agent createAgent(Agent agent);
}
