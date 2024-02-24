package com.zote.agent.service.infrastructure.outbound;

import com.zote.agent.service.domain.enums.Status;
import com.zote.agent.service.domain.models.Agent;
import com.zote.agent.service.domain.ports.outbound.AgentRepositoryPort;
import com.zote.agent.service.infrastructure.outbound.models.AgentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.zote.agent.service.domain.enums.Status.ACTIVE;
import static com.zote.agent.service.domain.enums.Status.INACTIVE;

@RequiredArgsConstructor
@Repository
@Slf4j
public class AgentRepositoryPortImpl implements AgentRepositoryPort {

    private final AgentRepository agentRepository;
    @Override
    public Agent createAgent(Agent agent) {
        log.info("creating agent with data {}", agent);
        return agentRepository.save(AgentEntity.create(agent)).toDomain();
    }

    @Override
    public Page<Agent> findAgentByPage(Pageable pageable) {
        log.info("getting all agents with data {}", pageable);
        return agentRepository.findAll(pageable).map(AgentEntity::toDomain);
    }

    @Override
    public Agent findAgentById(String agentId) {
        log.info("getting agent with agentId {}", agentId);
        return agentRepository.findById(agentId).map(AgentEntity::toDomain).orElse(null);
    }

    @Override
    public Agent findAgentByPhoneNumber(String phoneNumber) {
        log.info("getting agent with phoneNumber {}", phoneNumber);
        return agentRepository.findAgentEntityByPhoneNumber(phoneNumber).toDomain();
    }

    @Override
    public Agent updateAgent(Agent agent) {
        log.info("update agent with data {}", agent);
        // TODO: 2/11/2024 add use case to change phone number for two factor authentication
        var agentEntity = agentRepository.findById(agent.getAgentId()).get();
        return agentRepository.save(updateAgent(agentEntity, agent)).toDomain();
    }

    @Override
    public Boolean existsById(String agentId) {
        log.info("verifies if agent exists by id {}", agentId);
        return agentRepository.existsById(agentId);
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        log.info("verifies if agent exists by phoneNumber {}", phoneNumber);
        return agentRepository.existsAgentEntityByPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean existsByEmail(String email) {
        log.info("verifies if agent exists by phoneNumber {}", email);
        return agentRepository.existsAgentEntityByEmail(email);
    }

    @Override
    public void deleteAgentById(String agentId) {
        log.info("deleting agent with agent id {}", agentId);
        agentRepository.deleteById(agentId);
    }

    @Override
    public void activateAgent(String agentId) {
        log.info("activating agent with agent id {}", agentId);
        var agentEntity = agentRepository.findById(agentId).get();
        agentEntity.setAgentStatus(ACTIVE);
        agentRepository.save(agentEntity);
    }

    @Override
    public void deActivateAgent(String agentId) {
        log.info("deleting agent with agent id {}", agentId);
        var agentEntity = agentRepository.findById(agentId).get();
        agentEntity.setAgentStatus(INACTIVE);
        agentRepository.save(agentEntity);
    }

    private AgentEntity updateAgent(AgentEntity agentEntity, Agent agent) {
        agentEntity.setAddress(agent.getAddress());
        agentEntity.setTown(agent.getTown());
        agentEntity.setCountry(agent.getCountry());
        agentEntity.setEmail(agent.getEmail());
        agentEntity.setFirstName(agent.getFirstName());
        agentEntity.setImage(agent.getImage());
        agentEntity.setUpdatedOn(LocalDate.now());
        agentEntity.setLastName(agent.getLastName());
        agentEntity.setUpdatedTime(LocalTime.now());
        return agentEntity;
    }
}
