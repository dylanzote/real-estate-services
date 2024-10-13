package com.zote.user.service.domain.model;

import com.zote.common.utils.models.Gender;
import com.zote.common.utils.models.Status;
import com.zote.keycloak.adapter.model.KeyCloakUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private String id;
    private String keycloakUserId;
    private String firstName;
    private String userName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String password;
    private Set<Role> roles;
    private Status status;
    private boolean emailConfirmed;
    private boolean newUser;
    private Gender gender;
    private String town;
    private String address;
    private String imageUrl;
    private AuthData authResponse;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public KeyCloakUser toKeyCloakUser() {
        KeyCloakUser keyCloakUser = new KeyCloakUser();
        BeanUtils.copyProperties(this, keyCloakUser);
        return keyCloakUser;
    }

    public  KeyCloakUser toKeyCloakUpdateUser() {
        KeyCloakUser keyCloakUser = new KeyCloakUser();
        BeanUtils.copyProperties(this, keyCloakUser);
        keyCloakUser.setId(keycloakUserId);
        return keyCloakUser;
    }
}
