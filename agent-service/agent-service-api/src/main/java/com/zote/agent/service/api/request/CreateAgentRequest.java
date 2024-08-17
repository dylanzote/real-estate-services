package com.zote.agent.service.api.request;


import com.zote.agent.service.domain.enums.Status;
import com.zote.agent.service.domain.models.Agent;
import com.zote.common.utils.models.Gender;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateAgentRequest(
        String firstName,
        String lastName,
        Gender gender,
        String email,
        String phoneNumber,
        String dateOfBirth,
        String town,
        String address,
        @RequestPart(value = "image", required = true) MultipartFile image,
        String password
) {

    public Agent toDomain() {
        return Agent.builder()
                .agentId(UUID.randomUUID().toString())
                .address(this.address())
                .agentStatus(Status.PENDING)
                .createdOn(LocalDate.now())
                .createdTime(LocalTime.now())
                .town(this.town())
                .gender(this.gender())
                .email(this.email())
                .image(this.image())
                .firstName(this.firstName())
                .lastName(this.lastName())
                .phoneNumber(this.phoneNumber())
                .password(this.password)
                .build();
    }
}
