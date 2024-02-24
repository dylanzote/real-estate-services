package com.zote.agent.service.domain.ports.inbound;

import com.zote.agent.service.domain.models.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface GetAgentByPage {
    Page<Agent> getAllPropertiesByPage(int pageNo, int sizePerPage, String sortField, Sort.Direction sortDirection);
}
