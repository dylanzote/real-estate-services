package com.zote.user.service.api.controller;


import com.zote.user.service.api.request.CreatePermissionRequest;
import com.zote.user.service.api.request.UpdatePermissionRequest;
import com.zote.user.service.api.response.PermissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Permission API")
@RestController
@RequestMapping("/permission/")
public interface PermissionApi {

    @Operation(summary = "create a new Permission")
    @PostMapping("create")
    @RolesAllowed({"AdMIN"})
    PermissionResponse createPermission(@RequestBody CreatePermissionRequest request);

    @Operation(summary = "get all permissions")
    @GetMapping("get-all")
    List<PermissionResponse> getAllPermissions();

    @Operation(summary = "get permission by id")
    @GetMapping("get/{id}")
    PermissionResponse getPermission(@PathVariable("id") String id);

    @Operation(summary = "update permission")
    @PutMapping("update")
    PermissionResponse updatePermission(@RequestBody UpdatePermissionRequest request);

    @Operation(summary = "delete permission by id")
    @DeleteMapping("delete/{id}")
    void deletePermission(@PathVariable("id") String id);
}
