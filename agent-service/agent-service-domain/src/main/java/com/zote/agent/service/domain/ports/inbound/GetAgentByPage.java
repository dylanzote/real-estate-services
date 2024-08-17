package com.zote.agent.service.domain.ports.inbound;

import com.zote.agent.service.domain.models.Agent;
import com.zote.agent.service.domain.models.AgentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface GetAgentByPage {
    Page<AgentDto> getAllAgentsByPage(int pageNo, int sizePerPage, String sortField, Sort.Direction sortDirection);
}
