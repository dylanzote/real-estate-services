package com.zote.agent.service.domain.usecases;
;
import com.zote.agent.service.domain.models.AgentDto;
import com.zote.agent.service.domain.ports.inbound.GetAgentById;
import com.zote.agent.service.domain.ports.inbound.GetAgentByPage;
import com.zote.agent.service.domain.ports.outbound.AgentRepositoryPort;
import com.zote.common.utils.exceptions.AgentNotFoundException;
import com.zote.common.utils.files.MinioObjectStorage;
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

    private final MinioObjectStorage minioObjectStorage;

    @Override
    public AgentDto findAgentById(String agentId) {
        var agent = agentRepository.findAgentById(agentId);
        if (Objects.isNull(agent)) {
            throw new AgentNotFoundException("Agent not found");
        }
        agent.setImage(MinioObjectStorage.convertToBase64(minioObjectStorage.getObject(minioObjectStorage.getAgentImageName(agent.getAgentId(), agent.getFirstName()))));
        return agent;
    }

    @Override
    public Page<AgentDto> getAllAgentsByPage(int page, int sizePerPage, String sortField, Sort.Direction sortDirection) {
        var pageNo = page < 0 ? 0 : page - 1;
        var pageable = PageRequest.of(pageNo, sizePerPage, sortDirection, sortField);
        var agents = agentRepository.findAgentByPage(pageable);
        agents.forEach(agentDto -> agentDto.setImage(MinioObjectStorage.convertToBase64(minioObjectStorage.getObject(minioObjectStorage.getAgentImageName(agentDto.getAgentId(), agentDto.getFirstName())))));
        return agents;
    }
}
