package com.zote.keycloak.adapter;

import com.zote.common.utils.exceptions.FunctionalError;
import com.zote.keycloak.adapter.model.KeyCloakUser;
import com.zote.keycloak.adapter.model.KeycloakProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class KeyCloakService {

    private final Keycloak keycloak;

    private final KeycloakProperties keycloakProperties;


    public List<KeyCloakUser> getUsers() {
        return keycloak
                .realm(keycloakProperties.getRealm())
                .users()
                .list()
                .stream()
                .map(KeyCloakUser::toUser)
                .collect(Collectors.toList());
    }

    public String createUser(KeyCloakUser keyCloakUser, String password) {
        keyCloakUser.setPassword(password);
        log.info("Creating keycloak user");
        var response = keycloak
               .realm(keycloakProperties.getRealm())
               .users()
               .create(keyCloakUser.toUserRepresentation());
        if (!Objects.equals(201, response.getStatus())) {
            throw new FunctionalError("user could not be created in Realm");
        }
// TODO: 9/22/2024 set emailverified to false to allow this method work accordinly
//        List<UserRepresentation> representationList = keycloak
//                .realm(keycloakProperties.getRealm())
//                .users().searchByUsername(keycloakProperties.getUsername(), true);
//        if(!CollectionUtils.isEmpty(representationList)){
//            UserRepresentation userRepresentation1 = representationList.stream().filter(userRepresentation -> Objects.equals(false, userRepresentation.isEmailVerified())).findFirst().orElse(null);
//            assert userRepresentation1 != null;
//            sendEmailVerification(userRepresentation1.getId());
//        }

        return getUserRepresentation(keyCloakUser.getUserName()).getId();
    }

    public KeyCloakUser getUserById(String userId) {
        log.info("getting keycloak user with id {}", userId);
        UserResource userResource = getUserResource(userId);
        return KeyCloakUser.toUser(userResource.toRepresentation());
    }

    public UserRepresentation getUserRepresentation(String username) {
        return keycloak
                .realm(keycloakProperties.getRealm())
                .users().searchByUsername(username, true)
                .stream().findFirst().orElseThrow(() -> new FunctionalError("key clock user Not found"));
    }


    public void deleteUser(String userId) {
        log.info("Deleting keycloak user with id {}", userId);
        keycloak
               .realm(keycloakProperties.getRealm())
               .users()
               .delete(userId);
    }

    public void sendEmailVerification(String userId) {
        log.info("Sending email verification for user with id {}", userId);
        getUserResource(userId)
               .sendVerifyEmail();
    }

    public void assignRoleToUser(String userId, String roleName) {
        log.info("Assigning role {} to user with id {}", roleName, userId);
        var userResource = getUserResource(userId);
        var roleRepresentation = getRolesResource().get(roleName).toRepresentation();
        userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));
    }

    public void removeRoleToUser(String userId, String roleName) {
        log.info("Assigning role {} to user with id {}", roleName, userId);
        var userResource = getUserResource(userId);
        var oldRoleRepresentation = getRolesResource()
                .get(roleName)
                .toRepresentation();
        if (oldRoleRepresentation != null) {
            log.info("Removing old role '{}'", roleName);
            userResource.roles().realmLevel().remove(Collections.singletonList(oldRoleRepresentation));
        } else {
            log.warn("Old role '{}' not found for user '{}'", roleName, userId);
        }
    }

    public void resetPassword(String userId, String password) {
        log.info("Resetting password for user with id {}", userId);
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setValue(password);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        getUserResource(userId)
               .resetPassword(credentialRepresentation);
    }

    public void updateUser(KeyCloakUser keyCloakUser) {
        log.info("Updating keycloak user with id {}", keyCloakUser.getId());
        getUserResource(keyCloakUser.getId())
                .update(keyCloakUser.toUpdateUserRepresentation());
    }



    private UserResource getUserResource(String userId) {
        return keycloak
                .realm(keycloakProperties.getRealm())
                .users()
                .get(userId);
    }

    private RolesResource getRolesResource() {
        return keycloak
               .realm(keycloakProperties.getRealm())
               .roles();
    }

    public List<String> getRoles() {
        log.info("Getting all roles");
        return keycloak
                .realm(keycloakProperties.getRealm())
                .roles()
                .list()
                .stream()
                .map(RoleRepresentation::getName)
                .collect(Collectors.toList());
    }

    public void addRealmRole(String role, String description) {
        log.info("Adding realm role {}", role);
        if(!getRoles().contains(role)) {
            RoleRepresentation roleRep = new  RoleRepresentation();
            roleRep.setName(role);
            roleRep.setDescription(description);
            getRolesResource().create(roleRep);
        }
    }


    public void deleteRealmRole(String role) {
        log.info("Deleting realm role {}", role);
        getRolesResource().deleteRole(role);
    }

}
