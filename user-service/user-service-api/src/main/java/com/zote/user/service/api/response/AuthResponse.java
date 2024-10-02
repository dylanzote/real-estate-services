package com.zote.user.service.api.response;

import com.zote.common.utils.models.Gender;
import com.zote.user.service.domain.model.AuthData;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

@Data
public class AuthResponse {
    private String accessToken;
    private int expiresIn;
    private int refreshExpiresIn;
    private String refreshToken;

    public static AuthResponse toAuthResponse(AuthData authData) {
        AuthResponse authResponse = new AuthResponse();
        BeanUtils.copyProperties(authData, authResponse);
        return authResponse;
    }
}
