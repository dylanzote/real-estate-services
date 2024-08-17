package com.zote.agent.service.api.request;

import com.zote.agent.service.domain.enums.Status;
import com.zote.agent.service.domain.models.Agent;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record UpdateAgentRequest(String agentId, String firstName, String lastName, String email, String phoneNumber, String town, String address, MultipartFile image) {
    public Agent toDomain() {
        return Agent.builder()
                .agentId(UUID.randomUUID().toString())
                .address(this.address())
                .createdOn(LocalDate.now())
                .createdTime(LocalTime.now())
                .town(this.town())
                .email(this.email())
                .image(this.image())
                .firstName(this.firstName())
                .lastName(this.lastName())
                .phoneNumber(this.phoneNumber())
                .build();
    }
}
