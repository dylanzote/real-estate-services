package com.zote.user.service.api.usecase;

import com.zote.user.service.api.controller.AuthenticationApi;
import com.zote.user.service.api.request.AuthRequest;
import com.zote.user.service.api.response.AuthResponse;
import com.zote.user.service.domain.ports.inbound.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService implements AuthenticationApi {

    private final AuthenticationPort authenticationPort;

    @Override
    public AuthResponse login(AuthRequest request) {
        return AuthResponse.toAuthResponse(authenticationPort.authenticate(request.username(), request.password()));
    }
}
