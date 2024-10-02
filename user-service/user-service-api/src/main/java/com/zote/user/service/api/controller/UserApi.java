package com.zote.user.service.api.controller;

import com.zote.common.utils.models.Permissions;
import com.zote.user.service.api.enums.SortField;
import com.zote.user.service.api.request.CreateUserRequest;
import com.zote.user.service.api.request.UpdatePasswordRequest;
import com.zote.user.service.api.request.UpdateUserRequest;
import com.zote.user.service.api.response.UserPageResponse;
import com.zote.user.service.api.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "User API")
@RestController
@RequestMapping("/user/")
public interface UserApi {

    @Operation(summary = "create a new user" )
    @PostMapping("create")
    UserResponse createUser(@RequestBody CreateUserRequest createUserRequest);

    @Operation(summary = "create a new user by admin")
    @PostMapping("create/by-admin")
    @RolesAllowed({Permissions.IS_ADMIN})
    UserResponse createUserByAdmin(@RequestBody CreateUserRequest createUserRequest);

    @Operation(summary = "gets user by page")
    @GetMapping("get-all")
    @RolesAllowed({Permissions.IS_ADMIN, Permissions.IS_USER})
    UserPageResponse getAllUser(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "sizePerPage", defaultValue = "5") Integer sizePerPage,
                                @RequestParam(name = "sortField", defaultValue = "CREATED_ON") SortField sortField,
                                @RequestParam(name = "sortDirection", defaultValue = "DESC") Sort.Direction sortDirection);

    @Operation(summary = "gets user by id")
    @GetMapping("get/{id}")
    @RolesAllowed({Permissions.IS_ADMIN, Permissions.IS_USER, Permissions.IS_AGENT, Permissions.IS_CUSTOMER})
    UserResponse getUser(@PathVariable("id") String id);

    @Operation(summary = "updates user")
    @PutMapping("update")
    @RolesAllowed({Permissions.IS_ADMIN, Permissions.IS_USER, Permissions.IS_AGENT, Permissions.IS_CUSTOMER})
    UserResponse updateUser(@RequestBody UpdateUserRequest updateUserRequest);

    @Operation(summary = "deletes user by id")
    @DeleteMapping("delete/{id}")
    @RolesAllowed({Permissions.IS_ADMIN})
    void deleteUser(@PathVariable("id") String id);

    @Operation(summary = "validates user with email address")
    @PostMapping("validate/{email}")
    @RolesAllowed({Permissions.IS_ADMIN, Permissions.IS_USER, Permissions.IS_AGENT, Permissions.IS_CUSTOMER})
    void validateUserEmail(@PathVariable("email") String email);

    @Operation(summary = "updates user password")
    @PutMapping("update/password")
    @RolesAllowed({Permissions.IS_ADMIN, Permissions.IS_USER, Permissions.IS_AGENT, Permissions.IS_CUSTOMER})
    void updateUserPassword(@RequestBody UpdatePasswordRequest updatePasswordRequest);

    @Operation(summary = "uploads user image and receives in base64 encoded format")
    @PostMapping(value = "upload/image/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RolesAllowed({Permissions.IS_ADMIN, Permissions.IS_USER, Permissions.IS_AGENT, Permissions.IS_CUSTOMER})
    String uploadUserImage(@PathVariable("userId") String userId, @RequestParam("image") MultipartFile image);

    @Operation(summary = "gets user image by id from object storage link")
    @GetMapping("get/{id}/image")
    @RolesAllowed({Permissions.IS_ADMIN, Permissions.IS_USER, Permissions.IS_AGENT, Permissions.IS_CUSTOMER})
    UserResponse getUserImage(@PathVariable("id") String id);

    @Operation(summary = "gets user image by id from database")
    @GetMapping("get/{userId}/base-image")
    @RolesAllowed({Permissions.IS_ADMIN, Permissions.IS_USER, Permissions.IS_AGENT, Permissions.IS_CUSTOMER})
    String getUserImageBase64(@PathVariable("userId") String id);
}
