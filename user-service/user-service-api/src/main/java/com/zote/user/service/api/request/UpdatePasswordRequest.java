package com.zote.user.service.api.request;

import jakarta.validation.constraints.NotNull;

public record UpdatePasswordRequest(
        @NotNull
        String oldPassword,

        @NotNull
        String newPassword,

        @NotNull
        String confirmPassword
) {
}
