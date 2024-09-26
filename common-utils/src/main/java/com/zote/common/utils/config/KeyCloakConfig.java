package com.zote.common.utils.config;

import com.zote.common.utils.models.KeycloakProperties;
import lombok.AllArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KeyCloakConfig {

    private final KeycloakProperties keycloakProperties;

    @Bean
    Keycloak keyCloak() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getServerUrl())
                .realm(keycloakProperties.getRealm())
                .clientId(keycloakProperties.getClientId())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .username(keycloakProperties.getUsername())
                .password(keycloakProperties.getPassword())
                .clientSecret(keycloakProperties.getClientSecret())
                .build();
    }
}
