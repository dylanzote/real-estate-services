package com.zote.user.service.api.controller;

import com.zote.user.service.api.enums.SortField;
import com.zote.user.service.api.request.CreateUserRequest;
import com.zote.user.service.api.request.UpdatePasswordRequest;
import com.zote.user.service.api.request.UpdateUserRequest;
import com.zote.user.service.api.response.UserResponse;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/")
public interface UserApi {

    @PostMapping("create-user")
    UserResponse createUser(@RequestBody CreateUserRequest createUserRequest);

    @PostMapping("create-user/by-admin")
    UserResponse createUserByAdmin(@RequestBody CreateUserRequest createUserRequest);

    @GetMapping("get-user")
    List<UserResponse> getAllUser(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                  @RequestParam(name = "sizePerPage", defaultValue = "5") Integer sizePerPage,
                                  @RequestParam(name = "sortField", defaultValue = "CREATED_ON") SortField sortField,
                                  @RequestParam(name = "sortDirection", defaultValue = "DESC") Sort.Direction sortDirection);

    @GetMapping("get-user/{id}")
    UserResponse getUser(@PathVariable String id);

    @PutMapping("update-user")
    UserResponse updateUser(@RequestBody UpdateUserRequest updateUserRequest);

    @DeleteMapping("delete-user/{id}")
    void deleteUser(@PathVariable String id);

    @PostMapping("validate-user/email")
    void validateUserEmail(@RequestBody String email);

    @PutMapping("update-user/password")
    void updateUserPassword(@RequestBody UpdatePasswordRequest updatePasswordRequest);

    @PostMapping("upload-user/image/{id}")
    UserResponse uploadUserImage(@PathVariable String id, @RequestParam("image") MultipartFile image);

    @GetMapping("get-user/{id}/image")
    UserResponse getUserImage(@PathVariable String id);

    @GetMapping("get-user/{id}/base-image")
    UserResponse getUserImageBase64(@PathVariable String id);
}
