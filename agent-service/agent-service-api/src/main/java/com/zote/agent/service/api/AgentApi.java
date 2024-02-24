package com.zote.agent.service.api;

import com.zote.agent.service.api.enums.SortField;
import com.zote.agent.service.api.request.CreateAgentRequest;
import com.zote.agent.service.api.request.UpdateAgentRequest;
import com.zote.agent.service.api.response.AgentPageResponse;
import com.zote.agent.service.domain.models.Agent;
import com.zote.agent.service.domain.ports.inbound.*;
import com.zote.common.utls.models.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class AgentApi {

    private final CreateAgent createAgent;
    private final GetAgentById getAgentById;
    private final GetAgentByPage getAgentByPage;
    private final UpdateAgent updateAgent;
    private final DeleteAgent deleteAgent;
    private final ChangeAgentStatus changeAgentStatus;

    @PostMapping("/create")
    Agent createAgent(@RequestBody CreateAgentRequest createAgentRequest) {
        log.info("incoming create agent request with data {}", createAgentRequest);
        return createAgent.createAgent(createAgentRequest.toDomain());
    }

    @GetMapping("/getById/{agentId}")
    Agent getAgent(@PathVariable("agentId") String agentId) {
        log.info("incoming get agent request with data {}", agentId);
        return getAgentById.findAgentById(agentId);
    }

    @GetMapping("/getAllByPage")
    AgentPageResponse getAllAgents(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name = "sizePerPage", defaultValue = "5") Integer sizePerPage,
                             @RequestParam(name = "sortField", defaultValue = "CREATED_ON") SortField sortField,
                             @RequestParam(name = "sortDirection", defaultValue = "DESC") Sort.Direction sortDirection) {
        log.info("incoming get all Agents by page request with page {}, sizePerPage {}, SortField {}, SortOrder {}", pageNo, sizePerPage, sortField, sortDirection);
        return new AgentPageResponse(getAgentByPage.getAllPropertiesByPage(pageNo, sizePerPage, sortField.getFieldName(), sortDirection));
    }

    @PutMapping("/updateAgent")
    Agent updateAgent(UpdateAgentRequest updateAgentRequest) {
        log.info("incoming update agent request with data {}", updateAgentRequest);
        return updateAgent.updateAgent(updateAgentRequest.toDomain());
    }

    @DeleteMapping("/deleteAgent/{agentId}")
    ResponseData deleteAgent(@PathVariable("agentId") String agentId) {
        log.info("incoming delete agent request with id {}", agentId);
        deleteAgent.deleteAgent(agentId);
        return new ResponseData(200, "successful");
    }

    @PutMapping("/activateStatus/{agentId}")
    ResponseData activateAgentStatus(@PathVariable("agentId") String agentId){
        log.info("incoming activate agent status request with id {}", agentId);
        changeAgentStatus.activateAgent(agentId);
        return new ResponseData(200, "successful");
    }

    @PutMapping("/deActivateStatus/{agentId}")
    ResponseData deActivateAgentStatus(@PathVariable("agentId") String agentId){
        log.info("incoming deActivate agent status request with id {}", agentId);
        changeAgentStatus.deActivateAgent(agentId);
        return new ResponseData(200, "successful");
    }
}
