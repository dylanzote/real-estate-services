package com.zote.user.service.api.controller;

import com.zote.user.service.api.request.CreateRoleRequest;
import com.zote.user.service.api.request.UpdateRoleRequest;
import com.zote.user.service.api.response.RoleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Role API")
@RestController
@RequestMapping("/")
public interface RoleApi {

    @Operation(summary = "create a new Role")
    @PostMapping("create-role")
    RoleResponse createRole(@RequestBody CreateRoleRequest request);

    @Operation(summary = "get all Roles")
    @GetMapping("get-roles")
    List<RoleResponse> getAllRoles();

    @Operation(summary = "get a Role by id")
    @GetMapping("get-role/{id}")
    RoleResponse getRole(@PathVariable("id") String id);

    @Operation(summary = "update a Role")
    @PutMapping("update-role")
    RoleResponse updateRole(@RequestBody UpdateRoleRequest request);

    @Operation(summary = "delete a Role by id")
    @DeleteMapping("delete-role/{id}")
    void deleteRole(@PathVariable("id") String id);
}
