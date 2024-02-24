package com.zote.property.service.domain.models;

import com.zote.property.service.domain.enums.AgentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Agent {
    private String agentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String country;
    private String town;
    private String address;
    private AgentStatus agentStatus;
    private String image;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private LocalTime createdTime;
    private LocalTime updatedTime;

//    public Agent create(CreateAgentRequest request) {
//        return Agent.builder()
//                .agentId(String.valueOf(UUID.randomUUID()))
//                .agentStatus(Status.PENDING)
//                .address(request.address())
//                .city(request.city())
//                .country(request.country())
//                .createdOn(LocalDate.now())
//                .createdTime(LocalTime.now())
//                .email(request.email())
//                .image(request.image())
//                .firstName(request.firstName())
//                .phoneNumber(request.phoneNumber())
//                .lastName(request.lastName())
//                .build();
//    }
//
//    public Agent toDto() {
//        return new com.zote.agentservice.dtos.Agent(this.agentId, this.firstName, this.lastName, this.email, this.phoneNumber, this.country, this.city, this.address, this.image, this.agentStatus.name());
//    }
}
