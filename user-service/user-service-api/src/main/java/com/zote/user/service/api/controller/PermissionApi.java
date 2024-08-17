package com.zote.user.service.api.controller;


import com.zote.user.service.api.request.CreatePermissionRequest;
import com.zote.user.service.api.request.UpdatePermissionRequest;
import com.zote.user.service.api.response.PermissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Permission API")
@RestController
@RequestMapping("/")
public interface PermissionApi {

    @Operation(summary = "create a new Permission")
    @PostMapping("create-permissions")
    PermissionResponse createPermission(@RequestBody CreatePermissionRequest request);

    @Operation(summary = "get all permissions")
    @GetMapping("get-permissions")
    List<PermissionResponse> getAllPermissions();

    @Operation(summary = "get permission by id")
    @GetMapping("get-permission/{id}")
    PermissionResponse getPermission(@PathVariable("id") String id);

    @Operation(summary = "update permission")
    @PutMapping("update-permissions")
    PermissionResponse updatePermission(@RequestBody UpdatePermissionRequest request);

    @Operation(summary = "delete permission by id")
    @DeleteMapping("delete-permission/{id}")
    void deletePermission(@PathVariable("id") String id);
}
