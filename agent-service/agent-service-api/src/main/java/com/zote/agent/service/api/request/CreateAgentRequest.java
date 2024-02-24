package com.zote.agent.service.api.request;


import com.zote.agent.service.domain.enums.Status;
import com.zote.agent.service.domain.models.Agent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateAgentRequest(String firstName, String lastName, String email, String phoneNumber, String country, String town, String address, String image) {

    public Agent toDomain() {
        return Agent.builder()
                .agentId(UUID.randomUUID().toString())
                .address(this.address())
                .agentStatus(Status.PENDING)
                .createdOn(LocalDate.now())
                .createdTime(LocalTime.now())
                .town(this.town())
                .country(this.country())
                .email(this.email())
                .image(this.image())
                .firstName(this.firstName())
                .lastName(this.lastName())
                .phoneNumber(this.phoneNumber())
                .build();
    }
}
