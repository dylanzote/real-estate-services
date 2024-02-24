package com.zote.agent.service.domain.usecases;

import com.zote.agent.service.domain.models.Agent;
import com.zote.agent.service.domain.ports.inbound.GetAgentById;
import com.zote.agent.service.domain.ports.inbound.GetAgentByPage;
import com.zote.agent.service.domain.ports.outbound.AgentRepositoryPort;
import com.zote.common.utls.exceptions.AgentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetAgentImpl implements GetAgentById, GetAgentByPage {

    private final AgentRepositoryPort agentRepository;

    @Override
    public Agent findAgentById(String agentId) {
        var agent = agentRepository.findAgentById(agentId);
        if (Objects.isNull(agent)) {
            throw new AgentNotFoundException("Agent not found");
        }
        return agent;
    }

    @Override
    public Page<Agent> getAllPropertiesByPage(int page, int sizePerPage, String sortField, Sort.Direction sortDirection) {
        var pageNo = page < 0 ? 0 : page - 1;
        var pageable = PageRequest.of(pageNo, sizePerPage, sortDirection, sortField);
        return agentRepository.findAgentByPage(pageable);
    }
}
