package com.zote.user.service.api.usecase;

import com.zote.user.service.api.controller.UserApi;
import com.zote.user.service.api.enums.SortField;
import com.zote.user.service.api.request.CreateUserRequest;
import com.zote.user.service.api.request.UpdatePasswordRequest;
import com.zote.user.service.api.request.UpdateUserRequest;
import com.zote.user.service.api.response.UserPageResponse;
import com.zote.user.service.api.response.UserResponse;
import com.zote.user.service.domain.ports.inbound.UserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@Slf4j
@Service
public class UserService implements UserApi {

    private final UserPort userPort;
    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        log.info("incoming request for creating User {}", createUserRequest);
        return UserResponse.toResponse(userPort.createUser(createUserRequest.tocreateUserData()));
    }

    @Override
    public UserResponse createUserByAdmin(CreateUserRequest createUserRequest) {
        log.info("incoming request for creating User by admin {}", createUserRequest);
        return UserResponse.toResponse(userPort.createUserByAdmin(createUserRequest.tocreateUserData()));
    }

    @Override
    public UserPageResponse getAllUser(Integer pageNo, Integer sizePerPage, SortField sortField, Sort.Direction sortDirection) {
        log.info("incoming get all users by page request with page {}, sizePerPage {}, SortField {}, SortOrder {}", pageNo, sizePerPage, sortField, sortDirection);
        return new UserPageResponse(userPort.getAllUsersByPage(pageNo, sizePerPage, sortField.getFieldName(), sortDirection).map(UserResponse::toResponse));
    }

    @Override
    public UserResponse getUser(String id) {
        log.info("incoming request for getting user with id {}", id);
        return UserResponse.toResponse(userPort.findUserById(id));
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest updateUserRequest) {
        log.info("incoming request for updating user {}", updateUserRequest);
        return UserResponse.toResponse(userPort.updateUser(updateUserRequest.toUserUpdateData()));
    }

    @Override
    public void deleteUser(String id) {
        log.info("incoming request for deleting user with id {}", id);
        userPort.deleteUser(id);
    }

    @Override
    public void validateUserEmail(String email) {
        log.info("incoming request for validating user email {}", email);
        userPort.validateUserEmail(email);
    }

    @Override
    public void updateUserPassword(UpdatePasswordRequest updatePasswordRequest) {
        log.info("incoming request for updating user password {}", updatePasswordRequest);
        userPort.updateUserPassword(updatePasswordRequest.toPasswordUpdateData());
    }

    @Override
    public String uploadUserImage(String userId, MultipartFile image) {
        log.info("incoming request for uploading user image for user with id {}", userId);
        return userPort.uploadUserImage(userId, image);
    }

    @Override
    public UserResponse getUserImage(String id) {
        return null;
    }

    @Override
    public String getUserImageBase64(String userId) {
        log.info("incoming request for getting user image base64 for user with id {}", userId);
        return userPort.getUserImage(userId);
    }
}
