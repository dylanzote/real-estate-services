package com.zote.agent.service.infrastructure.outbound;

import com.zote.agent.service.infrastructure.outbound.models.AgentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends MongoRepository<AgentEntity, String> {
    AgentEntity findAgentEntityByPhoneNumber(String phoneNumber);

    AgentEntity findAgentEntityByEmail(String email);

    boolean existsAgentEntityByPhoneNumber(String phoneNumber);

    boolean existsAgentEntityByEmail(String email);
}
