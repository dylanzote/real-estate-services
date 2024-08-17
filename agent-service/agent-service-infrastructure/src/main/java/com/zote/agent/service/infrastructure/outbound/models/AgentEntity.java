package com.zote.agent.service.infrastructure.outbound.models;

import com.zote.agent.service.domain.enums.Status;
import com.zote.agent.service.domain.models.Agent;
import com.zote.agent.service.domain.models.AgentDto;
import com.zote.common.utils.models.Gender;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private Gender gender;
    private String town;
    private String address;
    private Status agentStatus;
    private String password;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private LocalTime createdTime;
    private LocalTime updatedTime;

    @SneakyThrows
    public static AgentEntity create(Agent agent) {
        return AgentEntity.builder()
                .agentId(agent.getAgentId())
                .agentStatus(Status.PENDING)
                .address(agent.getAddress())
                .town(agent.getTown())
                .gender(agent.getGender())
                .password(agent.getPassword())
                .createdOn(LocalDate.now())
                .createdTime(LocalTime.now())
                .email(agent.getEmail())
                .firstName(agent.getFirstName())
                .phoneNumber(agent.getPhoneNumber())
                .lastName(agent.getLastName())
                .build();
    }

    public AgentDto toDomain() {
        return new AgentDto(this.getAgentId(), this.getFirstName(), this.getLastName(), this.getEmail(), this.getPhoneNumber(), this.getGender(), this.getTown(), this.getAddress(), this.getAgentStatus(), "", this.getCreatedOn(), this.getUpdatedOn(), this.getCreatedTime(), this.getUpdatedTime());
    }
}
