package com.zote.agent.service.domain.models;

import com.zote.agent.service.domain.enums.Status;
import com.zote.common.utils.models.Gender;
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
public class AgentDto {
    private String agentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private String town;
    private String address;
    private Status agentStatus;
    private String image;
    private String property;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private LocalTime createdTime;
    private LocalTime updatedTime;
}
