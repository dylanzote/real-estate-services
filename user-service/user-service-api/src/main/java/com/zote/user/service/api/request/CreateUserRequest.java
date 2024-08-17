package com.zote.user.service.api.request;

import com.zote.common.utils.models.Gender;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateUserRequest(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        Gender gender,
        @NotNull
        String email,
        @NotNull
        String phoneNumber,

        @NotNull
        Set<String> roleIds,
        @NotNull
        String dateOfBirth,
        @NotNull
        String town,
        @NotNull
        String address,
        @NotNull
        String password
) {
}
