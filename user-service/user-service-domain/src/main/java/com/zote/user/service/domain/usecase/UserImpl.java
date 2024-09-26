package com.zote.user.service.domain.usecase;

import com.zote.common.utils.config.BeanConfig;
import com.zote.common.utils.config.KeyCloakService;
import com.zote.common.utils.exceptions.FunctionalError;
import com.zote.common.utils.files.MinioObjectStorage;
import com.zote.user.service.domain.model.*;
import com.zote.user.service.domain.ports.inbound.UserPort;
import com.zote.user.service.domain.ports.outbound.RoleRepositoryPort;
import com.zote.user.service.domain.ports.outbound.UserRepositoryPort;
import com.zote.user.service.domain.support.UserSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserImpl implements UserPort {

    private final UserRepositoryPort userRepository;

    private final RoleRepositoryPort roleRepository;

    private final UserSupport userSupport;

    private final BeanConfig config;

    private final MinioObjectStorage minioObjectStorage;

    private final KeyCloakService keyCloakService;

    @Override
    public User createUser(CreateUserData createUserData) {
        userSupport.validateData(createUserData);
        verifyIfUserExists(createUserData);
        var roles = getRoles(createUserData.getRoleIds());
        var user = userSupport.buildUser(createUserData, roles);
        var keycloakUserId = keyCloakService.createUser(user.toKeyCloakUser(), createUserData.getPassword());
        user.setKeycloakUserId(keycloakUserId);
        roles.forEach(role -> keyCloakService.assignRoleToUser(keycloakUserId, role.getName()));
        return userRepository.saveUser(user);
    }

    @Override
    public User createUserByAdmin(CreateUserData createUserData) {
        return null;
    }

    @Override
    public User updateUser(UserData userData) {
        var user  = userRepository.findUserById(userData.getId());
        userSupport.validateData(userData);
        var roles = getRoles(userData.getRoleIds());
        var updatedUser = userData.toUser(user);
        updatedUser.setRoles(roles);
        keyCloakService.updateUser(updatedUser.toKeyCloakUpdateUser());
        return userRepository.saveUser(updatedUser);
    }

    @Override
    public void updateUserPassword(PasswordUpdateData passwordUpdateData) {
        userSupport.validatePasswords(passwordUpdateData.getNewPassword());
        var user = userRepository.findUserById(passwordUpdateData.getUserId());
        if (config.passwordEncoder().matches(passwordUpdateData.getOldPassword(), user.getPassword()) && !passwordUpdateData.getOldPassword().equals(passwordUpdateData.getNewPassword())) {
            keyCloakService.resetPassword(user.getKeycloakUserId(), passwordUpdateData.getNewPassword());
            user.setPassword(config.passwordEncoder().encode(passwordUpdateData.getNewPassword()));
            userRepository.saveUser(user);
        } else
            throw new FunctionalError("old password does not match existing password or old password cannot be same as new password");
    }

    @Override
    public void deleteUser(String userId) {
        var user = userRepository.findUserById(userId);
        keyCloakService.deleteUser(user.getKeycloakUserId());
        userRepository.deleteUserById(userId);
    }

    @Override
    public void validateUserEmail(String email) {
        // TODO: 9/26/2024 validate user mail
    }

    @Override
    public User findUserById(String userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public Page<User> getAllUsersByPage(int page, int sizePerPage, String sortField, Sort.Direction sortDirection) {
        var pageNo = page < 0 ? 0 : page - 1;
        var pageable = PageRequest.of(pageNo, sizePerPage, sortDirection, sortField);
        return userRepository.findUsersByPage(pageable);
    }

    @Override
    public String uploadUserImage(String userId, MultipartFile image) {
        if (!userRepository.existsById(userId)) {
            throw new FunctionalError("User does not exist with given id");
        }
        minioObjectStorage.uploadImage(image, minioObjectStorage.getUserImageName(userId));
        return MinioObjectStorage.convertToBase64(minioObjectStorage.getObject(minioObjectStorage.getUserImageName(userId)));
    }

    @Override
    public String getUserImage(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new FunctionalError("User does not exist with given id");
        }
        return MinioObjectStorage.convertToBase64(minioObjectStorage.getObject(minioObjectStorage.getUserImageName(userId)));
    }

    private void verifyIfUserExists(CreateUserData createUserData) {
        if (userRepository.existsByEmail(createUserData.getEmail())) {
            throw new FunctionalError("User already exist with email");
        }
        if (userRepository.existsByPhoneNumber(createUserData.getPhoneNumber())) {
            throw new FunctionalError("User already exist with phoneNumber");
        }
        if (userRepository.existsByUserName(createUserData.getUserName())) {
            throw new FunctionalError("User already exist with userName");
        }
    }

    private Set<Role> getRoles(Set<String> roleIds) {
        return roleIds.stream()
               .map(roleRepository::findRoleById)
               .collect(Collectors.toSet());
    }

}
