package com.zote.agent.service.infrastructure.outbound.models;

import com.zote.agent.service.domain.enums.Status;
import com.zote.agent.service.domain.models.Agent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "agency")
public class AgentEntity {
    @Id
    private String agentId;
    private String firstName;
    private String lastName;
    private String email;
    @Indexed(name = "phoneNumber")
    private String phoneNumber;
    private String country;
    private String town;
    private String address;
    private Status agentStatus;
    private String image;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private LocalTime createdTime;
    private LocalTime updatedTime;

    public static AgentEntity create(Agent agent) {
        return AgentEntity.builder()
                .agentId(String.valueOf(UUID.randomUUID()))
                .agentStatus(Status.PENDING)
                .address(agent.getAddress())
                .town(agent.getTown())
                .country(agent.getCountry())
                .createdOn(LocalDate.now())
                .createdTime(LocalTime.now())
                .email(agent.getEmail())
                .image(agent.getImage())
                .firstName(agent.getFirstName())
                .phoneNumber(agent.getPhoneNumber())
                .lastName(agent.getLastName())
                .build();
    }

    public Agent toDomain() {
        return new Agent(this.getAgentId(), this.getFirstName(), this.getLastName(), this.getEmail(), this.getPhoneNumber(), this.getCountry(), this.getTown(), this.getAddress(), this.getAgentStatus(), this.getImage(), this.getCreatedOn(), this.getUpdatedOn(), this.getCreatedTime(), this.getUpdatedTime());
    }
}
