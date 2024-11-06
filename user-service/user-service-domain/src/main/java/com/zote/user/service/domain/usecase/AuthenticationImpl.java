package com.zote.user.service.domain.usecase;

import com.zote.common.utils.config.BeanConfig;
import com.zote.common.utils.exceptions.FunctionalError;
import com.zote.common.utils.files.MinioObjectStorage;
import com.zote.user.service.domain.model.AuthData;
import com.zote.user.service.domain.ports.inbound.AuthenticationPort;
import com.zote.user.service.domain.ports.outbound.UserRepositoryPort;
import com.zote.user.service.domain.support.UserSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationImpl implements AuthenticationPort {

    private final UserRepositoryPort userRepositoryPort;

    private final BeanConfig config;

    private final UserSupport userSupport;

    private final MinioObjectStorage minioObjectStorage;

    @Override
    public AuthData authenticate(String username, String password) {
        log.info("incoming Authentication request");
        var user = userRepositoryPort.findUserByUserName(username);
        if (!config.passwordEncoder().matches(password, user.getPassword())) {
            log.error("Invalid credentials for user {}", username);
            throw new FunctionalError("Invalid credentials for user");
        }
        user.setImageUrl(minioObjectStorage.getPresignedUrl(minioObjectStorage.getUserImageName(user.getId())));
//        user.setAuthResponse(userSupport.authenticateUser(username, password)); to be thought about critically
        var authData = userSupport.authenticateUser(username, password);
        authData.setUser(user);
        return authData;
    }
}
